#!/bin/bash

 ansible-playbook -i hosts playbook_roles.yml  # Запуск плейбука с hosts, расположенным в папке с плейбуком

# ansible-playbook -i inventory/gf/hosts playbook_test.yml   # Запуск плейбука с hosts, расположенным в inventory/gf/hosts относительно плейбука
# ansible-playbook playbook_test.yml -vvvv                   # Запуск плейбука с дефалтовым hosts, расположенным /etc/ansible/hosts


# ansible-playbook -i hosts playbook_test.yml  --list-host      # Просмотр на каких хостах будет выполнятся плeйбук
# ansible-playbook -i hosts playbook_test.yml  --syntax-check   # Проверка синтаксиса плейбука
# ansible-playbook -i hosts playbook_test.yml  --list-tasks     # Если нужно просмотреть какие таски и, ГЛАВНОЕ, в каком порядке будет выполнять плейбук таски, в том числе описанные в ролях и вложенных файлах.
# ansible-playbook -i hosts playbook_test.yml  --list-tags      # Аналогично просмотр тегов, чтобы ваполнить конкретный таск или роль.



