package com.example.jasonhu.triviaapp;

public class SimpleLinkedList<E> {
    class Item {
        E value;
        Item next;
        Item(E setValue, Item setNext) {
            value = setValue;
            next = setNext;
        }
    }
    private Item start;
    private int currentSize;

    public SimpleLinkedList() { }

    public SimpleLinkedList(E[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            this.add(0, array[i]);
        }
    }

    public void add(int index, E toAdd) {
        if (index == 0) {
            start = new Item(toAdd, start);
            currentSize++;
            return;
        }
        Item previousItem = getItem(index - 1);
        if (previousItem == null) {
            return;
        }
        Item newItem = new Item(toAdd, previousItem.next);
        previousItem.next = newItem;
        currentSize++;
    }

    public E remove(int index) {
        E toReturn;
        if (index == 0) {
            toReturn = start.value;
            start = start.next;
            return toReturn;
        }
        Item previousItem = getItem(index - 1);
        toReturn = previousItem.next.value;
        previousItem.next = previousItem.next.next;
        return toReturn;
    }

    public E get(int index) {
        Item item = getItem(index);
        if (item == null) {
            return null;
        } else {
            return item.value;
        }
    }

    public void set(int index, E toSet) {
        Item item = getItem(index);
        if (item != null) {
            item.value = toSet;
        }
    }

    public int size() {
        return currentSize;
    }

    protected Item getItem(int index) {
        if (index < 0 || index >= currentSize) {
            return null;
        }
        int currentIndex = 0;
        for (Item current = start; current != null; current = current.next) {
            if (currentIndex == index) {
                return current;
            }
            currentIndex++;
        }
        return null;
    }

    protected Item getStart() {
        return start;
    }
}

