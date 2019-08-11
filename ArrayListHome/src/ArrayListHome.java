import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static ArrayList<String> readFile(String filename) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(filename))) {
            ArrayList<String> list = new ArrayList<>();

            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }

            return list;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла. " + e);
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

    public static void main(String[] args) throws IOException {
        System.out.println(readFile("C:\\test\\out.txt"));

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


