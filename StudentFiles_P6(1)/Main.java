public class Main {
    public static void main(String[] args) {
        // Test MaxHeap
        Integer[] maxArray = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5 };
        MaxHeap<Integer> maxHeap = new MaxHeap<>(maxArray);
        System.out.println("MaxHeap:");
        System.out.println(maxHeap);
        maxHeap.push(10);
        System.out.println("After pushing 10:");
        System.out.println(maxHeap);
        System.out.println("Popped from MaxHeap: " + maxHeap.pop());
        System.out.println("After popping:");
        System.out.println(maxHeap);

        // Test MinHeap
        Integer[] minArray = { 8, 1, 7, 1, 0, 5, 3, 2, 9, 0, 4 };
        MinHeap<Integer> minHeap = new MinHeap<>(minArray);
        System.out.println("MinHeap:");
        System.out.println(minHeap);
        minHeap.push(0);
        System.out.println("After pushing 0:");
        System.out.println(minHeap);
        System.out.println("Popped from MinHeap: " + minHeap.pop());
        System.out.println("After popping:");
        System.out.println(minHeap);
    }
}
