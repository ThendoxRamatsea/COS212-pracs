public class PrimeNumberGenerator {
    PrimeNode head;

    @Override
    public String toString() {
        String res = head.toString();
        PrimeNode ptr = head.next;
        while (ptr != null) {
            res += "->" + ptr.toString();
            ptr = ptr.next;
        }
        return res;
    }

    public PrimeNumberGenerator() {
        head = new PrimeNode(2);
    }

    public int currentPrime() {
        return this.head.value;
    }

    public int nextPrime() {
        if (head.next == null) {
            sieveOfEratosthenes();
        }
        head = head.next;
        return this.head.value;
    }

    public void sieveOfEratosthenes() {
        boolean notPrime[] = new boolean[(head.value * 2) + 1];
        for (int i = 0; i < notPrime.length; i++) {
            notPrime[i] = false;
        }
        int jump = 2;
        while (jump < notPrime.length) {
            int counter = jump + jump;
            while (counter < notPrime.length) {
                notPrime[counter] = true;
                counter = counter + jump;
            }
            jump++;
        }
        for (int i = 0; i < notPrime.length; i++) {
            if (!notPrime[i] && i != 0 && i != 1) {
                if (!contains(head, i) && i > head.value) {
                    insert(head, i);
                }
            }
        }
    }

    private void insert(PrimeNode node, int i) {
        if (node.next == null) {
            PrimeNode newNode = new PrimeNode(i);
            node.next = newNode;
        } else {
            insert(node.next, i);
        }
    }

    public boolean contains(PrimeNode node, int value) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }
        return contains(node.next, value);
    }

    public PrimeNode getTail(PrimeNode node) {
        if (node.next == null) {
            return node;
        } else {
            return getTail(node.next);
        }
    }
}
