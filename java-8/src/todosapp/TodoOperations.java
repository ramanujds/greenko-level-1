package todosapp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TodoOperations {

    static void main() {

        List<Todo> todos = new ArrayList<>();

        todos.add(new Todo("1", "Complete project", "ramanujds",
                LocalDate.of(2025, 3, 15), 1, false));
        todos.add(new Todo("2", "Review code", "harsh",
                LocalDate.of(2025, 3, 10), 2, false));
        todos.add(new Todo("3", "Update documentation", "ramanujds",
                LocalDate.of(2025, 3, 20), 3, true));
        todos.add(new Todo("4", "Fix bugs", "harsh",
                LocalDate.of(2025, 3, 12), 1, false));

        // Group Tasks by Status

        var todoGroup = todos.stream()
                .collect(Collectors.groupingBy(Todo::priority));

//        for (var key : todoGroup.keySet()) {
//            System.out.println(key);
//            for (Todo t : todoGroup.get(key)) {
//                System.out.println(t);
//            }
//        }

        // true -> List<Todo>
        // false -> List<Todo>

//        Map<Boolean, List<Todo>> todoGroup = new HashMap<>();
//
//        for (Todo t:todos){
//            if (todoGroup.containsKey(t.completed())){
//                todoGroup.get(t.completed()).add(t);
//            }
//            else{
//                List<Todo> list = new ArrayList<>();
//                list.add(t);
//                todoGroup.put(t.completed(), list);
//            }
//        }
//

        // Sort Tasks by Priority and Due Date

        var sortedTodos = todos.stream().sorted(Comparator.comparing(Todo::priority)
                .thenComparing(Todo::dueDate));


        var todoMap = todos.stream().collect(Collectors.toMap(Todo::title,Todo::dueDate,(a, b) -> a,
                TreeMap::new));

        todoMap.forEach((t,d)-> System.out.println(t+"  :  "+d));
    }

}
