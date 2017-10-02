package org.billing.jlab.oop.abstracts;

// Предположим что это класс чтения из файла

public class FileReadProcessor extends AbstractReplaceProcessor{

    @Override
    protected String readString(String str) {
        return str;
    }

    public static void main(String[] args) {

        FileReadProcessor fileReadProcessor = new FileReadProcessor();
        fileReadProcessor.process();
    }
}

