package java17;

import java.time.LocalDate;
import java.time.LocalTime;

public record Message(String content,
                      LocalDate date,
                      LocalTime time) {

    public Message(String content){
        this(content,LocalDate.now(),LocalTime.now());
    }
    

}
