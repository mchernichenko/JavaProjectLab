package org.billing.jlab.threads.synchronizers;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkInJoinTest {

    static long numOfOperation =  10_000_000_000L;
    static int numOfThread = Runtime.getRuntime().availableProcessors(); // количество ядер

    public static void main(String[] args) {

        long sum = 0;
        System.out.println("Количество ядер: " + numOfThread);

        Instant startDate = Instant.now();

        ForkJoinPool forkJoinPool = new ForkJoinPool(numOfThread);
        sum = forkJoinPool.invoke(new MyFork(0, numOfOperation));

        Instant endDate = Instant.now();
        Duration duration = Duration.between(startDate, endDate);
        System.out.println("Результат " + sum);
        System.out.println("Длительность ForkInJoin операции: " + duration.toMillis() / 1000.00 + "сек");


        System.out.println("--- Однопоточный алгоритм складывания чисел ----- ");
        startDate = Instant.now();
        sum = Operatons.CalcSum(0, numOfOperation);
        endDate = Instant.now();
        duration = Duration.between(startDate, endDate);
        System.out.println("Результат " + sum);
        System.out.println("Длительность однопоточной операции: " + duration.toMillis() / 1000.00 + "сек");

    }

    static class Operatons {

        // однопоточный алгоритм складывания чисел
        static long CalcSum(long from, long to) {
          //  System.out.println("Складываем числа с " + from + " до " + to);
            long sum = 0;
            for (long i = from; i <= to; i++) {
                sum += i; //new Random().nextInt(100);
            }
            return sum;
        }
    }

    static class MyFork extends RecursiveTask<Long> {

        long from, to;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if ( (to - from) <= numOfOperation/numOfThread) { // блок вычисления операции
                return Operatons.CalcSum(from,to);
            } else { // блок разбиения на более мелкие части
                long middle = (to + from) / 2;
                MyFork firstHalf = new MyFork(from, middle);
                MyFork secondHalf = new MyFork((middle + 1), to);

                // 1-й вариант
               /* invokeAll(firstHalf, secondHalf);
                long result = secondHalf.join() + firstHalf.join();*/

                // 2-й вариант
                /*firstHalf.fork();
                secondHalf.fork();
                long result = secondHalf.join() + firstHalf.join();*/

                // 3-й вариант
                firstHalf.fork();
                long result = secondHalf.compute() + firstHalf.join();

                return result;
            }
        }
    }
}
