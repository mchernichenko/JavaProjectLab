package org.billing.jlab.obj;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        Employer[] staff = new Employer[3];
        staff[0] = new Employer("Имя работничка", 15000, "15.12.1987");
        staff[1] = new Employer("Имя работничка_1", 15000, "15.12.1997");
        staff[2] = new Employer();;

        Employer employer = new Employer("Имя работничка", 75000, "15.12.1987");
        System.out.println("Hello World!");

        System.out.println(staff[2].getFinalName());
        System.out.println(staff[2].getId());

        Stream.of("monkey", "ape", "bonobo").min((s1, s2) -> s1.length()-s2.length()).ifPresent(System.out::println);
        Stream.Builder<Integer> streamBuider = Stream.builder();
        streamBuider.add(9).add(10).build().forEach(System.out::println); // 9,10

       /* Comparator<Employer> comp = Comparator.comparing(v -> v.getName());
        List<Employer> list = Arrays.asList(staff);
        Collections.sort(list, comp);*/

        Stream.iterate(1, x -> x + 1).limit(5).peek(System.out::print).filter(x -> x % 2 == 1).forEach(System.out::print);

        IntStream ints = IntStream.of(1, 2, 3, 4, 5);
        IntSummaryStatistics stats = ints.summaryStatistics();
        System.out.println("aa=" + stats.getAverage());

        System.out.println(Collectors.toSet().characteristics());
        Stream<String> stream = Stream.of("w", "o", "l", "f").parallel();
        Set<String> set = stream.collect(Collectors.toSet());
        System.out.println(set); // [f, w, l, o]

    }
}
class ExceptionCaseStudy {
    static List<String> create() throws IOException {  throw new IOException(); } }