import java.util.Scanner;
public class coloring1 {
	static int m;//��ɫ������
	static int[] x;//���н�
	static int n;//ͼ�Ķ������
	static int[][] a;//ͼ���ڽӾ���
	static long sum;//���н����
	public static long color(int mm,int[][] aa,int nn) {
		m=mm;
		n=nn;
		a=aa;
		sum=0;
		x=new int[n+1];
		for(int i=0;i<=n;i++) 
			x[i]=0;
		backtrack(1);
		System.out.println("��ɫ�������У�");
		return sum;
	}
	public static void backtrack(int t) {
		if(t>n) {
			sum++;
			for(int i=1;i<=n;i++)
				System.out.print(x[i]+" ");
			System.out.println();
		}else {
			for(int i=1;i<=m;i++) {
				x[t]=i;
				if(ok(t))//��֦����
					backtrack(t+1);
				x[t]=0;
			}
		}
	}		
	public static boolean ok(int k) {
		for(int j=1;j<k;j++) {
			if(a[k][j]==1&&x[j]==x[k])//���ڶ�����ɫ������ͬ
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        int mm = s;
		int nn = 6;
		int[][] aa= {{-1,-1,-1,-1,-1,-1,-1},
					 {-1,0,1,0,1,1,0},
					 {-1,1,0,1,0,1,1},
					 {-1,0,1,0,0,0,1},
					 {-1,1,0,0,0,1,0},
					 {-1,1,1,0,1,0,1},
					 {-1,0,1,1,0,1,0}};
		System.out.println("��ɫ�������£�");
		System.out.println(color(mm, aa, nn));
	}
}
