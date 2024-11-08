package javaa;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of terms: ");
        int n = in.nextInt();

        int num1 = 0, num2 = 1;
        System.out.print("Fibonacci Sequence: " + num1 + ", " + num2);

        for (int i = 2; i < n; i++) {
            int nextTerm = num1 + num2;
            System.out.print(", " + nextTerm);
            num1 = num2;
            num2 = nextTerm;
        }
        in.close();
    }
}
