package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;


public class Donut extends Circle implements Cloneable {
 
	//
	private int innerRadius;
	private Color inner,edge;
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	
	// Konstruktori
	public Donut() {
		
	}
	public Donut(Point center,int r,int innerR) {
		super(center,r); // poziv konstruktora osnovne klase, prosledjujem mu parameter
						// mora biti prva naredba u konstruktoru izvedene klase
		this.innerRadius = innerR;
	}
	public Donut(Point center,int r,int innerR,boolean selected) {
		this(center,r,innerR);
		setSelected(selected);
	}
	public Donut(Point center,int r,int innerR,Color inner,Color edge) {
		super(center,r); 
		this.innerRadius = innerR;
		this.inner = inner;
		this.edge = edge;
	}
	
	// Redefinisemo metoede nasledjene iz osnovih klasa
	@Override
	public double area() {
		return super.area() - innerRadius*innerRadius*Math.PI; //Super poziva metodu osnovne klase.
															  //Da smo napisali samo area doslo bi do rekurzije
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			Donut pomocni = (Donut)obj;
			if(getCenter().equals(pomocni.getCenter()) && getRadius() == pomocni.getRadius() && innerRadius 
					== pomocni.innerRadius);
				return true;
		}
		return false;
	}
	@Override
	public boolean contains(int x, int y) {
		return getCenter().distance(x,y) <= radius && getCenter().distance(x, y)>=innerRadius;
		
	}
	@Override
	public boolean contains(Point p) {
		return this.contains(p.getX(),p.getY());
	}
	@Override
	public String toString() {
		return super.toString() + ", innerRadius = "+innerRadius;
	}
	
	//
	@Override
	public void draw(Graphics g) {
		/*
		g.setColor(edge);
		g.drawOval(getCenter().getX()- radius,getCenter().getY()- radius, 2*radius, 2*radius);
		g.setColor(inner);
		g.fillOval(getCenter().getX()- radius+1,getCenter().getY()- radius+1, 2*radius-2, 2*radius-2);
		g.setColor(edge);
		g.drawOval(getCenter().getX()- innerRadius,getCenter().getY()- innerRadius, 2*innerRadius, 2*innerRadius);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX()- innerRadius+1,getCenter().getY()- innerRadius+1, 2*innerRadius-2, 2*innerRadius-2);
		*/
		
		g.setColor(edge);
		g.drawOval(getCenter().getX()- radius,getCenter().getY()- radius, 2*radius, 2*radius);
		g.drawOval(getCenter().getX()- innerRadius,getCenter().getY()- innerRadius, 2*innerRadius, 2*innerRadius);
		Ellipse2D ellipseInner=new Ellipse2D.Float(center.getX()-innerRadius,center.getY()- innerRadius,2* innerRadius,2* innerRadius);
		Ellipse2D ellipseOuter=new Ellipse2D.Float(center.getX()-getRadius(),center.getY()- getRadius(),2* getRadius(),2* getRadius());
		
	
		Area outerArea = new Area(ellipseOuter);
		Area innerArea =new Area(ellipseInner);
		outerArea.subtract(innerArea);
		
		
		g.setColor(inner);
		((Graphics2D) g).fill(outerArea);
		
		
		
		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX()-innerRadius-2,getCenter().getY()-2 , 4, 4);
			g.drawRect(getCenter().getX()+innerRadius-2,getCenter().getY()-2 , 4, 4);
			g.drawRect(getCenter().getX()-2,getCenter().getY()-2+innerRadius , 4, 4);
			g.drawRect(getCenter().getX()-2,getCenter().getY()-2-innerRadius , 4, 4);
		}
	}
	
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Circle) {
			return (int)(this.area() - ((Donut)o).area());
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
	
	
	public Donut clone()
	{
		Point p1 = new Point();
		Donut donut = new Donut(p1,0,0);
		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		try {
			donut.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setInnerRadius(this.getInnerRadius());
		donut.setEdge(this.getEdge());
		donut.setInner(this.getInner());
		return donut;
	}
	
}