package searchsort;

public class BozoSortThread extends AbstractSearchSortThread
{
    

    public BozoSortThread(BarArray barArrayToSort, StatsThread stats, ThreadCompletedDelegate del)
    {
        super("BozoSortThread",barArrayToSort, stats,del);
       

    }
    @Override
    public int executeAlgorithm() throws InterruptedException
    {
        int numBars = mainArray.size();
        
        while (true)
        {
        		int num1 = (int)(Math.random()*numBars);
        		int num2 = (int)(Math.random()*numBars);
        		
        		ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("1", num1); //lets the graphics know that the "i" variable has changed.
        		ActionIndicatorQueue.sharedActionIndicatorQueue().addVariable("2", num2); //lets the graphics know that the "i" variable has changed.
        
        		mainArray.swap(num1, num2);
        		checkIn();
        		if (mainArray.isSorted())
        			break;        
        }
        return STATUS_FINISHED;
        
        
        
    }
    
}