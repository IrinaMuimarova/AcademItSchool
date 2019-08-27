package ru.mujmarova.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.mujmarova.SinglyLinkedList;

import java.util.Arrays;

public class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> list;

    @BeforeTest
    public void initObjects() {

        list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
    }

    @Test
    public void getCount() {
        initObjects();
        Assert.assertEquals(list.getCount(), 8);
    }

    @Test
    public void getHead() {
        initObjects();
        Assert.assertEquals((int) list.getFirstItem(), 1);
    }

    @Test
    public void getItem() {
        initObjects();
        Assert.assertEquals(list.getItemData(3),(Integer) 4);
    }

    @Test
    public void setItem() {
        initObjects();
        list.setItem(2, 9);
        Assert.assertEquals((int) list.getItemData(2), 9);
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
        list.add(3, 99);
        Assert.assertEquals(list.toString(), "[1, 2, 3, 99, 4, 5, 6, 7, 8]");

        list.add(0, 0);
        Assert.assertEquals(list.toString(), "[0, 1, 2, 3, 99, 4, 5, 6, 7, 8]");

        list.add(10, 10);
        Assert.assertEquals(list.toString(), "[0, 1, 2, 3, 99, 4, 5, 6, 7, 8, 10]");
    }

    @Test
    public void removeObject() {
        initObjects();
        list.add(null);
        boolean isRemove = list.remove((Integer) 99);
        Assert.assertEquals(list.toString(), "[1, 2, 3, 4, 5, 6, 7, 8, null]");
        Assert.assertFalse(isRemove);

        isRemove = list.remove(null);
        Assert.assertEquals(list.toString(), "[1, 2, 3, 4, 5, 6, 7, 8]");
        Assert.assertTrue(isRemove);

        isRemove = list.remove((Integer)7);
        Assert.assertEquals(list.toString(), "[1, 2, 3, 4, 5, 6, 8]");
        Assert.assertTrue(isRemove);
    }

    @Test
    public void cloneTest() {
        initObjects();
        SinglyLinkedList<Integer> listClone = list.copy();
        Assert.assertEquals(listClone.toString(), "[1, 2, 3, 4, 5, 6, 7, 8]");
        int[] array = new int[]{1,2,3,4};
        System.out.println(Arrays.binarySearch(array, -21));
    }

    @Test
    void spreadTest() {
        initObjects();
        list.invert();
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