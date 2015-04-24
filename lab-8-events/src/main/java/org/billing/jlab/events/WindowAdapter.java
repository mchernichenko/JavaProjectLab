package org.billing.jlab.events;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Класс адаптер, реализующий WindowListener в котором определены все события, которые могут произойти в окне (т.к. мы обязаны реалировать все методы интерфейса).
 * Но все они с пустым телом. Адаптер требуется расширииить и переопределить толко нужный метод, а не все.
 */
public class WindowAdapter implements WindowListener {
    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
