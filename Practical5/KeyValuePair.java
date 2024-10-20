public class KeyValuePair {
    public int studentNumber;
    public int mark;

    public KeyValuePair(int sN, int m) {
        studentNumber = sN;
        mark = m;
    }

    @Override
    public String toString() {
        return "u" + studentNumber + ":" + mark + "%";
    }
}
