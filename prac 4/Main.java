public class Main {
    public static void main(String[] args) {
        // Initialize a RedBlackTree
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();

        // Insert nodes to form a complex tree
        int[] valuesToInsert = { 50, 25, 75, 15, 35, 65, 85, 5, 20, 30, 40, 60, 70, 80, 90 };
        for (int value : valuesToInsert) {
            rbTree.bottomUpInsert(value);
        }

        System.out.println("Initial tree:");
        System.out.println(rbTree.toString());

        // Delete a leaf node
        System.out.println("\nDeleting a leaf node (20):");
        rbTree.topDownDelete(20);
        System.out.println(rbTree.toString());

        // Delete a node with only one child
        System.out.println("\nDeleting a node with one child (75):");
        rbTree.topDownDelete(75);
        System.out.println(rbTree.toString());

        // Delete a node with two children
        System.out.println("\nDeleting a node with two children (25):");
        rbTree.topDownDelete(25);
        System.out.println(rbTree.toString());

        // Delete the root node
        System.out.println("\nDeleting the root (50):");
        rbTree.topDownDelete(50);
        System.out.println(rbTree.toString());

        // Delete a node that does not exist
        System.out.println("\nTrying to delete a non-existing node (100):");
        rbTree.topDownDelete(100);
        System.out.println(rbTree.toString());

        // Delete nodes until the tree is empty
        for (int value : valuesToInsert) {
            rbTree.topDownDelete(value);
        }
        System.out.println("\nAfter deleting all nodes:");
        System.out.println(rbTree.toString());

        // Validate again
        System.out.println("Is valid Red-Black Tree after deletions? " +
                rbTree.isValidRedBlackTree());
    }
}
