
public class Loading {
	
	static int n;
	static int[] w;
	static int c;
	static int cw;
	static int bestw;
	static int r;
	static int[] x;
	static int[] bestx;
	public static int maxLoading(int[] ww, int cc, int[] xx) {
		n = ww.length - 1;
		w = ww;
		c = cc;
		cw = 0;
		bestw = 0;
		x = new int[n+1];
		bestx = xx;
		for(int i = 1; i <= n; i++)
			r += w[i];
		backtrack(1);
		return bestw;
	}
	public static void backtrack(int i) {
		if(i > n) {
			for(int j = 1; j <= n; j++)
				bestx[j] = x[j];
			bestw = cw;
			return;
		}
		r -= w[i];
		if(cw + w[i] <= c) {
			x[i] = 1;
			cw += w[i];
			backtrack(i+1);
			cw -= w[i];
		}
		if(cw + r > bestw) {
			x[i] = 0;
			backtrack(i+1);
		}
		r += w[i];
	}
	public static void main(String[] args) {
		int[] ww = {0,5,2,1,3};
		int c1 = 10;
		int c2 = 12;
		int n = 4;
		int[] xx = new int[n+1];
		maxLoading(ww,c1,xx);
		int w2 = 0;//保存第二艘船可能要装的重量
		for(int i = 1; i <= n; i++) {
			w2 += ww[i] * (1 - bestx[i]);//bestx[i]的值只能为0或1
		}
		if(w2 > c2) {
			System.out.println("无解");
		}
		else {
			System.out.println("第一艘船装载货物的重量： " + bestw);
			System.out.println("第二艘船装载货物的重量： " + w2);
			//第一艘船的装载情况
			for(int i = 1; i <= n; i++) {
				if(bestx[i] == 1) {
					System.out.println("第" + i + "件货物装入第一艘船");	
				}
			}
			//第二艘船的装载情况
			for(int i = 1; i <= n; i++) {
				if(bestx[i] == 0) {
					System.out.println("第" + i + "件货物装入第二艘船");
				}
			}
		}
	}

}
