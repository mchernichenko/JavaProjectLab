#!/bin/bash


# ansible-playbook -i hosts playbook_roles.yml  # Запуск плейбука с hosts, расположенным в папке с плейбуком
 ansible-playbook -i hosts playbook_roles.yml --tags "show_var"


# ansible-playbook -i inventory/gf/hosts playbook_test.yml   # Запуск плейбука с hosts, расположенным в inventory/gf/hosts относительно плейбука
# ansible-playbook playbook_test.yml -vvvv                   # Запуск плейбука с дефалтовым hosts, расположенным /etc/ansible/hosts


# ansible-playbook -i hosts playbook_test.yml  --list-host      # Просмотр на каких хостах будет выполнятся плeйбук
# ansible-playbook -i hosts playbook_test.yml  --syntax-check   # Проверка синтаксиса плейбука
# ansible-playbook -i hosts playbook_test.yml  --list-tasks     # Если нужно просмотреть какие таски и, ГЛАВНОЕ, в каком порядке будет выполнять плейбук таски, в том числе описанные в ролях и вложенных файлах.
# ansible-playbook -i hosts playbook_test.yml  --list-tags      # Аналогично просмотр тегов, чтобы ваполнить конкретный таск или роль.

# ansible-playbook example.yml --tags "delete_file,copy_file"  # запуск конкретных теггированных тасков
# ansible-playbook example.yml --skip-tags "delete_file"       # запуск плейбука кроме теггированных тасков

# ansible -i hosts box1 -m setup -a "filter=ansible_system" # -a "filter=ansible_local"