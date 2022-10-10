package BinarySearch;

import java.util.List;

public class BinarySearch {

    public static void main(String... args) {
        final var data = List.of("Adamiak", "Bór", "Cionowski", "Fabiński", "Gil", "Gralewska", "Jędrzejczyk",
                "Jodłowski", "Kapustka", "Krajewski", "Lewandowski", "Lin", "Maran", "Milewski",
                "Parański", "Perska", "Piszcz", "Salamandra", "Starzyński", "Stępiński",
                "Warzywny", "Zieliński");
        binarySearch(data, 0, data.size() - 1, "Krajewski");
    }

    private static int binarySearch(final List<String> data, final int leftEnd, final int rightEnd, final String value) {
        final int mid = (leftEnd + rightEnd) / 2;
        System.out.printf("Current value: %s index: %s%n", data.get(mid), mid);
        if (value.compareTo(data.get(mid)) < 0) {
            return binarySearch(data, leftEnd, mid, value);
        } else if (value.compareTo(data.get(mid)) > 0) {
            return binarySearch(data, mid, rightEnd, value);
        }
        return mid;
    }
}