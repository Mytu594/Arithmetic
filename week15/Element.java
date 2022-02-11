
public  class Element implements Comparable<Element>{
	int id;			// the id number;
	double valuePerUnitWeight; 	//  good value of unit weight;
	double profit;
	double weight;
	
	public Element(int id, double profit, double weight) {
		this.id = id;
		this.profit = profit;
		this.weight = weight;
		this.valuePerUnitWeight = profit/weight;
	}
	
	@Override
	public int compareTo(Element obj) {
		// compare the valuePerWeight of two object;
		if (Math.abs(this.valuePerUnitWeight - obj.valuePerUnitWeight) <= 0.00001)
			return 0;
		else if (this.valuePerUnitWeight - 	 obj.valuePerUnitWeight >0.0001)
			return 1;
		else
			return -1;
	}
 
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Element other = (Element)obj;
		return Math.abs(this.valuePerUnitWeight - other.valuePerUnitWeight) < 0.00001;
	}
			
}