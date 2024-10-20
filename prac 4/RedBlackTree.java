public class RedBlackTree<T extends Comparable<T>> {

    /*
     * Sentinel is not the root. Go check the text book if this doesn't make sense
     */
    public RedBlackNode<T> SENTINEL;
    public RedBlackNode<T> NULL_NODE;
    private RedBlackNode<T> root;

    public static final int RED = 1;
    public static final int BLACK = 0;

    public RedBlackTree() {
        // Initialize sentinel and null nodes
        SENTINEL = new RedBlackNode<>(null);
        SENTINEL.colour = BLACK;
        SENTINEL.left = SENTINEL;
        SENTINEL.right = SENTINEL;

        NULL_NODE = new RedBlackNode<>(null);
        NULL_NODE.colour = BLACK;
        NULL_NODE.left = SENTINEL;
        NULL_NODE.right = SENTINEL;

        SENTINEL.right = NULL_NODE;

    }

    public RedBlackNode<T> getRoot() {
        if (SENTINEL.right == SENTINEL) {

            return NULL_NODE;
        } else {
            return SENTINEL.right;
        }
    }

    public void bottomUpInsert(T data) {

        RedBlackNode<T> newNode = new RedBlackNode<>(data);
        newNode.left = NULL_NODE;
        newNode.right = NULL_NODE;

        newNode.colour = RED;
        // If the tree is empty
        if (SENTINEL.right == NULL_NODE) {
            SENTINEL.right = newNode;
            newNode.parent = SENTINEL; // Link the new node parent to SENTINEL
        } else {
            // Start recursion with actual root and SENTINEL as parent
            insertRecursive(SENTINEL.right, newNode, SENTINEL);
        }
        fixInsertionViolations(newNode);
    }

    private void insertRecursive(RedBlackNode<T> current, RedBlackNode<T> newNode, RedBlackNode<T> parent) {
        if (current == NULL_NODE) {

            newNode.parent = parent;
            if (parent == SENTINEL) {
                parent.left = newNode;
            } else if (newNode.data.compareTo(parent.data) < 0) {
            } else {
                parent.right = newNode;
            }
            return;
        }

        // Determine whether to go left or right in the tree.
        if (newNode.data.compareTo(current.data) < 0) {

            if (!(current.left == NULL_NODE)) {
                insertRecursive(current.left, newNode, current);
            } else {
                current.left = newNode;

                newNode.parent = current;
            }

        } else if (newNode.data.compareTo(current.data) > 0) {

            if (current.right != NULL_NODE) {

                insertRecursive(current.right, newNode, current);
            } else {
                current.right = newNode;
                newNode.parent = current;
            }
        }
    }

    private void fixInsertionViolations(RedBlackNode<T> node) {
        while (node != getRoot() && node.parent != null && node.parent != SENTINEL && node.parent.colour == RED) {
            if (node.parent.parent == null) {
                break;
            }
            if (node.parent.parent == SENTINEL) {
                break;
            }
            if (node.parent == node.parent.parent.left) {
                // Uncle node
                RedBlackNode<T> uncle = node.parent.parent.right;
                // Case 1: Uncle is red
                if (uncle != null && uncle != SENTINEL && uncle.colour == RED) {
                    node.parent.colour = BLACK;
                    uncle.colour = BLACK;
                    node.parent.parent.colour = RED;
                    node = node.parent.parent;
                } else { // Case 2: Node is right child
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    // Case 3: Node is left child
                    node.parent.parent.colour = RED;
                    node.parent.colour = BLACK;

                    rightRotate(node.parent.parent);
                }
            } else { // Mirror cases: parent is the right child
                RedBlackNode<T> uncle = node.parent.parent.left;

                if (uncle != null && uncle != SENTINEL && uncle.colour == RED) {

                    node.parent.colour = BLACK;
                    uncle.colour = BLACK;
                    node.parent.parent.colour = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.colour = BLACK;
                    node.parent.parent.colour = RED;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        SENTINEL.right.colour = BLACK;
    }

    private void rotateLeft(RedBlackNode<T> x) {

        RedBlackNode<T> y = x.right;
        // y left subtree becomes x right subtree
        x.right = y.left;

        // Check if y left subtree is not NULLNODE
        if (y.left != NULL_NODE) {

            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == SENTINEL) {

            SENTINEL.right = y;

        } else if (x == x.parent.left) {

            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;

        x.parent = y;
    }

    private void rightRotate(RedBlackNode<T> x) {
        RedBlackNode<T> y = x.left;

        x.left = y.right;

        if (y.right != NULL_NODE) {
            // Check if y right subtree != NULLNODE
            y.right.parent = x;

        }

        y.parent = x.parent;

        if (x.parent == SENTINEL) {

            SENTINEL.right = y;
        } else if (x == x.parent.right) {

            x.parent.right = y;
        } else {
            x.parent.left = y;
        }

        y.right = x;

        x.parent = y;
    }

    private boolean isBlack(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return true;
        }
        if (node.colour == BLACK) {
            return true;
        }
        return false;
    }

    private boolean isRed(RedBlackNode<T> node) {

        return node != NULL_NODE && node.colour == RED;
    }

    public boolean isValidRedBlackTree() {
        if (SENTINEL.right.colour != BLACK) {

            return false;
        }
        if (SENTINEL.right == NULL_NODE) {

            return true;
        }

        if (checkNode(SENTINEL.right, 0) != -1) {
            return true;
        } else {
            return false;
        }

    }

    private int checkNode(RedBlackNode<T> node, int blacknum) {
        // Base
        if (node == NULL_NODE) {

            return 1 + blacknum;
        }
        if (node.colour == RED) {

            if (node.left != NULL_NODE && node.left.colour == RED) {
                return -1;
            }

            if (node.right != NULL_NODE && node.right.colour == RED) {
                return -1;
            }

        } else {
            blacknum++;
        }

        int leftblack = checkNode(node.left, blacknum);

        int rightblack = checkNode(node.right, blacknum);

        if (leftblack != rightblack) {

            return -1;
        }
        if (leftblack == -1) {
            return -1;
        }
        return leftblack;
    }

    public void topDownDelete(T data) {

        SENTINEL.colour = RED;
        RedBlackNode<T> P = SENTINEL;
        RedBlackNode<T> X = SENTINEL.right;

        // Sibling of X.
        RedBlackNode<T> T = null;

        while (X != NULL_NODE) {

            if (isBlack(X.left) && isBlack(X.right)) {

                if (T != null && (isBlack(T.left)) && (isBlack(T.right))) {
                    if ((isBlack(T.left)) && (isBlack(T.right))) {

                        X.colour = RED;
                        T.colour = RED;
                        if (P != SENTINEL) {
                            P.colour = BLACK;
                        }
                    }
                }

            }

            P = X;

            int cmp = data.compareTo(X.data);

            if (cmp < 0) {

                T = X.right;
                X = X.left;

            } else if (cmp > 0) {

                T = X.left;
                X = X.right;
            } else {
                // Node found

                deleteNode(X);
                break;
            }
        }

        SENTINEL.colour = BLACK;
    }

    private void deleteNode(RedBlackNode<T> node) {

        RedBlackNode<T> rep, child;

        boolean bothBlack;

        // Finding the rep (in-order predecessor or successor)

        if (node.left != NULL_NODE && node.right != NULL_NODE) {

            rep = Getmin(node.right);

            bothBlack = isBlack(rep) && isBlack(node);
            child = rep.right;

            if (rep.parent != node) {

                replaceNode(rep, rep.right);
                rep.right = node.right;
                rep.right.parent = rep;
            }

            replaceNode(node, rep);

            rep.left = node.left;
            rep.left.parent = rep;
            rep.colour = node.colour;
        } else {
            if (node.left == NULL_NODE) {

                child = node.right;
            } else {
                child = node.left;

            }
            rep = node;

            bothBlack = isBlack(node) && isBlack(child);

            replaceNode(node, child);
        }

        if (bothBlack) {

            fixDoubleBlack(child);

        } else {

            if (child != NULL_NODE) {

                child.colour = BLACK;
            }
        }

    }

    private void replaceNode(RedBlackNode<T> oldNode, RedBlackNode<T> newNode) {
        if (oldNode.parent == null) {

            SENTINEL.right = newNode;

        } else {

            if (oldNode == oldNode.parent.left) {

                oldNode.parent.left = newNode;
            } else {

                oldNode.parent.right = newNode;
            }
        }

        if (newNode != null) {

            newNode.parent = oldNode.parent;
        }
    }

    private void fixDoubleBlack(RedBlackNode<T> x) {

        if (x == SENTINEL.right) {
            return;
        }

        RedBlackNode<T> sibling = x.sibling();
        RedBlackNode<T> parent = x.parent;

        if (sibling == NULL_NODE) {

            fixDoubleBlack(parent);
        } else {
            if (isRed(sibling)) {

                parent.colour = RED;
                sibling.colour = BLACK;
                if (sibling == parent.left) {

                    rightRotate(parent);
                } else {

                    rotateLeft(parent);
                }
                fixDoubleBlack(x);

            } else {

                if (isRed(sibling.left) || isRed(sibling.right)) {

                    if ((sibling.left != NULL_NODE) && (isRed(sibling.left))) {

                        if (sibling == parent.left) {
                            // left left
                            sibling.left.colour = sibling.colour;

                            sibling.colour = parent.colour;

                            rightRotate(parent);
                        } else {

                            // Right left
                            sibling.left.colour = parent.colour;

                            rightRotate(sibling);
                            rotateLeft(parent);
                        }
                    } else {
                        // Left
                        if (sibling == parent.left) {

                            sibling.right.colour = parent.colour;

                            rotateLeft(sibling);
                            rightRotate(parent);
                        } else {
                            // Right
                            sibling.right.colour = sibling.colour;
                            sibling.colour = parent.colour;
                            rotateLeft(parent);
                        }
                    }
                    parent.colour = BLACK;
                } else {

                    sibling.colour = RED;

                    if (isBlack(parent)) {
                        fixDoubleBlack(parent);

                    } else {
                        parent.colour = BLACK;
                    }
                }
            }
        }
    }

    private RedBlackNode<T> Getmin(RedBlackNode<T> node) {
        if (node == null) {
            return null;
        }
        while (node.left != NULL_NODE) {
            node = node.left;
        }
        return node;
    }

    /* -------------------------------------------------------------------------- */
    /* Private methods, which shouldn't be called from outside the class */
    /* -------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I worked really hard to make it pretty. */
    /* Also, it matches the website. -------------------------------------------- */
    /* Also, also, we might test against it ;) ---------------------------------- */
    /* -------------------------------------------------------------------------- */
    private StringBuilder toString(RedBlackNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != NULL_NODE) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.toString()).append("\n");
        if (node.left != NULL_NODE) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return SENTINEL.right == NULL_NODE ? "Empty tree"
                : toString(SENTINEL.right, new StringBuilder(), true, new StringBuilder()).toString();
    }

    public String toVis() {
        return toVisHelper(getRoot());
    }

    private String toVisHelper(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return "{}";
        }
        String leftStr = toVisHelper(node.left);
        String rightStr = toVisHelper(node.right);
        return "{" + node.toString() + leftStr + rightStr + "}";
    }

}
