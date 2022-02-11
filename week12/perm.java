
public class perm {
	public void perm(int[] li,int k,int m)
	{
		//产生list[k:m]的所以排列
		if(k==m) {
			//只剩下一个元素
			for(int i=0;i<=m;i++) {
				System.out.print(li[i]);
			}
			System.out.println();
		}
		else
			for(int i=k;i<=m;i++) {
				swap(li,k,i);
				perm(li,k+1,m);
				swap(li,k,i);
			}
	}

	private static void swap(int[] list, int m, int n) {
		int temp = (int) list[m];
		list[m] = list[n];
		list[n] = temp;
	}
	public static void main(String[] args) {
		int[] li = {1,2,3};
		perm p = new perm(); //如果全部方法都是static,可以不用new对象
		p.perm(li, 0, li.length-1);
	}
}
