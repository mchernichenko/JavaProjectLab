package org.billing.jlab.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Фрейм с панелью кнопок.
 * Пример использования обработчиков событий.
 */
public class ButtonFrame extends JFrame {
    private static final int DAFAULT_WIDTH = 800;
    private static final int DAFAULT_HEIGHT = 500;
    private JPanel buttonPanel;

    public ButtonFrame() {
        // присваиваем фрейму размер
        setSize(DAFAULT_WIDTH, DAFAULT_HEIGHT);

        // создали панель (контейнер) и кинули в него три кнопки
        buttonPanel = new JPanel();
        makeButton("Yellow", Color.YELLOW);
        makeButton("Blue", Color.BLUE);
        makeButton("Red", Color.RED);

        // добавили панель на фрейм
        add(buttonPanel);
    }

    /**
     * Создаём кнопку на панели, создаём обработчик и связываем его с кнопкой
     * @param name - имя кнопки
     * @param backgroundColor - цвет фона панели, на который меняет обработчик кнопки
     */
    public void makeButton(String name, final Color backgroundColor) {
        JButton jButton = new JButton(name);
        buttonPanel.add(jButton);
        ColorAction colorAction = new ColorAction(backgroundColor);

        //jButton.addActionListener(colorAction); // пример без анонимного класса

        /* класс colorAction требуется всего один раз, только в данном методе => можно сделать его анонимным
           все локальные переменные, используемые в локально классе должны быть финальными. Механизм внутренних классов, автоматически генерирует конструктор,
           размещающий в памяти все локальные конечные переменные.
        */
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setBackground(backgroundColor);
            }
        });

    }

    /**
     * Приёмник событий от кнопок. Как правило приёмник изменяет состояние другого класса => приёмник должен иметь доступ к его переменным
     * В данном случае к панели, цвет которой изменяется по нажатию кнопок.
     */
    class ColorAction implements ActionListener {

        private Color backgroundColor;

        public ColorAction(Color color) {
            this.backgroundColor = color;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonPanel.setBackground(backgroundColor);

        }
    }
}
