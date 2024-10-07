import java.util.*;
import java.time.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int m1, d1, m2, d2;
    static Map<String, Integer> dayToIdx = new HashMap<>();

    public static void main(String[] args) {
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int idx = 0;
        for (String day : days)
            dayToIdx.put(day, idx++);

        idx = 0;
        m1 = sc.nextInt();
        d1 = sc.nextInt();
        m2 = sc.nextInt();
        d2 = sc.nextInt();
        String A = sc.next();

        int daysGone = LocalDate.of(2024, m2, d2).getDayOfYear() - LocalDate.of(2024, m1, d1).getDayOfYear();
        int AToIdx = dayToIdx.get(A);
        int res = daysGone / days.length + (daysGone % days.length >= AToIdx ? 1 : 0);

        System.out.print(res);
    }
}