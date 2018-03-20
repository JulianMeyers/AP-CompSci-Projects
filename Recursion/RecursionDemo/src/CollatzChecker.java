
public class CollatzChecker
{

	public void checkValue(int val)
	{
		System.out.println(val);
		if (val == 1)
			return;

		if (val%2 == 0)
			checkValue(val/2);
		else
			checkValue(3*val + 1);
		
	}
	
}
