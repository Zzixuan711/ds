package adt;

import java.util.Iterator;

/*
 * made by: Heng Hao Zhe
 */
public interface List<T> extends Collection<T> {
  // return list size
  int size();

  // return element at index
  T get(int index);

  // set element at index
  // return previous element at index
  T set(int index, T element);

  // replace the element using object as search instead of index
  default T replace(T oldElem, T newElem) {
    return this.set(this.indexOf(oldElem), newElem);
  }

  // replace all found element
  void replaceAll(T oldElem, T newElem);

  // return true if list is empty
  boolean isEmpty();

  // return true if list contains object
  boolean contains(Object obj);

  // return new array with only list element's value
  // new copy not referenced
  Object[] toArray();

  // return new array with only list element's value
  // returned array will follow the given array's type
  <A> A[] toArray(A[] a);

  // add new element at back of list
  // return true if the adding is successful
  boolean add(T t);

  // add new element at index and push existing elements backwards
  void add(int index, T element);

  // remove the given object from the list
  // return true if remove is successful
  boolean remove(Object obj);

  // remove element at the index
  // return the element
  T remove(int index);

  // remove all element in the list
  void clear();

  // check between two list
  // return true if the two list is exactly the same in terms of values
  // return false if the new list is not instance of this list
  boolean equals(Object obj);

  // return the first occurrence(starting from front) index of the given object
  // return -1 when there are no occurrence found
  int indexOf(Object obj);

  // return the last occurrence(starting from back) index of the given object
  // return -1 when there are no occurrence found
  int lastIndexOf(Object obj);

  // automatically add the given element to the front of the list
  default void addFirst(T t) {
    this.add(0, t);
  }

  // automatically add the given element to the back of the list
  default void addLast(T t) {
    this.add(t);
  }

  // return the first element in the list
  // return null if unavailable
  default T getFirst() {
    if (this.isEmpty()) {
      return null;
    }
    return this.get(0);
  }

  // return the last element in the list
  // return null if unavailable
  default T getLast() {
    if (this.isEmpty()) {
      return null;
    }
    return this.get(this.size() - 1);
  }

  // remove and return the first element in the list
  // return null if unavailable
  default T removeFirst() {
    if (this.isEmpty()) {
      return null;
    }
    return this.remove(0);
  }

  // remove and return the last element in the list
  // return null if unavailable
  default T removeLast() {
    if (this.isEmpty()) {
      return null;
    }
    return this.remove(this.size() - 1);
  }

  // return the iterator of the list
  Iterator<T> getIterator();

}
