package com.company.collection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

class Person implements Comparable<Person> {
    public enum Sex {
        MALE, FEMAlE
    }

    private int id;
    private String name;
    private LocalDate birthdate;
    private Sex gender;
    private BigDecimal balance;

    public Person(int id, String name, LocalDate birthdate, Sex gender, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.balance = balance;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Sex getGender() {
        return gender;
    }

    public BigDecimal getBalance() { return balance; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(Person otherPerson) {
        return this.name.compareTo(otherPerson.getName());
    }
}

class PersonAgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        return person1.getBirthdate().compareTo(person2.getBirthdate());
    }
}

class PersonBalanceComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        return person1.getBalance().compareTo(person2.getBalance());
    }
}

public class Basic {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person(1, "Jason Lee", LocalDate.of(1990, 3, 27), Person.Sex.MALE, BigDecimal.valueOf(250)));
        persons.add(new Person(2, "John Black", LocalDate.of(1981, 7, 3), Person.Sex.MALE, BigDecimal.valueOf(29.87)));
        persons.add(new Person(3, "Alice Wonder", LocalDate.of(1971, 1, 16), Person.Sex.FEMAlE, BigDecimal.valueOf(2829.87)));
        persons.add(new Person(4, "Al Larry", LocalDate.of(1971, 10, 11), Person.Sex.MALE, BigDecimal.valueOf(829.12)));
        persons.add(new Person(5, "Kim Basinger", LocalDate.of(1967, 11, 30), Person.Sex.FEMAlE, BigDecimal.valueOf(58.21)));
        persons.add(new Person(6, "Lionel Messi", LocalDate.of(1985, 4, 13), Person.Sex.MALE, BigDecimal.valueOf(582469.21)));

        System.out.println("before sorting: " + Arrays.toString(persons.stream().map(x -> x.getName()).toArray()));

        // sort by using comparable
        Collections.sort(persons);
        System.out.println("after sorting: " + Arrays.toString(persons.stream().map(x -> x.getName()).toArray()));

        // sort by using Comparator
        Collections.sort(persons, new PersonAgeComparator());
        System.out.println("after sorting by age: " + Arrays.toString(persons.stream().map(x -> x.getName()).toArray()));

        Collections.sort(persons, new PersonBalanceComparator().reversed());
        System.out.println("after sorting by balance: " + Arrays.toString(persons.stream().map(x -> x.getName()).toArray()));

        // sort by Lambda for Comparator
        Comparator<Person> byAge = (p1, p2) -> p2.getBirthdate().compareTo(p1.getBirthdate());
        Collections.sort(persons, byAge);
        System.out.println("after sorting by age with lambda: " + Arrays.toString(persons.stream().map(x -> x.getName()).toArray()));

        // sort by Comparator.comparing method
        Collections.sort(persons, Comparator.comparing(Person::getGender).thenComparing(Person::getBalance));
        System.out.println("after sorting by gender with Comparator.comparing: " + Arrays.toString(persons.stream().map(x -> x.getName()).toArray()));
    }
}
