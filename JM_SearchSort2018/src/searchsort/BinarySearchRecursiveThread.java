package searchsort;

public class BinarySearchRecursiveThread extends AbstractSearchThread{


    public BinarySearchRecursiveThread(BarArray bars, StatsThread stats, ThreadCompletedDelegate del, double searchValue) {
        super("BinarySearchThread", bars, stats, del, searchValue);
    }

    @Override
    public int executeAlgorithm() throws InterruptedException {

        int n = mainArray.size();
        int l = 0;
        int r = n-1;
        return sort(l,r);
    }

    public int sort(int l, int r){
        if (l > r) {
            announceMissedBar();
            return STATUS_FINISHED_FAILED;
        }
        int m = (l + r)/2;
        ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("l", l);
        ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("m", m);
        if (mainArray.get(m).compareTo(searchTarget) < 0) {
            return sort(m + 1,r);
        }else if (mainArray.get(m).compareTo(searchTarget) > 0) {
            return sort(l, m-1);
        } else {
            announceFoundBar(mainArray.get(m),m);
            return STATUS_FINISHED_FOUND;
        }
    }
}
