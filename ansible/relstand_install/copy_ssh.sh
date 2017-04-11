#!/bin/bash

# ssh-keygen -t rsa  -- генерация ключей
# копирование публичных ключей на виртуалки стенда

sshpass -p 'rs123456' ssh-copy-id -i relstand@srv3-amain-a
sshpass -p 'rs123456' ssh-copy-id -i relstand@srv3-amain-m
sshpass -p 'rs123456' ssh-copy-id -i relstand@srv3-amain-p
sshpass -p 'rs123456' ssh-copy-id -i relstand@srv2-gf-app01
sshpass -p 'rs123456' ssh-copy-id -i relstand@srv2-gf-app02
sshpass -p 'rs123456' ssh-copy-id -i relstand@srv2-gf-cd01
sshpass -p 'rs123456' ssh-copy-id -i relstand@srv2-gf-spp

sshpass -p 'root123456' ssh-copy-id -i root@srv3-amain-a
sshpass -p 'root123456' ssh-copy-id -i root@srv3-amain-m
sshpass -p 'root123456' ssh-copy-id -i root@srv3-amain-p
sshpass -p 'root123456' ssh-copy-id -i root@srv2-gf-app01
sshpass -p 'root123456' ssh-copy-id -i root@srv2-gf-app02
sshpass -p 'root123456' ssh-copy-id -i root@srv2-gf-cd01
sshpass -p 'root123456' ssh-copy-id -i root@srv2-gf-spp
