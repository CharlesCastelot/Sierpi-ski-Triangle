/*
 * Written by Charles Castelot
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Triangles extends Canvas{
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sierpinki's triangle");
		frame.setSize(900,900);
		Triangles sp = new Triangles();
		frame.add(sp);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Set the initial triangle
	public void paint(Graphics g){
		
		// First triangle (The base, and the only upright, and black triangle)
		int X[] = { 0, this.getSize().width/2, this.getSize().width};
		int Y[] = { this.getSize().height, 0, this.getSize().height};
		int numberOfPoints = 3;
		g.fillPolygon(X, Y, 3);
		g.setColor(Color.black);
		
		// The other triangles (structure)
		int x[] = { (X[0]+X[1])/2, (X[1]+X[2])/2 ,(X[2]+X[0])/2 };
		int y[] = { (Y[0]+Y[1])/2, (Y[1]+Y[2])/2 ,(Y[2]+Y[0])/2 };
		drawTriangle(x, y, numberOfPoints, g);
	}
	
	//Draw the triangles
	public void drawTriangle(int[] xPoints, int[] yPoints, int numberOfPoints, Graphics g) {
		
		//Draw the triangle in white with the x and y coordinate entering the method
		g.setColor(Color.white);
		g.fillPolygon(xPoints, yPoints, numberOfPoints);
		
		//Loop to make smaller and smaller upside-down triangles
		int lengthSides = xPoints[1]-xPoints[0];
		if(lengthSides >= 4) 
		{
			//BottomLeft triangles
			int BLx[] = { ((2*xPoints[0])+xPoints[2]-xPoints[1])/2, (xPoints[0]+xPoints[2])/2 		 	  , xPoints[0] };
			int BLy[] = { yPoints[0]+((yPoints[2]-yPoints[0])/2)  , yPoints[0]+((yPoints[2]-yPoints[0])/2), yPoints[2] };
			drawTriangle(BLx, BLy, numberOfPoints, g);
			
			//Top triangles
			int Tx[] = { (xPoints[0]+xPoints[2])/2							  , (xPoints[1]+xPoints[2])/2 			  				 , xPoints[2] };
			int Ty[] = { ((yPoints[0]+yPoints[2])/2) - (yPoints[2]-yPoints[0]), ((yPoints[0]+yPoints[2])/2) - (yPoints[2]-yPoints[0]), yPoints[0] };
			drawTriangle(Tx, Ty, numberOfPoints, g);
			
			//BottomRight triangles
			int BRx[] = { (xPoints[1]+xPoints[2])/2				, ((2*xPoints[1])+xPoints[2]-xPoints[0])/2 	, xPoints[1]};
			int BRy[] = { yPoints[0]+((yPoints[2]-yPoints[1])/2), yPoints[0]+((yPoints[2]-yPoints[0])/2)	, yPoints[2]};
			g.setColor(Color.white);
			drawTriangle(BRx, BRy, numberOfPoints, g);
		}
	}
}
