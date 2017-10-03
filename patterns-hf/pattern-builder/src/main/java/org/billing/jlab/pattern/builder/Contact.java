package org.billing.jlab.pattern.builder;

/**
 * является паттерном создания объекто и используется для создания инстансов классов модели,
 * т.е. если класс содержит много однотипных полей, то его, как правило, доддают билдером.
 * Требует создание класса, который называется как и основкной класс который будем билдить + постфикс Builder
 * Обычно этот класс является внутренним, чтобы подчеркнуть логическую связь билдера с нстансом класса который создаём
 * Он имеет теже самые поля, что и класс инстанс которого будет билдиться, только не final (т.е. объект мутируемый)
 * и для каждого поля билдера создается метод для мутирования каждого поля и возвращающий экзампляр самого же себя
 * Имя метода должно совпадать с именем переменной.
 * Конструктор создаваемой модели (инстанса) не должно быть доступно, кроме самого билдера и на вход принимать свой билдер
 */
public class Contact {

    private final String firstName;
    private final String lastName;
    private final String adderess;
    private final String phone;

    Contact(final ContactBuilder contactBuilder) {
        this.firstName = contactBuilder.getFirstName();
        this.lastName = contactBuilder.getLastName();
        this.adderess = contactBuilder.getAdderess();
        this.phone = contactBuilder.getPhone();
    }

    public static void main(String[] args) {
        Contact contact = new ContactBuilder()
                .firstName("firstName")
                .lastName("lastName")
                .adderess("address")
                .phone("phone")
                .build();

        System.out.println(contact);
    }
}