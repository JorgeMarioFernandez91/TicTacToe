
public class PolynomialHashFunction {
	
	int size;
	
	String S;
	
	int value;
	
	public PolynomialHashFunction(String S, int size, int value)
	{
		this.size = size;
		this.S = S;
		this.value = value;
	}
	
	public int hashFunction(String S, int size, int value)
	{
		int val = Integer.valueOf(S);
		
		for (int i = S.length(); i >= 0; i--)
		{
			val = (val*value + Integer.valueOf(S))%(size);
		}
		
		return val;
	}

}
