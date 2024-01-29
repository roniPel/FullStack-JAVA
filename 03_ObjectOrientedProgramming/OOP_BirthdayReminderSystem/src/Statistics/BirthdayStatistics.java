package Statistics;

import cls.BirthdaySystemReminder;
import cls.Person;

import java.util.*;

public class BirthdayStatistics {
    public static int countMembers(Set<Person> people) {
        return people.size();
    }

    public static int getHappyMonth(Set<Person> people) {
        int[] countBirtArr = birthdaysInEachMonth(people);
        //Find maximum value in array (happy month = max # of birthdays)
        int maxIdx = 0;
        for (int i = 1; i < countBirtArr.length; i++) {
            if(countBirtArr[i] > countBirtArr[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx+1;
    }

    public static int getSadMonth(Set<Person> people) {
        int[] countBirtArr = birthdaysInEachMonth(people);
        //Find minimum value in array (sad month = min # of birthdays)
        int minIdx = 0;
        for (int i = 1; i < countBirtArr.length; i++) {
            if(countBirtArr[i] < countBirtArr[minIdx]) {
                minIdx = i;
            }
        }
        return minIdx+1;
    }

    public static Map<Integer,Integer> getEvents(Set<Person> people) {
        Map<Integer,Integer> eventsMap = new HashMap<>();
        int[] countBirtArr = birthdaysInEachMonth(people);
        for (int i = 0; i < countBirtArr.length; i++) {
            eventsMap.put(i+1,countBirtArr[i]);
        }
        return eventsMap;
    }

    private static int[] birthdaysInEachMonth(Set<Person> people) {
        int[] countBirthdays = new int[12];
        for(Person person: people) {
            int birthMonth = person.getBirthday().getMonthValue();
            countBirthdays[birthMonth-1]++;
        }
        return countBirthdays;
    }

    public static Map<Integer, Set<Person> > getPeopleInEachMonth(Set<Person> people) {
        Map<Integer, Set<Person> > peopleMap = new HashMap<>();
        int[] countBirthdays = birthdaysInEachMonth(people);
        for (int i = 1; i <= countBirthdays.length; i++) {
            // Add map keys = months, initialize 'Person' set for each month
            peopleMap.put(i,new HashSet<>());
            // Fill in people set for each month
            for(Person person: people) {
                int birthMonth = person.getBirthday().getMonthValue();
                if(birthMonth == i) {
                    Set<Person> peopleInMonth = peopleMap.get(i);
                    peopleInMonth.add(person);
                }
            }
        }
        return peopleMap;
    }


}
