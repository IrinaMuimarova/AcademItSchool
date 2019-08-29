package ru.mujmarova;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private static final String LIST_EMPTY = "Список пуст";
    private ListItem<T> head;
    private int size = 0;

    public int getCount() {
        return size;
    }

    public T getFirstItem() {
        if (size == 0) {
            throw new NoSuchElementException(LIST_EMPTY);
        }
        return head.getData();
    }

    private ListItem<T> getItem(int index) {
        checkElementIndex(index);
        int count = 0;
        ListItem<T> temp = head;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (count == index) {
                temp = p;
            }
            count++;
        }
        return temp;
    }

    public T getItemData(int index) {
        checkElementIndex(index);
        ListItem<T> temp = getItem(index);
        return temp.getData();
    }

    private ListItem<T> getLastItem() {
        if (size == 0) {
            throw new NoSuchElementException(LIST_EMPTY);
        }

        return getItem(size - 1);
    }

    public T setItem(int index, T data) {
        checkElementIndex(index);
        ListItem<T> p = getItem(index);
        T outItem = null;
        if (p != null) {
            outItem = p.getData();
            p.setData(data);
        }
        return outItem;
    }

    public void add(T data) {
        if (size == 0) {
            head = new ListItem<>(data);
            size++;
        } else {
            ListItem<T> p = getLastItem();
            if (p != null) {
                p.setNext(new ListItem<>(data));
            }
            size++;
        }
    }

    public void add(int index, T data) {
        if (index == size) {
            add(data);
            return;
        }

        checkElementIndex(index);
        ListItem<T> temp = new ListItem<>(data);
        if (index == 0) {
            temp.setNext(head);
            head = temp;
            size++;
        } else {
            ListItem<T> prevItem = getItem(index - 1);
            if (prevItem != null) {
                ListItem<T> nextItem = prevItem.getNext();
                temp.setNext(nextItem);
                prevItem.setNext(temp);
            }
            size++;
        }

    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(getOutOfBoundsMsg(index));
        }
    }

    private String getOutOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    public T remove(int index) {
        checkElementIndex(index);
        T itemRemove = Objects.requireNonNull(getItem(index)).getData();
        if (index == 0) {
            if (size == 1) {
                head = null;
            } else {
                head = getItem(1);
            }
        } else {
            ListItem<T> prevElement = getItem(index - 1);
            prevElement.setNext(prevElement.getNext().getNext());
        }
        size--;
        return itemRemove;
    }

    public boolean remove(T data) {
        ListItem<T> prevItem = head;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (Objects.equals(data, p.getData())) {
                if (p.getNext() != null) {
                    if (prevItem == head) {
                        head = p.getNext();
                    } else {
                        prevItem.setNext(p.getNext());
                    }
                    size--;
                    return true;
                } else {
                    if (prevItem == head) {
                        head = null;
                    } else {
                        prevItem.setNext(null);
                    }
                    size--;
                    return true;
                }
            }
            prevItem = p;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getNext() == null) {
                str.append(p.getData());
            } else {
                str.append(p.getData()).append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> clone = new SinglyLinkedList<>();
        clone.head = new ListItem<>(this.head.getData());
        ListItem<T> prevItem = clone.head;
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> temp = new ListItem<>(p.getData());
            prevItem.setNext(temp);
            prevItem = temp;
        }
        return clone;
    }

    public void reverse() {
        if (size == 0 || size == 1) {
            return;
        }

        ListItem<T> firstItem = head;
        ListItem<T> secondItem = firstItem.getNext();
        ListItem<T> thirdItem = secondItem.getNext();

        firstItem.setNext(null);
        secondItem.setNext(firstItem);

        ListItem<T> current = thirdItem;
        ListItem<T> previous = secondItem;

        while (current != null) {
            ListItem<T> nextItem = current.getNext();
            current.setNext(previous);
            previous = current;
            current = nextItem;
        }

        head = previous;
    }
}
