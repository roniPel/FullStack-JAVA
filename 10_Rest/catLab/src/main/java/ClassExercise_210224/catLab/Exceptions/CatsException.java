package ClassExercise_210224.catLab.Exceptions;

public class CatsException extends Exception{
    public CatsException(Errors errors) {
        super(errors.getMessage());
    }
}
