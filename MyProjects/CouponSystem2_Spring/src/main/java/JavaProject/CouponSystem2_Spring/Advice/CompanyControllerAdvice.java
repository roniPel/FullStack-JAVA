package JavaProject.CouponSystem2_Spring.Advice;

import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

/**
 * Company Controller Advice Class - used to advise user regarding system errors
 */
@RestControllerAdvice
public class CompanyControllerAdvice {
    /**
     * Method used to handle errors arriving from Company Exceptions
     * @param exception exception received
     * @return Error details object containing Type of error and error message
     */
    @ExceptionHandler(value = {CompanyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails HandleError(Exception exception) {
        return new ErrorDetails("Company Error",exception.getMessage());
    }

    /**
     * Method used to handle errors arriving from a Constraint Violation Exception or Method Argument Not Valid Exception
     * @param exception exception received
     * @return A map of errors containing field name, and error details
     */
    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> HandleConstraintViolationExceptions(ConstraintViolationException exception) {
        Map<String,String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = ((FieldError) error).getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }
}
