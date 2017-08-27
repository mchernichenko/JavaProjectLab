rem В батнике команда %~dp0 дает полный путь к этому бантику.

rem @echo off отключит вывод команд на экран на протяжении выполнения всего пакетного файла. Чтобы сама команда не выводилась, она начинается с символа @.
@echo off
chcp 1251
echo Path to file: %~dp0

mkdir "%~dp0/../log"
cd "%~dp0/.."
java -cp "%~dp0/../lib/*" org.billing.jlab.javacore.javadoc.Client