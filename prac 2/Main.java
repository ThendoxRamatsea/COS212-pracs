public class Main {

    public static void main(String[] args) {
        // Create an instance of your BST
        BST<Integer> bst = new BST<>();

        // Insert some elements
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Print the tree
        System.out.println("Binary Search Tree:");
        System.out.println(bst);

        // Test findMax and findMin
        System.out.println("Max value: " + bst.findMax().data);
        System.out.println("Min value: " + bst.findMin().data);

        // Test getHeight
        System.out.println("Tree height: " + bst.getHeight());

        // Test contains
        System.out.println("Contains 40? " + bst.contains(40));
        System.out.println("Contains 90? " + bst.contains(90));

        // Test delete
        bst.delete(30);
        System.out.println("After deleting 30:");
        System.out.println(bst);

        // Test getNumLeaves

        // Test extractBiggestSuperficiallyBalancedSubTree
        BST<Integer> balancedSubTree = bst.extractBiggestSuperficiallyBalancedSubTree();
        System.out.println("Biggest superficially balanced subtree:");
        System.out.println(balancedSubTree);

        // Test getNode
        BinaryNode<Integer> node = bst.getNode(60);
        System.out.println("Node with value 60: " + (node != null ? node.data : "Not found"));

        // Test isSuperficiallyBalanced
        System.out.println("Is the entire tree superficially balanced? " + bst.isSuperficiallyBalanced());
    }
}
