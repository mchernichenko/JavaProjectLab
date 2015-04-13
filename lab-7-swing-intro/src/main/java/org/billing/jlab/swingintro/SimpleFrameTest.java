package org.billing.jlab.swingintro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

/**
 * JFrame - объект, который представляет собой окно на экране в которое помещаются все остальные элементы GUI
 * JFrame по-разному выглядит в зависимости от платформы
 */
public class SimpleFrameTest {

    JButton colorButton;
    SimpleFrame frame;
    JLabel label;
    int x, y; // координаты эллипса

    public static void main(String[] args) {
        SimpleFrameTest simpleFrameTest = new SimpleFrameTest();
        simpleFrameTest.go();
    }

    public void go() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                // начинается создание GUI с создание фрейма, т.е. с new JFrame() или с класса наследника JFrame
                frame = new SimpleFrame();

                // создаём кнопку и регистрируем текущий класс в список слушателей. Слушатели должны реализовывать интерфейс ActionListener
                // Объект является источником событий, если он имеет метод addБлаБлаБлаListener - это шаблон наименования метода,
                // а в качестве аргумента интерфейс слушателя
                colorButton = new JButton("Изменить цвет");
                colorButton.addActionListener(new ColorListener());

                JButton labelButton = new JButton("Change Label");
                labelButton.addActionListener(new LabelListener());

                label = new JLabel("Label");

                MyDrawPanel myDrawPanel = new MyDrawPanel();

                // завершает работу программыы при закрытии окна, иначе оно будет висеть на экране вечно
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // добавляем кнопку и личный виджет на панель фрейма, указывая область, куда будет добавлен виждет
                // JFrame не позволяет добавлять в него объекты напрямую, нужно истопьзовать понель содержимого принадлежащую JFrame
                frame.getContentPane().add(BorderLayout.SOUTH,colorButton);
                frame.getContentPane().add(BorderLayout.CENTER,myDrawPanel);
                frame.getContentPane().add(BorderLayout.EAST,labelButton);
                frame.getContentPane().add(BorderLayout.WEST,label);

                // по умолчанию фрейм не видим, требуется явно указать его видимость
                frame.setVisible(true);

                for (int i = 0; i < 200; i++) {
                    // меняем координаты эллипса
                    y++;
                    x++;
                    myDrawPanel.repaint();  // перересовываем панель (т.е. вызываем каждый раз paintComponent)

                    // немного замедляем процесс чтобы что-то увидеть
            /*        try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        });
    }

    class ColorListener implements ActionListener {

        /**
         * Метод обработки события нажатия кнопки
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //button.setText("Опаньки");
            x++;
            y++;
            frame.repaint(); // перерисовка фрейма вызывает paintComponent для каждого виждета во фрейме
        }
    }

    class LabelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("Опаньки\n" + Math.round(100 * Math.random()));
        }
    }

    /**
     * Панель для рисования недостаточно подходит для повторного использования, чтобы стать отдельным классом (поэтому делаем внутренним),
     * ведь созданные на ней рисунки фактически предназначены для данного графического приложения
     *
     * Создание личного виджета для рисования, который помещается на фрейм аналогично другим виджетам, например, кнопке.
     * Graphics - Системная поверхность(холст) для рисования, где можно рисовать.
     * Объект Graphics g на самом деле является экземпляром класса Graphics2G. Можно смето преобразовать тип, если требуется
     * использовать методы из этого класса.
     * paintComponent - метод, где должен располагаться весь графический код. Он никогда не вызывается самостоятельно!
     * Можно только через принудительную перерисовку frame.repaint()
     * Как правило его вызывает система, например, при изменении размеров фреймаи пр.
     */
    class MyDrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {

            // установка цвета фона и закрашиваем прямойгольник а всю ширину панели.
            g.setColor(Color.orange);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            // установка произвольных цветов для градиента
            Graphics2D g2d = (Graphics2D) g;

            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            Color startColor = new Color(red, green, blue);

            red = (int) (Math.random() * 255);
            green = (int) (Math.random() * 255);
            blue = (int) (Math.random() * 255);
            Color endColor = new Color(red, green, blue);

            GradientPaint gradientPaint = new GradientPaint(x, y, startColor, x + 100, y + 100, endColor);
            g2d.setPaint(gradientPaint); // устанавливаем текущий цвет рисования (в даном случае градент)
            g2d.fillOval(x, y, 100, 100); // рисуем овал текущим цветом с диаметром 100

        }
    }
}

/**
 *  Класс наследник JFrame для установки начальных параметров фрейма
 */
class SimpleFrame extends JFrame {
    private static final int DAFAULT_WIDTH = 800;
    private static final int DAFAULT_HEIGHT = 500;

    public SimpleFrame() {
        // присваиваем фрейму размер
        setSize(DAFAULT_WIDTH, DAFAULT_HEIGHT);
    }
}


