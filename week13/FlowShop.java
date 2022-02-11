import java.util.Scanner;

public class FlowShop {
	static int n,
	f1,
	f,
	bestf;
	static int [][]m;
	static int []x;
	static int []bestx;
	static int []f2;
	public static void backtrack(int i) {
		if(i > n) {
			for(int j = 1;j <= n;j++)
				bestx[j] = x[j];
				bestf = f;
		}else {
			for(int j = i;j <= n;j++) {
				f1 +=m[x[j]][1];
				f2[i] = ((f2[i - 1] > f1)?f2[i - 1]:f1) + m[x[j]][2];
				f += f2[i];
				if(f < bestf) {
					swap(i,j);
					backtrack(i + 1);
					swap(i,j);
				}
				f1 -= m[x[j]][1];
				f -= f2[i];
			}
		}
	}
	public static void swap(int x,int y) {
		int temp = x;
		x = y;
		y = temp;
		
	}
	public static void main(String[] args) {
		int n=3;
		int[][] m={{0,0,0},{0,2,1},{0,3,1},{0,2,3}};//m的下标从1开始，因此第一行的0和每一行第一列的0无用
		backtrack(1);
		System.out.println("最优批处理作业调度顺序为：");
		for(int i=1;i<=n;i++)
			System.out.print(bestx[i]+" ");
		System.out.println();
		System.out.println("最优调度所需的最短时间为："+bestf);
		
		
		
		
	}
}
