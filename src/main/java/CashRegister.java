public class CashRegister {

    public static void main(String[] args) {

        // Zadanie 1

        String rawShop = "  kurs market  ";
        String rawCustomer = "  anna   nowak  ";
        String rawEmail = " Anna.Nowak@Example.COM ";
        String rawCode1 = "  spoz-0599  ";
        String rawCode2 = "spoz-0429";
        String rawCode3 = "  kawa-3999";
        char register = 'A';
        int receiptNumber = 0;

        // Zadanie 2

        String code1 = rawCode1.trim().toUpperCase();
        String code2 = rawCode2.trim().toUpperCase();
        String code3 = rawCode3.trim().toUpperCase();

        String[] split1 = code1.split("-");
        String[] split2 = code2.split("-");
        String[] split3 = code3.split("-");

        int price1 = Integer.parseInt(split1[1]);
        int price2 = Integer.parseInt(split2[1]);
        int price3 = Integer.parseInt(split3[1]);

        double zl1 = price1 / 100.0;
        double zl2 = price2 / 100.0;
        double zl3 = price3 / 100.0;

        // Zadanie 3

        String product1 = "Chleb razowy";
        int quantity1 = 2;

        String product2 = "Mleko 3,2%";
        int quantity2 = 3;

        String product3 = "Kawa ziarnista";
        int quantity3 = 1;

        double total1 = Math.round(zl1 * quantity1 * 100.0) / 100.0;
        double total2 = Math.round(zl2 * quantity2 * 100.0) / 100.0;
        double total3 = Math.round(zl3 * quantity3 * 100.0) / 100.0;

        // Zadanie 4

        String[] parts = rawCustomer.trim().split("\\s+");

        String customerName =
                parts[0].substring(0, 1).toUpperCase()
                        + parts[0].substring(1)
                        + " "
                        + parts[1].substring(0, 1).toUpperCase()
                        + parts[1].substring(1);

        String initials = ""
                + customerName.charAt(0)
                + customerName.charAt(customerName.indexOf(" ") + 1);

        String loyaltyCard = initials + "-" + split1[1];

        String email = rawEmail.trim().toLowerCase();

        String maskedEmail =
                email.substring(0, 2)
                        + "***"
                        + email.substring(email.indexOf('@'));

        // Zadanie 5

        String shopName = rawShop.trim().toUpperCase();

        // Zadanie 7

        double netTotal =
                Math.round((total1 + total2 + total3) * 100.0) / 100.0;

        double vat =
                Math.round(netTotal * 0.23 * 100.0) / 100.0;

        double grossTotal =
                Math.round((netTotal + vat) * 100.0) / 100.0;

        double discount =
                Math.round(grossTotal * 0.07 * 100.0) / 100.0;

        double amountToPay =
                Math.round((grossTotal - discount) * 100.0) / 100.0;

        // Zadanie 8

        int paidInCents = 10000;
        double cash = paidInCents / 100.0;

        // Zadanie 9

        int itemCounter = 0;
        itemCounter++;
        itemCounter++;
        itemCounter++;

        System.out.println(
                shopName
                        + " · kasa "
                        + register
                        + " · paragon "
                        + ++receiptNumber);

        System.out.println(
                customerName
                        + " · karta "
                        + loyaltyCard
                        + " · "
                        + maskedEmail);

        System.out.println();

        System.out.printf("%-16s %-10s %d x %7s = %9s zł%n",
                product1,
                code1,
                quantity1,
                String.format("%.2f", zl1).replace(".", ","),
                String.format("%.2f", total1).replace(".", ","));

        System.out.printf("%-16s %-10s %d x %7s = %9s zł%n",
                product2,
                code2,
                quantity2,
                String.format("%.2f", zl2).replace(".", ","),
                String.format("%.2f", total2).replace(".", ","));

        System.out.printf("%-16s %-10s %d x %7s = %9s zł%n",
                product3,
                code3,
                quantity3,
                String.format("%.2f", zl3).replace(".", ","),
                String.format("%.2f", total3).replace(".", ","));

        System.out.printf("RAZEM NETTO%31s zł%n",
                String.format("%.2f", netTotal).replace(".", ","));

        System.out.printf("VAT 23%%%35s zł%n",
                String.format("%.2f", vat).replace(".", ","));

        System.out.printf("RAZEM BRUTTO%30s zł%n",
                String.format("%.2f", grossTotal).replace(".", ","));

        System.out.printf("RABAT 7%%%35s zł%n",
                String.format("%.2f", discount).replace(".", ","));

        System.out.printf("DO ZAPŁATY%31s zł%n",
                String.format("%.2f", amountToPay).replace(".", ","));

        System.out.printf("GOTÓWKA%34s zł%n",
                String.format("%.2f", cash).replace(".", ","));

        if (cash >= amountToPay) {

            double change =
                    Math.round((cash - amountToPay) * 100.0) / 100.0;

            System.out.printf("RESZTA%35s zł%n",
                    String.format("%.2f", change).replace(".", ","));

            int fiveZlotyCoins = (int) (change / 5);

            int remainingGrosze =
                    (int) Math.round((change - fiveZlotyCoins * 5) * 100);

            System.out.println(
                    "monet 5 zł: "
                            + fiveZlotyCoins
                            + ", drobne poza nimi: "
                            + remainingGrosze
                            + " gr");
        }

        System.out.println();

        System.out.println(
                String.format(
                        "%s · %d pozycji · do zapłaty %s zł",
                        shopName,
                        itemCounter,
                        String.format("%.2f", amountToPay).replace(".", ",")
                ));

        System.out.println(
                "Dziękujemy, "
                        + parts[0].substring(0, 1).toUpperCase()
                        + parts[0].substring(1)
                        + " · następna kasa B");
    }
}