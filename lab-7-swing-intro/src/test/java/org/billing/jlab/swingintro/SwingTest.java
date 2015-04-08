package org.billing.jlab.swingintro;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Unit test for simple App.
 */
public class SwingTest {

    @Test
    public void testSimpleFrame() throws Exception
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
