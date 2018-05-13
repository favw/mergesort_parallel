import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        int n = 1000000;
        int maxValue = Integer.MAX_VALUE;
        Random rand = new Random();

        int[] randomArray = new int[n]; //Array with 1.000.000 slots
        for (int i = 0; i < n; i++) {
            randomArray[i] = rand.nextInt(maxValue); //Filling array with random values
        }


        // 4 Threads
        ParallelMergeSort.numberOfThreads = 4;
        long timeStart = System.nanoTime(); //Time measurement
        ParallelMergeSort.mergeSort(randomArray); //Initiates sorting
        long timeEnd = System.nanoTime();
        long timeDiff =  timeEnd - timeStart;

        System.out.println("Parallel MergeSort with 4 Threads took: " + timeDiff + " nanoseconds.");
    }
}
