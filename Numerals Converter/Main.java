import java.util.Scanner;

//test change of details

class FractionalNumber {
    private final String literals = "0123456789abcdefghijklmnopqrstuvwxyz";
    private final int integer;
    private double fractional;
    private boolean isFractionalPart = false;

    FractionalNumber(String number, int base) {
        if (base != 1) {
            this.integer = Integer.parseInt(number.split("\\.")[0], base);
            if (number.contains(".")) {
                this.isFractionalPart = true;
                this.fractional = convertFractionalPartToDecimal(number.split("\\.")[1], base);
            }
        } else {
            this.integer = number.length();
        }
    }

    private double convertFractionalPartToDecimal(String string, int base) {
        double num = 0.0d;
        for (int i = 0; i < string.length(); i++) {
            num += literals.indexOf(string.charAt(i)) / Math.pow(base, i + 1);
        }
        return num;
    }

    private String covertFractionalPartFromDecimal(double number, int base) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {

            result.append(literals.charAt((int) (number * base)));
            number = (number * base) - (int) (number * base);
        }
        return result.toString();
    }

    public String convertTo(int base) {
        StringBuilder result = new StringBuilder();
        if (base == 1) {
            return "1".repeat(this.integer);
        }
        result.append(Integer.toString(this.integer, base));
        if (isFractionalPart) {


            result.append(".").append(covertFractionalPartFromDecimal(this.fractional, base));
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return integer + "." + fractional;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int baseRadix = scanner.nextInt();
            FractionalNumber fractionalNumber = new FractionalNumber(scanner.next(), baseRadix);
            try {
                int targetRadix = scanner.nextInt();
                if (targetRadix < 1 || targetRadix > 36) {
                    System.out.println("Error!");
                } else {
                    System.out.println(fractionalNumber.convertTo(targetRadix));
                }
            } catch (Exception e) {
                System.out.println("Error!");
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
    
    }
}