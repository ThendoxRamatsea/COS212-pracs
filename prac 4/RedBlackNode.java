public class RedBlackNode<T extends Comparable<T>> {

    public T data;

    public int colour;

    public RedBlackNode<T> left;
    public RedBlackNode<T> right;
    public RedBlackNode<T> parent;

    // Nodes are red by default
    public RedBlackNode(T data) {
        this.colour = RedBlackTree.BLACK;
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public RedBlackNode<T> sibling() {
        if (parent == null) {
            return null;
        }
        if (this == parent.left) {
            return parent.right;
        } else {
            return parent.left;
        }
    }

    @Override
    public String toString() {
        if (this.data == null) {
            return "SENTINEL/NULL";
        }
        return (colour == RedBlackTree.RED) ? "(" + data.toString() + ")" : data.toString();
    }

}
