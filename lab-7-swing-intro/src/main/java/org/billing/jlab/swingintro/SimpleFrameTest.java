package org.billing.jlab.swingintro;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 *
 */
public class SimpleFrameTest
{
    public static void main( String[] args )
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimpleFrame frame = new SimpleFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class SimpleFrame extends JFrame
{
    private static final int DAFAULT_WIDTH = 300;
    private static final int DAFAULT_HEIGHT = 200;

    public SimpleFrame() {
        setSize(DAFAULT_WIDTH, DAFAULT_HEIGHT);
    }
}
