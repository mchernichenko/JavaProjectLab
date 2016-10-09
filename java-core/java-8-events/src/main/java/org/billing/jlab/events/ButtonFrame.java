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
    private JButton jButton;
    public JLabel jLabel;

    public ButtonFrame() {
        // присваиваем фрейму размер
        setSize(DAFAULT_WIDTH, DAFAULT_HEIGHT);

        // создали панель (контейнер) и кинули в него три кнопки
        buttonPanel = new JPanel();
        makeButton("Yellow", Color.YELLOW);
        makeButton("Green", Color.GREEN);
        makeButton("Red", Color.RED);

        //
        jLabel = new JLabel("Label");
        buttonPanel.add(jLabel);

        // получаем массив объектов, описывающих реализации установленных визуальных стилей
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        // на каждый стиль создаём кнопку с именем стиля.
        for (UIManager.LookAndFeelInfo info : infos) {
            makeButton(info.getName(), info.getClassName());
        }
        // добавили панель на фрейм
        add(buttonPanel);
    }

    /**
     * Создаём кнопку на панели, создаём обработчик и связываем его с кнопкой
     * @param name - имя кнопки
     * @param backgroundColor - цвет фона панели, на который меняет обработчик кнопки
     */
    public void makeButton(String name, final Color backgroundColor) {
        jButton = new JButton(name);
        buttonPanel.add(jButton);

        ColorAction colorAction = new ColorAction(backgroundColor);

       // jButton.addActionListener(colorAction); // пример без анонимного класса

        /* класс colorAction требуется всего один раз, только в данном методе => можно сделать его анонимным
           все локальные переменные, используемые в локально классе должны быть финальными. Механизм внутренних классов, автоматически генерирует конструктор,
           размещающий в памяти все локальные конечные переменные.
        */
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // возвращает ссылку на объект, являющийся источником события
                Object source = e.getSource();
                if (source instanceof JButton) {
                    JButton btn = (JButton) source;
                    jLabel.setText("Label: " + btn.getText());
                }
                buttonPanel.setBackground(backgroundColor);
            }
        });
    }

    /**
     * Создание кнопки изменяющий подключаемфй стиль после нажатия на кнопку
     * @param name - имя стиля
     * @param plafName - имя класса реализующего стиль
     */

    public void makeButton(String name, final String plafName) {
        jButton = new JButton(name);
        buttonPanel.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Устанавливает текущий стиль, используя заданое имя класса
                    UIManager.setLookAndFeel(plafName);
                    SwingUtilities.updateComponentTreeUI(ButtonFrame.this); // ссылка на внешний класс
                    //pack();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
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

        public ColorAction() { }

        @Override
        public void actionPerformed(ActionEvent e) {

            // возвращает ссылку на объект, являющийся источником события
            Object source = e.getSource();
            if (source instanceof JButton) {
                JButton btn = (JButton) source;
                jLabel.setText("Label: " + btn.getText());
            }
            buttonPanel.setBackground(backgroundColor);
        }
    }
}
