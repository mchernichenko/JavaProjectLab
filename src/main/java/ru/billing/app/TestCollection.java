package ru.billing.app;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Рассматривается работа с коллекциями
 */

public class TestCollection {

    public static void main( String[] args ) {
        testLinkedList();
    }

    /**
     * LinkedList vs ArrayList  - разница только в структуре их организации.
     * LinkedList это двунаправленный список, где каждый элемент списка хранит ссылку на предыдущий и последующий элемент, а
     * ArrayList - по сути это обертка к массиву. Этим объясняются обобенности их использовании:
     * 1. В ArrayList быстрый доступ к элементу. Любой элемент списка доступен по индексу за константное время, т.к. он основан на массиве.
     *    В LinkedList доступ к элементу линейно зависит от его длинны, т.к. для этого нужно перебрать все элементы итератором сначала списка.
     * 2. В ArrayList вставка в конец имеет особеность. Т.к. это по сути массив и если ему не хватает места, то создается новый массив с размером
     *      lastSize*3/2 +1 в который копируются существующие элементы + новый. Это может привести к небольшой задержке при вставке.
     *      Лучше сразу задавать размер для ArrayList. По дефолту 10.
     * 3. В ArrayList вставка/удаление из середины пропорционально его длине, т.к. приходится всю правую часть массива полностью сдвигать.
     *    В LinkedList вставка/удаление из середины за константное время, т.к. обновляются только ссылки соседних элементов.
     * 4. В ArrayList cортировка быстрее, т.к. список сначала копируется в массив, сортируется и обратно в пишется в массив. Эти операции быстрее для ArrayList.
     * 5. LinkedList дополнительно реализует Queue и Deque
     *
     * Итого: ArrayList больше подходит для задач чтения из списка, использует меньше ресурсов.
     *        LinkedList больше подходит для задач частой вставки/удаления из списка + гарантированное время вставки в конец.
     *
     */
    public static void testLinkedList() {
        Integer cnt = 0;
        List<String> list = new LinkedList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ddd");
        list.add("ccc");

        List<String> list1 = new LinkedList<>();
        list1.add("aaa1");
        list1.add("bbb1");
        list1.add("ddd1");
        list1.add("ccc1");

/*      Для обхода LinkedList необходимо всегда использовать итераторы!!! т.к вызов метода get(index) в цикле каждый раз
        будет перебирать связанный список сначала. Доступ по индексу подходит только для ArrayList.*/

//      для list1 достаточно создать простой итератор, т.к. по этому списку мы будем только перемещаться вперёд.
        Iterator<String> iterator = list1.iterator();
//       для list создаём ListIterator т.к. он даёт доп. возможности (добавлять в список, изменять, перемещаться назад)
        ListIterator<String> listIterator = list.listIterator();

//        объединяем списки так, чтобы получить list = [aaa, aaa1, bbb, bbb1, ddd, ddd1, ccc, ccc1]
        while (iterator.hasNext()) {
            if (listIterator.hasNext()) listIterator.next();
          //  list.add(cnt);           // вот так добавлять в цикле не стоит для связанных списков, т.к. при каждом вызове создаётся новый итерптор, который должей пройти до нужного индекса
            listIterator.add(iterator.next());
        }
        System.out.println("list = " + list);

//      Удаляем каждое второе слово из list1
        iterator = list1.iterator();
        while (iterator.hasNext()) {
            iterator.next();            // пропустить один элемент
            if (iterator.hasNext()) {
                iterator.next();        // пройти следующий элемент
                iterator.remove();      // удалить этот  элемент
            }
        }
        System.out.println("list1 = " + list1);

//      Удалим из list все элементы которые есть в list1
        list.removeAll(list1);
        System.out.println("listAfterRemove = " + list);
    }

    /**
     *  Подсчет уникальных слов
     */
    public static void testHashSet() {
        Set<String> words = new HashSet<>();
        long totalTime = 0;

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String word = in.next();
            long callTime = System.currentTimeMillis();
            words.add(word);
            callTime = System.currentTimeMillis() - callTime;
            totalTime += callTime;
        }

        Iterator<String> iterator = words.iterator();
        for (int i = 1; i <= 20 && iterator.hasNext(); i++) {
            System.out.println(iterator.next());
        }
        System.out.println("........");
        System.out.println("Уникальных слов: " + words.size() + " Время: " + totalTime);
    }
}
