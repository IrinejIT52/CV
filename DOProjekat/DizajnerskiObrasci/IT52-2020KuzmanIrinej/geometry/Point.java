package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Cloneable {

	private int x,y;
	private Color color;
	
	
	
//Konstruktori
	
	public Point() {
		
	}
	public Point(int x, int y) {
		this.x =x;
		this.y =y;
	}
	public Point(int x,int y, boolean selected) {
		this (x,y);
		this.selected = selected;
	}
	public Point(int x,int y, Color color) {
		this(x,y);
		setColor(color);
	}
	
	//
	public double distance(int x2,int y2) {
		double dx = x-x2;
		double dy = y-y2;
		
		double d = Math.sqrt(dx*dx+dy*dy);
		return d;
	}
	//
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point pomocna=(Point)obj;
			if(this.x == pomocna.getX() && this.y == pomocna.getY())
				return true;
			else
				return false;
		}else {
			return false;
		}			
		}
	
	
	//
	public boolean contains(int x,int y) {
		return this.distance(x, y)<=2;
	}
	
	
	
	
	//set i get metoda
	
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	
	
	
	//
	public String toString() {
		return "Point (" + x + ","+y+", Color="+ this.color+ ")"; // (x,y)
	}
	
	//
	@Override
	public void draw(Graphics g) {
		
		g.setColor(getColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);
		
		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(x-2,y-2,4,4);
		}
	
	}
	// Interface
	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public void moveBy(int byX, int byY) {
		this.x = this.x+byX;
		this.y = this.y+byY;
	}
	
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Point) {
			 return (int)(this.distance(0, 0)-((Point)o).distance(0, 0)); 
		}
		return 0;
		
	}
	

	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		this.setColor(color);
	}
	
	public Point clone()
	{
		Point point =new Point();
		point.setX(this.getX());
		point.setY(this.getY());
		point.setColor(this.color);
		return point;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
}
