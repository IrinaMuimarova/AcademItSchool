import org.testng.Assert;
import org.testng.annotations.Test;

public class ListItemTest {

    @Test
    public void ListItem(){
        ListItem<Integer> listItem = new ListItem<>(3);
        Assert.assertEquals((int) listItem.getData(), 3);
    }

    @Test
    public void ListItem1(){
        ListItem<Integer> listItem1 = new ListItem<>(3);
        ListItem<Integer> listItem2 = new ListItem<>(9, listItem1);
        Assert.assertEquals((int) listItem2.getData(), 9);
        Assert.assertEquals(listItem2.getNext(), listItem1);
    }

    @Test
    public void getData() {
        ListItem<String> str = new ListItem<>("la-la-la");
        Assert.assertEquals((String) str.getData(), "la-la-la");
    }

    @Test
    public void setData() {
        ListItem<String> str = new ListItem<>("la-la-la");
        str.setData("na-na-na");
        Assert.assertEquals((String) str.getData(), "na-na-na");
    }

    @Test
    public void getNext() {
        ListItem<Integer> listItem1 = new ListItem<>(3);
        ListItem<Integer> listItem2 = new ListItem<>(9, listItem1);
        Assert.assertEquals(listItem2.getNext(), listItem1);
    }

    @Test
    public void setNext() {
        ListItem<Integer> listItem1 = new ListItem<>(3);
        ListItem<Integer> listItem2 = new ListItem<>(9, listItem1);
        ListItem<Integer> listItem3 = new ListItem<>(27, listItem2);
        listItem2.setNext(listItem3);
        Assert.assertEquals(listItem2.getNext(), listItem3);
    }
}