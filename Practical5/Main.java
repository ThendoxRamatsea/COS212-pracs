public class Main {
    public static void main(String[] args) {
        // Create a new Hashmap
        Hashmap hashmap = new Hashmap();

        // Test the insert method
        hashmap.insert(12345, 85);
        hashmap.insert(67890, 90);
        hashmap.insert(11111, 95);

        // Print the Hashmap
        System.out.println("Hashmap after insertion:");
        System.out.println(hashmap.toString());

        // Test the search method
        KeyValuePair kv = hashmap.search(12345);
        if (kv != null) {
            System.out.println("Found student number 12345 with mark: " + kv.mark);
        } else {
            System.out.println("Student number 12345 not found.");
        }

        kv = hashmap.search(67890);
        if (kv != null) {
            System.out.println("Found student number 67890 with mark: " + kv.mark);
        } else {
            System.out.println("Student number 67890 not found.");
        }

        kv = hashmap.search(11111);
        if (kv != null) {
            System.out.println("Found student number 11111 with mark: " + kv.mark);
        } else {
            System.out.println("Student number 11111 not found.");
        }

        // Test the remove method
        hashmap.remove(12345);
        hashmap.remove(67890);

        // Print the Hashmap after removal
        System.out.println("Hashmap after removal:");
        System.out.println(hashmap.toString());
        PrimeNumberGenerator primeGen = new PrimeNumberGenerator();

        // Test the currentPrime method
        System.out.println("Current prime: " + primeGen.currentPrime());

        // Test the nextPrime method
        System.out.println("Next prime: " + primeGen.nextPrime());

        // Test the toString method
        System.out.println("PrimeNumberGenerator: " + primeGen.toString());
    }
}
