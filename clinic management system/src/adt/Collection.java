package adt;

import java.util.Iterator;

/*
 * made by: Heng Hao Zhe
 * 
 * this should be the basic interface for all collection adt
 * this is as close as i would like to code to java's collection interface, there are missing functionalities
 * it would be good to extend this interface in your own collection adt (not all, do lookup what adt need collection interface)
 */
public interface Collection<T> extends Iterable<T> {
  // return size of collection
  int size();

  // return true if collection is empty
  boolean isEmpty();

  // return true if collection contain object
  boolean contains(Object obj);

  // override iterator function in iterable interface
  Iterator<T> iterator();

  // return collection as object array
  Object[] toArray();

  // return collection as array following the class of the array given
  <A> A[] toArray(A[] a);

  // must be override by subclass
  // must throw exception if subclass is not concrete class
  boolean add(T t);

  // remove the first equal object in the collection
  // return true if success
  boolean remove(Object obj);

  // remove all element
  void clear();

  // return true if object is equal to this collection
  boolean equals(Object obj);
}
