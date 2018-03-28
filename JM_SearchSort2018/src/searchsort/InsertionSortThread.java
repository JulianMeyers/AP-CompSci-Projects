package searchsort;

public class InsertionSortThread extends  AbstractSearchSortThread{
    public InsertionSortThread( BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del) {
        super("InsertionSortThread", barArrayToSort, stats, del);
    }

    @Override
    public int executeAlgorithm() throws InterruptedException {
        int numBars = mainArray.size();//n
        int j;
        for(int i = 1; i < numBars; i++) {
            ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("i", i);
            j = i;
            while(j > 0 && mainArray.get(j-1).compareTo(mainArray.get(j))>0) {
                ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("j", j);
                mainArray.swap(j,j-1);
                j--;
                checkIn();
            }
        }
        return STATUS_FINISHED;
    }
}
