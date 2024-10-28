package ru.academits.ignatov.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Андрей", 23),
                new Person("Евгений", 21),
                new Person("Евгений", 19),
                new Person("Владимир", 72),
                new Person("Александр", 17),
                new Person("Екатерина", 44),
                new Person("Екатерина", 19)
        );

        List<String> uniqueNamesList = people.stream()
                .map(Person::name)
                .distinct()
                .sorted()
                .toList();

        String uniqueNamesString = uniqueNamesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesString);

        List<Person> underagePeople = people.stream()
                .filter(p -> p.age() < 18)
                .toList();

        OptionalDouble underagePeopleAverageAge = underagePeople.stream()
                .mapToInt(Person::age)
                .average();

        if (underagePeopleAverageAge.isPresent()) {
            System.out.println("Список несовершеннолетних: " + underagePeople + ", их средний возраст = " + underagePeopleAverageAge.getAsDouble());
        } else {
            System.out.println("Нет несовершеннолетних в списке.");
        }

        Map<String, Double> averageAgesByNamesMap = people.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingInt(Person::age)));

        System.out.println("Ключи - имена, значения - средний возраст: " + averageAgesByNamesMap);

        List<String> peopleNamesFrom20To45 = people.stream()
                .filter(p -> p.age() >= 20 && p.age() <= 45)
                .sorted(Comparator.comparingInt(Person::age).reversed())
                .map(Person::name)
                .toList();

        System.out.println("Список людей в возрасте от 20 до 45, их имена в порядке убывания: " + peopleNamesFrom20To45);
    }
}