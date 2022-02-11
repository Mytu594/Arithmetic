
 
import java.util.PriorityQueue;
 
public class BBK{
	static double capacity ;  	// the capacity of backpack
	static double[]  weight;	// the weight array of goods
	static double[]  profit;	// the profit array of goods
	static double  currentWeight; 		// current weight of goods in backpack
	static double  currentProfit; 		// current profit of goods in backpack
	static int[]   bestSolution;		// the  node path of best solution in solution space tree
	static PriorityQueue<HeapNode> heap	;	// the Max Heap of live nodes;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		double[] weight = {35, 30, 60, 50, 40, 10, 25};
		double[] profit = {10, 40, 30, 50, 35, 40, 30};
		
		backpack(profit, weight, 150);
		System.out.print("the best solution: ");
		for(int i=0; i<bestSolution.length; i++) {
			System.out.printf("\t[%d]: %d", i, bestSolution[i]);
		}
	}
	
	/**
	 * This method  pre-process the input data by sort the goods with Profit/Weight in descend order, 
	 * then call method branchBoundBackpack() to complete the Priority Queue Branch Bound Search.
	 * @param theProfit the array of goods profit
	 * @param theWeight the array of goods weight
	 * @param theCapacity  the capacity of backpack
	 * @param thePackingState  the array of packing state of goods
	 * @return
	 */
	private static double backpack(double[] theProfit, double[] theWeight, double theCapacity) {
		capacity = theCapacity;
		int goodsNumber = theProfit.length;
		//***********step 1: pre-process**********************************************
		// calculate the Profit/Weight for each goods and store in array of Element. *
		//****************************************************************************
		Element[] queue = new Element[goodsNumber];
		
		for (int i=0; i< goodsNumber; i++) {
			// calculate the Profit/Weight and generate a Element object, put it in array;
			queue[i] = new Element(i, theProfit[i] , theWeight[i]);
			
		}
		
		//************Step2: sort *****************************************************
		// sort the array of Elements in Descend order using MergeSort;               *
		//*****************************************************************************
		MergeSort.mergeSort(queue);
		
		// initialize the Profit and Weight array with the SORTED data;
		profit = new double[goodsNumber];
		weight = new double[goodsNumber];
		for (int i=0; i<goodsNumber; i++) {
			profit[i] = queue[i].profit;
			weight[i] = queue[i].weight;
		}
		
		currentWeight = 0.0;
		currentProfit = 0.0;
		bestSolution = new int[goodsNumber];
		heap = new PriorityQueue<HeapNode>();
		
		//*********Step 3: call method branchBoundBackpack()********************
		double maxProfit = branchBoundBackpack();
		
 
		return maxProfit;
	}
	
	/**
	 * This method go through the goods array and return the max profit and best path of selection;
	 * @return
	 */
	private static double branchBoundBackpack() {
		// initialization
		BBNode expandNode = null;  		// working expand node
		int i =0; 
		double bestProfit =0.0;			// working best profit;
		double upperProfit = bound(0);   // working upper bound profit ;
		
		// search the subset tree while current node is not a leaf node
		while (i < weight.length) {
			
			// check left child node;
			double targetWeight = currentWeight + weight[i]; 
			if (targetWeight <= capacity) {
				// adding left child without exceed the capacity, makes left child be a feasible node;
				// check if new profit is better than current best profit, 
				// then update best profit if true; 
				
				double targetProfit = currentProfit + profit[i]; 
				if (targetProfit > bestProfit) {
					bestProfit = targetProfit;
				}
				//  add the left child in MaxHeap
				addLiveNode(upperProfit, targetProfit, currentWeight + weight[i], i+1, expandNode, true);
				
			}
			
			// update upper boundary
			upperProfit = bound(i + 1);
			
			// check right child node;
			if (upperProfit >= bestProfit) {
				addLiveNode(upperProfit, currentProfit, currentWeight , i+1, expandNode, false);
			}
			
			// pop and process next expand node from MaxHeap
			HeapNode node = heap.poll();
			expandNode = node.liveNode;
			currentWeight = node.weight;
			currentProfit = node.profit;
			upperProfit = node.upperProfit;
			i = node.level;
		}
		
		// build the best solution 
		for(int j= weight.length -1; j>=0; j--) {
			bestSolution[j]= expandNode.isLeftChild?1:0;
			expandNode = expandNode.parent;
		}
		System.out.printf(" Current best Proft : %7.2f\r\n", bestProfit);
		return currentProfit;
	}
	
	/*
	 * calculate the bound for node i, NOT do real packing ;
	 * prerequisite: the goods is sorted by profit per weight  in ascend order;
	 */
	private static double bound(int i) {
		// remain capacity
		double capacityRemains = capacity - currentWeight; 
		// upper bound of profit
		double boundary = currentProfit;
		
		// calculate the upper bound by adding feasible goods with this one to backpack  
		while(i < weight.length && weight[i] <= capacityRemains) {
			capacityRemains -= weight[i];
			boundary += profit[i];
			i++;
		}
		
		if (i< weight.length) {
			// if the backpack is not fulfilled when there is no feasible goods, 
			// then adding parts of goods with highest value per weight until the capacity is 0;
			boundary += profit[i]/ weight[i] * capacityRemains;
		}
		return boundary;
	}
	
	private static void addLiveNode(double upperProfit, double profit, double weight, int level, BBNode parent, boolean isLeft) {
		// add a tree node into MaxHeap 
		BBNode node = new BBNode(parent, isLeft);
		HeapNode heapNode = new HeapNode(node, upperProfit, profit ,weight, level);
		heap.add(heapNode);
	}
	
	/**
	 * This internal class represents a node of solution space tree;
	 */
	static class BBNode{
		BBNode parent;
		boolean isLeftChild; 
	
	
	public BBNode(BBNode parent, boolean isLeft) {
			this.parent = parent;
			this.isLeftChild = isLeft;
		}
	}
	static class HeapNode implements Comparable<HeapNode>{
		BBNode liveNode ;   	//  live node related to  this heap node;
		double profit; 			//  the profit of this node;
		double upperProfit;     //  the upper bound of profit for this node;
		double weight; 		 	// weight of this node;
		int level;				// the level of node in subset tree
		
		public HeapNode(BBNode treenode,  double upperProfit, double profit, double weight, int level) {
			this.liveNode = treenode;
			this.upperProfit = upperProfit;
			this.profit = profit;
			this.weight = weight;
			this.level = level;
		}
		
		@Override
		public int compareTo(HeapNode obj) {
			// Note: we need a MaxHeap for algorithm, be careful of the order of two comparable item;
			return (Double.compare(obj.upperProfit, this.upperProfit));
			
		}
		
	}
	
	
}