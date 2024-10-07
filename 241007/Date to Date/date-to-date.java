import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        int[] inputs = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int res = 1;
        for (int i = inputs[0] - 1; i < inputs[2] - 1; i++)
            res += days[i];
        res += inputs[3] - inputs[1];

        System.out.println(res);
    }
}