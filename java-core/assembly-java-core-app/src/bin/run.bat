rem � ������� ������� %~dp0 ���� ������ ���� � ����� �������.

rem @echo off �������� ����� ������ �� ����� �� ���������� ���������� ����� ��������� �����. ����� ���� ������� �� ����������, ��� ���������� � ������� @.
@echo off
chcp 1251
echo Path to file: %~dp0

mkdir "%~dp0/../log"
cd "%~dp0/.."
java -cp "%~dp0/../lib/*" org.billing.jlab.javacore.javadoc.Client