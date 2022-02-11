
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
		//������i��ڵ�
		if(i > n)
		{//����Ҷ�ڵ�
			for(int j = 1;j <= n;j++) {
				bestx[j] = x[j];
				bestw = cw;
				return;
			}
		}
		//��������
		r -= w[i];  //ʣ�༯װ������
		if(cw + w[i] <= c) {
			//����������
			x[i] = 1;
			cw += w[i];
			backtrack(i + 1);
			cw -= w[i];
		}
	}
	public static void main(String[] args) {
		
	}
}
