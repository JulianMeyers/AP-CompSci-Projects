import java.util.ArrayList;

public class Folder
{
	private ArrayList<Folder> folderList;
	private String name;
	private ArrayList<Integer> fileList; // just a list of fileSizes, really.
	
	public Folder(String inName, Folder[] folders, int[] files)
	{
		folderList = new ArrayList<Folder>();
		fileList = new ArrayList<Integer>();
		name = inName;
		for (Folder f:folders)
			folderList.add(f);
		for (int i:files)
			fileList.add(i);
	}
	
	public Folder(String inName,int[] files)
	{
		folderList = new ArrayList<Folder>();
		fileList = new ArrayList<Integer>();
		name = inName;
		for (int i:files)
			fileList.add(i);
	}
	
	public Folder(String inName)
	{
		folderList = new ArrayList<Folder>();
		fileList = new ArrayList<Integer>();
		name = inName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addFolder(Folder f)
	{
		folderList.add(f);
	}
	
	public void addFile(int i)
	{
		fileList.add(i);
	}
	
	public int getSize()
	{
		int mySize = 0;
		for (Folder f:folderList)
			mySize += f.getSize();
		for (int i:fileList)
			mySize += i;
		return mySize;
		
	}
	
	public void printFolder()
	{
		printFolder(0);
	}
	
	public void printFolder(int tabLevel)
	{
		for (int i=0; i<tabLevel; i++)
			System.out.print("\t");
		System.out.print("<<<"+name+">>>");
		System.out.print("\t");
		System.out.print("Files: [");
		for (int size: fileList)
			System.out.print(size+"MB ");
//		System.out.print("]\tFolders: [");
//		for (Folder f: folderList)
//			System.out.print(f.name+" ");
		System.out.print("]\n");
		for (Folder f: folderList)
			f.printFolder(tabLevel+1);
	}
}
