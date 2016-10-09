package org.billing.jlab.patterns.chainofresponsibility;

import java.io.*;

/**
 * вспомогательный класс, обеспечивает решение обратной задачи— извлечения данных для использования их в рассматриваемом примере
 */
public class DataRetriever {
    public static Object deserializeData(String fileName) {
        Object returnValue = null;
        try {
            File inputFile = new File(fileName);
            if (inputFile.exists() && inputFile.isFile()) {
                ObjectInputStream readIn = new ObjectInputStream(new FileInputStream(fileName));
                returnValue = readIn.readObject();
                readIn.close();
            } else {
                System.err.println("Не найден файл" + fileName);
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }
}
