package ru.mujmarova.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.mujmarova.ListItem;
import ru.mujmarova.SinglyLinkedList;

public class SinglyLinkedListTest {

    private ListItem<Integer> i4;
    private SinglyLinkedList<Integer> list;

    @BeforeTest
    public void initObjects() {
        ListItem<Integer> i1 = new ListItem<>(1);
        ListItem<Integer> i2 = new ListItem<>(2);
        ListItem<Integer> i3 = new ListItem<>(3);
        i4 = new ListItem<>(4);
        ListItem<Integer> i5 = new ListItem<>(5);
        ListItem<Integer> i6 = new ListItem<>(6);
        ListItem<Integer> i7 = new ListItem<>(7);
        ListItem<Integer> i8 = new ListItem<>(8);

        list = new SinglyLinkedList<>();
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        list.add(i5);
        list.add(i6);
        list.add(i7);
        list.add(i8);
    }

    @Test
    public void getCount() {
        initObjects();
        Assert.assertEquals(list.getCount(), 8);
    }

    @Test
    public void getHead() {
        initObjects();
        Assert.assertEquals((int) list.getHead().getData(), 1);
    }

    @Test
    public void getItem() {
        initObjects();
        Assert.assertEquals(list.getItem(3), i4);
    }

    @Test
    public void setItem() {
        initObjects();
        list.setItem(2, 9);
        Assert.assertEquals((int) list.getItem(2).getData(), 9);
    }

    @Test
    public void remove() {
        initObjects();
        list.remove(0);
        Assert.assertEquals(list.getCount(), 7);
        Assert.assertEquals(list.toString(), "[2, 3, 4, 5, 6, 7, 8]");
    }

    @Test
    public void addIndex() {
        initObjects();
        list.add(3, new ListItem<>(99));
        Assert.assertEquals(list.toString(), "[1, 2, 3, 99, 4, 5, 6, 7, 8]");
    }

    @Test
    public void removeObject() {
        initObjects();
        boolean isRemove = list.remove((Integer) 99);
        Assert.assertEquals(list.toString(), "[1, 2, 3, 4, 5, 6, 7, 8]");
        Assert.assertFalse(isRemove);

        isRemove = list.remove((Integer) 7);
        Assert.assertEquals(list.toString(), "[1, 2, 3, 4, 5, 6, 8]");
        Assert.assertTrue(isRemove);

    }

    @Test
    public void cloneTest() {
        initObjects();
        SinglyLinkedList<Integer> listClone = list.copy();
        Assert.assertEquals(listClone.toString(), "[1, 2, 3, 4, 5, 6, 7, 8]");
    }

    @Test
    void spreadTest() {
        initObjects();
        list.spread();
        Assert.assertEquals(list.toString(), "[8, 7, 6, 5, 4, 3, 2, 1]");
    }

    @Test
    void setItemTest() {
        initObjects();
        Assert.assertEquals(list.setItem(4, 4), (Integer) 5);
    }

    @Test
    void removeTest() {
        initObjects();
        Assert.assertEquals(list.remove(3), (Integer) 4);
    }
}