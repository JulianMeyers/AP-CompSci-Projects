package searchsort;

import javax.swing.*;

public class QuicksortThread extends AbstractSearchSortThread {
    public QuicksortThread(BarArray barArray, StatsThread stats, ThreadCompletedDelegate del) {
        super("QuicksortThread", barArray, stats, del);
    }

    @Override
    public int executeAlgorithm() throws InterruptedException {
        int numBars = mainArray.size();
        quickSort(0, numBars-1);
        return STATUS_FINISHED;
    }

    private void quickSort(int lowerIndex, int higherIndex) throws InterruptedException {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivotPos = lowerIndex+(higherIndex-lowerIndex)/2;
        SortableBar pivot = mainArray.get(pivotPos);
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("i", i);
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("j", j);
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("h", higherIndex);
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("l", lowerIndex);
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("p",pivotPos);
            while (mainArray.get(i).compareTo(pivot) < 0) {
                i++;
                checkIn();
            }
            while (mainArray.get(j).compareTo(pivot) > 0) {
                j--;
                checkIn();
            }
            if (i <= j) {
                mainArray.swap(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
            checkIn();
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }


}
