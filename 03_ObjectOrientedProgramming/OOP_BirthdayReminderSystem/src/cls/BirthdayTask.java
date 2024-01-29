package cls;

import DateFunctions.DateFactory;
import DateFunctions.DateUtil;

import java.time.LocalDate;
import java.util.Set;

public class BirthdayTask implements Runnable {
    // Create a set of people
    private final Set<Person> people;
    private final Integer sleepTime = 1000*60*60*24;  //24 hours
    private Boolean isRunning = true;

    public BirthdayTask(Set<Person> people) {
        this.people = people;
    }
    public void setRunning(Boolean running) {
        isRunning = running;
    }

    // User Methods
    @Override
    public void run() {
        while(isRunning) {
            for (Person person: people) {
                if (isBirthMonth(person) && isBirthDay(person)) {
                    printHappyBday(person);
                }
            }
            // System.out.println("Thread was run");  //For testing
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean isBirthMonth(Person person) {
        return person.getBirthday().getMonth() == LocalDate.now().getMonth() ;
    }
    private boolean isBirthDay(Person person) {
        return person.getBirthday().getDayOfMonth() == LocalDate.now().getDayOfMonth() ;
    }

    private void printHappyBday(Person person) {
        System.out.println("Happy birthday to "+person.getName()+"!!! - " +
                "Birthday: "+ DateUtil.beautifyDate( person.getBirthday() )
        );
    }
}
