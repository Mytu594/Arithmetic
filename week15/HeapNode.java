class HeapNode implements Comparable{		
	BBnode liveNode;
	double upperProfit;
	double profit;
	double weight;
	int level;
	HeapNode(BBnode node,double up,double pp,double ww, int lev){
		liveNode = node;
		upperProfit = up;
		profit = pp;
		weight = ww;
		level = lev;
	}
}