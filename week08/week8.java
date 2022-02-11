
public class week8 {
	public static void optimalBinarySearchTree(float []a,float []b,float[][] m,int [][]s,float[][] w)
	{
		int n=b.length;
		for(int i=0;i<n;i++)
		{
			w[i+1][i]=a[i];
			m[i+1][i]=0;
		}
		for(int r=0;r<n;r++)
			for(int i=1;i<n-r;i++)
			{
				int j=i+r;
				w[i][j]=w[i][j-1]+a[j]+b[j];
				m[i][j]=m[i][i-1]+m[i+1][j]; //m[i][i-1]表示左子树的检索开销 为0  i不可能到i-1的位置
				s[i][j]=i;
				for(int k=i+1;k<=j;k++)
				{
					float t=m[i][k-1]+m[k+1][j];
					if(t<m[i][j])
					{
						m[i][j]=t;
						s[i][j]=k;
					}
				}
				m[i][j]+=w[i][j];
			}
	}
	//打印
	public static void display(float[][] s) {
		for(int i=0;i<s.length;i++)
		{
			for(int j=0;j<s[i].length;j++)
			{
				System.out.print(s[i][j]+" ");
				System.out.print(' ');
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args) {
		float []a= {2,3,1,1,1};
		float []b= {3,3,1,1};
		float[][] m=new float[5][5];  //保存最优子树T(i, j)的平均搜索次数(平均步长)(检索开销)
		int[][] s=new int[5][5];      //保存最优子树T(i, j)的根节点元素
		float[][] w=new float[5][5];  //保存最优子树T(i, j)的概率和
		int n=b.length;
		optimalBinarySearchTree(a,b,m,s,w);
		System.out.println("平均搜索次数(平均步长):");
		display(m);
		System.out.println("存储结点的概率和: ");
		display(w);
		//最优二叉搜索树输出
		System.out.println("最优二叉查找树根节点元素: ");
		for(int i=0;i<s.length;i++)
		{
			for(int j=0;j<s[i].length;j++)
			{
				System.out.print(s[i][j]+" ");
				System.out.print(' ');
			}
			System.out.println();
		}
		System.out.println();
	}

}
