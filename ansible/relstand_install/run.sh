#!/bin/bash

# -vvvv кол-во 'v' уровень детализации.

ansible-playbook -i inventory/hosts playbook_test.yml

# ansible -i inventory/hosts relstand -m ping
# ansible -i hosts box2 -m copy -a "src=/etc/hosts dest=/tmp/hosts" -v


