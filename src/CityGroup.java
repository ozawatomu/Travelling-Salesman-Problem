import java.awt.Graphics2D;
import java.util.ArrayList;

public class CityGroup {
	ArrayList<City> cities;
	
	public CityGroup() {
		cities = new ArrayList<City>();
	}
	
	public void add(City city) {
		cities.add(city);
	}
	
	public City get(int i) {
		return cities.get(i);
	}
	
	public City getFromPoint(XYPair mousePoint) {
		for(City city: cities) {
			if(city.isOver(mousePoint)) {
				return city;
			}
		}
		return null;
	}
	
	public void draw(Graphics2D g) {
		for(City city: cities) {
			city.draw(g);
		}
	}
}
