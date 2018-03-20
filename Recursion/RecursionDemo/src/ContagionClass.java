
public class ContagionClass
{

	public int expandContagion(String[] world, int index)
	{
		if (index<0 || index >= world.length)
			return 0;
		if (!world[index].equals("."))
			return 0;

		world[index]="*";
		printWorld(world);
		int count = expandContagion(world,index-1) + expandContagion(world,index+1);
		return count;
	}
	
	public void printWorld(String[] world)
	{
		for (String s: world)
			System.out.print(s);
		System.out.print("\n");
	}
	
}
