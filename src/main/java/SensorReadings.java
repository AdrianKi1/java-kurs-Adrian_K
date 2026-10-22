import java.util.Arrays;
import java.util.Scanner;

public class SensorReadings {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // CZĘŚĆ 1

        System.out.println("Ile odczytów?");
        int numberOfReads = sc.nextInt();

        int[] readingsTable = new int[numberOfReads];

        for (int i = 0; i < readingsTable.length; i++) {
            System.out.println("Podaj odczyt:");
            readingsTable[i] = sc.nextInt();
        }

        int sum = 0;
        int min = readingsTable[0];
        int max = readingsTable[0];

        for (int i = 0; i < readingsTable.length; i++) {

            sum += readingsTable[i];

            if (readingsTable[i] < min) {
                min = readingsTable[i];
            }

            if (readingsTable[i] > max) {
                max = readingsTable[i];
            }
        }

        double average = (double) sum / readingsTable.length;

        int aboveAverage = 0;

        for (int reading : readingsTable) {
            if (reading > average) {
                aboveAverage++;
            }
        }

        int[] sorted = Arrays.copyOf(readingsTable, readingsTable.length);

        Arrays.sort(sorted);

        double median;

        if (sorted.length % 2 == 0) {

            int middle1 = sorted[sorted.length / 2 - 1];
            int middle2 = sorted[sorted.length / 2];

            median = (middle1 + middle2) / 2.0;

        } else {

            median = sorted[sorted.length / 2];
        }

        System.out.println();
        System.out.println("=== ODCZYTY Z CZUJNIKÓW ===");

        System.out.println("Suma: " + sum);
        System.out.println("Minimum: " + min);
        System.out.println("Maksimum: " + max);
        System.out.println("Średnia: " + average);

        System.out.println(
                "Liczba odczytów powyżej średniej: "
                        + aboveAverage);

        System.out.println(
                "Oryginalna tablica: "
                        + Arrays.toString(readingsTable));

        System.out.println(
                "Posortowana kopia: "
                        + Arrays.toString(sorted));

        System.out.println("Mediana: " + median);

        // CZĘŚĆ 2

        System.out.println();
        System.out.println("=== HALE ===");

        int[][] halls = {
                {10, 14, 16, 13},
                {20, 18, 19, 20},
                {8, 6, 2, 7}
        };

        double highestAverage = 0;
        int bestHall = 0;

        for (int i = 0; i < halls.length; i++) {

            int hallSum = 0;

            for (int j = 0; j < halls[i].length; j++) {
                hallSum += halls[i][j];
            }

            double hallAverage =
                    (double) hallSum / halls[i].length;

            System.out.println(
                    "Średnia hali "
                            + (i + 1)
                            + ": "
                            + hallAverage);

            if (i == 0 || hallAverage > highestAverage) {
                highestAverage = hallAverage;
                bestHall = i + 1;
            }
        }

        System.out.println(
                "Hala o najwyższej średniej: "
                        + bestHall);

        System.out.println(
                "Najwyższa średnia: "
                        + highestAverage);

        sc.close();
    }
}