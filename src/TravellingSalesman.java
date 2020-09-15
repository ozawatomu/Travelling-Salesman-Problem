import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TravellingSalesman extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	Timer timer = new Timer(1, this);
	static int screenSizeWidth = 1620;
	static int screenSizeHeight = 1620;
	boolean isCalculate = false;
	static CityGroup cities;
	static Path bestPath;
	static Path currentPath;
	boolean isFound = false;
	static City selectedCity;
	
	public static void main(String[] args) {
		cities = new CityGroup();
		
		JFrame jFrame = new JFrame();
		jFrame.setTitle("Travelling Salesman");
		jFrame.setSize(screenSizeWidth, screenSizeHeight);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TravellingSalesman travellingSalesman = new TravellingSalesman();
		travellingSalesman.addMouseListener(travellingSalesman);
		travellingSalesman.addMouseMotionListener(travellingSalesman);
		travellingSalesman.setPreferredSize(new Dimension(screenSizeWidth, screenSizeHeight));
		jFrame.add(travellingSalesman);
		jFrame.pack();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRect(0, 0, screenSizeWidth, screenSizeHeight);
		cities.draw(g2d);
		if(isCalculate) {
			currentPath.draw(g2d, 32);
			bestPath.draw(g2d, 255);
			if(!isFound) {
				//https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering
				//STEP 1
				int x = -1;
				for(int i = 0; i < currentPath.path.length - 1; i++) {
					if(currentPath.path[i] < currentPath.path[i + 1]) {
						x = i;
					}
				}
				if(x != -1) {
					//STEP 2
					int y = -1;
					for(int i = 0; i < currentPath.path.length; i++) {
						if(currentPath.path[x] < currentPath.path[i]) {
							y = i;
						}
					}
					
					//STEP 3
					swap(currentPath.path, x, y);
					
					//STEP 4
					int[] prefix = splice(currentPath.path, 0, x + 1);
					int[] suffix = splice(currentPath.path, x + 1, currentPath.path.length);
					suffix = reverse(suffix);
					currentPath.path = connect(prefix, suffix);
				}else{
					System.out.println("Shortest Distance: " + bestPath.getDistance());
					isFound = true;
					currentPath = bestPath.copy();
				}
				
				if(currentPath.getDistance() < bestPath.getDistance()) {
					bestPath = currentPath.copy();
					System.out.println(bestPath.getDistance());
				}
			}
		}
		timer.start();
	}
	
	public int[] splice(int[] array, int i, int j) {
		int[] newArray = new int[j - i];
		for(int k = i; k < j; k++) {
			newArray[k - i] = array[k];
		}
		return newArray;
	}
	
	public int[] reverse(int[] array) {
		int[] newArray = new int[array.length];
		for(int i = array.length - 1; i >= 0; i--) {
			newArray[array.length - 1 - i] = array[i];
		}
		return newArray;
	}
	
	public int[] connect(int[] a, int[] b) {
		int[] newArray = new int[a.length + b.length];
		for(int i = 0; i < a.length; i++) {
			newArray[i] = a[i];
		}
		for(int i = 0; i < b.length; i++) {
			newArray[a.length + i] = b[i];
		}
		return newArray;
	}
	
	public void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		isCalculate = false;
		selectedCity = cities.getFromPoint(new XYPair(e.getX(), e.getY()));
		if(selectedCity != null) {
			selectedCity.isSelected = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(selectedCity == null) {
			cities.add(new City(e.getX(), e.getY(), 40));
		}else {
			selectedCity.isSelected = false;
			if(e.getButton() == MouseEvent.BUTTON3) {
				cities.cities.remove(selectedCity);
			}
		}
		System.out.println("_____________________________________");
		bestPath = new Path(cities);
		currentPath = new Path(cities);
		isFound = false;
		isCalculate = true;
	}

	public void mouseDragged(MouseEvent e) {
		if(selectedCity != null) {
			selectedCity.location.x = e.getX();
			selectedCity.location.y = e.getY();
		}
	}

	public void mouseMoved(MouseEvent e) {
		
	}

}
