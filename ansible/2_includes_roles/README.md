# Применение тегов, ролей, включений

=== Роли ========================================

Роль - это по сути вынесение тасков в отдельные файлы, для лучшей организации структруры плейбука и инсталлятора вообще, по сравнению с включениями (include)
Рекомендация - всегда используйте роли, а include в крайней необходимости
Например, роль test это набор YALM файлав, расположенных в /roles/test/tasks/main.yml
Роли всегда выполняются до тасков, перечисленных в плейбуке, в независимости от их расположения, кроме тасков в pre_tasks:.

Структура роли:
- roles/<имя_роли>/tasks/main.yml ,если существует, то таски перечисленные в main.yml добавляются в плёйбук
- roles/<имя_роли>/handlers/main.yml ,если существует, то обработчики перечисленные в main.yml добавляются в плёйбук
- roles/<имя_роли>/vars/main.yml exists ,переменные роли
- roles/<имя_роли>/defaults/main.yml , переменные роли по умолчанию
- roles/<имя_роли>/meta/main.yml exists, зависимые роли, выполняются перед самой ролью


===  Теги ========================================

tags:
  - name_tag

По сути метка, позволяющая запускать не весь плейбук, а только теггированные таски. Одним именем тега могут помечаться несколько тесков, тогда будет выполняться группа тасков.
Также можно, наоборот, исключать теггированные таски при запуске плейбука
Теги можно передавать в роли: - { role: webserver, port: 5000, tags: [ 'web', 'foo' ] }

> ansible-playbook example.yml --tags "delete_file,copy_file"  # запуск конкретных теггированных тасков
> ansible-playbook example.yml --skip-tags "delete_file"       # запуск плейбука кроме теггированных тасков


=== Переменные =======================================

1. Имя переменной не допускает '-', '.', пробел, толко цифры: foo-port, foo port, foo.port and 12
2. Могут определяться мапы, обращаться как foo['field1']:
foo:
  field1: one
  field2: two

Где м.б. определены, в порядке увеличения приоритета:
1. role defaults                                - переменные роли по умолчанию: roles/<имя_роли>/defaults/main.yml
2. inventory INI or script group vars           - переменные в самом файле hosts для групп хостов, например, [box1:vars]
3. inventory group_vars/all                     - общие переменные для всех групп хостов в hosts по дефолту:
                                                        /etc/ansible/group_vars/
4. playbook group_vars/all                      - общие переменных для всех групп хостов в hosts плейбука, указываются в каталоге плейбука:
                                                        /group_vars/all.yml
