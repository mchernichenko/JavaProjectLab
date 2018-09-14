package org.billing.jlab.collections;

import java.util.*;

public class UseIterators {

    public static void main(String[] args) {

        List<String> listA = new LinkedList<>();
        List<String> listB = new LinkedList<>();

        listA.add("aaa1");
        listA.add("aaa2");
        listA.add("aaa3");

        listB.add("bbb1");
        listB.add("bbb2");
        listB.add("bbb3");
        listB.add("bbb4");
        listB.add("bbb5");

        // объединить связанные списки
        ListIterator<String> iterA = listA.listIterator();
        Iterator<String> iterB = listB.iterator();

        while (iterB.hasNext()) {
            if (iterA.hasNext()) iterA.next();
            iterA.add(iterB.next());
        }
        System.out.println(listA);

        // удалить каждое второе слово из связанного списка
        iterB = listB.iterator();
        while (iterB.hasNext()) {
            iterB.next();
            if (iterB.hasNext()) {
                iterB.next();
                iterB.remove();
            }
        }
        System.out.println(listB);

        // удалить из списка А все элементы списка B
        listA.removeAll(listB);
        System.out.println(listA);

        Collection<String> collectionA = new LinkedList<>();
        Queue<String> queueA = new LinkedList<>();

        Deque<String> dequeA = new ArrayDeque<>();


        collectionA.add("aaa");
        queueA.add(null);

        System.out.println(collectionA);
        System.out.println(queueA);
    }

}
