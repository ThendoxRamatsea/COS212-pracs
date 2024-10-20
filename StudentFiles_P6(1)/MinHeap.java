class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap() {
        super();
    }

    public MinHeap(T[] array) {
        super(array);
    }

    @Override
    protected boolean compare(Comparable<T> child, Comparable<T> parent) {
        return child.compareTo((T) parent) < 0;

    }
}