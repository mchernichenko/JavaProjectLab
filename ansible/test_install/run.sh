#!/bin/bash

# -vvvv кол-во 'v' уровень детализации.
# включить в ansible.cfg логирование log_path=/home/vagrant/ansible.log

# ansible-playbook playbook_test.yml -vvvv                   # Запуск плейбука с дефалтовым hosts, расположенным /etc/ansible/hosts
 ansible-playbook -i hosts playbook_test.yml                # Запуск плейбука с hosts, расположенным в папке с плейбуком
# ansible-playbook -i inventory/gf/hosts playbook_test.yml   # Запуск плейбука с hosts, расположенным в inventory/gf/hosts относительно плейбука


# ansible-playbook -i hosts playbook_test.yml --list-host       # Просмотр на каких хостах будет выполнятся плeйбук
# ansible-playbook -i hosts playbook_test.yml  --syntax-check   # Проверка синтаксиса плейбука

# Ad-Hoc - запуск отдельных команд без плейбука: ansible -i inventory_file <all|группа_хостов|хост> -m module_name -a "mod_arg"
# ansible -i hosts box12 -m ping
# ansible -i hosts box2 -m copy -a "src=/etc/hosts dest=/tmp/hosts" -v
# ansible -i hosts box2 -m shell -a "echo $TERM" -v
# ansible -i hosts box2 -m command -a 'uptime' -v


