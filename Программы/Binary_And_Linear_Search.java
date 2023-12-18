import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Binary_And_Linear_Search {

    static int needed = 975000;
    static int wanted;
    static List<Integer> numbers = new ArrayList<>();
    static StringBuilder results_linear = new StringBuilder();
    static StringBuilder results_binary = new StringBuilder();
    static StringBuilder results_built_in = new StringBuilder();

    static void linear() {
        long startTime = System.nanoTime();
        for (int i = 0; i < needed; i++) {
            if (numbers.get(i) == wanted) {
                break;
            }
        }
        long endTime = System.nanoTime();
        long spent_time = endTime-startTime; // Divide by 1000000 to get milliseconds.
        results_linear.append(spent_time / 1000000).append(",").append((spent_time / 10000) % 100).append(";");
    }

    static void binary() {
        long startTime = System.nanoTime();
        int first = 0;
        int last = wanted;
        while (first < last) {
            int mid = (first + last) / 2;
            if (wanted == numbers.get(mid)) {
                break;
            } else if (wanted < numbers.get(mid)) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }
        long endTime = System.nanoTime();
        long spent_time = endTime - startTime; // Divide by 1000000 to get milliseconds.
        results_binary.append(spent_time / 1000000).append(",").append((spent_time / 10000) % 100).append(";");
    }

    static void built_in(){
        // В Java есть метод binarySearch() у объекта Arrays.
        Object[] for_search = numbers.toArray();
        long startTime = System.nanoTime();
        int found_item = Arrays.binarySearch(for_search, wanted);
        long endTime = System.nanoTime();
        long spent_time = endTime-startTime; // Divide by 1000000 to get milliseconds.
        results_built_in.append(spent_time / 1000000).append(",").append((spent_time / 10000) % 100).append(";");
    }

    public static void main(String[] args) {
        Random rand = new Random();
        while (needed < 7025000){
            results_linear.append(needed).append(";");
            results_binary.append(needed).append(";");
            results_built_in.append(needed).append(";");
            numbers = new ArrayList<>();
            for (int i = 0; i < needed; i++) numbers.add(i);
            int max = (int) (needed * 0.6);
            int min = (int) (needed * 0.4);
            // Middle
            wanted = rand.nextInt((max - min) + 1) + min;
            linear();
            binary();
            built_in();
            // Doesn't exist, -1
            wanted = needed;
            linear();
            binary();
            built_in();
            results_linear.append("\n");
            results_binary.append("\n");
            results_built_in.append("\n");
            System.out.println(needed);
            needed += 25000;
        }
        File outputFile = new File("Results Linear.txt");
        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(results_linear.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputFile = new File("Rusilts Binar.txt");
        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(results_binary.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputFile = new File("Rusilts Built In.txt");
        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(results_built_in.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
