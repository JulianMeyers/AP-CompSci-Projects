package searchsort;

public class MergeSortThread extends AbstractSearchSortThread {
    public MergeSortThread( BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del) {
        super("MergeSortThread", barArrayToSort, stats, del);
    }

    @Override
    public int executeAlgorithm() throws InterruptedException {
        int numBars = mainArray.size();
        sort(mainArray, 0, numBars - 1);
        return STATUS_FINISHED;
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(BarArray arr, int l, int m, int r) throws InterruptedException {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
//        BarArray L = new BarArray(n1);
//        BarArray R = new BarArray(n2);
//
//        /*Copy data to temp arrays*/
//
//        for (int i=0; i<n1; ++i)
//            L.set(i,arr.get(l+i));
//        for (int j=0; j<n2; ++j)
//            R.set(j, arr.get(m + 1 + j));

        BarArray L = arr.subarray(l,l+n1);
        BarArray R = arr.subarray(m+1,m+n2+1);

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L.get(i).compareTo(R.get(j)) <= 0)
            {
                arr.set(k, L.get(i));
                i++;
            }
            else
            {
                arr.set(k,R.get(j));
                j++;
            }
            k++;
            checkIn();
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr.set(k,L.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr.set(k, R.get(j));
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(BarArray arr, int l, int r) throws InterruptedException {
        if (l < r) {
            checkIn();
            // Find the middle point
            int m = (l + r) / 2;
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("m", m);

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
}
