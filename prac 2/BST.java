public class BST<T extends Comparable<T>> {

    public BinaryNode<T> root;

    public BST() {
        root = null;

    }

    public void delete(T data) {
        root = deletehelper(root, data);

    }

    private BinaryNode<T> deletehelper(BinaryNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        int compare = data.compareTo(node.data);
        if (compare < 0) {
            node.left = deletehelper(node.left, data);
        } else if (compare > 0) {
            node.right = deletehelper(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.data = findMinRec(node.right).data;
            node.right = deletehelper(node.right, node.data);
        }
        return node;
    }

    public boolean contains(T data) {
        return getNode(data) != null;
    }

    public void insert(T data) {
        root = inserthelper(root, data);

    }

    private BinaryNode<T> inserthelper(BinaryNode<T> node, T data) {
        if (node == null) {
            return new BinaryNode<>(data);
        }

        int compare = data.compareTo(node.data);
        if (compare < 0) {
            node.left = inserthelper(node.left, data);
        } else if (compare > 0) {
            node.right = inserthelper(node.right, data);
        }
        return node;
    }

    public int getHeight() {
        return getHeightRec(root);
    }

    private int getHeightRec(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeightRec(node.left);
        int rightHeight = getHeightRec(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private String findPathHelper(BinaryNode<T> node, T data, StringBuilder path) {
        if (node == null) {
            return null;
        }

        path.append(node.data);

        int comparison = data.compareTo(node.data);

        if (comparison == 0) {
            return path.toString();
        }

        path.append(" -> ");

        if (comparison > 0) {
            return findPathHelper(node.right, data, path);
        } else {
            return findPathHelper(node.left, data, path);
        }
    }

    public String printSearchPath(T data) {
        StringBuilder path = new StringBuilder();
        String result = findPathHelper(root, data, path);

        if (result == null) {
            return "NULL";
        } else if (result.endsWith(" -> ")) {
            return result + "NULL";
        } else {
            return result;
        }
    }

    public BST<T> extractBiggestSuperficiallyBalancedSubTree() {
        BST<T> result = new BST<>();
        extractBiggestSuperficiallyBalancedSubTreeRec(root, result);
        return result;

    }

    private int extractBiggestSuperficiallyBalancedSubTreeRec(BinaryNode<T> node, BST<T> result) {
        if (node == null) {
            return 0;
        }

        int leftCount = extractBiggestSuperficiallyBalancedSubTreeRec(node.left, result);
        int rightCount = extractBiggestSuperficiallyBalancedSubTreeRec(node.right, result);

        if (leftCount == rightCount) {
            result.insert(node.data);
        }

        return 1 + leftCount + rightCount;
    }

    public BinaryNode<T> getNode(T data) {
        return getNodeRec(root, data);
    }

    private BinaryNode<T> getNodeRec(BinaryNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        int compare = data.compareTo(node.data);
        if (compare == 0) {
            return node;
        } else if (compare < 0) {
            return getNodeRec(node.left, data);
        } else {
            return getNodeRec(node.right, data);
        }
    }

    public boolean isSuperficiallyBalanced() {
        return isSuperficiallyBalancedRec(root);
    }

    private boolean isSuperficiallyBalancedRec(BinaryNode<T> node) {
        if (node == null) {
            return true;
        }

        int leftCount = countNodes(node.left);
        int rightCount = countNodes(node.right);

        return leftCount == rightCount
                && isSuperficiallyBalancedRec(node.left)
                && isSuperficiallyBalancedRec(node.right);
    }

    private int countNodes(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public BinaryNode<T> findMax() {
        return findMaxRec(root);
    }

    private BinaryNode<T> findMaxRec(BinaryNode<T> node) {
        if (node == null || node.right == null) {
            return node;
        }
        return findMaxRec(node.right);
    }

    public BinaryNode<T> findMin() {

        return findMinRec(root);
    }

    private BinaryNode<T> findMinRec(BinaryNode<T> node) {
        if (node == null || node.left == null) {
            return node;
        }
        return findMinRec(node.left);
    }

    ///////////////

    private StringBuilder toString(BinaryNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != null) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.data.toString()).append("\n");
        if (node.left != null) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return root == null ? "Empty tree" : toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }

}
