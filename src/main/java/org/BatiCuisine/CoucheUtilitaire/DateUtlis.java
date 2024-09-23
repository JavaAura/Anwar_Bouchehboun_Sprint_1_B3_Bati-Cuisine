package org.BatiCuisine.coucheUtilitaire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateUtlis {


    private static Scanner scanner = new Scanner(System.in);

    public static LocalDate getDateInput(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    LocalDate date = LocalDate.parse(input, formatter);
                    if (date.isAfter(LocalDate.now())) {
                        return date;
                    } else {
                        LoggerMessage.error("La date doit être après la date actuelle.");
                    }
                } catch (DateTimeParseException e) {
                    LoggerMessage.error("Format de date invalide. Veuillez entrer la date au format 'yyyy-MM-dd'.");
                }
            } else {
                LoggerMessage.error("L'entrée ne peut pas être vide.");
            }
        }
    }

}
