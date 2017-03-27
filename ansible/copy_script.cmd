rem Накатка скриптов на общую vagrant шару для запуска на виртуалке

rem ============= xcopy =======================================================================

rem про xcopy - http://www.celitel.info/klad/nhelp/helpbat.php?dcmd=xcopy
rem /d - копирует только обновленные файлы
rem /i - создание каталогов, если они не существуют
rem /e - копирование всех подкаталогов, включая пустые
rem /f - Выводит имена исходных файлов и файлов-результатов в процессе копирования.
rem /y - Устраняет выдачу запроса на подтверждение перезаписи существующего конечного файла.

rem ================ scp - копирование на удалённую linux машину ====================================

rem про scp https://websetnet.com/ru/12-scp-command-examples-to-transfer-files-on-linux/
rem -r - рекурсивное копирование (для директорий)
rem -C - использовать сжатие при передачи
rem -v - вывод подробногго лог
rem -F - для указания пути к файлу ssh_config, если нужно использовать не поумолчанию
rem -c blowfish - указание более быстрого алгоритма шифрования
rem -P - порт ssh
rem -p - сохранить информацию о времени создания, модификации файла.
rem -P большая! и -P указывает перед ssh хостом.

rem ============= rsync  - аналогично scp, только пересылаются изменения, что эффективнее  =========

rem Важно, что на машине, на которую производится копирования, сервис rsync должен быть сконфигурирован и запущен! Порт на котором висит сервис в конфиге  /etc/rsync.conf.
rem https://rtfm.co.ua/linux-primery-ispolzovaniya-rsync/
rem Версия для Windows: https://www.itefix.net/content/cwrsync-free-edition
rem Под Windows, rsync можно использовать через cygwin или отдельным приложением cwrsync.

rem -rvz -  рекурсивный режим, подробный вывод, компрессия при передаче
rem Наличие завершающих слешей в /home/data1/ обязательно, если копируется папка
rem rsync -a foobar_src/ foobar_dst/    -- синхронизировать 2 каталога
rem rsync -a C:\Users\Mikhail.Chernichenko\Documents\Project\GIT_Repositories\!_MyProjects\JavaProjectLab\ansible\test_install vagrant@127.0.0.1:/home/vagrant/dir2
rem rsync -e='ssh -p 2222' -rvz /cygdrive/c/Users/Mikhail.Chernichenko/Documents/Project/GIT_Repositories/!_MyProjects/JavaProjectLab/ansible/test_install/ vagrant@127.0.0.1:/home/vagrant/dir

rem =================================
xcopy test_install c:\work\vagrant\run_scripts\ /d /i /e /f /y
rem scp  -r test_install -P 2222 vagrant@127.0.0.1:/home/vagrant/dir1

pause