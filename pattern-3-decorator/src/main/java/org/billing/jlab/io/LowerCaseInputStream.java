package org.billing.jlab.io;

import java.io.*;

/**
 * Пример написания собственного декоратора ввода/вывода, для этого требуется расширить FilterInputStream - абстрактного декоратора для всех объектов InputStream
 * и переопдерелить методы read()
 */
public class LowerCaseInputStream extends FilterInputStream {

	public LowerCaseInputStream(InputStream in) {
		super(in);
	}

	/**
	 * Чтение байтов и преобразование их к нижнему регистру
	 * @return - возвращает символ (байт) в нижнем регистре
	 * @throws IOException
	 */
	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : Character.toLowerCase((char)c));
	}

	/**
	 *
	 * @param b - массив символов
	 * @param offset - начальное смещение, т.е. с какого символа читаем
	 * @param len - сколько символов читаем
	 * @return - the total number of bytes read into the buffer, or -1 if there is no more data because the end of the stream has been reached.
	 * @throws IOException
	 */
	public int read(byte[] b, int offset, int len) throws IOException {
		int result = super.read(b, offset, len);
		for (int i = offset; i < offset+result; i++) {
			b[i] = (byte)Character.toLowerCase((char)b[i]);
		}
		return result;
	}
}
