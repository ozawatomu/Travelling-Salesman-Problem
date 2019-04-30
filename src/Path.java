import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Path {
	CityGroup cityGroup;
	int[] path;
	
	public Path(CityGroup cityGroup) {
		this.cityGroup = cityGroup;
		path = new int[cityGroup.cities.size()];
		for(int i = 0; i < path.length; i++) {
			path[i] = i;
		}
	}
	
	public void draw(Graphics2D g, int opacity) {
		g.setColor(new Color(255, 255, 255, opacity));
		for(int i = 0; i < path.length - 1; i++) {
			g.drawLine((int) cityGroup.get(path[i]).location.x, (int) cityGroup.get(path[i]).location.y,
					(int) cityGroup.get(path[i + 1]).location.x, (int) cityGroup.get(path[i + 1]).location.y);
		}
	}
	
	public Path copy() {
		int[] newPath = new int[path.length];
		for(int i = 0; i < path.length; i++) {
			newPath[i] = path[i];
		}
		Path returnPath = new Path(cityGroup);
		returnPath.path = newPath;
		return returnPath;
	}
	
	public double getDistance() {
		double distance = 0;
		for(int i = 0; i < path.length - 1; i++) {
			distance += cityGroup.get(path[i]).location.distanceTo(cityGroup.get(path[i + 1]).location);
		}
		return distance;
	}
}
