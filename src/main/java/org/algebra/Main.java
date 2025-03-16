package org.algebra;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=======================");
            System.out.println("Input a prime number p to build GF(p^2) tables, or press q to exit:");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                break;
            }
            try {
                int p = Integer.parseInt(input);
                if (!isPrime(p)) {
                    System.out.println("Input must be a prime number.");
                    continue;
                }
                GFp2Tables field = new GFp2Tables(p);
                field.printElements();
                field.printAdditionTable();
                field.printMultiplicationTable();
            } catch (NumberFormatException e) {
                System.out.println("Input must be an integer or 'q' to exit.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
