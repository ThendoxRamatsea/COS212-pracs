public class CustomClass implements Comparable<CustomClass> {
    private int value;

    public CustomClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int compareTo(CustomClass other) {
        return Integer.compare(this.value, other.getValue());
    }
}
