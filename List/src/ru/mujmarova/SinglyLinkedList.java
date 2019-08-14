package ru.mujmarova;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size = 0;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return size;
    }

    public ListItem<T> getHead() {
        return head;
    }

    public ListItem<T> getItem(int index) {
        checkElementIndex(index);
        int count = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (count == index) {
                return p;
            }
            count++;
        }
        return null;
    }

    public T setItem(int index, T data) {
        int count = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (count == index) {
                T outItem = p.getData();
                p.setData(data);
                return outItem;
            }
            count++;
        }
        return null;
    }

    public void add(ListItem<T> listItem) {
        if (size == 0) {
            head = listItem;
            size++;
        } else {
            for (ListItem<T> p = head; p != null; p = p.getNext()) {
                if (p.getNext() == null) {
                    p.setNext(listItem);
                    size++;
                    break;
                }
            }
        }
    }

    public void add(int index, ListItem<T> listItem) {
        checkElementIndex(index);
        if (index == 0) {
            listItem.setNext(head);
            head = listItem;
            size++;
        } else if (index == size - 1) {
            add(listItem);
        } else {
            ListItem<T> nextItem = getItem(index);
            listItem.setNext(nextItem);
            ListItem<T> prevItem = getItem(index - 1);
            prevItem.setNext(listItem);
            size++;
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    public T remove(int index) {
        checkElementIndex(index);
        T itemRemove = getItem(index).getData();
        if (index == size - 1) {
            getItem(index - 1).setNext(null);
        } else if (index == 0) {
            head = getItem(1);
        } else {
            getItem(index - 1).setNext(getItem(index + 1));
        }
        size--;
        return itemRemove;
    }

    public boolean remove(T data) {
        int count = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getData().equals(data)) {
                ListItem<T> prevItem = getItem(count - 1);
                if (p.getNext() != null) {
                    prevItem.setNext(p.getNext());
                    size--;
                    return true;
                } else {
                    prevItem.setNext(null);
                    size--;
                    return true;
                }
            }
            count++;
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
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            ListItem<T> temp = new ListItem<>(p.getData());
            clone.add(temp);
        }
        return clone;
    }

    public void spread() {
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
