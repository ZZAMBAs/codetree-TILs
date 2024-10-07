import java.util.*;
import java.time.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final int YEAR = 2011;

    static int m1, d1, m2, d2;
    static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] daysName = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    public static void main(String[] args) {
        m1 = sc.nextInt();
        d1 = sc.nextInt();
        m2 = sc.nextInt();
        d2 = sc.nextInt();

        int dayCursor = 1 + LocalDate.of(YEAR, m2, d2).getDayOfYear() - LocalDate.of(YEAR, m1, d1).getDayOfYear();

        while (dayCursor < 0)
            dayCursor += daysName.length;

        System.out.print(daysName[dayCursor % daysName.length]);
    }
}