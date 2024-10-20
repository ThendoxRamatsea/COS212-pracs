public abstract class Heap<T extends Comparable<T>> {

    public Comparable<T>[] data;
    public int size;

    public Heap() {

        this.data = (Comparable<T>[]) new Comparable[2];
        size = 0;
    }

    public Heap(T[] array) {
        this.data = array;
        this.size = array.length;
        this.floydAlgorithm();

    }

    protected abstract boolean compare(Comparable<T> child, Comparable<T> parent);

    public void push(T item) {
        if (this.size == this.data.length) {
            T[] newData = (T[]) new Comparable[this.size * 2];
            System.arraycopy(this.data, 0, newData, 0, this.size);
            this.data = newData;
        }
        this.data[this.size] = item;
        this.size++;
        this.heapifyUp();
    }

    public Comparable<T> pop() {

        Comparable<T> root = this.data[0];
        this.data[0] = this.data[this.size - 1];
        this.size--;
        this.heapifyDown();
        return root;
    }

    public Comparable<T> peek() {
        return this.data[0];
    }

    /*
     * 
     * Functions used for the toString
     * Do not change them but feel free to use them
     * 
     */

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return this.getLeftChildIndex(index) < this.size;
    }

    private boolean hasRightChild(int index) {
        return this.getRightChildIndex(index) < this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(0, "", true, sb); // Start from the root
        return sb.toString();
    }

    private void buildString(int index, String prefix, boolean isTail, StringBuilder sb) {
        if (index < size) {
            String linePrefix = isTail ? "└── " : "┌── ";
            if (getRightChildIndex(index) < size) { // Check if there is a right child
                buildString(getRightChildIndex(index), prefix + (isTail ? "|   " : "    "), false, sb);
            }
            sb.append(prefix).append(linePrefix).append(data[index]).append("\n");
            if (getLeftChildIndex(index) < size) { // Check if there is a left child
                buildString(getLeftChildIndex(index), prefix + (isTail ? "    " : "│   "), true, sb);
            }
        }
    }

    private void floydAlgorithm() {
        for (int i = this.size / 2; i >= 0; i--) {
            this.heapifyDownFrom(i);
        }
    }

    private void heapifyUp() {
        int index = this.size - 1;
        while (index > 0 && this.compare(this.data[index], this.data[this.getParentIndex(index)])) {
            this.swap(index, this.getParentIndex(index));
            index = this.getParentIndex(index);
        }
    }

    private void heapifyDownFrom(int index) {
        while (this.hasLeftChild(index)) {
            int smallerChildIndex = this.getLeftChildIndex(index);
            if (this.hasRightChild(index)
                    && this.compare(this.data[this.getRightChildIndex(index)], this.data[smallerChildIndex])) {
                smallerChildIndex = this.getRightChildIndex(index);
            }
            if (!this.compare(this.data[index], this.data[smallerChildIndex])) {
                this.swap(index, smallerChildIndex);
            } else {
                break;
            }
            index = smallerChildIndex;
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (this.hasLeftChild(index)) {
            int smallerChildIndex = this.getLeftChildIndex(index);
            if (this.hasRightChild(index)
                    && this.compare(this.data[this.getRightChildIndex(index)], this.data[smallerChildIndex])) {
                smallerChildIndex = this.getRightChildIndex(index);
            }
            if (!this.compare(this.data[index], this.data[smallerChildIndex])) {
                this.swap(index, smallerChildIndex);
            } else {
                break;
            }
            index = smallerChildIndex;
        }
    }

    private void swap(int i, int j) {
        Comparable<T> temp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = temp;
    }

}