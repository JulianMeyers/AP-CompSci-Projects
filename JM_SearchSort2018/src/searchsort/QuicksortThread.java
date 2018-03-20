package searchsort;

public class QuicksortThread extends AbstractSearchSortThread {
    public QuicksortThread(BarArray barArray, StatsThread stats, ThreadCompletedDelegate del) {
        super("QuicksortThread", barArray, stats, del);
    }

    @Override
    public int executeAlgorithm() throws InterruptedException {
        int numBars = mainArray.size();
        sort(0, numBars-1);
        return STATUS_FINISHED;
    }

    private void quickSort(int lowerIndex, int higherIndex) throws InterruptedException {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        SortableBar pivot = mainArray.get(lowerIndex+(higherIndex-lowerIndex)/2);
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

    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    int partition(int low, int high) throws InterruptedException {
        ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("low", low); //lets the graphics know that the "i" variable has changed.
        ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("high", high); //lets the graphics know that the "i" variable has changed.
        SortableBar pivot = mainArray.get(high);//was arr.get(high)
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (mainArray.get(j).compareTo(pivot) <= 0)
            {
                i++;

                // swap arr[i] and arr[j]
                mainArray.swap(i,j);
            }
            checkIn();
        }

        // swap arr[i+1] and arr[high] (or pivot)
        mainArray.swap(i+1, high);

        return i+1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void sort(int low, int high) throws InterruptedException {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(low, pi-1);
            sort(pi+1, high);
        }
    }


}
