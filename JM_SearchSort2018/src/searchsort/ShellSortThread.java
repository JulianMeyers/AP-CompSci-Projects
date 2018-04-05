package searchsort;

public class ShellSortThread extends AbstractSearchSortThread {
    public ShellSortThread( BarArray barArray, StatsThread stats, ThreadCompletedDelegate del) {
        super("ShellSortThread", barArray, stats, del);
    }

    @Override
    public int executeAlgorithm() throws InterruptedException {
        sort();
        return STATUS_FINISHED;
    }

    /* function to search arr using shellSort */
    void sort() throws InterruptedException {
        int n = mainArray.size();

        // Start with a big gap, then reduce the gap
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            // Do a gapped insertion search for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("gap", gap);
            for (int i = gap; i < n; i += 1)
            {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                SortableBar temp = mainArray.get(i);
                ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("i", i);

                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && mainArray.get(j-gap).compareTo(temp)>0; j -= gap) {
                    mainArray.set(j, mainArray.get(j - gap));
                    ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("j", j);
                    checkIn();
                }

                // put temp (the original a[i]) in its correct
                // location
                mainArray.set(j,temp);
                checkIn();
            }
        }
    }
}
