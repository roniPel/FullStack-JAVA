package ExceptionsExercise;

public class InvalidEmployeeSalaryException extends RuntimeException{
    public InvalidEmployeeSalaryException(String message) {
        super(message);
    }
}
