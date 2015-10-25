package org.billing.jlab.swing;
	
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Субъект, т.е. наблюдаемый объект это кнопка, которая реализует абстрактный класс AbstractButton -> EventListenerList
 * и в котором  декларируются метода добавления/удаления/нотификации надбодателей
 * Кнопка рагистрирует наблюдателей, которые должны реализовывать интерфейс ActionListener с методом actionPerformed
 *
 */

public class SwingObserverExample {
	JFrame frame;
	
	public static void main(String[] args) {
		SwingObserverExample example = new SwingObserverExample();
		example.go();
	}
	
	public void go() {
		frame = new JFrame();

		JButton button = new JButton("Should I do it?");
		button.addActionListener(new AngelListener());
		button.addActionListener(new DevilListener());
		frame.getContentPane().add(BorderLayout.CENTER, button);

		// Set frame properties 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER, button);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	
	class AngelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("Don't do it, you might regret it!");
		}
	}

	class DevilListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("Come on, do it!");
		}
	}
}
