package org.billing.jlab.exceptions;

import java.io.IOException;

/**
 * Пример создания собственных классов исключений, если не подходит ни один из стандартных классов исключений.
 * Он должен быть обязательно подклассом Exception или одного из его подклассов
 */
public class MyFileFormatException extends IOException {

    public MyFileFormatException() {
    }

    public MyFileFormatException(String message) {
        super(message);
    }
}
