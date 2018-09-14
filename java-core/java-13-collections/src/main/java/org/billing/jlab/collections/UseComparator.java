package org.billing.jlab.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class UseComparator {

    public static void main(String[] args) {

        List<String> listA = new ArrayList<>();
        listA.add("aaa2");
        listA.add("aaa1");

     //   Collections.binarySearch().sort(listA);
        System.out.println(listA);

        //Consumer<List<String>> lambda1 = l -> Collections.sort(l);
        System.out.println(Math.ceil(16/3.0));


    }
}

class ChainingComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {

      //  Comparator<Employee> c = Comparator.comparing();

        double d = o1.getSalary() - o2.getSalary();
        return d > 0 ? 1 : d < 0 ? -1 : 0;
    }
}