public class MergeSortThread implements Runnable{
    private int[] arr;
    private int threadAmount;
    private int low;
    private int high;
    public MergeSortThread(int[] array, int low, int high, int threadAmount){
        this.low = low;
        this.high = high;
        this.arr = array;
        this.threadAmount = threadAmount;
    }

    @Override
    public void run() {
        ParallelMergeSort.mergeSort(arr,low, high, threadAmount);
    }
}
