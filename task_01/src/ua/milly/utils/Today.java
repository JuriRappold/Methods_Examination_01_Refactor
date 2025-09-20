package ua.milly.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Today {
    public static void print(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yy");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("It's " + date.format(now) + " and time is " + time.format(now) + "\n");
    }
}
