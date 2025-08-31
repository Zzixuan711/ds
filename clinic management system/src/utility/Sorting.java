package utility;

import adt.List;
import adt.ArrayList;

/*
 * the sorting functions in this static class can only work on primitive data type and their enclosing class only (unless the class uses comparable interface)
 * the sorting only support the list interface made by ourselves (not java.util)
 * if need more function do tell me
 * 
 * list of functions
 * binInsertSort : List<T>, T[], int[]
 * quickSort : List<T>, T[], int[]
 * 
 * when using quickSort if the console keeps printing until you can't find the top of the error, something like "at utility.Sorting.quickSort(Sorting.java:156)"
 * it means it reach recursion limit and do use the binInsertSort instead
 * 
 * made by: Heng Hao Zhe
 */
public class Sorting {
  private static void arrayShift(int[] arr, int i) {
    System.arraycopy(arr, i + 1, arr, i, arr.length - 1 - i);
  }

  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  private static <T> void swap(T[] arr, int i, int j) {
    T tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  private static <T> void swap(List<T> arr, int i, int j) {
    T tmp = arr.set(i, arr.get(j));
    arr.set(j, tmp);
  }

  public static void binInsertSort(int[] arr) {
    int i, loc, j;
    int selected;
    for (i = 1; i < arr.length; i++) {
      j = i - 1;
      selected = arr[i];
      loc = binSearch(arr, 0, j, selected);
      arrayShift(arr, i);
      arr[loc] = selected;
    }
  }

  public static <T extends Comparable<T>> void binInsertSort(List<T> arr) {
    int i, loc, j;
    T selected;
    for (i = 1; i < arr.size(); i++) {
      j = i - 1;
      selected = arr.get(i);
      loc = binSearch(arr, 0, j, selected);
      arr.remove(i);
      arr.add(loc, selected);
    }
  }

  public static <T extends Comparable<T>> void binInsertSort(T[] arr) {
    int i, loc, j;
    T selected;
    ArrayList<T> buffer = new ArrayList<T>();
    for (T elem : arr) {
      buffer.add(elem);
    }
    for (i = 1; i < buffer.size(); i++) {
      j = i - 1;
      selected = buffer.get(i);
      loc = binSearch(buffer, 0, j, selected);
      buffer.remove(i);
      buffer.add(loc, selected);
    }
    for (int k = 0; k < arr.length; k++) {
      arr[k] = buffer.get(k);
    }
  }

  private static <T extends Comparable<T>> int binSearch(List<T> arr, int start, int end, T searchObj) {
    while (start <= end) {
      int pivot = start + (end - start) / 2;
      if (searchObj.compareTo(arr.get(pivot)) == 0)
        return pivot + 1;
      else if (searchObj.compareTo(arr.get(pivot)) > 0)
        start = pivot + 1;
      else
        end = pivot - 1;
    }
    return start;
  }

  private static int binSearch(int[] arr, int start, int end, int searchObj) {
    int pivot;
    while (start <= end) {
      pivot = start + (end - start) / 2;
      if (searchObj == arr[pivot])
        return pivot + 1;
      else if (searchObj > arr[pivot])
        start = pivot + 1;
      else
        end = pivot - 1;
    }
    return start;
  }
  
  // look at the top if unknown errors is found
  public static <T extends Comparable<T>> void quickSort(T[] arr) {
    quickSort(arr, 0, arr.length);
  }

  private static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    QuickSortBoundary pivot = partition(arr, low, high);
    quickSort(arr, low, pivot.low);
    quickSort(arr, pivot.high, high);
  }

  private static <T extends Comparable<T>> QuickSortBoundary partition(T[] arr, int low, int high) {
    if ((high - low) <= 1) {
      if (arr[high].compareTo(arr[low]) < 0) {
        swap(arr, low, high);
      }
      return new QuickSortBoundary(low, high);
    }
    int mid = low;
    T pivot = arr[high];
    while (mid <= high) {
      if (arr[mid].compareTo(pivot) < 0)
        swap(arr, low++, mid++);
      else if (arr[mid].compareTo(pivot) == 0)
        mid++;
      else if (arr[mid].compareTo(pivot) > 0)
        swap(arr, mid, high--);
    }
    return new QuickSortBoundary(low - 1, mid);
  }

  // look at the top if unknown errors is found
  public static <T extends Comparable<T>> void quickSort(List<T> arr) {
    quickSort(arr, 0, arr.size() - 1);
  }

  private static <T extends Comparable<T>> void quickSort(List<T> arr, int low, int high) {
    if (low >= high) {
      return;
    }
    QuickSortBoundary pivot = partition(arr, low, high);
    quickSort(arr, low, pivot.low);
    quickSort(arr, pivot.high, high);
  }

  private static <T extends Comparable<T>> QuickSortBoundary partition(List<T> arr, int low, int high) {
    if ((high - low) <= 1) {
      if (arr.get(high).compareTo(arr.get(low)) < 0) {
        swap(arr, low, high);
      }
      return new QuickSortBoundary(low, high);
    }
    int mid = low;
    T pivot = arr.get(high);
    while (mid <= high) {
      if (arr.get(mid).compareTo(pivot) < 0)
        swap(arr, low++, mid++);
      else if (arr.get(mid).compareTo(pivot) == 0)
        mid++;
      else if (arr.get(mid).compareTo(pivot) > 0)
        swap(arr, mid, high--);
    }
    return new QuickSortBoundary(low - 1, mid);
  }

  // look at the top if unknown errors is found
  public static void quickSort(int[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  private static void quickSort(int[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    QuickSortBoundary pivot = partition(arr, low, high);
    quickSort(arr, low, pivot.low);
    quickSort(arr, pivot.high, high);
  }

  private static QuickSortBoundary partition(int[] arr, int low, int high) {
    if ((high - low) <= 1) {
      if (arr[high] < arr[low]) {
        swap(arr, low, high);
      }
      return new QuickSortBoundary(low, high);
    }
    int mid = low;
    int pivot = arr[high];
    while (mid <= high) {
      if (arr[mid] < pivot)
        swap(arr, low++, mid++);
      else if (arr[mid] == pivot)
        mid++;
      else if (arr[mid] > pivot)
        swap(arr, mid, high--);
    }
    return new QuickSortBoundary(low - 1, mid);
  }

  private static class QuickSortBoundary {
    public int low, high;

    public QuickSortBoundary(int low, int high) {
      this.low = low;
      this.high = high;
    }

  }
}
