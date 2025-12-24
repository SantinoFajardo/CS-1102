package unit4;
import java.util.ArrayList;

public class StockPrice {

    /**
     * Calculates the average stock price from an array of prices.
     *
     * @param prices array of stock prices (float)
     * @return average price
     */
    public static float calculateAveragePrice(float[] prices) {
        float sum = 0.0f;

        for (int i = 0; i < prices.length; i++) {
            sum += prices[i];
        }

        return sum / prices.length;
    }

    /**
     * Finds the maximum stock price in an array of prices.
     *
     * @param prices array of stock prices (float)
     * @return maximum price
     */
    public static float findMaximumPrice(float[] prices) {
        float max = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > max) {
                max = prices[i];
            }
        }

        return max;
    }

    /**
     * Counts how many times a target price appears in the array.
     *
     * @param prices      array of stock prices (float)
     * @param targetPrice price to search for
     * @return number of occurrences of targetPrice
     */
    public static int countOccurrences(float[] prices, float targetPrice) {
        int count = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] == targetPrice) {
                count++;
            }
        }

        return count;
    }

    /**
     * Computes the cumulative sum of stock prices stored in an ArrayList.
     * Example: [10, 20, 30] -> [10, 30, 60]
     *
     * @param pricesList ArrayList of stock prices
     * @return new ArrayList with cumulative sums
     */
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> pricesList) {
        ArrayList<Float> cumulative = new ArrayList<>();
        float runningSum = 0.0f;

        for (int i = 0; i < pricesList.size(); i++) {
            runningSum += pricesList.get(i);
            cumulative.add(runningSum);
        }

        return cumulative;
    }

    /**
     * Example main method to test the implemented methods.
     */
    public static void main(String[] args) {
        // Array with 10 days of opening stock prices
        float[] openingPrices = {150.5f, 152.3f, 149.8f, 151.0f, 153.2f, 154.1f, 155.0f, 156.3f, 158.9f, 160.2f};

        // Create an ArrayList with the same data
        ArrayList<Float> pricesList = new ArrayList<>();
        for (int i = 0; i < openingPrices.length; i++) {
            pricesList.add(openingPrices[i]);
        }

        // Test methods
        float averagePrice = calculateAveragePrice(openingPrices);
        float maxPrice = findMaximumPrice(openingPrices);
        int occurrences = countOccurrences(openingPrices, 153.2f);
        ArrayList<Float> cumulativeSumList = computeCumulativeSum(pricesList);

        // Print results
        System.out.println("Average price: " + averagePrice);
        System.out.println("Maximum price: " + maxPrice);
        System.out.println("Occurrences of 153.2: " + occurrences);
        System.out.println("Cumulative sum list: " + cumulativeSumList);
    }
}