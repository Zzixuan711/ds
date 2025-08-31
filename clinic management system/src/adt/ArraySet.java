package adt;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

/*
 * made by: Heng Hao Zhe
 * 
 * just in case you guys have some weird bug, it may be because this class does not support concurrent access
 * 
 * for using dat file only:
 * remember to add in serializable interface at your own class or dat file will not store anything
 */
public class ArraySet<T> implements Set<T>, Serializable {
  // used for serialization purpose
  // no need to care this value
  private static final long serialVersionUID = 1L;

  // array used to actually store objects
  // can be larger than list size for minor optimization purpose
  // protected for subclass access
  // transient keyword is for serialization purpose
  protected transient Object[] arr;

  // the actual size of the list
  private int size;

  // the default capacity of the array(not list)
  private static final int DEFAULT_CAPACITY = 10;

  // both are actually the same
  // just to differentiate when writing functions
  private static final Object[] EMPTY_ELEMENTDATA = {};
  private static final Object[] DEFAULT_EMPTY = {};

  // the limit of the list
  // not true limit to tolerate memory size
  private static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

  // used as separator when turning this list to string
  private String splitRegex = ",";

  public ArraySet() {
    this.arr = DEFAULT_EMPTY;
  }

  public ArraySet(int initialCapacity) {
    if (initialCapacity > 0) {
      this.arr = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
      this.arr = EMPTY_ELEMENTDATA;
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " +
          initialCapacity);
    }
  }

  // set string used to split between element when turning arraylist to string
  public void setSplit(String regex) {
    splitRegex = regex;
  }

  // get string used to split between element when turning arraylist to string
  public String getSplit() {
    return splitRegex;
  }

  // make sure array is down to size due to other operation saving allocation time
  public void trimToSize() {
    if (size < arr.length) {
      arr = (size == 0)
          ? EMPTY_ELEMENTDATA
          : Arrays.copyOf(arr, size);
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean contains(Object obj) {
    return indexOf(obj) >= 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new Itr();
  }

  // return new array with value of the list without any reference relation
  @Override
  public Object[] toArray() {
    return Arrays.copyOf(arr, size);
  }

  // return new array with value of the list without any reference relation
  // the new array will follow the class of the parameter
  // additional space from the given array will be ignored but not removed
  @SuppressWarnings("unchecked")
  @Override
  public <A> A[] toArray(A[] a) {
    if (a.length < size)
      // Make a new array of a's runtime type, but my contents:
      return (A[]) Arrays.copyOf(arr, size, a.getClass());
    System.arraycopy(arr, 0, a, 0, size);
    if (a.length > size)
      a[size] = null;
    return a;
  }

  public List<T> toList() {
    List<T> ans = new ArrayList<>();

    return ans;
  }

  // add the object "t" at the last position
  @Override
  public boolean add(T t) {
    if (contains(t))
      return false;
    add(t, arr, size);
    return true;
  }

  // remove the object if found and resize the array
  // return true if removed successful
  @Override
  public boolean remove(Object obj) {
    final Object[] es = arr;
    final int size = this.size;
    int i = 0;
    found: {
      if (obj == null) {
        for (; i < size; i++)
          if (es[i] == null)
            break found;
      } else {
        for (; i < size; i++)
          if (obj.equals(es[i]))
            break found;
      }
      return false;
    }
    fastRemove(es, i);
    return true;
  }

  @Override
  public void clear() {
    final Object[] es = arr;
    for (int to = size, i = size = 0; i < to; i++)
      es[i] = null;
  }

  @Override
  public Set<T> union(Set<T> otherSet) {
    Set<T> ans = new ArraySet<>();
    for (T elem : this) {
      ans.add(elem);
    }
    for (T elem : otherSet) {
      ans.add(elem);
    }
    return ans;
  }

  @Override
  public Set<T> intersection(Set<T> otherSet) {
    Set<T> ans = new ArraySet<>();
    for (T elem : this) {
      if (otherSet.contains(elem)) {
        ans.add(elem);
      }
    }
    return ans;
  }

  @Override
  public Set<T> difference(Set<T> otherSet) {
    Set<T> ans = union(otherSet);
    for (T elem : intersection(otherSet)) {
      ans.remove(elem);
    }
    return ans;
  }

  // check if otherSet is a subset of this set
  @Override
  public boolean isSubset(Set<T> otherSet) {
    if (otherSet == this) {
      return true;
    }
    if (!(otherSet instanceof Set<T>)) {
      return false;
    }
    if (otherSet.size() > size) {
      return false;
    }
    for (T t : otherSet) {
      if (!contains(t)) {
        return false;
      }
    }
    return true;
  }

  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Set)) {
      return false;
    }
    boolean equal = (obj.getClass() == ArraySet.class)
        ? equalsArraySet((ArraySet<?>) obj)
        : equalsRange((Set<?>) obj, 0, size);
    return equal;
  }

  // automatically return the list as string with the following format:
  // [elem1,elem2,elem3,elem4]
  // the string will automatically uses splitregex for separation
  public String toString() {
    return toString(splitRegex);
  }

  // automatically return the set as string with the following format:
  // [elem1,elem2,elem3,elem4]
  // the string will use the given splitregex for separation
  public String toString(String splitRegex) {
    String str = "[";
    for (int i = 0; i < size; i++) {
      str += arr[i] + splitRegex;
    }
    if (size > 0)
      str = str.substring(0, str.length() - 1);
    str += "]";
    return str;
  }

  // ----------------------------------------------------------------------------------
  // private separator
  // ----------------------------------------------------------------------------------

  private class Itr implements Iterator<T> {
    int cursor; // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such

    protected Itr() {
    }

    // check if there are more element left
    @Override
    public boolean hasNext() {
      return cursor != size;
    }

    // return the element at cursor and increase the cursor by 1
    @SuppressWarnings("unchecked")
    @Override
    public T next() {
      int i = cursor;
      if (i >= size)
        throw new NoSuchElementException();
      Object[] elementData = ArraySet.this.arr;
      cursor = i + 1;
      return (T) elementData[lastRet = i];
    }

    // remove the last element and move cursor by -1
    public void remove() {
      if (lastRet < 0)
        throw new IllegalStateException();
      try {
        ArraySet.this.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
      } catch (IndexOutOfBoundsException ex) {
        throw ex;
      }
    }

    // foreach loop starting from the cursor to the end
    public void forEachRemaining(Consumer<? super T> action) {
      Objects.requireNonNull(action);
      final int size = ArraySet.this.size;
      int i = cursor;
      if (i < size) {
        final Object[] es = arr;
        for (; i < size; i++)
          action.accept(elementAt(es, i));
        // update once at end to reduce heap write traffic
        cursor = i;
        lastRet = i - 1;
      }
    }
  }

  private boolean equalsRange(Set<?> other, int from, int to) {
    final Object[] es = arr;
    var oit = other.iterator();
    for (; from < to; from++) {
      if (!oit.hasNext() || !Objects.equals(es[from], oit.next())) {
        return false;
      }
    }
    return !oit.hasNext();
  }

  private boolean equalsArraySet(ArraySet<?> other) {
    final int s = size;
    boolean equal;
    if (equal = (s == other.size)) {
      final Object[] otherEs = other.arr;
      final Object[] es = arr;
      if (s > es.length || s > otherEs.length) {
        return false;
      }
      for (int i = 0; i < s; i++) {
        if (!Objects.equals(es[i], otherEs[i])) {
          equal = false;
          break;
        }
      }
    }
    return equal;
  }

  private void fastRemove(Object[] es, int i) {
    final int newSize;
    if ((newSize = size - 1) > i)
      System.arraycopy(es, i + 1, es, i, newSize - i);
    es[size = newSize] = null;
  }

  private void add(T e, Object[] elementData, int s) {
    if (s == elementData.length)
      elementData = grow();
    elementData[s] = e;
    size = s + 1;
  }

  private Object[] grow() {
    return grow(size + 1);
  }

  private Object[] grow(int minCapacity) {
    int oldCapacity = arr.length;
    if (oldCapacity > 0 || arr != DEFAULT_EMPTY) {
      int newCapacity = newLength(oldCapacity,
          minCapacity - oldCapacity, /* minimum growth */
          oldCapacity >> 1 /* preferred growth */);
      return arr = Arrays.copyOf(arr, newCapacity);
    } else {
      return arr = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }
  }

  private static int newLength(int oldLength, int minGrowth, int prefGrowth) {
    // preconditions not checked because of inlining
    // assert oldLength >= 0
    // assert minGrowth > 0

    int prefLength = oldLength + Math.max(minGrowth, prefGrowth); // might overflow
    if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
      return prefLength;
    } else {
      // put code cold in a separate method
      return hugeLength(oldLength, minGrowth);
    }
  }

  private static int hugeLength(int oldLength, int minGrowth) {
    int minLength = oldLength + minGrowth;
    if (minLength < 0) { // overflow
      throw new OutOfMemoryError(
          "Required array length " + oldLength + " + " + minGrowth + " is too large");
    } else if (minLength <= SOFT_MAX_ARRAY_LENGTH) {
      return SOFT_MAX_ARRAY_LENGTH;
    } else {
      return minLength;
    }
  }

  @SuppressWarnings("unchecked")
  protected T elementData(int index) {
    return (T) arr[index];
  }

  @SuppressWarnings("unchecked")
  static <T> T elementAt(Object[] es, int index) {
    return (T) es[index];
  }

  protected int indexOf(Object obj) {
    return indexOfRange(obj, 0, size);
  }

  protected int indexOfRange(Object o, int start, int end) {
    Object[] es = arr;
    if (o == null) {
      for (int i = start; i < end; i++) {
        if (es[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = start; i < end; i++) {
        if (o.equals(es[i])) {
          return i;
        }
      }
    }
    return -1;
  }
}
