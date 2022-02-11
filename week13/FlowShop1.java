/**
 * ��������ҵ��������--���ݷ�����������
 * @author Administrator
 *
 */
public class FlowShop1 {
	 int n;//��ҵ��
	 int f1;//����1��ɴ���ʱ��
	 int f;//���ʱ���
	 int bestf;//��ǰ����ֵ
	 int[][] m;//����ҵ����Ĵ���ʱ��
	 int []x;//��ǰ��ҵ����
	 int[] bestx;//��ǰ������ҵ����
	 int[] f2;//����2��ɴ���ʱ��
	public FlowShop1(int n,int[][] m){
		this.n=n;
		this.m=m;
		f1=0;
		f=0;
		bestf=10000;//������ʼֵ
		bestx=new int[n+1];
		x=new int[n+1];
		//��ʼ����x[i]Ϊԭʼ����
		for(int i=1;i<=n;i++){
			x[i]=i;
		}
		f2=new int[n+1];
	}
	public  void swap(int[] x,int i,int j){
		int temp=x[i];
		x[i]=x[j];
		x[j]=temp;
	}
 
	public  void backtrack(int i){
		if(i>n){
			for(int j=1;j<=n;j++)
				bestx[j]=x[j];
			bestf=f;
		}
		else{
			for(int j=i;j<=n;j++){
				f1+=m[x[j]][1];//��ҵx[j]�ڵ�һ̨������ʱ��
				f2[i]=((f2[i-1]>f1)?f2[i-1]:f1)+m[x[j]][2];//f2[i]����f2[i-1]��f1�нϴ��߼�����ҵx[j]�ڵ�2̨������ʱ��
				f+=f2[i];
				if(f<bestf){
					swap(x,i,j);
					backtrack(i+1);
					swap(x,i,j);
				}
				f1-=m[x[j]][1];
				f-=f2[i];
			}
		}
	}
	public static void main(String[] args) {
		int n=3;
		int[][] m={{0,0,0},{0,2,1},{0,3,1},{0,2,3}};//m���±��1��ʼ����˵�һ�е�0��ÿһ�е�һ�е�0����
		FlowShop1 f=new FlowShop1(n,m);
		f.backtrack(1);
		System.out.println("������������ҵ����˳��Ϊ��");
		for(int i=1;i<=n;i++)
			System.out.print(f.bestx[i]+" ");
		System.out.println();
		System.out.println("���ŵ�����������ʱ��Ϊ��"+f.bestf);
	}
}
/*
 ���н����
 
 ������������ҵ����˳��Ϊ��
1 3 2 
���ŵ�����������ʱ��Ϊ��18
 */