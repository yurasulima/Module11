package org.example;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {

        ArrayList<String> myArrayList = new ArrayList<>();
        myArrayList.add("ivan");
        myArrayList.add("oleg");
        myArrayList.add("olga");
        myArrayList.add("max");
        myArrayList.add("evgeniy");
        System.out.println("myArrayList = " + getNames(myArrayList));


        System.out.println("sort: " + sortAndUpperCase(myArrayList));


        String[] numbers = {"1, 2, 0", "4, 5"};
        System.out.println("numbers: " + sortNumbers(numbers));


        Stream<Long> randomStream = linearCongruentialGenerator(25214903917L, 11L, (long) Math.pow(2, 48), 1L);
        randomStream.limit(2).forEach(System.out::println);


        Stream<Integer> firstStream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> secondStream = Stream.of(6, 7, 8, 9, 10);
        Stream<Integer> zippedStream = zip(firstStream, secondStream);
        zippedStream.forEach(System.out::println);

    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        Stream.Builder<T> builder = Stream.builder();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            builder.accept(iterator1.next());
            builder.accept(iterator2.next());
        }

        return builder.build();
    }


    public static Stream<Long> linearCongruentialGenerator(long a, long c, long m, long seed) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }
    public static String sortNumbers(String[] array) {
        List<Integer> numbers = new ArrayList<>();

        for (String str : array) {
            String[] parts = str.split(", ");
            for (String part : parts) {
                try {
                    int num = Integer.parseInt(part.trim());
                    numbers.add(num);
                } catch (NumberFormatException ignored) {
                }
            }
        }

        Collections.sort(numbers);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            result.append(numbers.get(i));
            if (i < numbers.size() - 1) {
                result.append(", ");
            }
        }

        return result.toString();
    }
    public static ArrayList<String> sortAndUpperCase(ArrayList<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, strings.get(i).toUpperCase());
        }
        strings.sort(Collections.reverseOrder());
        return strings;
    }


    public static String getNames(ArrayList<String> names) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            if (i % 2 == 0) {
                result.append((i + 1)).append(". ").append(names.get(i)).append(", ");
            }
        }
        if (!result.isEmpty()) {
            result.delete(result.length() - 2, result.length()); // видаляємо останню кому та пробіл
        }
        return result.toString();
    }
}
