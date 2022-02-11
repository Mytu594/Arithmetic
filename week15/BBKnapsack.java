import java.util.Arrays;
import java.util.PriorityQueue;
public class BBKnapsack {
	
	static double c ;  	// the capacity of backpack
	static int n;
	static double[] w;	// the weight array of goods
	static double[] p;	// the profit array of goods
	static double cw; 		// current weight of goods in backpack
	static double cp; 		// current profit of goods in backpack
	static int[] bestx;		// the  node path of best solution in solution space tree
	static PriorityQueue<HeapNode> heap	;	// the Max Heap of live nodes;
	
	public static double knapsack(double[] pp,double[] ww,double cc,int[] xx)
	//完成对输入数据对预处理
	{
		c = cc;
		Element []q = new Element[n];
		double ws = 0.0;
		double ps = 0.0;
		for(int i = 1;i <= n;i++) {
			q[i-1]=new Element(i,pp[i]/ww[i]);
			ps+=pp[i];
			ws+=ww[i];
		}
		if(ws<=c) {
			for(int i =1;i<=n;i++)
				xx[i]=1;
			return ps;
		}
		MergeSort(q,0,n-1);
		p = new double[n+1];
		w = new double[n+1];
		for(int i=1;i<=n;i++) {
			p[i] = pp[q[n-1].id];
			w[i] = ww[q[n-1].id];
		}
		cw = 0.0;
		cp = 0.0;
		bestx = new int[n+1];
		HeapNode []h1 = new HeapNode[n+1];
		heap = new PriorityQueue<HeapNode>();//
		double maxp = bbKnapsack();
		for(int i =1;i<=n;i++) {
			xx[q[n-1].id]=bestx[i];
			return maxp;
		}
	}
	private static void MergeSort(Element[] q, int i, int j) {
		if (q.length < 2) return q;
        int mid = q.length / 2;
        int[] left = q.copyOfRange(q, 0, mid);
        int[] right = Arrays.copyOfRange(q, mid, q.length);
        return merge(MergeSort(left), MergeSort(right));
		
	}
	public static Element[] merge(Element[] left, Element[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }
	private static double bbKnapsack() {
		BBnode enode = A;
		int i = 1;
		double bestp = 0.0;
		double up = bound(1);
		while(i !=n+1) {
			double wt = cw +w[i];
			if(wt<c) {
				if(cp+p[i]>bestp)
					bestp = cp + p[i];
					addLiveNode(up,cp+p[i],cw+w[i],i+1,enode,true);
			}
			up = bound(i+1);
			if(up>=bestp)
				adLiveNode(up,cp,cw,i+1,enode,true);
			HeapNode node = (NeapNode)heap.removemax();
			enode = node.liveNode;
			cw = node.weight;
			cp = node.profit;
			up = node.upperProfit;
			i = node.upperProfit;
		}
		for(int j = n;j>0;j--) {
			bestx[j] = (enode.leftChild)?1:0;
			enode = enode.parent;
		}
		return cp;
		
	}
	private static void addLiveNode(double up,double pp,double ww,inr lev,BBnode par,boolean ch) {
		BBnode b = new BBnode(par,ch);
		HeapNode node = new HeapNode(b,up,pp,ww,lev);
		heap.insert(node);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
//		double[] weight = {35, 30, 60, 50, 40, 10, 25};
//		double[] profit = {10, 40, 30, 50, 35, 40, 30};
//		
//		backpack(profit, weight, 150);
//		System.out.print("the best solution: ");
//		for(int i=0; i<bestSolution.length; i++) {
//			System.out.printf("\t[%d]: %d", i, bestSolution[i]);
//		}
	}

}
