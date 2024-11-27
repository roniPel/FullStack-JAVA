package TaskSystem.example.TaskSystem_Spring.Annotations;

/**
 * Annotation for programmer and system details
 */
public @interface Programmer {
    String author();
    String revision();
    String connectionType();
}
