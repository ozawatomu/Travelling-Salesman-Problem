
public class XYPair {
	double x;
	double y;
	
	public XYPair(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distanceTo(XYPair otherPair) {
		return Math.sqrt(Math.pow(x - otherPair.x, 2) + Math.pow(y - otherPair.y, 2));
	}
}
