package cls;
import java.time.Month;
import java.util.*;

import static Statistics.BirthdayStatistics.*;

public class BirthdaySystemReminder {
    // Fields
    private Set<Person> people;
    private static Scanner scanner = new Scanner(System.in);
    private BirthdayTask task;
    // Constructors

    public BirthdaySystemReminder() {
        this.people = new HashSet<>();
        task = new BirthdayTask(this.people);
        Thread thread = new Thread(task);
        thread.start();
        startSystem();
    }

    // User Methods
    public void startSystem() {
        System.out.println("------- The System is now ON! --------");
        System.out.println();
        int choice;
        while(true) {
            showMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addPerson(new Person());
                    break;
                case 2:
                    deletePerson();
                    break;
                case 3:
                    printAll();
                    break;
                case 4:
                    printCount();
                    break;
                case 5:
                    printHappyMonth();
                    break;
                case 6:
                    printSadMonth();
                    break;
                case 7:
                    printEvents();
                    break;
                case 0:
                    endSystem();
                    break;
                default:
                    System.out.println("Not an option");
            }
        }
    }
    public void showMenu() {
        System.out.println();
        System.out.println("*** Birthday System Menu ***\n" +
                "=============================\n" +
                "Please select an option: \n" +
                "1. Add person\n" +
                "2. Delete person\n" +
                "3. Print all people\n" +
                "4. Print size of friend list\n" +
                "5. Print happy month\n" +
                "6. Print sad month\n" +
                "7. Print event list\n" +
                "\n" +
                "0. Exit\n"+
                "> "
        );
    }

    public void endSystem() {
        scanner.close();
        task.setRunning(false);
        System.out.println("****** END ******\n"+
                           "***** END *****\n" +
                           "**** END ****\n"+
                           "*** END ***\n"+
                           "** END **\n"+
                           "* END *\n"+
                           " END\n"
        );
        System.out.println("That's all folks!!!");
        // Exit main thread
        System.exit(0);
    }

    public void addPerson(Person person) {
        if(!people.add(person)) {
            System.out.println("Person"+person.getId()+" already exists.");
        }
        else {
            System.out.println("Person"+person.getId()+" was added successfully!");
        }
    }

    public void deletePerson() {
        System.out.println("Select which ID to delete");
        System.out.println("> ");
        int chosenID = scanner.nextInt();
        Person choice = null;
        for (Person person: people) {
            if(people.isEmpty()) {
                System.out.println("No people in system");
                return;
            }
            else if(person.getId() == chosenID) {
                choice = person;
                people.remove(choice);
                System.out.println("Person"+chosenID+" was removed successfully.");
                return;
            }
        }
        System.out.println("There is no person with id "+chosenID+" in the system");
    }

    public void printAll() {
        // Check if empty
        if(this.people.size() == 0) {
            System.out.println("There are no people in the system");
            return;
        }
        // Convert to list - in order to sort
        List<Person> arrayPeople = new ArrayList<>(this.people);
        Collections.sort(arrayPeople);
        System.out.println(arrayPeople);
    }

    public void printStatistic() {
        printCount();
        printHappyMonth();
        printSadMonth();
        printEvents();
    }

    public void printCount() {
        System.out.println("Size of friend list is: "+ countMembers(this.people) );
    }

    public void printHappyMonth() {
        System.out.println("The HAPPY month is: *"+getHappyMonth(this.people)+"*");
    }
    public void printSadMonth() {
        System.out.println("The SAD month is: *"+getSadMonth(this.people)+"*");
    }
    public void printEvents() {
        printMapBirthdays( getEvents(this.people) );
        printMapPeople( getPeopleInEachMonth(this.people) );
    }

    private static void printMapBirthdays(Map<Integer,Integer> eventsMap) {
        for (Integer key :eventsMap.keySet()) {
            System.out.println(Month.of(key) + " --> Birthdays: " + eventsMap.get(key));
        }
    }

    private static void printMapPeople(Map<Integer, Set<Person> > peopleMap) {
        for (Integer key :peopleMap.keySet()) {
            System.out.println("*****   "+ Month.of(key) + "    *****");
            printPeopleSet(peopleMap.get(key));
            System.out.println();
        }
    }
    private static void printPeopleSet(Set<Person> peopleSet) {
        if(peopleSet.isEmpty()){
            return;
        }
        System.out.println(peopleSet);
    }

}