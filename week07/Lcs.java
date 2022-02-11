
public class Lcs {
	private static int m;
	private static int n;
	public static int lcsLength(char []x,char[] y,int [][]b) {
		int[][]c=new int[m+1][n+1];
		for(int i=0;i<=m;i++)c[i][0]=0;
		for(int i=0;i<=n;i++)c[0][i]=0;
		for(int i=1;i<=m;i++)
			for(int j=1;j<=n;j++)
			{
				if(x[i-1]==y[j-1])
				{
					c[i][j]=c[i-1][j-1]+1;
					b[i][j]=1;
				}
				else
					if(c[i-1][j]>=c[i][j-1])
					{
						c[i][j]=c[i-1][j];
						b[i][j]=2;
					}
					else
					{
						c[i][j]=c[i][j-1];
						b[i][j]=3;
					}
			}
		return c[m][n];		
	}
	public static void lcs(int i,int j,char[] x,int [][]b) {
		if(i==0||j==0)return;
		if(b[i][j]==1) {
			lcs(i-1,j-1,x,b);
			System.out.print(x[i-1]);
		}
		else if(b[i][j]==2)lcs(i-1,j,x,b);
		else lcs(i,j-1,x,b);
		
	}
	public static void main(String[] args) {
		char[] x = {'A','C','T','C','C','T','A','G'};
		char[] y = {'C','A','T','T','C','A','G','C'};
		m=x.length;
		n=y.length;
		int[][] b=new int[m+1][n+1];
		System.out.println("两人基因片段中最长相同的部分为:"+lcsLength(x,y,b));
		lcs(m,n,x,b);
	}
}
