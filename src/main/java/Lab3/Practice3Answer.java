package Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Practice3Answer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option = 100;

        while (option != 0) {

            // Get filtering criteria from user
            System.out.println("Please input the function No:");
            System.out.println("1 - Get even numbers");
            System.out.println("2 - Get odd numbers");
            System.out.println("3 - Get prime numbers");
            System.out.println("4 - Get prime numbers that are bigger than 5");
            System.out.println("0 - Quit");
            option = scanner.nextInt();

            Predicate<Integer> predicate;
            switch (option) {
                case 1:
                    predicate = n -> n % 2 == 0;
                    break;
                case 2:
                    predicate = n -> n % 2 != 0;
                    break;
                case 3:
                    predicate = Practice3Answer::isPrime;
                    break;
                case 4:
                    predicate = n -> n > 5 && isPrime(n);
                    break;
                default:
//                    System.out.println("Invalid option.");
                    return;
            }

            // Input array size and elements
            System.out.print("Input size of the list:");
            int size = scanner.nextInt();

            int[] arr = new int[size];
            System.out.print("Input elements of the list:");
            for (int i = 0; i < size; i++) {
                arr[i] = scanner.nextInt();
            }

            // Filter the array
            List<Integer> result = new ArrayList<>();
            for (int n : arr) {
                if (predicate.test(n)) {
                    result.add(n);
                }
            }

            // Output the filtered array
            System.out.println("Filter results:");
//        for (int n : result) {
//            System.out.print(n + " ");
//        }
            System.out.println(result);
        }
    }

    // Helper method to check if a number is prime
    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
