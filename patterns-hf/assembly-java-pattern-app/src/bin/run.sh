#!/bin/bash

main_class=com.peterservice.cci.ccisrv.Main
appl_name="cci-srv"
pid_file="cci-srv.pid"

if [ "$2" == "any" ]
then
  any="true"
else
  any="false"
fi

cci_home=$( cd "$(dirname "${BASH_SOURCE}")" ; cd .. ; pwd -P )
pid_file=$cci_home/$pid_file

start() {
    pid;
    if [ -z "$pid" ]
    then
        echo Starting cci-srv...
        if [ ! -d "$cci_home/log" ]; then
            mkdir $cci_home/log >/dev/null 2>/dev/null
        fi
        nohup java -cp "$cci_home/conf/:$cci_home/lib/*" $main_class >$cci_home/log/out.txt 2>$cci_home/log/err.txt & echo $! > $pid_file
        pid=`cat $pid_file`
        echo $pid
   else
        echo "$appl_name is started already [$pid]"
    fi
}

stop() {
    pid;
    if [ -z "$pid" ]
    then
        echo "$appl_name is not running..."
    else
        echo "Stopping $appl_name [$pid]..."
        kill -9 $pid
    fi
    rm $pid_file >/dev/null 2>/dev/null
}

status() {
    pid;
    if [ -z "$pid" ]
    then
        echo "$appl_name is not running..."
    else
        echo "$appl_name is running now [$pid]..."
    fi
}

pid() {
    if [ "$any" == "true" ]
    then
        pid="cci"
    else
        if [ -f $pid_file ]
        then
          pid=`cat $pid_file`
        else
          pid=NO-CNC-PID
        fi
    fi
    pid=`ps -ef|grep $main_class|grep $pid|grep -v grep|awk {'print $2'}`
}

case "$1" in
    start)   start ;;
    stop)    stop ;;
    restart) stop; start ;;
    status)  status; ;;
    *) echo "usage: $0 start|stop|restart|status [any]" >&2
       exit 1
       ;;
esac
