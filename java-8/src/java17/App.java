package java17;

import java.util.Scanner;

public class App {
    static String findDayName(int day){
    return switch (day){
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            default -> throw new IllegalArgumentException("Invalid date");
        };
    }
    static void main() {

        int day = 2;

        String dayName = switch (day){
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            default -> throw new IllegalArgumentException("Invalid date");
        };
        System.out.println(dayName);

//        switch (day) {
//            case 1:
//                dayName = "Monday";
//                break;
//            case 2:
//                dayName = "Tuesday";
//                break;
//
//            default:
//                throw new IllegalArgumentException("Invalid date");
//        }




    }
}
