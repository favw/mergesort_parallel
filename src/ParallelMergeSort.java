import java.util.Arrays;

public class ParallelMergeSort {
 public static int numberOfThreads = 8;

        public static void mergeSort(int[] arr) {
            mergeSort(arr, 0, arr.length - 1, numberOfThreads);
        }
    public static void mergeSort(int[] arr, int low, int high, int threadAmount) {
            if(threadAmount==1){
                NormalMergeSort.mergeSort(arr, low, high);
            } else if(numberOfThreads==0){
                throw new RuntimeException("something went wrong"); //TODO evt. weglassen? Wann tritt der Fehler, außer bei falscher Nutzereingabe, auf?
            } else {
                if(low<high) {
                    /** Threads starten rekursiv mergeSort() bis zum nicht rekursiven Fall dass
                     * threadAmount == 1 (= Es kann nicht weiter gesplittet werden) ist,
                     * bzw. wird dann der normale mergeSort() ausgeführt
                     */
                    int mid = (low + high) / 2;
                    Thread rechterThread = new Thread(new MergeSortThread(arr, low, mid, threadAmount / 2));
                    Thread linkerThread = new Thread(new MergeSortThread(arr, mid+1, high, threadAmount / 2));
                    rechterThread.run();
                    linkerThread.run();
                    NormalMergeSort.merge(arr, low, mid, high); //Arrays werden wieder zusammengefügt. TODO merge selber schreiben?
                }
            }
    }
}
