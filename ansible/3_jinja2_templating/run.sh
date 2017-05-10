#!/bin/bash

 ansible-playbook -i hosts playbook_jinja_test.yml  --tags "tg1" # Запуск плейбука с hosts, расположенным в папке с плейбуком
# ansible-playbook -i hosts playbook_jinja_test.yml --tags "show_var"

# ansible-playbook example.yml --tags "delete_file,copy_file"  # запуск конкретных теггированных тасков