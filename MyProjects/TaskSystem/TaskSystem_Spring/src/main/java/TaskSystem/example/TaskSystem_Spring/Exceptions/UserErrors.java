package TaskSystem.example.TaskSystem_Spring.Exceptions;

import lombok.Getter;

@Getter
public enum UserErrors {
    GENERAL_CUSTOMER_ERROR("\nERROR! There was an error when trying to perform the user method. \n"),
    INCORRECT_LOGIN_DETAILS("\nERROR! Incorrect customer username and/or password. \n"),
    CUSTOMER_DOES_NOT_EXIST("\nERROR! User doesn't exist in the system. \n"),
    DUPLICATE_ENTRY("\nERROR! The value inserted (name/ id/ email/ user) already exists in the system. \n"),
    EMPTY_OR_NULL("\nERROR! The relevant table/field is empty or null. \n"),
    NO_PERMISSIONS("\nERROR! User has insufficient permissions for the requested action. \n"),

    // Task related actions
    TASK_EXISTS_FOR_USER("\nERROR! The task already exists for this user. \n"),
    TASK_DATE_EXPIRED("\nERROR! The task due date has expired. \n"),
    TASK_DOES_NOT_EXIST("\nERROR! Task doesn't exist in the system. \n"),

    // Project related actions
    PROJECT_EXISTS_FOR_USER("\nERROR! The project already exists for this user. \n"),
    PROJECT_DOES_NOT_EXIST("\nERROR! Project doesn't exist in the system. \n");

    private String message;

    UserErrors(String message) {
        this.message = message;
    }
}
