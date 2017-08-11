package org.billing.jlab.javacore.javadoc;

import java.util.Random;

/* <p>Нужно иметь ввиду, что в заголовках только первое предложение.</p>

 Подробрное описание утилиты javadoc см. Шилдт стр. 1085

 Утилита javadoc извлекает комментарии из следующих компонент:
 - overview.html - обзорные комментарии к исходным файлам (располагается в папке javadoc/ относительно корневого каталога пакета)
 - package-info.java (или package.html) - Комментарии к пакетам
 - public классы и интерфейсы
 - public и protected методы и поля
 - все картинки используемые в javadoc храним в папке javadoc/resources относительно корневого каталога пакета

 * Комментарии к классу должы быть сразу после директив import, непосредственно перед опредедением класса

 * Первое предложение в комментарии д.б. кратким описанием, где можно использовать HTML-разметку
 <em>для выделения такста КУРСИВОМ</em>
 <code>для фоматирования текста МОНОШИРИННОГО ШРИФТА</code>
 <strong>для выделениы ПОЛУЖИРНЫМ</strong>
 и даже для вставки рисунков <img src="../../../../resources/uml.png" alt="UML диаграмма!!!" />  которые сохраняем в
 Важно: ../../../../ - потому что описание класса в пакете org.billing.jlab.javadoc => расположено в соответствующем каталоге,
 поэтому нужно подняться на четыре уровня выше в корень javadoc где и расположен каталог resources/

 * Далее идут дескрипторы начинающиеся с символа @

 * Для классов могут использоваться следующие дескрипторы:
    @author Имя автора - создаёт раздел автора программ. Может быть несколько таких дескрипторов, по одному на каждого автора.
    @version 0.01 - создаёт раздел версии программы

 * Общие дескрипторы для классов и методов:
  @since версия 1.0 - создаёт раздел начальной точки отсчета. Описание версии программы, в которой впервые был внедрён данный компонент.
  deprecated - добавляет сообщение о том, что класс, метод или переменная не рекомендуется к использованию

  @see "Добавляет ссылку в радел 'См. также'" - можно указывать гипертекстовые ссылки на соответствующие внешние документы или часть документа, сформированного javadoc
  @see JavaDocClass#raiseSalary(double)
  @see <a href="www.horstmann.com/">Web страница книги</a>
  @see "Не гипертекстовая ссылка"

  <p/>
  Для размещения ссылки в любом месте комментария используется {@link java.util.Random#nextInt() Просто ссылка на Random.nextInt()} в javadoc
  <a href="http://docs.oracle.com/javase/1.5.0/docs/tooldocs/solaris/javadoc.html#{@link}">Почитать про использование @link</a>
  <p/>

 Для извлечения комметнариев мспользуется либо утилита javadoc, либо плагин maven
 <strong>javadoc -d docDirectory имя_пакета</strong>

 */
public class JavaDocClass {
    private String name;
    private Double salary;
    private String s;


    /**
     * Документировать можно лишь открытые поля
     */
    public static final int HEARTS = 1;

    /**
     * Для методов могут использоваться следующие дескрипторы:
     * @param name добавляет в описание метода раздел параметров.
     *             Можно развернуть на несколько строк и использовать HTML.
     * @param salary все параметры относящиеся к одному методу, должны быть сгруппированы вместе
     * @param s
     * return - описание - добавляет раздел возвращаемого значения
     * throws java.lang.RuntimeException - описание класса - указывает на то, что метод способен генерировать исключения
     */
    public JavaDocClass(String name, Double salary, String s) {
        this.name = name;

        this.salary = salary;
        this.s = s;
    }

    public String getName() {
        return name;
    }

    /**
     * @since Version 0.77 Здесь пишем версию программы, в которой впервые был внедрён данный компонент
     */
    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        Random random = new Random();
        int i = random.nextInt(1000);
        this.salary = salary;
    }

    /**
     * Увеличивает ЗП сотрудников на %
     *
     * @param byPercent % увеличения ЗП (например, 10 = 10%)
     * @return Величина, на которую повышается ЗП
     */
    public double raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        this.salary += raise;
        return raise;
    }

}
