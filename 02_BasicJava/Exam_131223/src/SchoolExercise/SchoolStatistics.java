package SchoolExercise;
public class SchoolStatistics {

    public static void main(String[] args) {
        // Initializing all classes in the exercise
        String[] teacherNamesArr = {"Nachum", "Aviva", "Matan", "Arbel", "Barak"};
        String[] classRoomNamesArr = {"Flower","Ocean","Cloud","Tree","Breeze"};
        School school = new School(InitializeClassRoom(5,classRoomNamesArr,teacherNamesArr));

        // Question 4 - Print out school details
        System.out.println("!!! Question 4 - school details !!!\n");
        for (ClassRoom cr: school.getClassRoom()) {
            System.out.println("-- ClassRoom: "+cr.getName()+" --\n");
            System.out.println("*** Teacher ***\n"+cr.getTeacher());
            System.out.println("* Students *");
            for (int i = 0; i < cr.getStudents().length; i++) {
                System.out.print("Student "+(i+1)+"- ");
                System.out.print((cr.getStudents()[i]));
            }
            System.out.println();
        }

        // Question 5 - School statistics
        System.out.println("!!! Question 5 - School Statistics !!!");
        int countTotalGrades=0,sumTotalGrades = 0;
        int sumClassAverage;
        int[] subjectGradeSum = new int[Profession.values().length];
        int[] subjectGradeCount = new int[Profession.values().length];
        for (ClassRoom cr: school.getClassRoom()) {
            sumClassAverage = 0;
            Profession pro;
            for (Student s: cr.getStudents()) {
                for (Grade g: s.getGrades()) {
                    sumClassAverage += g.getScore();
                    sumTotalGrades += g.getScore();
                    countTotalGrades++;
                    switch(g.getProfession()) {
                        case math:
                            subjectGradeSum[0] += g.getScore();
                            subjectGradeCount[0]++;
                            break;
                        case chemistry:
                            subjectGradeSum[1] += g.getScore();
                            subjectGradeCount[1]++;
                            break;
                        case geography:
                            subjectGradeSum[2] += g.getScore();
                            subjectGradeCount[2]++;
                            break;
                        case literature:
                            subjectGradeSum[3] += g.getScore();
                            subjectGradeCount[3]++;
                            break;
                        case physics:
                            subjectGradeSum[4] += g.getScore();
                            subjectGradeCount[4]++;
                            break;
                        case sports:
                            subjectGradeSum[5] += g.getScore();
                            subjectGradeCount[5]++;
                            break;
                    }
                }
            }
            System.out.println("The average grade for class "+cr.getName()+" is: "+(sumClassAverage/(15*6)));
        }
        System.out.println();
        for (int i = 0; i < Profession.values().length; i++) {
            System.out.println("The average school grade for profession "+Profession.values()[i]+" is: "+subjectGradeSum[i]/subjectGradeCount[i]);
        }
        System.out.println();
        System.out.println("The total school grade average is: "+(sumTotalGrades/countTotalGrades));
        System.out.println();

        // Question 6 - Bonus
        System.out.println("!!! Question 6 - Bonus !!!");
        int sumCountStudents1=0, numCountStudents1=0;
        int sumCountStudents2=0, numCountStudents2=0;
        int sumCountStudents3=0, numCountStudents3=0;
        Student[] sportsTeam = new Student[6*15];
        int countSportsTeam = 0;
        for (ClassRoom cr: school.getClassRoom()) {
            for (Student s : cr.getStudents()) {
                sumCountStudents3 += s.getAge();
                numCountStudents3++;
                for (Grade g : s.getGrades()) {
                    if((s.getAge() <= 30) && (s.getAge() >= 20)) {
                        sumCountStudents1 += g.getScore();
                        numCountStudents1++;
                    }
                    else if (s.getAge() >= 31) {
                        sumCountStudents2 += g.getScore();
                        numCountStudents2++;
                    }
                    Profession pro = g.getProfession();
                    if( ( (pro.toString() =="sports") && (g.getScore() >= 90) ) ) {
                        sportsTeam[countSportsTeam] = s;
                        countSportsTeam++;
                    }
                }
            }
        }
        System.out.println("1. The average school grade for students between ages 20 and 30 is: "+(sumCountStudents1/numCountStudents1));
        System.out.println("2. The average school grade for students older than 31 is: "+(sumCountStudents1/numCountStudents1));
        System.out.println("3. The average age for all students is: "+(sumCountStudents3/numCountStudents3));
        System.out.println("6. Please find the school's sports team below (total "+countSportsTeam+" students): ");
        for (Student s: sportsTeam) {
            if( s != null)
                System.out.print(s);
            else
                break;
        }
    }
    public static ClassRoom[] InitializeClassRoom(int size, String[] classRoomnames, String[] teacherNames) {
        ClassRoom[] crArr = new ClassRoom[size];
        Teacher[] teachersArr = InitializeTeachersArray(5,teacherNames);
        for (int i = 0; i < size; i++) {
            crArr[i] = new ClassRoom(classRoomnames[i],teachersArr[i],InitializeStudentsArray(15));
        }
        return crArr;
    }
    public static Teacher[] InitializeTeachersArray(int size,String[] teacherNames) {
        Teacher[] tArr = new Teacher[size];
        for (int i = 0; i < size; i++) {
            tArr[i] = new Teacher(teacherNames[i],GetRandomAge(),Profession.values()[i]);
        }
        return tArr;
    }
    public static int GetRandomAge() {
        int minAge = 19, maxAge = 121;
        int ageRange = maxAge-minAge;
        return (int)(Math.random()*ageRange)+minAge;
    }

    public static Student[] InitializeStudentsArray(int size) {
        Student[] sArr = new Student[size];
        int choice;
        for (int i = 0; i < size; i++) {
            choice = (int)(Math.random()*3);
            switch (choice) {
                case 0:
                    sArr[i] = new Student("Maya",GetRandomAge(),InitializeGrades(6));
                    break;
                case 1:
                    sArr[i] = new Student("Omer",GetRandomAge(),InitializeGrades(6));
                    break;
                case 2:
                    sArr[i] = new Student("Noga", GetRandomAge(),InitializeGrades(6));
                    break;
            }

        }
        return sArr;
    }
    public static Grade[] InitializeGrades(int size) {
        Grade[] gradesArr = new Grade[size];
        int max = 101, min = 39;
        int range = max-min;
        int grade;
        for (int i = 0; i < size; i++) {
            grade = (int) (Math.random()*range)+min;
            gradesArr[i] = new Grade(Profession.values()[i],grade);
        }
        return gradesArr;
    }
}
