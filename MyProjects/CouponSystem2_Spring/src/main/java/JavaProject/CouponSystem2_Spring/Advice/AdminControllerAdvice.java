package JavaProject.CouponSystem2_Spring.Advice;

import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Admin Controller Advice Class - used to advise user regarding system errors
 */
@RestControllerAdvice
public class AdminControllerAdvice {
    /**
     * Method used to handle errors arriving from Admin Exceptions
     * @param exception exception received
     * @return Error details object containing Type of error and error message
     */
    @ExceptionHandler(value = {AdminException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails HandleError(Exception exception) {
        return new ErrorDetails("Admin Error",exception.getMessage());
    }

    /**
     * Method used to handle errors arriving from a Constraint Violation Exception or Method Argument Not Valid Exception
     * @param exception exception received
     * @return A map of errors containing field name, and error details
     */
    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> HandleConstraintViolationExceptions(Exception exception) {
        Map<String,String> errors = new HashMap<>();
        if (exception instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> errList = ((ConstraintViolationException) exception).getConstraintViolations();
            errList.forEach((error)->{
                String fieldName = ((FieldError)error).getField();
                String errorMessage = ((FieldError) error).getDefaultMessage();
                errors.put(fieldName,errorMessage);
            });
        }
        else if (exception instanceof MethodArgumentNotValidException) {
            List<ObjectError> errList = ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors();
            errList.forEach((error)->{
                String fieldName = ((FieldError)error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName,errorMessage);
            });
        }
        return errors;
    }

    //Todo - return the handler below?
    /*
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)-> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        System.out.println(e.getBindingResult().getAllErrors());
        return errors;
    }
     */

}
