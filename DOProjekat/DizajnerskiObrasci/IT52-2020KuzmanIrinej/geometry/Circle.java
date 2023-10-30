package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape implements Cloneable {

	protected Point center;
	protected int radius; 
	private Color inner,edge;
	//
	public Circle() {
		
	}
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius =radius;
	}
	public Circle(Point center, int radius,boolean selected) {
		this(center,radius);
		this.selected =selected;
	}
	public Circle(Point center, int radius,Color inner,Color edge) {
		this.center = center;
		this.radius =radius;
		this.inner = inner;
		this.edge = edge;
	}
	
	
	//
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception {
		if(radius<0)
			throw new Exception("Radius ne moze biti manji od nule.");
		this.radius = radius;
	}
	//Exception moramo napisati za sve motede gdje je potrebno 
	
	
	
	//Povrsina i Obim
	
	public double area() {
		return radius*radius*Math.PI;
	}
	public double circumfrence() {
		return 2*radius*Math.PI;
	}
	
	//
	public boolean equals(Object obj) {
		if(obj instanceof Circle) {
			Circle pomocni =(Circle)obj;
			if(this.center.equals(pomocni.center) && this.radius == pomocni.radius)
				return true;
			else 
				return false;
		}else {
			return false;
				
	}
	}
	
	//
	public boolean contains(int x,int y) {
		return center.distance(x, y) <= radius;
	}
	public boolean contains(Point p) {
		return contains(p.getX(),p.getY());
	}
	
	
	
	// Ispis
	 public String toString() {
		 return "Center: "+center+", radius: "+ radius+" Edge Color:"+ edge + " Inner Color:"+ inner;
	 }
	//
	@Override
	public void draw(Graphics g) {
		g.setColor(getEdge());
		g.drawOval(center.getX()-radius, center.getY()-radius, 2*radius, 2*radius);
		g.setColor(getInner());
		g.fillOval(center.getX()-radius+1, center.getY()-radius+1, 2*radius-2, 2*radius-2);
		
		if(selected) {
			g.setColor(Color.BLUE);
		g.drawRect(center.getX()-2, center.getY()-2, 4, 4);
		g.drawRect(center.getX()-2+radius, center.getY()-2, 4, 4);
		g.drawRect(center.getX()-2-radius, center.getY()-2, 4, 4);
		g.drawRect(center.getX()-2, center.getY()-2+radius, 4, 4);
		g.drawRect(center.getX()-2, center.getY()-2-radius, 4, 4);
		}
	}
	//Interface
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}
	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
	}
	
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Circle) {
			return(int)(this.area() - ((Circle)o).area());
		}
		return 0;
	}
	public Color getInner() {
		return inner;
	}
	public void setInner(Color inner) {
		this.inner = inner;
	}
	public Color getEdge() {
		return edge;
	}
	public void setEdge(Color edge) {
		this.edge = edge;
	}
	
	
	public Circle clone()
	{
		Point p1 = new Point();
		Circle circle =  new Circle(p1,0);
		circle.getCenter().setX(this.getCenter().getX());
		circle.getCenter().setY(this.getCenter().getY());
		try {
			circle.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setEdge(this.getEdge());
		circle.setInner(this.getInner());
		
		return circle;
			
	}
	
	
	
}
