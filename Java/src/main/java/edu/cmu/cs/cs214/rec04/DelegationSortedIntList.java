package edu.cmu.cs.cs214.rec04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DelegationSortedIntList -- a variant of a SortedIntList that keeps
 * count of the number of attempted element insertions (not to be confused
 * with the current size, which goes down when an element is removed)
 * and exports an accessor (totalAdded) for this count.
 *
 * @author Nora Shoemaker
 */
public class DelegationSortedIntList implements IntegerList {
    // List to store the sorted integers
    private List<Integer> list;
    
    // To count the number of attempted insertions
    private int totalAdded;

    // Constructor
    public DelegationSortedIntList() {
        this.list = new ArrayList<>(); // create the internal list
        this.totalAdded = 0; // initialize totalAdded
    }

    @Override
    public boolean add(int num) {
        totalAdded++;  // Increment totalAdded every time an element is added
        
        // Insert the element while keeping the list sorted
        int index = Collections.binarySearch(list, num);
        if (index < 0) {
            index = -(index + 1); // Find the index where the element should be inserted
            list.add(index, num);
            return true;
        }
        return false; // Return false if the element already exists in the list
    }

    @Override
    public boolean addAll(IntegerList list) {
        boolean changed = false;
        for (int i = 0; i < list.size(); i++) {
            if (add(list.get(i))) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public int get(int index) {
        return list.get(index);
    }

    @Override
    public boolean remove(int num) {
        return list.remove(Integer.valueOf(num));
    }

    @Override
    public boolean removeAll(IntegerList list) {
        boolean changed = false;
        for (int i = 0; i < list.size(); i++) {
            if (remove(list.get(i))) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public int size() {
        return list.size();
    }

    /**
     * Returns the total number of attempted insertions into the list.
     *
     * @return the total number of insertions
     */
    public int getTotalAdded() {
        return totalAdded;
    }
}
