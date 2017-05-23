Для тех, кому лень что-то писать самому, уже есть тысячи готовых ролей на сайте ansible galaxy,
и они вполне достойного качества, чтобы пользоваться ими в бою.

Команда ansible-galaxy входит в поставку Ansible и позволяет устанавливать роли с общего репозитория
https://galaxy.ansible.com/list#/roles?page=1&page_size=10
или и напрямую с git. Также используется для создания новых ролей, удаления и выполнения тасков на web сервере Galaxy

Например: можно создать шаблон для новой роли: ansible-galaxy init nginx -p roles
Эта команда сразу создаст шаблонную структуру для роли
├── roles
│   └── nginx
│       ├── README.md
│       ├── defaults
│       │   └── main.yml
│       ├── files
│       ├── handlers
│       │   └── main.yml
│       ├── meta
│       │   └── main.yml
│       ├── tasks
│       │   └── main.yml
│       ├── templates
│       └── vars
│           └── main.yml

Можно установить сразу несколько ролей, где устанавливаемые роли прописываются в файле requirements.yml
$ ansible-galaxy -r requirements.yml install --roles-path ./roles_dir

-- Ansible Container - это проект Ansible
https://hghltd.yandex.net/yandbtm?fmode=inject&url=https%3A%2F%2Fhabrahabr.ru%2Fcompany%2Fcentosadmin%2Fblog%2F306488%2F&tld=ru&lang=ru&la=1490381824&tm=1495459781&text=Container%20Enabled%20ansible&l10n=ru&mime=html&sign=d8ce0ea62858c75405c51e118135bef5&keyno=0
Это возможность сборки docker-образа при помощи  ansible-плейбука !

Ansible - это не только инструмент сборки и деплоя, но также деплоя образов docker-контейнеров и самих docker-контейнеров, благодаря соответствующему модулю docker - manage docker containers
Ранее для провиженинга docker-контейнеров с помощью  Ansible  необходим был запущенный в контейнере sshd, но в версии 2.1 в  Ansible  был добавлен Docker connection plugin,
благодаря которому стало возможно запускать плейбуки не только на физических/виртуальных машинах, но и внутри docker-контейнеров и отпала необходимость использования sshd внутри docker-контейнера.
+ Dockerfile - для описания содержимого контейнера можно юзать yaml-формате, а не городить портянку из shell-скрипта,



