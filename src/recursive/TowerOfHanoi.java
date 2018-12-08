package recursive;

import java.util.Scanner;

public class TowerOfHanoi {
	
	// == driver ==
	public static void main(String[] args) {
		TowerOfHanoi towerOfHanoi = new TowerOfHanoi();
		System.out.print("Enter number of discs: ");
		Scanner scanner = new Scanner(System.in);
		int discs = scanner.nextInt();
		towerOfHanoi.move(discs, "I", "II", "III");

	}
	
	// == public methods ==
	public void move(int n, String start, String temp, String end) {
		if(n == 1) {
			System.out.println("Move disk " + n + " from peg " + start + " to peg " + end);
		}else {
			move(n - 1, start, end, temp);
			System.out.println("Move disk " + n + " from peg " + start + " to peg " + end);
//			move(n-1, temp, start, end);
		}
	}

}
