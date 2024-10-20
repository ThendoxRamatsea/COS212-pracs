public class MaxSkewHeap {
    Node root;

    @Override
    public String toString() {
        return (root == null ? "Empty Tree" : toString(root, "", true));
    }

    public String toString(Node node, String prefix, boolean end) {
        String res = "";
        if (node.right != null) {
            res += toString(node.right, prefix + (end ? "│   " : "    "), false);
        }
        res += prefix + (end ? "└── " : "┌── ") + node.toString() + "\n";
        if (node.left != null) {
            res += toString(node.left, prefix + (end ? "    " : "│   "), true);
        }
        return res;
    }

    public String toStringOneLine() {
        return (root == null ? "{}" : toStringOneLine(root));
    }

    public String toStringOneLine(Node ptr) {
        if (ptr == null) {
            return "{}";
        }
        return "{" + ptr.toString() + toStringOneLine(ptr.left) + toStringOneLine(ptr.right) + "}";
    }

    public MaxSkewHeap() {
        root = null;
    }

    public MaxSkewHeap(String input) {
        root = buildHeapFromStr(input);
    }

    private Node buildHeapFromStr(String input) {
        if (input.equals("{}")) {
            return null;
        }
        int firstBrace = input.indexOf('{', 1);
        int matchingBrace = findMatchingBrace(input, firstBrace);
        int data = Integer.parseInt(input.substring(1, firstBrace));
        Node node = new Node(data);
        node.left = buildHeapFromStr(input.substring(firstBrace, matchingBrace + 1));
        if (matchingBrace + 2 < input.length()) {
            node.right = buildHeapFromStr(input.substring(matchingBrace + 2));
        } else {
            node.right = null;
        }
        return node;
    }

    private int findMatchingBrace(String input, int start) {
        int counter = 1;
        int i = start + 1;
        while (i < input.length() && counter != 0) {
            counter += (input.charAt(i) == '{') ? 1 : (input.charAt(i) == '}') ? -1 : 0;
            i++;
        }
        return (counter == 0) ? i - 1 : -1;
    }

    public void insert(int data) {
        if (search(data) != null) {
            return; // Don't insert duplicates
        }
        Node newNode = new Node(data);
        root = merge(root, newNode);
    }

    public void remove(int data) {
        root = remove(root, data);
    }

    private Node remove(Node node, int data) {
        if (node == null || node.data < data)
            return node;
        if (node.data == data)
            return merge(node.left, node.right);

        node.left = remove(node.left, data);
        node.right = remove(node.right, data);

        return node;
    }

    public Node search(int value) {
        return search(root, value);
    }

    private Node search(Node node, int value) {
        if (node == null || node.data < value)
            return null;

        Node foundNode = (node.data == value) ? node : search(node.right, value);
        return (foundNode == null) ? search(node.left, value) : foundNode;
    }

    public String searchPath(int value) {
        return searchPath(root, value, "");
    }

    private String searchPath(Node node, int value, String path) {
        if (node == null) {
            return path;
        }
        String newPath = path + (path.isEmpty() ? "" : " -> ") + node.data;
        if (node.data == value) {
            return "[" + newPath + "]";
        }
        String foundPath = searchPath(node.right, value, newPath);
        if (foundPath.contains("[")) {
            return foundPath;
        }
        return searchPath(node.left, value, newPath);
    }

    public boolean isLeftist() {
        return isLeftist(root);
    }

    private boolean isLeftist(Node node) {
        if (node == null)
            return true;

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return leftHeight >= rightHeight && isLeftist(node.left) && isLeftist(node.right);
    }

    public boolean isRightist() {
        return isRightist(root);
    }

    private boolean isRightist(Node node) {
        if (node == null) {
            return true;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return rightHeight >= leftHeight && isRightist(node.left) && isRightist(node.right);
    }

    private int getHeight(Node node) {
        return (node == null) ? 0 : 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private Node merge(Node h1, Node h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        if (h1.data < h2.data) {
            Node temp = h1;
            h1 = h2;
            h2 = temp;
        }
        h1.right = merge(h1.right, h2);
        if (h1.left == null) {
            h1.left = h1.right;
            h1.right = null;
        } else {
            if (h1.left.data < h1.right.data) {
                Node temp = h1.left;
                h1.left = h1.right;
                h1.right = temp;
            }
        }
        return h1;
    }

}
