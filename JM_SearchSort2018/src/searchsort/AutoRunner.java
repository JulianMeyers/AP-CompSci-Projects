package searchsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JLabel;

public class AutoRunner implements ThreadCompletedDelegate
{
	private BarArray mainArray;
	private ArrayList<Integer> NValues;
	private ArrayList<String> sortNames;
	private int repetitions;
	private JLabel dummyGetsLabel, dummySetsLabel, dummyComparesLabel, dummyTimeLabel;
	private StatsThread statsThread;
	boolean iAmRunning;
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		AutoRunner app = new AutoRunner();
	}
	
	public AutoRunner()
	{
		NValues = new ArrayList<Integer>();
		sortNames = new ArrayList<String>();
		repetitions = 4;
		
		dummyGetsLabel = new JLabel();
		dummySetsLabel = new JLabel();
		dummyComparesLabel = new JLabel();
		dummyTimeLabel = new JLabel();
		statsThread = new StatsThread(dummyGetsLabel,dummySetsLabel,dummyComparesLabel,dummyTimeLabel);
		statsThread.start();
		
		Scanner keyboardInput = new Scanner(System.in);
		System.out.print("Enter the N values you wish to test as a comma delimited list. (e.g., \"100,300,1000,3000\". ");
		String nVals = keyboardInput.nextLine();
		for (String p:nVals.split(","))
			try
			{
				NValues.add(Integer.parseInt(p.replaceAll("\\s+","")));
			}
			catch (NumberFormatException nfExp)
			{	
				System.out.println("could not parse \""+p+".\"");
			}
		System.out.println("Enter the names of the sorts you wish to test as a comma delimited list, or type space to skip.");
		System.out.println("Legal sorts: BubbleSort, BozoSort, SelectionSort, InsertionSort, MergeSort, QuickSort, HeapSort.");
		String sortList = keyboardInput.nextLine().replaceAll("\\s",""); // a snazzy trick which replaces all spaces "\s" with "".
		sortNames.addAll(Arrays.asList(sortList.split(",")));
		
		try {
			
		
			for (String name:sortNames)
			{ 
				if (sortThreadByName(name) == null)
				{
					System.out.println("Could not find a sort for \""+name+".\"");
					continue;
				}
				for(int n:NValues)
				{
					for (int trial=0; trial<repetitions; trial++)
					{
						System.out.print(name+": N = "+n+" trial# "+trial);
						mainArray = new BarArray(n);
						AbstractSearchSortThread activeThread = sortThreadByName(name);
						iAmRunning=true;
						statsThread.beginCheckingStats(true);
						activeThread.start();
						while (iAmRunning)
							Thread.sleep(1);
						System.out.println(": "+dummyTimeLabel.getText()+" seconds");
					}
					System.out.println("--------------------------");
				}
				System.out.println("=========================================");
			}
		}
		catch (InterruptedException iExc)
		{
			iExc.printStackTrace();
		}
	}
	
	public AbstractSearchSortThread sortThreadByName(String name)
	{
		if (name.equals("BubbleSort"))
			return new BubbleSortThread(mainArray,statsThread,this);
		if (name.equals("BozoSort"))
			return new BozoSortThread(mainArray,statsThread,this);
		if (name.equals("SelectionSort"))
			return new SelectionSortThread(mainArray,statsThread,this);
		if (name.equals("InsertionSort"))
			return new InsertionSortThread(mainArray,statsThread,this);
		if (name.equals("MergeSort"))
			return new MergeSortThread(mainArray,statsThread,this);
		if (name.equals("QuickSort"))
			return new QuicksortThread(mainArray,statsThread,this);
		if (name.equals("HeapSort"))
			return new HeapSortThread(mainArray,statsThread,this);
		return null;
	}

	@Override
	public void threadHasFinished(String whichThread, int status)
	{
		// TODO Auto-generated method stub
		statsThread.stopCheckingStats();
//		statsThread.updateGUI();
//		System.out.print(status+"\t");
		iAmRunning = false;
	}


	@Override
	public void updateStatus(int s)
	{
		// TODO Auto-generated method stub
		
	}

}
