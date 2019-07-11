package utils;

public class Utils {
    public String formatCurrency(String balance) {
        return balance.replaceAll("\\s", "").replace("$", "");
    }
}
