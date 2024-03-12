package ClassExercise_210224.catLab.Utils;

import ClassExercise_210224.catLab.Beans.Cat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatUtilities {
    public int GetRandomIdFromList(List<Cat> myList) {
        int randIdx = (int)(Math.random()*(myList.size()));
        if(myList.size() == 1){
            randIdx = 0;
        }
        return myList.get(randIdx).getId();
    }

    public int GetrandInt(int range) {
        return (int)(Math.random()*(range));
    }
}
