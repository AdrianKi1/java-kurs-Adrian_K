import java.util.Scanner;

class ParcelSorting {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        int accepted = 0;
        int rejected = 0;

        int totalWeight = 0;
        int totalFee = 0;

        String heaviestCode = "";
        int heaviestWeight = 0;

        String previousZone = "";
        int currentSeries = 0;
        int longestSeries = 0;
        String longestSeriesZone = "";

        while (true) {

            String code = "";
            boolean validCode = false;

            for (int attempt = 1; attempt <= 3; attempt++) {

                System.out.print("Podaj kod paczki: ");
                code = scanner.nextLine();

                if (code.equalsIgnoreCase("KONIEC")) {
                    break;
                }

                if (code.length() >= 5 && code.contains("-")) {
                    validCode = true;
                    break;
                }

                System.out.println("Błędny kod.");
            }

            if (code.equalsIgnoreCase("KONIEC")) {
                break;
            }

            if (!validCode) {
                System.out.println("ODRZUCONA");
                rejected++;
                continue;
            }

            int weight;

            while (true) {

                System.out.print("Podaj wagę w gramach: ");
                String weightText = scanner.nextLine();

                boolean onlyDigits = true;

                if (weightText.length() < 1 || weightText.length() > 5) {
                    System.out.println("Błędna waga.");
                    continue;
                }

                for (int i = 0; i < weightText.length(); i++) {

                    char c = weightText.charAt(i);

                    if (c < '0' || c > '9') {
                        onlyDigits = false;
                        break;
                    }
                }

                if (!onlyDigits) {
                    System.out.println("Błędna waga.");
                    continue;
                }

                weight = Integer.parseInt(weightText);

                if (weight < 1 || weight > 30000) {
                    System.out.println("Błędna waga.");
                    continue;
                }

                break;
            }

            String zone;

            while (true) {

                System.out.print("Podaj strefę: ");
                zone = scanner.nextLine().toUpperCase();

                if (zone.equals("PL")
                        || zone.equals("EU")
                        || zone.equals("WORLD")) {
                    break;
                }

                System.out.println("Błędna strefa.");
            }

            int kilograms = (weight + 999) / 1000;

            int fee = switch (zone) {
                case "PL" -> 1200 + kilograms * 150;
                case "EU" -> 2500 + kilograms * 400;
                case "WORLD" -> 4900 + kilograms * 900;
                default -> 0;
            };

            accepted++;
            totalWeight += weight;
            totalFee += fee;

            if (weight > heaviestWeight) {
                heaviestWeight = weight;
                heaviestCode = code;
            }

            if (zone.equals(previousZone)) {
                currentSeries++;
            } else {
                currentSeries = 1;
                previousZone = zone;
            }

            if (currentSeries > longestSeries) {
                longestSeries = currentSeries;
                longestSeriesZone = zone;
            }

            System.out.printf("Opłata: %d,%02d zł%n",
                    fee / 100,
                    fee % 100);
        }

        System.out.println();
        System.out.println("=== RAPORT ===");

        System.out.println("Przyjęte: " + accepted);
        System.out.println("Odrzucone: " + rejected);

        System.out.println("Łączna waga: " + totalWeight + " g");

        if (accepted > 0) {
            System.out.println("Średnia waga: "
                    + (totalWeight / accepted) + " g");
        }

        System.out.printf("Łączna opłata: %d,%02d zł%n",
                totalFee / 100,
                totalFee % 100);

        System.out.println("Najcięższa paczka:");
        System.out.println("Kod: " + heaviestCode);
        System.out.println("Waga: " + heaviestWeight + " g");

        System.out.println("Najdłuższa seria:");
        System.out.println(longestSeries + " x " + longestSeriesZone);
    }
}