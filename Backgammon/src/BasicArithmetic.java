
public enum BasicArithmetic implements Arithmetic
{
	
	Addition{
	public int calculate(int i, int j) {
		return i+j;
	}
	},
	Subtraction{
		public int calculate(int i, int j) {
			return i-j;
		}
		}
	  
}