5. inventory group_vars/*                       - переменные для контретной группы хостов, указанного в hosts по каталоге дефолту. Читаются из
                                                        /etc/ansible/group_vars/<имя_группы_из_hosts>.yml или из папки
                                                        /etc/ansible/group_vars/<имя_группы_из_hosts> (из папки переменные вычитываются из всех файлов, ханолящихся в ней)
6. playbook group_vars/*                        - переменные для контретной группы хостов, указанного в hosts для плейбука:
                                                        /group_vars/<имя_группы_из_hosts>.yml или в папке
                                                        /group_vars/<имя_группы_из_hosts>
7. inventory INI or script host vars            - переменные в самом файле hosts для конкретных хостов, например, [server_name:vars]
8. inventory host_vars/*                        - переменные для контретых хостов, указанных в hosts по каталоге дефолту:
                                                        /etc/ansible/host_vars/<имя_хоста_из_hosts>.yml или каталога
                                                        /etc/ansible/host_vars/<имя_хоста_из_hosts>
9. playbook host_vars/*                         - переменные хостов, указанных в hosts для плейбука:
                                                        /host_vars/<имя_хоста_из_hosts>.yml или каталога
                                                        /host_vars/<имя_хоста_из_hosts>
10. host facts                                  - переменные фактов, если собираются gather_facts: yes
11. play vars                                   - секция 'vars' в playbook, определяющая параметры плейбука
12. play vars_prompt                            - секция 'vars_prompt' в playbook, определяющая переменные которые запрашиваются при запуске плейбука
13. play vars_files                             - секция 'vars_files' в playbook, определяющая файл из которого беруться параметры плейбука
14. role vars (defined in role/vars/main.yml)   - переменные роли в role/vars/main.yml
15. block vars (only for tasks in block)        - переменные определённые внутри блочного таска: tasks: - block:
16. task vars (only for the task)               - переменные определённые внутри таска, секция 'vars' внутри tasks:
17. role (and include_role) params              - переменные, определённые в самом таске роли или зависимой роли
18. include params                              - переменные таска, встраиваемые в таск в виде файла include:    tasks: - include_vars: "<имя_файла>.yml"
19. include_vars                                - переменные передаваемые во вложенный (include) таск:           tasks: - include: wordpress.yml wp_user=timmy
20. set_facts / registered vars                 - результаты работы модуля таска могут быть сохранены в переменной, с помощью:  register: var_name как факт работы модуля
21. extra vars (always win precedence)          - переменные, передаваймые в командной строке: ansible-playbook playbook.yml --extra-vars "hosts=vipers user=starbuck"

-- Локальные переменные фактов --
Зачем они? Как я понял, это ещё один из способов определить переменные хоста, что-то типа /host_vars, чтобы не вносить изменения в инсталлятор,
переменные, которые по сути являются фактами о системе (пути установки и пр.), можно определить как внешний файлик, который кладётся на удалённую машину и плейбук его считывает и применяет.

Определяются в файле с расширением .fact, например /etc/ansible/facts.d/preferences.fact, где определны переменные так
[general]
asdf=1
bar=2

тогда при сборе фактов, например, gather_facts: yes или явно запустить модуль setup: ansible -i hosts box1 -m setup -a "filter=ansible_system"
результат будет возвращён в формате json, например:
"ansible_local": {
        "preferences": {
            "general": {
                "asdf" : "1",
                "bar"  : "2"
            }
        }
 }

, к атрибутам которого можно доступиться так:
  - {{ ansible_local.preferences.general.asdf }}
  - {{ ansible_local["preferences"]["general"]["asdf"] }}
  - {{ ansible_local[0] }} - первый элемент в списке

-- Для обращения в переменным фактов других хостов есть зарезервированные имена:
 hostvars - переменная хранит собранные факты о хосте с помощью модуля setup или gather_facts: yes
            IP адрес заданного хоста {{ hostvars['name_host']['ansible_all_ipv4_addresses'] }}
 group_names - список групп в который входит текущий хост, для которого выполняется таск

 groups - список групп и хостов файла hosts

=============================================
1. в
1. в playbook, в конструкции vars или vars_files - из внешнего файла. Описать нужно до описания тасков и ролей.
		vars:
		  favcolor: blue
		vars_files:
		  - /vars/external_vars.yml
2. include_vars - передаваться в таск как вложенный файл. переопределяет переменные playbook
		tasks:
		- include_vars: "<имя_файла>.yml"  -- таск с параметрами из файла .yml

3. include params - передаваться в include таск
	   tasks:
	   - include: wordpress.yml wp_user=timmy -- вложенный таск с передачей параметров

4. в отдельных файлах для группы ролей или для всех ролей: group_vars/<group_name>.yml или 'group_vars/all.yml'
   аналогично для хостов: 'host_vars/<host_name>.yml'
   Файлы должны именоваться соответственно как именуются хосты или роли. group_vars/host_vars должны располагаться там, где и сам файл hosts
   но как правило, переменные роли указываются см. п.5

5. переменные роли:
	3.1 /roles/<имя_роли>/defaults/main.yml   - если на момент выполнения роли переменная не определена, например в playbook или в inventory, то ёе значение определяется по умолчанию
	3.2 /roles/<имя_роли>/vars/main.yml       - переменные роли, переопределяют значение из host_vars/group_vars/play vars и пр., но не переопределяют, например, extra vars, include_vars
	3.3 /roles/common/vars/main.yml           - общие переменные для ролей
	3.4 roles:
		- { role: apache, http_port: 8080 }   - роли с параметрами (role params), переопределяют role vars (определённые в role/vars/main.yml)

	см. все приоритеты: http://docs.ansible.com/ansible/playbooks_variables.html

4.


Проверка переменных перед запуском задания:
 - либо в самом playbook
 - либо в файле /roles/<имя_роли>/tasks/init.yml , который затем нужно заиклюдить в main.yml ( - include: init.yml )
 - либо в командной строке: ansible-playbook playbook.yml --extra-vars "hosts=vipers user=starbuck"

Пример:
 - assert:
    that:
      - zk_install_dir != ''
      - zk_arch != ''
