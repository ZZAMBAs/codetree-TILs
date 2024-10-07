import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    static int m1, d1, m2, d2;
    static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] daysName = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    public static void main(String[] args) {
        m1 = sc.nextInt();
        d1 = sc.nextInt();
        m2 = sc.nextInt();
        d2 = sc.nextInt();

        int dayCursor = 1;
        int passedDay = 0;
        int w = 1;

        if (m2 < m1 || (m1 == m2 && d2 < d1)) {
            w *= -1;

            int temp = m1;
            m1 = m2;
            m2 = temp;

            temp = d1;
            d1 = d2;
            d2 = temp;
        }
        
        for (int i = m1 - 1; i < m2 - 1; i++) {
            passedDay += days[i];
        }
        passedDay += d2 - d1;
        passedDay *= w;
        dayCursor += passedDay;

        while (dayCursor < 0)
            dayCursor += daysName.length;

        System.out.print(daysName[dayCursor % daysName.length]);
    }
}