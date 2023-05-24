public class Cryptography {
    public long encrypt(int[] numbers) {
        long product = 1L;

        for (int number : numbers) {
            product *= number;
        }

        long output = 0L;

        for (int number : numbers) {
            long currentProduct = product / number * (number + 1);

            if (currentProduct > output) {
                output = currentProduct;
            }
        }

        return output;
    }
}