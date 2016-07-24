/**
 Основные моменты:
 1. Во фрейм добавляются различные виджеты (кнопки, элементы управления, графику и пр.) и некоторые виджеты являются
    ИСТОЧНИКАМИ событий, основываясь на действиях пользователя. Понять это можно по наличию в классе метода
    add#ТипСобытия#Listener - метод добавления слушателя в список подписчиков
    actionPerformed - метод интерфейса ActionListener вызываемый источником события для всех подписчиков (метод обратного вызова)

 2. ActionListener применяется:
    - после двойного щелчка на элементе списка
    - после выбора пункта меню
    - после надатия Enter, когда курсор находится в текстовом поле
    - в Timer по истечении заданного периода времени

 3. Классы адаптеров.
    В отличие от ActionListener содержащий один метод, существуют Listener`ы с множеством методов из которых нужен только один.
    В этом случае удобно использовать класс адаптер, реализующий все методы интерфейса, причём с пустыми телами.
    Далее расширяем класс адаптера, переопределяя только нужные методы (см. EventTest).

 4. Основные классы событий и интерфейсы для отслеживания этих событий (см. PGN):
    ActionEvent - ActionListener - щелчёк на кнопке, выбор пункта меню или из списка, ввод текста в поле
    AdjustmentEvent - AdjustmentListener - перемещение бегунка по полосе прокрутки
    ItemEvent - ItemListener - выбор одной из кнопок переключателей, установка флажка, выбор элемента из списка
    KeyEvent - KeyListener - нажатие или отпускание клавиши
    MouseEvent - MouseListener - нажатие или отпускание кнопки мыши, перемещение курсора мыши
    MouseWhellEvent - MouseWhellListener - вращение колёсика мыши
    FocusEvent - FocusListener - получение или потеря фокуса
    WindowEvent - WindowListener - изменение состояния окна

5. Действия. Интерфейс Action расширяющий ActionListener
   По сути это объект, в котором определяется некоторое имя, иконка, обработчик actionPerformed, т.к. Action расширяяет ActionListener
   Далее этот объект передаётся в конструктор кнопки  и уже сам конструктор берёт имя, иконку для кнопки из объекта Action
   и регистрирует этот объект в качестве приёмника действий

6. События мыши
    <TBD>

 */
 package org.billing.jlab.events;