import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SinglyLinkedListTest {

    ListItem<Integer> i1;
    ListItem<Integer> i2;
    ListItem<Integer> i3;
    ListItem<Integer> i4;
    ListItem<Integer> i5;
    SinglyLinkedList<Integer> list;

    @BeforeTest
    public void initObjects(){
        i1 = new ListItem<>(1);
        i2 = new ListItem<>(2);
        i3 = new ListItem<>(3);
        i4 = new ListItem<>(4);
        i5 = new ListItem<>(5);
        list = new SinglyLinkedList<>();
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        list.add(i5);
    }

    @Test
    public void getCount() {
        Assert.assertEquals(list.getCount(), 5);
    }

    @Test
    public void getHead() {
        Assert.assertEquals((int)list.getHead().getData(), 1);
    }

    @Test
    public void getItem() {
        Assert.assertEquals(list.getItem(3), i4);
    }

    @Test
    public void setItem() {
        list.setItem(2, 9);
        Assert.assertEquals((int)list.getItem(2).getData(), 9);
    }

    @Test
    public void remove() {
        list.remove(0);
        Assert.assertEquals(list.getCount(), 4);
    }

    @Test
    public void addIndex(){
        list.add(3, new ListItem<Integer>(99));
        Assert.assertEquals(list.toString(), "[1, 2, 3, 99, 4, 5]" );
    }

    @Test
    public void removeObject(){
        boolean isRemove = list.remove((Integer) 99);
        System.out.println(list.toString());
        System.out.println(isRemove);
    }

    @Test
    public void cloneTest(){
     SinglyLinkedList<Integer> listClone = list.clone();
        System.out.println(listClone.toString());
    }

    @Test void spreadTest(){
        list.spread();
        System.out.println(list.toString());
    }
}