public class Main {
    public static void main(String[] args) {

        // Test the constructor that takes a string as input
        RecursiveArray ra1 = new RecursiveArray("1,2,3,4,5");
        System.out.println(ra1.toString()); // Should print: [1,2,3,4,5]

        // Test the append method
        ra1.append(6);
        System.out.println(ra1.toString()); // Should print: [1,2,3,4,5,6]

        // Test the prepend method
        ra1.prepend(0);
        System.out.println(ra1.toString()); // Should print: [0,1,2,3,4,5,6]

        // Test the contains method
        System.out.println(ra1.contains(3)); // Should print: true
        System.out.println(ra1.contains(7)); // Should print: false

        // Test the isAscending method
        System.out.println(ra1.isAscending()); // Should print: true

        // Test the isDescending method
        System.out.println(ra1.isDescending()); // Should print: false

        // Test the sortDescending method
        ra1.sortDescending();
        System.out.println(ra1.toString()); // Should print: [6,5,4,3,2,1,0]
        System.out.println(ra1.isDescending()); // Should print: true

        // Test the sortAscending method
        ra1.sortAscending();
        System.out.println(ra1.toString()); // Should print: [0,1,2,3,4,5,6]
        System.out.println(ra1.isAscending()); // Should print: true

        // Test the default constructor
        RecursiveArray ra2 = new RecursiveArray();
        System.out.println(ra2.toString()); // Should print: Empty Array
    }
}
