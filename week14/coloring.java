import java.util.Scanner;

public class coloring {
	static int n;
	static int m;
	static boolean[][] a;
	static int[] x;
	static long sum;
	
	public static long mColoring(int mm) {
		m = mm;
		sum = 0;
		backtrack(1);
		return sum;
	}

	private static void backtrack(int t) {
		if (t > n) {
			sum++;
			for (int i = 1; i <= n; i++) {
				System.out.println(x[i] + " ");
			}
			System.out.println();
		} else {
			for (int i = 1; i <= m; i++) {
				x[t] = i;
				if (ok(t))
					backtrack(t + 1);
				x[t] = 0;
			}
		}
	}

	private static boolean ok(int k) {
		for (int j = 1; j <= n; j++) {
			if (a[k][j] && (x[j] == x[k]))
				return false;
		}
		return true;
	}
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        int mm = s;
		int nn=6;
		int[][] aa= {{-1,-1,-1,-1,-1,-1,-1},
					 {-1,0,1,0,1,1,0},
					 {-1,1,0,1,0,1,1},
					 {-1,0,1,0,0,0,1},
					 {-1,1,0,0,0,1,0},
					 {-1,1,1,0,1,0,1},
					 {-1,0,1,1,0,1,0}};
		System.out.println("着色方案如下：");
		long res = mColoring(mm);
		System.out.println(res);
	}
}
