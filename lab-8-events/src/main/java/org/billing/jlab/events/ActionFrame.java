package org.billing.jlab.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Пример определения и использования Действий для источников событий, а также  привязка горячих кнопок к источникам действий
 */
public class ActionFrame extends JFrame
{
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 500;
    private static final String PATH = "lab-8-events/src/main/";

    /**
     * Пример определения и использования Действий для источников событий, а также  привязка горячих кнопок к источникам действий
     */
    public ActionFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        buttonPanel = new JPanel();

        // Определяем действия
        Action yellowAction = new ColorAction("Yellow", new ImageIcon(PATH + "resources/yellow-ball.gif"), Color.YELLOW);
        Action blueAction = new ColorAction("Blue", new ImageIcon(PATH + "resources/blue-ball.gif"), Color.BLUE);
        Action redAction = new ColorAction("Red", new ImageIcon(PATH + "resources/red-ball.gif"), Color.RED);

        // создаём кнопки и передаём в конструктор кнопки не имя, а Action т.е. действие
        // этот конструктор считывает имя, иконку, всплывающую подсказку и регистрирует этот объект в качестве приёмника действий
        // т.к. ColorAction расширяет AbstractAction, который в свою очеред реализует интерфейс Action, а
        // Action расширяяет ActionListener
        buttonPanel.add(new JButton(yellowAction));
        buttonPanel.add(new JButton(blueAction));
        buttonPanel.add(new JButton(redAction));

        // кидаем кпонки во фрейм
        add(buttonPanel);

        // получаем Map, где key - комбинация клавиш, value - некий строковый ключ
        // т.е. ассоциируем комбинацию клавиш ctrl-Y, B, and R c именами
        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // наполняем коллекцию ,где KeyStroke - это объект представляющий комбнацию клавиш конструируемый из строкового выражения
        imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");

        // получаем Map, где действие привязывается к комбинации клавиш, выраженное строкой
        ActionMap amap = buttonPanel.getActionMap();
        amap.put("panel.yellow", yellowAction);
        amap.put("panel.blue", blueAction);
        amap.put("panel.red", redAction);
    }

    /**
     * AbstractAction по сути адаптер, т.е. реализует интерфейс Action в котором 7 методов, а нам нужен только actionPerformed
     */
    public class ColorAction extends AbstractAction
    {
        /**
         * @param name имя кнопки
         * @param icon иконка для кнопки
         * @param c цвет фона на который меняет кнопка
         */
        public ColorAction(String name, Icon icon, Color c)
        {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, "Всплывающая подсказка к кнопке " + name.toLowerCase());
            putValue("color", c);
        }

        public void actionPerformed(ActionEvent event)
        {
            Color c = (Color) getValue("color");
            buttonPanel.setBackground(c);
        }
    }
}
