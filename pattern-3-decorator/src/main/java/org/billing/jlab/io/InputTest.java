package org.billing.jlab.io;
import java.io.*;

/**
 * Пример написания собственного декоратора ввода/вывода, преобразующего все символы верхнего регистра к нижнему регистру
 */
public class InputTest {
	public static void main(String[] args) throws IOException {
		int c;

		try {
			InputStream in =
				new LowerCaseInputStream(
					new BufferedInputStream(
						new FileInputStream("data/input.txt")));

			while((c = in.read()) >= 0) {
				System.out.print((char)c);
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
