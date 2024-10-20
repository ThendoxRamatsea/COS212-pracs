public class RecursiveArray {
    public Integer[] array;

    public RecursiveArray() {
        this.array = new Integer[0];
    }

    public RecursiveArray(String input) {
        if (input.isEmpty()) {
            this.array = new Integer[0];
        } else {
            this.array = stringToArray(input, 0, input.split(",").length - 1);
        }
    }

    private Integer[] stringToArray(String input, int start, int end) {
        if (start > end) {
            return new Integer[0];
        } else {
            Integer[] arr = stringToArray(input, start + 1, end);
            Integer[] newArray = new Integer[arr.length + 1];
            newArray[0] = Integer.parseInt(input.split(",")[start].trim());
            System.arraycopy(arr, 0, newArray, 1, arr.length);
            return newArray;
        }
    }

    public void append(Integer value) {
        Integer[] newArray = new Integer[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        array = newArray;
    }

    public void prepend(Integer value) {
        Integer[] newArray = new Integer[array.length + 1];
        System.arraycopy(array, 0, newArray, 1, array.length);
        newArray[0] = value;
        array = newArray;
    }

    public boolean contains(Integer value) {
        return containsHelper(array, 0, array.length - 1, value);
    }

    private boolean containsHelper(Integer[] array, int start, int end, Integer value) {
        if (start > end) {
            return false;
        } else if (array[start].equals(value)) {
            return true;
        } else {
            return containsHelper(array, start + 1, end, value);
        }
    }

    public boolean isAscending() {
        return isAscendingHelper(array, 0, array.length - 1);
    }

    private boolean isAscendingHelper(Integer[] array, int start, int end) {
        if (start >= end) {
            return true;
        } else if (array[start] > array[start + 1]) {
            return false;
        } else {
            return isAscendingHelper(array, start + 1, end);
        }
    }

    public boolean isDescending() {
        return isDescendingHelper(array, 0, array.length - 1);
    }

    private boolean isDescendingHelper(Integer[] array, int start, int end) {
        if (start >= end) {
            return true;
        } else if (array[start] < array[start + 1]) {
            return false;
        } else {
            return isDescendingHelper(array, start + 1, end);
        }
    }

    public void sortAscending() {
        sortHelper(array, 0, array.length - 1, true);
    }

    public void sortDescending() {
        sortHelper(array, 0, array.length - 1, false);
    }

    private void sortHelper(Integer[] array, int start, int end, boolean ascending) {
        if (start < end) {
            int pivotIndex = partition(array, start, end, ascending);
            sortHelper(array, start, pivotIndex - 1, ascending);
            sortHelper(array, pivotIndex + 1, end, ascending);
        }
    }

    private int partition(Integer[] array, int start, int end, boolean ascending) {
        int pivot = array[end];
        int i = start - 1;

        for (int j = start; j < end; j++) {
            if ((ascending && array[j] <= pivot) || (!ascending && array[j] >= pivot)) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
    }

    public String toString() {
        if (array.length == 0) {
            return "Empty Array";
        } else {
            return "[" + arrayToString(array, 0, array.length - 1) + "]";
        }
    }

    private String arrayToString(Integer[] array, int start, int end) {
        if (start > end) {
            return "";
        } else if (start == end) {
            return array[start].toString();
        } else {
            return array[start] + "," + arrayToString(array, start + 1, end);
        }
    }
}
