package adt;

import java.util.Iterator;

/*
 * made by: Heng Hao Zhe
 */
public interface Set<T> extends Collection<T> {
  // return size of set
  int size();

  // return true if set is empty
  boolean isEmpty();

  // return true if set contain object
  boolean contains(Object obj);

  // override iterator function in iterable interface
  Iterator<T> iterator();

  // return set as object array
  Object[] toArray();

  // return set as array following the class of the array given
  <A> A[] toArray(A[] a);

  // add element uniquely (must check if not exist)
  boolean add(T t);

  // remove the object found in the set
  // return true if success
  boolean remove(Object obj);

  // remove all element
  void clear();

  // return true if object is equal to this set
  boolean equals(Object obj);

  // return new set with elements from both set
  Set<T> union(Set<T> otherSet);

  // return new set with overlapped elements from both set
  Set<T> intersection(Set<T> otherSet);

  // return new set with non-overlapped elements from both set
  Set<T> difference(Set<T> otherSet);

  // check if otherSet is subset of this set
  // return true if is subset
  boolean isSubset(Set<T> otherSet);
}
