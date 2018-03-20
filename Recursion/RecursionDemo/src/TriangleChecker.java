
public class TriangleChecker
{
	public int generateTriangle(int i)
	{
		return generateTriangle(i,i);
	}
	
	public int generateTriangle(int i, int maxSpace)
	{
		if (i==0)
		{
			System.out.println(multiplyString("#",maxSpace));
			return maxSpace;
		}
		int subArea = 0;
		System.out.println(multiplyString(" ",i)+multiplyString("#",maxSpace-i));
		subArea += maxSpace-i;
		subArea += generateTriangle(i-1,maxSpace);
		System.out.println(multiplyString(" ",i)+multiplyString("#",maxSpace-i));
		subArea += maxSpace-i;
		return subArea;
	}
	
	public String multiplyString(String character, int numTimes)
	{
		String result = "";
		for (int i=0; i<numTimes; i++)
			result += character;
		return result;
	}
}
