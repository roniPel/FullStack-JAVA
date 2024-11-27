package TaskSystem.example.TaskSystem_Spring.Exceptions;

public class UserExceptions extends Exception{
    public UserExceptions(UserErrors userErrors){
        super(userErrors.getMessage());
    }

}
