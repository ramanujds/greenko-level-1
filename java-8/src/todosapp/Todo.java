package todosapp;


import java.time.LocalDate;

public record Todo(String id,
                   String title,
                   String user,
                   LocalDate dueDate,
                   int priority,
                   boolean completed) {
}
