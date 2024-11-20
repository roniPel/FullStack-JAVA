package com.JohnBryce.Exam130324.Utilities;

import com.JohnBryce.Exam130324.Beans.Grade;
import com.JohnBryce.Exam130324.Beans.Student;
import com.JohnBryce.Exam130324.Beans.Topic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradesFactory {
    public List<Grade> CreateGradesList(Long counter, int numGrades) {
        List<Grade> grades = new ArrayList<>();
        for (int j = 0; j < numGrades+1; j++) {
            Topic topic = Topic.GetRandomTopic();
            int score = (int) (Math.random() * 30)+70;
            Grade grade = Grade.builder()
                    .id(counter++)
                    .topic(topic)
                    .score(score)
                    .build();
            grades.add(grade);
        }
        return grades;
    }

    public Long GetRandIdFromList(List<Student> myList) {
        int randIdx = (int)(Math.random()*(myList.size()));
        if(myList.size() == 1){
            randIdx = 0;
        }
        return (myList.get(randIdx)).getId();
    }
}
