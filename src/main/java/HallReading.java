class HallReading {

    public static void main(String[] args) {

        int[][] halls = {
                {10, 14, 16, 13},
                {20, 18, 19, 20},
                {8, 6, 2, 7}
        };

        double highestAverage = 0;
        int theBestHall = 0;

        for (int i = 0; i < halls.length; i++) {

            int sum = 0;

            for (int j = 0; j < halls[i].length; j++) {
                sum += halls[i][j];
            }

            double average = (double) sum / halls[i].length;

            System.out.println("Średnia hali " + (i + 1) + ": " + average);

            if (i == 0 || average > highestAverage) {
                highestAverage = average;
                theBestHall = i + 1;
            }
        }

        System.out.println("Hala o najwyższej średniej: " + theBestHall);
        System.out.println("Najwyższa średnia: " + highestAverage);
    }
}