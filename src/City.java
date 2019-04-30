import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class City {
	XYPair location;
	double radius;
	boolean isSelected;
	
	public City(double x, double y, double radius) {
		location = new XYPair(x, y);
		this.radius = radius;
	}
	
	public double distanceTo(City otherCity) {
		return location.distanceTo(otherCity.location);
	}
	
	public boolean isOver(XYPair mousePoint) {
		if(location.distanceTo(mousePoint) <= radius) {
			return true;
		}else {
			return false;
		}
	}
	
	public void draw(Graphics2D g) {
		if(isSelected) {
			g.setColor(new Color(255, 255, 255, 32));
			g.fillOval((int) (location.x - radius), (int) (location.y - radius), (int) (radius*2), (int) (radius*2));
			g.setColor(new Color(255, 0, 0));
		}else {
			g.setColor(new Color(255, 255, 255));
		}
		BasicStroke stroke = new BasicStroke(2f);
		g.setStroke(stroke);
		g.drawOval((int) (location.x - radius), (int) (location.y - radius), (int) (radius*2), (int) (radius*2));
	}
}
