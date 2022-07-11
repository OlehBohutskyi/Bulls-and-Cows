package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    final static String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int n = 0;
        try {
            n = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: number required");
            System.exit(0);
        }
        if (n <= 0 || n > 36) {
            System.out.println("Error: 0 < length >= 36.");
            System.exit(0);

        }
        System.out.println("Input the number of possible symbols in the code:");
        int sym = scanner.nextInt();
        if (sym <= 0 || sym > 36) {
            System.out.println("Error: 0 < unique symbols >= 36.");
            System.exit(0);
        }

        if (n > sym) {
            System.out.println("Error: n <= sym.");
            System.exit(0);
        }
        String preCode = generateCodeRandom(n, sym);
        System.out.println("Okay, let's start a game!");


        boolean check;
        int turn = 1;
        do {
            System.out.printf("Turn %d:\n", turn);
            String code = scanner.next();
            for (int i = 0; i < code.length(); i++) {
                if (!symbols.contains(String.valueOf(code.charAt(i)))){
                    System.out.println("Error: isn't a valid input.");
                    System.exit(0);
                }
            }
            if (code.length() != n) {
                System.out.println("Error: isn't a valid input.");
                System.exit(0);
            }
            check = grader(code, preCode);
            turn++;
        } while (!check);

        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static String generateCodeRandom(int n, int sym) {
        Random random = new Random();
        sym--;
        StringBuilder code = new StringBuilder();



        System.out.print("The secret is prepared: ");
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }

        if (sym <= 9) {
            System.out.printf(" (0-%c).\n", symbols.charAt(sym));
        } else {
            System.out.printf(" (0-9, a-%c).\n", symbols.charAt(sym));
        }

        while (code.length() != n) {

            int rand = random.nextInt(sym);
            if (!code.toString().contains(String.valueOf(symbols.charAt(rand)))){
                code.append(symbols.charAt(rand));
            }

        }
        return code.toString();
    }

    public static boolean grader(String code, String preCode) {
        int cows = 0;
        int buls = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == preCode.charAt(i)) {
                buls++;
            } else if (preCode.contains(String.valueOf(code.charAt(i)))) {
                cows++;
            }
        }

        if (buls != 0 && cows != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s).\n", buls, cows);
        } else if (cows == 0 && buls != 0) {
            System.out.printf("Grade: %d bull(s).\n", buls);
            return buls == preCode.length();
        } else if (cows != 0) {
            System.out.printf("Grade: %d cow(s).\n", cows);
        } else {
            System.out.println("Grade: None.");
        }
        return false;
    }
}