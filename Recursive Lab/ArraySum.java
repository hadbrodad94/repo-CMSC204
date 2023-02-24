
public class ArraySum {
	//recursive method
	public int sumOfArray(Integer [] a, int index) {
		int sum;
		if(index == 0) {
			sum = a[index];
		}else {
			sum = sumOfArray(a,index-1) + a[index];
		}
		return sum;
	}
}
