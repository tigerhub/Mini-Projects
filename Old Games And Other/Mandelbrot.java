import java.awt.*;
import java.applet.Applet;

public class Mandelbrot extends Applet  {
	public static final int SCREEN_WIDTH = 900;
	// This is 514 because its made to be proportional to the graph's scale, which is
	// 3.5 for x and 2 for y, so 2/3.5 * 900 = 514.
	public static final int SCREEN_HEIGHT = 514;
	public static final double MIN_X = -2.5;
	public static final double MAX_X = 1.0;
	public static final double MIN_Y = -1.0;
	public static final double MAX_Y = 1.0;
	private double scaleFactorX;
	private double scaleFactorY;
	private double shiftFactorX;
	private double shiftFactorY;

	public void init() {
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		scaleFactorX = (MAX_X - MIN_X)/SCREEN_WIDTH;
		scaleFactorY = (MAX_Y - MIN_Y)/SCREEN_HEIGHT;
		shiftFactorX = MIN_X;
		shiftFactorY = MIN_Y;
	}

	public void paint(Graphics page) {
		int x = 0;
		int y = 0;
		while(x < SCREEN_WIDTH) {
			y = 0;
			double rx0 = correspXCoord(x);
			while(y < SCREEN_HEIGHT) {
				double ry0 = correspYCoord(y);
				int i = 0;
				//The smaller the iteration, the less detailed the graph is general.
				int maxIteration = 45; 
				double rx = rx0;
				double ry = ry0;
				while(rx*rx + ry*ry <= 4 && i < maxIteration) {
					double rxtemp = rx*rx - ry*ry + rx0;
					ry = 2*rx*ry + ry0;
					rx = rxtemp;
					i++;
				}
				if(i == maxIteration) {
					page.setColor(Color.black);
				}
				else {
					//as the 0.1 becomes greater, the color around the graph becomes
					// less detailed; at 0.9, it looks like small colored dots around \
					//the graph. 
					float color = (float)i/maxIteration;  
					if(color > .1) {
						//blue; (R,G,B).
						page.setColor(new Color(color, color, 1f));
					}
					else if(color > .3) {
						//red and green= yellow; (R,G,B).
						page.setColor(new Color(1, 1, color));
					}
					else {
						//else set page color to blue.
						page.setColor(new Color(0f, 0f, 1f)); 
					}
				}
				page.drawRect(x, y, 0, 0); //plots the points. 
				y++;
			}
			x++;
		}
	}

	// Get corresponding x coordinate.
	private double correspXCoord(int x) {
		return x * scaleFactorX + shiftFactorX;
	}

	// Get corresponding y coordinate.
	private double correspYCoord(int y) {
		return y * scaleFactorY + shiftFactorY;
	}
}