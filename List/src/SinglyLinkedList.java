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

    public ListItem<T> remove(int index) {
        checkElementIndex(index);
        ListItem<T> itemRemove = getItem(index);
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
        String str = "[";
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getNext() == null) {
                str += p.getData();
            } else {
                str += p.getData() + ", ";
            }
        }
        str += "]";
        return str;
    }

    public SinglyLinkedList<T> clone() {
        SinglyLinkedList<T> clone = new SinglyLinkedList<>();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            ListItem<T> temp = new ListItem<>(p.getData());
            clone.add(temp);
        }
        return clone;
    }

    public void spread() {
        int count = 0;
        ListItem<T> temp;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            temp = p;
            ListItem<T> nextItem = p.getNext();
            p = p.getNext().getNext();
            if (count == 1) {
                temp.setNext(null);
            }
            if (p.getNext() == null) {
                p.setNext(nextItem);
                head = p;
            }
            nextItem.setNext(temp);
            count++;
        }

    }
}
