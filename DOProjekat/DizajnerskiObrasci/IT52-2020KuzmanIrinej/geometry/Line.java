package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Cloneable {

	private Point startPoint;
	private Point endPoint;
	Color color;
	
	//Konstruktori
	
	public Line() {
		
	}
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint,endPoint);
		this.selected = selected;
	}
	 
	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint, endPoint);
		setColor(color);
	}
	public Line(Point point,Point point2, boolean selected, Color color) {
		this(point, point2, selected);
		this.setColor(color);
	}
	
	
	
	
	
		
		
	
	//
	public Point getStartPoint() {
		return this.startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	
	
	
	//
	public double lenght() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	//
	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			Line pomocna = (Line)obj;
			if(this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint))
				return true;
			else
				return false;
		} else {
			return false;
						
		}
	}
	
	//
	public boolean contains(int x,int y) {
		return startPoint.distance(x, y) + endPoint.distance(x, y) - lenght()<=2;
	}
	
	
	
	@Override
	public String toString() {
		return "Line [startPoint=" + startPoint + ", endPoint=" + endPoint+", Color=" + color+"]";
	}
	//
	@Override
	public void draw(Graphics g) {
		
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		
		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(getStartPoint().getX()-2,getStartPoint().getY()-2 , 4, 4);
			g.drawRect(getEndPoint().getX()-2,getEndPoint().getY()-2,4,4);
		}
		
		
	}
	// Inteface
	@Override
	public void moveTo(int x, int y) {
	}
	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}
	
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line) {
			return(int)(this.lenght()-((Line)o).lenght());
		}
		return 0;
	}
	
	
	public Line clone()
	{
		Point p1= new Point();
		Point p2=new Point();
		Line line = new Line(p1,p2);
		line.getStartPoint().setX(this.getStartPoint().getX());
		line.getStartPoint().setY(this.getStartPoint().getY());
		line.getEndPoint().setX(this.getEndPoint().getX());
		line.getEndPoint().setY(this.getEndPoint().getY());
		line.setColor(this.getColor());
		return line;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
