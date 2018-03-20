package searchsort;

public class SelectionSortThread extends AbstractSearchSortThread {


    public SelectionSortThread(BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del) {
        super("SelectionSortThread", barArrayToSort, stats, del);
    }

    @Override
    public int executeAlgorithm() throws InterruptedException {
        int numBars = mainArray.size();//n
        int i,j;
        System.out.println("I got there 2");

        /* advance the position through the entire array */
        /*   (could do j < n-1 because single element is also min element) */
        for (j = 0; j < numBars-1; j++)
        {
            System.out.println("I got here 1");
            /* find the min element in the unsorted a[j .. n-1] */

            /* assume the min is the first element */
            int iMin = j;
            /* test against elements after j to find the smallest */
            for (i = j+1; i < numBars; i++)
            {
                System.out.println("I got here");
                /* if this element is less, then it is the new minimum */
                if (mainArray.get(i).compareTo(mainArray.get(iMin)) < 0) {
                    /* found new minimum; remember its index */
                    iMin = i;
                }
                checkIn();
            }

            if (iMin != j)
            {
                mainArray.swap(j,iMin);
            }
        }
        return STATUS_FINISHED;
    }
}
