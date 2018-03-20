
public class MysteryRecursionChecker
{

	public int mystery(int N)
	{
		if (0 == N)
			return 1;
		return mystery(N-1) * N*N + 1;
	}
	
	public String mystery2(int N)
	{
		if (1==N)
			return "1";
		return mystery2(N-1)+N+mystery2(N-1);
		
		
	}
}
