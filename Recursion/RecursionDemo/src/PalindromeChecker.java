
public class PalindromeChecker
{
	public boolean isPalindrome(String input)
	{
		int len = input.length();
		if (len<2)
			return true;
		String firstLetter = input.substring(0,1);
		String lastLetter = input.substring(len-1);
		if (firstLetter.equals(lastLetter))
		{
			String middleString = input.substring(1,len-1);
			return isPalindrome(middleString);
		}
		else {
			return false;
		}
		
	}
}
