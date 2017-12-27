import java.awt.*;
import java.util.*;
import java.applet.Applet;
import java.util.Scanner;

import javax.swing.JFrame;


//Dan Brown; D block;

public class Sierpinski_Triangle extends Applet 
{
	int x1, y1, x2, y2, x3, y3;
	Point [] triangle = new Point[3];
	double sideLength;
	public static final int MAX_POINTS = 40000;
	/* MAX_POINTS is exactly what it should mean; 
	 *its the max points possible;
	 * this also defines how detailed the triangle will be.
	the "Point [] triangle = new Point[3];" is just an object 
	that will be used later in the program.
	*/
	public void init() {
		setSize(600, 600);
	}
	// this sets the side to be 600 by 600;

	public void paint(Graphics page) {
		x1 = 250;
		y1 = 54; 
		x2 = 50;
		y2 = 400;
		x3 = 450;
		y3 = 400;
		/*
		x1= x cord of 1st point; y1=y cord of 1st point; 
		x2= x cord of 2nd point; ect...
		note: At first the coordinates for some of these points did not work, 
		so thats why I has to change them to make the triangle equilateral; 
		which is also why you see weird coordinates such as for y1.
		*/
		triangle[0] = new Point(x1, y1);
		triangle[1] = new Point(x2, y2);
		triangle[2] = new Point(x3, y3);
		sideLength = x3 - x2;
		// triangle time! :D; this is just where you plot the top points;
		/* sidelength for triangle base in this case is x3-x2 
		*(since the points containing x3, x2 are base points.*/
		
		page.setColor(Color.red);
		page.drawRect(x1, y1, 0, 0);
		page.setColor(Color.green);
		page.drawRect(x2, y2, 0, 0);
		page.setColor(Color.blue);
		page.drawRect(x3, y3, 0, 0);		
		
		/* this above code not only sets the color but also 
		 * lets us see the points :D;
		*/
		double slope1 = (double)(y1 - y2)/(double)(x1 - x2);
		double slope2 = (double)(y1 - y3)/(double)(x1 - x3);
		int b1 = (int)(y2 - slope1 * x2);
		int b2 = (int)(y3 - slope2 * x3);
		/*the slopes must be doubles! otherwise, if it is put as an int, 
		 * the numbers get rounded off, and those rounding-offs only add up
		 * to lots and lots of bugs, and a really creepy triangle.
		b1, b2 are the y-intercepts of the equation(s) of the slopes.
		*/
		
		Random generator = new Random();
		
		int randY = generator.nextInt(y3 - y1) + y1;
		
		/* this is the random generator for the 
		 * y value of a new point that the computer will pick. As you can see
		 * y3 is the top y value of the max/top point of the triangle, 
		 * while y1 is the y value of the minimum y value at the base.
		*/
		int xMin = (int)((randY - b1)/slope1);
		int xMax = (int)((randY - b2)/slope2);
		
		/* these 2 equations calculate the minimum and maximum values of x, 
		 * using the restrictions for y and the values of b, to find x.
		*/
		int randX = generator.nextInt(xMax - xMin) + xMin;
		
		/*again, a random function used to pick a random x, between
		 * the max and min x values which were found out using the above 
		 * 2 equations. 
		*/
		Point chosenVertex = triangle[generator.nextInt(3)];
		int midPointX = (chosenVertex.x + randX)/2;
		int midPointY = (chosenVertex.y + randY)/2;
		
		/* this above code tells the computer to chose the midpoint 
		 * between the randomly selected point and a randomly selected 
		 * vertex of the triangle. 
		*/
		int i = 0;
		while(i <= MAX_POINTS) {
			page.setColor(new Color(getColorVal(distance(midPointX, midPointY, triangle[0].x, triangle[0].y), sideLength), getColorVal(distance(midPointX, midPointY, triangle[1].x, triangle[1].y), sideLength), getColorVal(distance(midPointX, midPointY, triangle[2].x, triangle[2].y), sideLength)));
			page.drawRect(midPointX, midPointY, 0, 0);
			chosenVertex = triangle[generator.nextInt(3)];
			midPointX = (chosenVertex.x + midPointX)/2;
			midPointY = (chosenVertex.y + midPointY)/2;
			i++;
		
		/* this code above tells the computer to basically sets the 
		 * color of each point in the triangle (not only vertex).
			we use the distance between the selected point and 
			each vertex (where a given color 
			(either r, g or b) is 1 (100%)) to
			find the intensity of each color at that point.
			Note: the "getcolorval" basically is the function 
			that tells the computer what that intensity is;
			*/
		}

	}
	
	private float getColorVal(double distance, double sideLength) {
		float dist = (float)(distance / sideLength);
		if(dist > 1) {
			dist = 1;
		}
		return 1 - dist;
	}
	/* this code above tells the computer to grab the  distance 
	 * (which is defined below, as the square root of 
	 * difference between x and y coordinates of 2 different points) 
	 *divided by side length (which is the length of one side of the triangle).
	 * This program then returns the value of 
	 * 1- dist (which is the variable that equals distance/ sidelegnth);
	 * Note: we do 1- dist, since in terms of color and the RGB, 1= 100%;
	
*/
	private double distance(int x1, int y1, int x2, int y2) {
		int ySq = (y2 - y1)*(y2 - y1);
		int xSq = (x2 - x1)*(x2 - x1);
		return Math.sqrt(xSq + ySq);
	}
}
// this code above defines the "mini program" for distance.
class Point {
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

//above is a class that just deals with points. 

// Dan Brown; D block;

