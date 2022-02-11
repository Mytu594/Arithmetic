
public class Loading1 {
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
	public static void backtrack(int i)
	{
		//搜索第i层节点
		if(i > n)
		{//到达叶节点
			for(int j = 1;j <= n;j++) {
				bestx[j] = x[j];
				bestw = cw;
				return;
			}
		}
		//搜索子树
		r -= w[i];  //剩余集装箱重量
		if(cw + w[i] <= c) {
			//搜索左子树
			x[i] = 1;
			cw += w[i];
			backtrack(i + 1);
			cw -= w[i];
		}
	}
	public static void main(String[] args) {
		
	}
}
