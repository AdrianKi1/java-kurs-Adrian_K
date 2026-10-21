import java.util.Arrays;
import java.util.Scanner;

class ReadingTable {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ile odczytów:");
        int numberOfReads = sc.nextInt();

        int[] readingsTable = new int[numberOfReads];

        for (int i = 0; i < readingsTable.length; i++) {
            System.out.println("Podaj odczyt:");
            readingsTable[i] = sc.nextInt();
        }

        int sum = 0;
        int min = readingsTable[0];
        int max = readingsTable[0];
        int aboveAverage = 0;
        double median;

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

        for (int reading : readingsTable) {
            if (reading > average) {
                aboveAverage++;
            }
        }

        int[] sorted = Arrays.copyOf(readingsTable, readingsTable.length);
        Arrays.sort(sorted);

        if (sorted.length % 2 == 0) {

            int middle1 = sorted[sorted.length / 2 - 1];
            int middle2 = sorted[sorted.length / 2];

            median = (middle1 + middle2) / 2.0;

        } else {

            median = sorted[sorted.length / 2];
        }

        System.out.println("Suma wszystkich odczytów: " + sum);
        System.out.println("Najmniejsza wartość odczytu: " + min);
        System.out.println("Największa wartość odczytu: " + max);
        System.out.println("Średnia odczytów: " + average);
        System.out.println("Liczba odczytów powyżej średniej: " + aboveAverage);

        System.out.println("Oryginalna tablica odczytów: "
                + Arrays.toString(readingsTable));

        System.out.println("Posortowana kopia odczytów: "
                + Arrays.toString(sorted));

        System.out.println("Mediana odczytów: " + median);

        sc.close();
    }
}