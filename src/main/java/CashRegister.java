import java.util.Scanner;

public class CashRegister {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Zadanie 1.

        String rawShop = "  kurs market  ";
        String rawCustomer = "  anna   nowak  ";
        String rawEmail = " Anna.Nowak@Example.COM ";
        String rawCode1 = "  spoz-0599  ";
        String rawCode2 = "spoz-0429";
        String rawCode3 = "  kawa-3999";
        char register = 'A';
        int receiptNumber = 0;

        // Zadanie 2.

        String Code1 = rawCode1.trim().toUpperCase();
        String Code2 = rawCode2.trim().toUpperCase();
        String Code3 = rawCode3.trim().toUpperCase();

        String[] split1 = Code1.split("-");
        String[] split2 = Code2.split("-");
        String[] split3 = Code3.split("-");

        int money1 = Integer.parseInt(split1[1]);
        double zl1 = money1 / 100.0;

        int money2 = Integer.parseInt(split2[1]);
        double zl2 = money2 / 100.0;

        int money3 = Integer.parseInt(split3[1]);
        double zl3 = money3 / 100.0;

        // Zadanie 3.

        String product1 = "Chleb razowy";
        int quantity1 = 2;

        String product2 = "Mleko 3,2%";
        int quantity2 = 3;

        String product3 = "Kawa ziarnista";
        int quantity3 = 1;

        double suma1 = Math.round(zl1 * 2 * 100.0) / 100.0;
        double suma2 = Math.round(zl2 * 3 * 100.0) / 100.0;
        double suma3 = Math.round(zl3 * 1 * 100.0) / 100.0;

        double sumaProduktow =
                Math.round((suma1 + suma2 + suma3) * 100.0) / 100.0;

        double vat =
                Math.round(sumaProduktow * 0.23 * 100.0) / 100.0;

        double brutto =
                Math.round((sumaProduktow + vat) * 100.0) / 100.0;

        double rabat =
                Math.round(brutto * 0.07 * 100.0) / 100.0;

        double finalCash =
                Math.round((brutto - rabat) * 100.0) / 100.0;

        String sellProduct1 =
                product1 + "       " + Code1 + "     "
                        + quantity1 + " x      "
                        + String.format("%.2f", zl1).replace(".", ",")
                        + " =     "
                        + String.format("%.2f", suma1).replace(".", ",")
                        + " zł";

        String sellProduct2 =
                product2 + "         " + Code2 + "     "
                        + quantity2 + " x      "
                        + String.format("%.2f", zl2).replace(".", ",")
                        + " =     "
                        + String.format("%.2f", suma2).replace(".", ",")
                        + " zł";

        String sellProduct3 =
                product3 + "     " + Code3 + "     "
                        + quantity3 + " x      "
                        + String.format("%.2f", zl3).replace(".", ",")
                        + " =     "
                        + String.format("%.2f", suma3).replace(".", ",")
                        + " zł";

        // Zadanie 4.

        String[] parts = rawCustomer.trim().split("\\s+");

        String name =
                parts[0].substring(0, 1).toUpperCase()
                        + parts[0].substring(1)
                        + " "
                        + parts[1].substring(0, 1).toUpperCase()
                        + parts[1].substring(1);

        String Inicjaly = ""
                + name.charAt(0)
                + name.charAt(name.indexOf(" ") + 1);

        String card = Inicjaly + "-" + split1[1];

        String newEmail = rawEmail.trim().toLowerCase();

        String maskedEmail =
                newEmail.substring(0, 2)
                        + "***"
                        + newEmail.substring(newEmail.indexOf('@'));

        // Zadanie 5.

        String headLine = rawShop.trim().toUpperCase();

        int paidInCents = 10000;
        double cash = paidInCents / 100.0;

        int itemCounter =
                quantity1 + quantity2 + quantity3;

        // Cały kod po kolei zgodnie z przykładem

        System.out.println(
                headLine + " · kasa "
                        + register
                        + " · paragon "
                        + ++receiptNumber);

        System.out.println(
                name + " · karta "
                        + card
                        + " · "
                        + maskedEmail);

        System.out.println();

        System.out.println(sellProduct1);
        System.out.println(sellProduct2);
        System.out.println(sellProduct3);

        System.out.println(
                "RAZEM NETTO "
                        + String.format("%.2f", sumaProduktow)
                        .replace(".", ",")
                        + " zł");

        System.out.println(
                "VAT 23% "
                        + String.format("%.2f", vat)
                        .replace(".", ",")
                        + " zł");

        System.out.println(
                "RAZEM BRUTTO "
                        + String.format("%.2f", brutto)
                        .replace(".", ",")
                        + " zł");

        System.out.println(
                "RABAT 7% "
                        + String.format("%.2f", rabat)
                        .replace(".", ",")
                        + " zł");

        System.out.println(
                "DO ZAPŁATY "
                        + String.format("%.2f", finalCash)
                        .replace(".", ",")
                        + " zł");

        if (cash >= finalCash) {

            double reszta =
                    Math.round((cash - finalCash) * 100.0) / 100.0;

            System.out.println(
                    "RESZTA "
                            + String.format("%.2f", reszta)
                            .replace(".", ",")
                            + " zł");

            int monety5zl = (int) (reszta / 5);

            int grosze =
                    (int) Math.round(
                            (reszta - monety5zl * 5) * 100);

            System.out.println(
                    "Monet 5 zł: " + monety5zl);

            System.out.println(
                    "Drobne poza nimi: "
                            + grosze
                            + " gr");

        } else {

            double lackOfCash =
                    Math.round((finalCash - cash) * 100.0) / 100.0;

            System.out.println(
                    "BRAKUJE "
                            + String.format("%.2f", lackOfCash)
                            .replace(".", ",")
                            + " zł");
        }

        System.out.println(
                headLine + " · "
                        + itemCounter
                        + " pozycji · ");

        System.out.println(
                "Dziękujemy, Anna · następna kasa B");

        scanner.close();
    }
}