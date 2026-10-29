public class LoyaltyCards {

    public static boolean hasValidFormat(String card) {
        return card != null
                && card.length() == 9
                && card.startsWith("LC-");
    }

    public static int digitSum(String card) {
        int sum = 0;

        for (int i = 3; i < card.length(); i++) {
            char c = card.charAt(i);
            sum += c - '0';
        }

        return sum;
    }

    public static int digitSumRecursive(int number) {
        if (number < 10) {
            return number;
        }

        return number % 10 + digitSumRecursive(number / 10);
    }

    public static int pointsFor(int cents) {
        return cents / 10000;
    }

    public static String drawBar(int points) {
        String bar = "";

        for (int i = 0; i < points; i++) {
            bar += "#";
        }

        return bar;
    }

    public static String formatMoney(int cents) {
        return formatMoney(cents, "zł");
    }

    public static String formatMoney(int cents, String currency) {
        int zlote = cents / 100;
        int grosze = cents % 100;

        return String.format("%d,%02d %s", zlote, grosze, currency);
    }

    public static void main(String[] args) {

        String[] cards = {
                "LC-123456",
                "AB-111111",
                "LC-433467"
        };

        int[] amounts = {
                24909,
                10000,
                55000
        };

        for (int i = 0; i < cards.length; i++) {

            if (!hasValidFormat(cards[i])) {
                System.out.println("Format odrzucony");
                continue;
            }

            int sum = digitSum(cards[i]);
            int cardNumber = Integer.parseInt(cards[i].substring(3));
            int recursiveSum = digitSumRecursive(cardNumber);
            int points = pointsFor(amounts[i]);

            System.out.println("Karta: " + cards[i]);
            System.out.println("Suma cyfr: " + sum);
            System.out.println("Kwota: " + formatMoney(amounts[i]));
            System.out.println("Punkty: " + points);
            System.out.println("Pasek: " + drawBar(points));
            System.out.println("digitSum = " + sum
                    + ", digitSumRecursive = " + recursiveSum);
            System.out.println();
        }
    }
}