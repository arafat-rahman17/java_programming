import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class FractionalKnapsack {

    static class ItemComparator implements Comparator<Item> {
        public int compare(Item a, Item b) {
            double ratio1 = (double) a.value / a.weight;
            double ratio2 = (double) b.value / b.weight;
            return Double.compare(ratio2, ratio1);
        }
    }

    public static double fractionalKnapsack(int W, Item[] items) {
        Arrays.sort(items, new ItemComparator());

        double totalValue = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= W) {
                currentWeight += item.weight;
                totalValue += item.value;
            }
                        else {
                int remainingWeight = W - currentWeight;
                totalValue += item.value * ((double) remainingWeight / item.weight);
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the capacity of the knapsack: ");
        int W = scanner.nextInt();

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        Item[] items = new Item[n];

        System.out.println("Enter the value and weight of each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + ": ");
            int value = scanner.nextInt();
            int weight = scanner.nextInt();
            items[i] = new Item(weight, value);
        }

        double maxTotalValue = fractionalKnapsack(W, items);
        System.out.println("Maximum total value obtained = " + maxTotalValue);

        scanner.close();
    }
}
