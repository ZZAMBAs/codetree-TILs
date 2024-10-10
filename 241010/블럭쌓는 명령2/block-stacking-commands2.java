import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int N = sc.nextInt(), K = sc.nextInt();
        int[] block = new int[N + 1];
        while (K-- > 0) {
            int A = sc.nextInt(), B = sc.nextInt();

            for (int i = A; i <= B; i++)
                block[i]++;
            
        }

        System.out.print(Arrays.stream(block).max().getAsInt());
    }
}