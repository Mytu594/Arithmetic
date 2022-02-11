
public class mergeSort {
	public void mergeSort(Comparable a[],int left,int right)
	{
		Comparable [] b=new Comparable[a.length];
		if(left<right) {
			//至少有2个元素
			int i=(left+right)/2;
			mergeSort(a,left,i);
			mergeSort(a,i+1,right);
			merge(a,b,left,i,right);
			copy(a,b,left,right);
		}
		display(b);
	}
	public Comparable[] copy(Comparable[] a, Comparable[] b, int left, int right) {
		int i=0;
		for(i=left;i<right-1;i++) {
			a[i]=b[i];
		}
		return a;
	}
	public void merge(Comparable[] c, Comparable[] d, int l,int m, int r) {
		//合并c[1:m]和c[m+1:r]到d[1:r]
		int i=l,j=m+1,k=l;
		while((i<=m)&&(j<=r)) {
			if(c[i].compareTo(c[j])<=0) {
				d[k++]=c[i++];
			}
			else
				d[k++]=c[j++];
		}
		if(i>m) {
			for(int q=j;q<=r;q++) {
				d[k++]=c[q];
			}
		}
		else {
			for(int q=i;q<=m;q++) {
				d[k++]=c[q];
			}
		}
	}
	public void display(Comparable[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Comparable[] arr= {23,5,9,16,30,25,17,18};
		mergeSort m=new mergeSort();
		m.mergeSort(arr,0,arr.length-1);
		}
	}
