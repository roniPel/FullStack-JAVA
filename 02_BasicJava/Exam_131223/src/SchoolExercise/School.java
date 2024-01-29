package SchoolExercise;

import java.util.Arrays;

public class School {
    public School(ClassRoom[] classRoom) {
        this.classRoom = classRoom;
    }

    private ClassRoom[] classRoom;

    public ClassRoom[] getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom[] classRoom) {
        if(classRoom.length == 5)
            this.classRoom = classRoom;
        else
            System.out.println("The inserted classRoom array doesn't match the system requirements: should contain 5 classrooms");
    }

    @Override
    public String toString() {
        return " ------- ClassRooms in School -------\n"+PrintClassRooms();
    }
    public String PrintClassRooms() {
        String classRoomPrint = "";
        for (int i = 0; i < getClassRoom().length; i++) {
            classRoomPrint += getClassRoom()[i].toString();
        }
        return classRoomPrint;
    }
}
