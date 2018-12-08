package recursive;

public class Recursive {

	// == driver ==
	public static void main(String[] args) {
		
		int[] arr = {1,2,3,4,5};
		Recursive recursive = new Recursive();
//		System.out.println(recursive.fibonacci(6));
		System.out.println(recursive.factorial(3));
//		System.out.println(recursive.test(4));
//		System.out.println(recursive.sumArray(arr, arr.length-1));

	}

	// == private methods ==
	private int factorial(int n) {
		if(n == 0) {
			return 1;
		}else {
			return n * factorial(n-1);
		}
	}
	
	private int fibonacci(int n) {
//		int result = n;

		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

//		System.out.println(n);

		return fibonacci(n - 1) + fibonacci(n - 2);

	}

	private int sumArray(int[] arr, int i) {
		if (i == 0)
			return arr[i];
		else
			return arr[i] + sumArray(arr, i - 1);
//		System.out.println(arr[i]);
	}
	
	private int test(int n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		
		int prior2 = 0;
		int prior1 = 1;
		int result = 0;
		
		for(int i = 0; i < n; i++) {
			result = i + prior2;
			prior2 = prior1;
			prior1 = result;
		}
		
		return result;
	}
}
