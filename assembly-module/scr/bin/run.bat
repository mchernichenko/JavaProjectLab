rem � ������� ������� %~dp0 ���� ������ ���� � ����� �������.

rem @echo off �������� ����� ������ �� ����� �� ���������� ���������� ����� ��������� �����. ����� ���� ������� �� ����������, ��� ���������� � ������� @.
@echo off
chcp 1251
echo Path to file: %~dp0
rem mkdir "%~dp0/../log"
rem cd "%~dp0/.."
rem java -cp "%~dp0/../conf/;%~dp0/../lib/*" com.peterservice.cci.ccisrv.Main