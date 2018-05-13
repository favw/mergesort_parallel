
public class ParallelMergeSort {
    public static int numberOfThreads = 8;

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1, numberOfThreads);
    }

    public static void mergeSort(int[] arr, int low, int high, int threadAmount) {
        switch (numberOfThreads) {
            case 1:
                NormalMergeSort.mergeSort(arr, low, high);
            case 0:
                throw new RuntimeException("something went wrong");
                //TODO maybe unnecessary? When does this error occur?
            default:
                if (low < high) {
                    /** threads recursively start mergeSort(int[] arr, int low, int high, int threadAmount)
                     *  until numberOfThreads==1(= can't split the array any further)
                     *  and use the "normal" mergeSort right after.
                     */
                    int mid = (low + high) / 2;
                    Thread rightThread = new Thread(new MergeSortThread(arr, low, mid, threadAmount / 2));
                    Thread leftThread = new Thread(new MergeSortThread(arr, mid + 1, high, threadAmount / 2));
                    rightThread.run();
                    leftThread.run();
                    NormalMergeSort.merge(arr, low, mid, high);
                }
        }
    }
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
