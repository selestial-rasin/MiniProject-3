package com.training.algorithmtoolkit;

import java.util.*;

public class SortingUtil {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

class SearchUtil {
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}

class CollectionUtil<T> {
    private Stack<T> stack = new Stack<>();
    private Queue<T> queue = new LinkedList<>();

    public void push(T value) { stack.push(value); }
    public T pop() { return stack.isEmpty() ? null : stack.pop(); }
    public T peekStack() { return stack.isEmpty() ? null : stack.peek(); }

    public void enqueue(T value) { queue.add(value); }
    public T dequeue() { return queue.isEmpty() ? null : queue.poll(); }
    public T peekQueue() { return queue.isEmpty() ? null : queue.peek(); }
}

class AnalysisUtil {
    public static long timer(Runnable algorithm) {
        long start = System.nanoTime();
        algorithm.run();
        long end = System.nanoTime();
        return end - start;
    }
}

class TestAlgorithmToolkit {
    public static void main(String[] args) {
        int[] arr1 = {5, 2, 9, 1};
        SortingUtil.bubbleSort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {8, 3, 6, 2};
        SortingUtil.mergeSort(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {2, 4, 6, 8, 10};
        System.out.println(SearchUtil.linearSearch(arr3, 6));
        System.out.println(SearchUtil.binarySearch(arr3, 8));

        CollectionUtil<String> cu = new CollectionUtil<>();
        cu.push("A");
        cu.push("B");
        System.out.println(cu.pop());

        cu.enqueue("X");
        cu.enqueue("Y");
        System.out.println(cu.dequeue());

        long timeTaken = AnalysisUtil.timer(() -> SortingUtil.quickSort(arr1, 0, arr1.length - 1));
        System.out.println(timeTaken);
    }
}
