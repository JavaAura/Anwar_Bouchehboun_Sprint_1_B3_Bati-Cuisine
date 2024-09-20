package org.BatiCuisine.coucheUtilitaire;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {

    private static Scanner scanner = new Scanner(System.in);

    public static String getYesNoInput(String message) {
        String input;
        do {
            input = InputValidator.getStringInput(message).trim().toLowerCase();
            if (!input.equals("yes") && !input.equals("no")) {
                System.out.println("Veuillez entrer 'yes' ou 'no'.");
            }
        } while (!input.equals("yes") && !input.equals("no"));
        return input;
    }


    public static int getIntInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (Pattern.matches("\\d+", input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    }
    public static String getIntInputNombre(String prompt) {
        while (true) {
            String regex = "(\\+212|0)[0-9]{9}";
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (Pattern.matches(regex, input)) {
                return input;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre Telephone +212xxxxxxxxx.");
            }
        }
    }
    public static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("Entrée invalide. Veuillez entrer '1-true' ou '2-false'.");
            }
        }
    }
    public static double getDoubleInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (Pattern.matches("\\d+", input)) {
                return Double.parseDouble(input);
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    }
    public static double getDouble(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (Pattern.matches("\\d+\\.\\d+", input)) {
                return Double.parseDouble(input);
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier. 1.0");
            }
        }
    }

    public static String getStringInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;

            } else {
                System.out.println("L'entrée ne peut pas être vide.");
            }
        }
    }
    public static boolean askYesNoQuestion(String question) {
        String response;

        while (true) {
            System.out.println(question);
            response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                return true;
            } else if (response.equals("non")) {
                return false;
            } else {
                System.out.println("Veuillez répondre par 'yes' ou 'non'.");
            }
        }
    }

}
