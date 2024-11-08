package javaa;

import java.util.Scanner;

public class Calculator {

    double add(double num1, double num2) {
        return num1 + num2;
    }

    double subtract(double num1, double num2) {
        return num1 - num2;
    }

    double multiply(double num1, double num2) {
        return num1 * num2;
    }

    double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error! Division by zero.");
            return 0;
        }
        return num1 / num2;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner in = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double num1 = in.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = in.nextDouble();

        double a = calculator.add(num1, num2);
        double s = calculator.subtract(num1, num2);
        double m = calculator.multiply(num1, num2);
        double d = calculator.divide(num1, num2);

        System.out.println("Addition: " + a);
        System.out.println("Subtraction: " + s);
        System.out.println("Multiplication: " + m);
        System.out.println("Division: " + d);

        in.close();
    }
}
