package org.billing.jlab.camel.ch1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *  Класс демонстрирует простой кейс копирования файла из одной папки в другую без использования Apache Camel
 *  data/inbox -> data/outbox
 *  Здесь используются низкоуровневое API
 *  Если требуется периодически опрашивать данные входного каталога, требуется настройка таймера, а также отслеживать файлы, которые скопировали
 *  В этом случае кейс становится более сложным, хотя это обычная интеграционная задача, которая была сделана тыщу раз до нас и без Apache Camel мы
 *  по сути изобретаем колесо заново
 *
 */

public class FileCopier {

    public static void main(String args[]) throws Exception {
        File inboxDirectory = new File("camel-ch1/data/inbox");
        File outboxDirectory = new File("camel-ch1/data/outbox");

        outboxDirectory.mkdir();

        File[] files = inboxDirectory.listFiles();
        if (files == null) System.out.println("Нет файлов для копирования в каталоге: "+ inboxDirectory.getAbsolutePath());
        else {
            for (File source : files) {
                if (source.isFile()) {
                    File dest = new File(
                            outboxDirectory.getPath()
                                    + File.separator
                                    + source.getName());
                    copyFile(source, dest);
                }
              }
            }
        }

    /**
     * Функция побайтного копирования файла
     *
     * @param source исходной имя фала включая относительный путь до него
     * @param dest выходное имя фала включая относительный путь до него
     * @throws IOException
     */
    private static void copyFile(File source, File dest) 
        throws IOException {
        OutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[(int) source.length()];
        FileInputStream in = new FileInputStream(source);
        in.read(buffer);
        try {
            out.write(buffer);
        } finally {
            out.close();      
            in.close();
        }
    }
}
