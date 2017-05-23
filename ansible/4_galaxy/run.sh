#!/bin/bash

# ansible-playbook -i hosts playbook_main.yml

# ansible-playbook example.yml --tags "delete_file,copy_file"  # запуск конкретных теггированных тасков

# команда создания шаблонной структуры для роли nginx
#ansible-galaxy init nginx -p roles

# загрузка и установка роли с https://galaxy.ansible.com/ в текущий катало, без указания roles-path роли ставятся в /etc/ansible/roles:~/.ansible/roles
# можно указать конкретную версию, или скачать из github

# ansible-galaxy install --roles-path . geerlingguy.apache
# ansible-galaxy install --roles-path . geerlingguy.apache,v1.0.0
# ansible-galaxy install --roles-path . git+https://github.com/geerlingguy/ansible-role-apache.git,0b7cd353c0250e87a26e0499e59e7fd265cc2f25

# Можно установить сразу несколько ролей, где путь и имя к устанавливаемой роли прописываются в файле requirements.yml
ansible-galaxy -r requirements.yml install --roles-path ./roles_dir