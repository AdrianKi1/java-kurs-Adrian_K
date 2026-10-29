import java.util.Scanner;

public class BaggageCheckIn {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj numer lotu:");
        String flightNumber = sc.nextLine().replace(" ", "").toUpperCase();

        if (flightNumber.isBlank()) {
            System.out.println("ODRZUCONO: pusty numer lotu");
            sc.close();
            return;
        }

        if (flightNumber.length() < 4 || flightNumber.length() > 6) {
            System.out.println("ODRZUCONO: numer lotu " + flightNumber);
            sc.close();
            return;
        }

        System.out.println("Podaj nazwisko pasażera:");
        String passengerSurname = sc.nextLine().trim().toUpperCase();

        if (passengerSurname.isBlank()) {
            System.out.println("ODRZUCONO: puste nazwisko");
            sc.close();
            return;
        }

        System.out.println("Podaj klasę:");
        String flightClass = sc.nextLine().trim().toUpperCase();

        System.out.println("Podaj wagę bagażu (np. 23,40):");
        String baggageInput = sc.nextLine().replace(" ", "");

        if (!baggageInput.contains(",")) {
            System.out.println("ODRZUCONO: niepoprawny format wagi " + baggageInput);
            sc.close();
            return;
        }

        String[] parts = baggageInput.split(",");

        if (parts.length != 2 || parts[1].length() != 2) {
            System.out.println("ODRZUCONO: niepoprawny format wagi " + baggageInput);
            sc.close();
            return;
        }

        int kilograms = Integer.parseInt(parts[0]);
        int hundreds = Integer.parseInt(parts[1]);

        int grams = kilograms * 1000 + hundreds * 10;

        if (grams < 0 || grams > 60000) {
            System.out.println("ODRZUCONO: waga poza zakresem " + baggageInput);
            sc.close();
            return;
        }

        System.out.println("Podaj wiek pasażera:");
        int passengerAge = Integer.parseInt(sc.nextLine());

        int baggageLimit = switch (flightClass) {
            case "ECONOMY" -> 23;
            case "PREMIUM" -> 28;
            case "BUSINESS" -> 32;
            case "FIRST" -> 40;
            default -> {
                System.out.println(
                        "UWAGA: nieznana klasa "
                                + flightClass
                                + ", przyjmuję limit economy");
                yield 23;
            }
        };

        int limitGrams = baggageLimit * 1000;
        int overWeightGrams = Math.max(0, grams - limitGrams);

        int fee;
        String cargoNote = "";

        if (overWeightGrams == 0) {
            fee = 0;
        } else if (overWeightGrams <= 5000) {
            fee = 50;
        } else if (overWeightGrams <= 15000) {
            fee = 150;
        } else {
            fee = 300;
            cargoNote = "BAGAŻ DO NADANIA JAKO CARGO";
        }

        String boarding =
                (flightClass.equals("BUSINESS")
                        || flightClass.equals("FIRST"))
                        ? "PRIORITY"
                        : "normalne";

        String guardianNote =
                passengerAge < 16
                        ? "PASAŻER MAŁOLETNI - WYMAGANA OPIEKA"
                        : "";

        System.out.println();
        System.out.println("===== PRZYWIESZKA BAGAŻOWA =====");

        System.out.printf("%-20s%s%n", "LOT:", flightNumber);
        System.out.printf("%-20s%s%n", "PASAŻER:", passengerSurname);
        System.out.printf("%-20s%s%n", "KLASA:", flightClass);
        System.out.printf("%-20s%s kg%n", "WAGA:", baggageInput);
        System.out.printf("%-20s%d kg%n", "LIMIT:", baggageLimit);

        System.out.printf(
                "%-20s%s kg%n",
                "NADWAGA:",
                String.format("%.2f", overWeightGrams / 1000.0)
                        .replace(".", ","));

        System.out.printf("%-20s%d zł%n", "OPŁATA:", fee);
        System.out.printf("%-20s%s%n", "BOARDING:", boarding);

        if (!guardianNote.isEmpty()) {
            System.out.println(guardianNote);
        }

        if (!cargoNote.isEmpty()) {
            System.out.println(cargoNote);
        }

        sc.close();
    }
}