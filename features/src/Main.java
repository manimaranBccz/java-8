import java.util.*;
import java.util.stream.Collectors;

public class Main implements WelcomePerson{
    public static void main(String[] args) {
        WelcomeAll welcomeAll = ()-> System.out.println("Hello world!");
        welcomeAll.great();
        WelcomePerson welcomePerson = (name)-> System.out.println("Hello " + name); // lambda expression
        welcomePerson.greatUserByName("Manimaran"); // abstract method
        welcomePerson.sayHello(); // default method
        WelcomePerson.sayHelloWorld(); // static method
        new Main().greatUserByName("Manimaran");
        List<Person> persons = getPersons();
        // Streams API
        System.out.println(persons.stream().max(Comparator.comparing(Person::getSalary))); // Person with maximum salary
        System.out.println(persons.stream().mapToLong(Person::getSalary).average()); // average salary
        System.out.println(persons.stream().max(Comparator.comparing(Person::getAge))); // Person with maximum age
        System.out.println(persons.stream().sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList())); // persons sorted by name
        System.out.println(persons.stream().filter(p->p.getDepartment().equals("EEE")).collect(Collectors.toList())); // Persons from EEE department
        System.out.println(persons.stream().collect(Collectors.groupingBy(Person::getDepartment))); // groupBy department
        System.out.println( persons.stream().collect(Collectors.partitioningBy(person -> person.getSalary() > 50000L ))); // partionBy salary 50000
        System.out.println(persons.stream().map(Person::toString).collect(Collectors.toList()));// map to toString
        System.out.println(persons.stream().map(person -> new Person(person.getName(), person.getAge(),
                person.getDepartment(), (long) (person.getSalary() * 1.30), person.getAge()) ).collect(Collectors.toList()));// giving 30% increment to all
        System.out.println(persons.stream().mapToLong(Person::getSalary).reduce(0, Long::sum)); // sum of all salary
        System.out.println(persons.stream().mapToLong(Person::getSalary).reduce(0, Long::max)); // max of all salary
        System.out.println(persons.stream().mapToLong(Person::getSalary).reduce((s1, s2)-> s1*s2)); // multiplication of all salary
        System.out.println(persons.stream().collect(Collectors.groupingBy(Person::getDepartment)).keySet().stream().sorted((d1, d2)->{
            long dc1 = persons.parallelStream().filter(p->p.getDepartment().equals(d1)).count();
            long dc2 = persons.parallelStream().filter(p->p.getDepartment().equals(d2)).count();
                  return  dc1>dc2 ? 1:0 ;}).collect(Collectors.toList()));

        System.out.println(persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.counting())));
        System.out.println(persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.counting())).entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(Map.Entry::getKey).collect(Collectors.toList()));

        // sort given list based on it's occurrence using stream API
        List<Integer> list = Arrays.asList(2,4,2,5,5,2);
        System.out.println(list.stream().distinct() // getting distinct elements
                .map(e-> Arrays.asList(e, (int) list.parallelStream().filter(el->el.equals(e)).count())) // creating list of following [element, noOfOccurances]
                .sorted(Collections.reverseOrder(Comparator.comparing(m -> m.get(1)))) // Sorting the list based on occurrence
                .map(e->e.get(0)).collect(Collectors.toList())); // collecting the output with elements
    }

    private static List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Anbukarasi",25,"EEE",60000L,8));
        persons.add(new Person("Barani",27,"ECE",70000L,3));
        persons.add(new Person("AnbuSelvan",28,"EEE",40000L,5));
        persons.add(new Person("Manimaran",26,"EEE",80000L,1));
        persons.add(new Person("Manikandan",27,"ECE",50000L,2));
        persons.add(new Person("Hari",27,"MECH",30000L,4));
        persons.add(new Person("Vignesh",26,"CSE",100000L,6));
        return persons;
    }

    @Override
    public void greatUserByName(String name) {
        System.out.println("Hello " + name);
    }
}
