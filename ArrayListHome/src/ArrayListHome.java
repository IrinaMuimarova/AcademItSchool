import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<String> readFile(String filename) {
        try (Scanner scanner = new Scanner(new FileReader(filename))) {
            ArrayList<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

            return list;
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден" + e);
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка. " + e);
        }

        return null;
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> removeDuplicateNumbers(ArrayList<Integer> list) {
        ArrayList<Integer> listOut = new ArrayList<>();
        for (Integer number : list) {
            if (!listOut.contains(number)) {
                listOut.add(number);
            }
        }
        return listOut;
    }

    public static void main(String[] args) {
        System.out.println(readFile("C:\\test\\out1.png"));

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        System.out.println(list);
        removeEvenNumbers(list);
        System.out.println(list);

        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 4, 6, 2, 1, 6, 9));

        System.out.println(removeDuplicateNumbers(list1));
    }
}


