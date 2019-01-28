package data_structure;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {

    //Difficulty: medium
    //TAG: Apple
    //TAG: data structure

    /**
     * 284. Peeking Iterator
     * Given an Iterator class interface with methods: next() and hasNext(), design and implement a data_structure.PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
     *
     * Example:
     *
     * Assume that the iterator is initialized to the beginning of the list: [1,2,3].
     *
     * Call next() gets you 1, the first element in the list.
     * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
     * You call next() the final time and it returns 3, the last element.
     * Calling hasNext() after that should return false.
     * Follow up: How would you extend your design to be generic and work with all types, not just integer?
     */

    /*
    Solution:
    Due to next and peek will all return next but peek will not move to next
    what we can do by iterator api next() and hasNext() is keep a current next number (num)
    when peek return num
    when hasNext just check num
    when next 1. save the num to res 2. assign num to next() if null to null 3. return res
     */

    private Integer next;
    private Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        if (this.iterator.hasNext()) next = this.iterator.next();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = next;
        next = iterator.hasNext() ? iterator.next() : null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

}
