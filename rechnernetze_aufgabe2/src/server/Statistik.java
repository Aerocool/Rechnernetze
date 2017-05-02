package server;

public class Statistik {
	int shortest;
	int longest;
	int count;
	long aggregatetSize;
	
	public Statistik(){
		shortest = -1;
		longest = -1;
		count = 0;
		aggregatetSize = 0;
	}
	
	public int getShortest() {
		return shortest;
	}
	public void setShortest(int shortest) {
		this.shortest = shortest;
	}
	public int getLongest() {
		return longest;
	}
	public void setLongest(int longest) {
		this.longest = longest;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getAggregatetSize() {
		return aggregatetSize;
	}
	public void setAggregatetSize(long aggregatetSize) {
		this.aggregatetSize = aggregatetSize;
	}
}
