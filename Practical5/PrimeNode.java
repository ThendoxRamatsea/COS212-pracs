public class PrimeNode {
    public PrimeNode next;
    public int value;

    public PrimeNode(int v) {
        value = v;
        next = null;
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }
}
