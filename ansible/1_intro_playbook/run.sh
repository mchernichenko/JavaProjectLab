#!/bin/bash

# -vvvv кол-во 'v' уровень детализации.
# включить в ansible.cfg логирование log_path=/home/vagrant/ansible.log

# ansible-playbook -i hosts 1_intro_playbook.yml  # Запуск плейбука с hosts, расположенным в папке с плейбуком

# ansible-playbook -i inventory/gf/hosts playbook_test.yml   # Запуск плейбука с hosts, расположенным в inventory/gf/hosts относительно плейбука
# ansible-playbook playbook_test.yml -vvvv                   # Запуск плейбука с дефалтовым hosts, расположенным /etc/ansible/hosts


# ansible-playbook -i hosts playbook_test.yml  --list-host      # Просмотр на каких хостах будет выполнятся плeйбук
# ansible-playbook -i hosts playbook_test.yml  --syntax-check   # Проверка синтаксиса плейбука
# ansible-playbook -i hosts playbook_test.yml  --list-tasks     # Если нужно просмотреть какие таски и, ГЛАВНОЕ, в каком порядке будет выполнять плейбук таски, в том числе описанные в ролях и вложенных файлах.
# ansible-playbook -i hosts playbook_test.yml  --list-tags      # Аналогично просмотр тегов, чтобы ваполнить конкретный таск или роль.

# Ad-Hoc - запуск отдельных команд без плейбука: ansible -i inventory_file <all|группа_хостов|хост> -m module_name -a "mod_arg"
# ansible -i hosts box12 -m ping
# ansible -i hosts box2 -m copy -a "src=/etc/hosts dest=/tmp/hosts" -v
# ansible -i hosts box2 -m shell -a "echo $TERM" -v
# ansible -i hosts box2 -m command -a 'uptime' -v
# ansible -i hosts box2 -m setup -a "filter=ansible_local"  # сбор фактов с хостов, можно собирать не все факты, т.к. м.б. долго, а указать какие конкретно,
                                                          # например, локальные (ansible_local), определляемые в файле <name_file>.fact в каталоге '/etc/ansible/facts.d' или "filter=<имя_атрибута_json>"
