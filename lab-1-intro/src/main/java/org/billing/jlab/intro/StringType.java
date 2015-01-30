package org.billing.jlab.intro;

/**
 * Рассматриваются строки - неизменяемые объекты.
 *
 */
public class StringType {
    public static void main(String[] args) {
        String str = "Hello";

        "Hello".equalsIgnoreCase("hello"); // сравнение без учета регистра

        if (str.length() == 0) {
            System.out.println("строка пустая");
        }
        if (str.equals("")) {
            System.out.println("Ещё один способ проверки на пустоту строки.");
        }
        if (str == null) {
            System.out.println("объект String ни на что не ссылается");
        }
        if (str != null && str.length() != 0) { // важно!! если объект ни на что не ссылается, вызов его методов вызовет ошибку, поэтому проверяем
            System.out.println("Строка не пустая и не null");
        }

        // особености определения длины строки в UTF-16
        String str1 = "A" + "\uD835\uDD0A" + "B" + "C"; // A@BC length- 5, codePointCount- 4.
        System.out.println("length() возвращает кол-ко кодовых единиц: " + str.length());
        System.out.println("Истинная длина, т.е. количество коловых точек определяется - codePointCount: " + str.codePointCount(0, str.length()));
        // пример перебора строки по символам
        String strSpell = DataTypePrimitives.getSpellingString(str1);
        System.out.println(strSpell);

    }
}
