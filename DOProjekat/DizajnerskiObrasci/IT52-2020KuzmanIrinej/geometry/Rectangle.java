package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends FillShape implements Cloneable {

	private Point upperLeft;
	private int width;
	private int height;
	private int X;
	private int Y;
	private Color inner1,edge1;
	
	public Rectangle() {
		
	}
	public Rectangle(Point upperLeft, int width, int height) {
		this.upperLeft =upperLeft;
		this.width =width;
		this.height=height;
	}
	public Rectangle(Point upperLeft, int width, int height,boolean selected) {
		this(upperLeft,width,height);
		this.selected=selected;
	}
	
	public Rectangle(Point upperLeft,int width,int height,Color inner1,Color edge1) {
		this.upperLeft = upperLeft;
		this.width = width;
		this.height = height;
		this.inner1 = inner1;
		this.edge1 = edge1;
	}
	
	
	//
	public Point getUpperLeft() {
		return upperLeft;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft =upperLeft;
	}
	
	
	//
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	//
	
	
	// Povrsina i obim
	public int area() {
		return width*height;
	}
	public int circumfrence() {
		return 2*width+2*height;
	}
	
	
	
	//
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle pomocni =(Rectangle)obj;
			if(this.upperLeft.equals(pomocni.upperLeft) && this.width == pomocni.width && this.height == pomocni.height)
				return true;
			else
				return false;
		}else {
			return false;
		}
	}
	
	//
	public boolean contains(int x,int y) {
		return (upperLeft.getX()<x && upperLeft.getX()+width>x && 
				upperLeft.getY()<y && upperLeft.getY()+height>y);
	}
	public boolean contains(Point p) {
		return contains(p.getX(),p.getY());
	}
	
	
	
	
	// Ispis
	@Override
	public String toString() {
		return "Rectangle X: "+upperLeft.getX()+" Y: "+upperLeft.getY()+" Width: "+getWidth()+" Hight: "+getHight()+" Edge Color:"+ edge1 + " Inner Color:"+ inner1;
	}
	//
	@Override
	public void draw(Graphics g) {
		g.setColor(getEdge1());
		g.drawRect(upperLeft.getX(),upperLeft.getY(),width,height);
		g.setColor(getInner1());
		g.fillRect(upperLeft.getX()+1,upperLeft.getY()+1,width-1,height-1);
		
		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(getUpperLeft().getX()-2,getUpperLeft().getY()-2, 4, 4);
			g.drawRect(getUpperLeft().getX()-2+width, getUpperLeft().getY()-2, 4, 4);
			g.drawRect(getUpperLeft().getX()-2,getUpperLeft().getY()-2+height, 4, 4);
			g.drawRect(getUpperLeft().getX()-2+width, getUpperLeft().getY()-2+height, 4, 4);
		}
		
	}
	
	//Interface
	@Override
	public void moveTo(int x, int y) {
		upperLeft.moveTo(x, y);
	}
	@Override
	public void moveBy(int byX, int byY) {
		upperLeft.moveBy(byX, byY);
	}
	
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			return(this.area()-((Rectangle)o).area());
		}
		return 0;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	
	
	public Rectangle clone()
	{
		Point p1 = new Point();
		Rectangle rectangle = new Rectangle(p1,0,0);
		rectangle.getUpperLeft().setX(this.getUpperLeft().getX());
		rectangle.getUpperLeft().setY(this.getUpperLeft().getY());
		rectangle.setHeight(this.getHight());
		rectangle.setWidth(this.getWidth());
		rectangle.setEdge1(this.edge1);
		rectangle.setInner1(this.inner1);
		return rectangle;
	}
	public Color getInner1() {
		return inner1;
	}
	public void setInner1(Color inner1) {
		this.inner1 = inner1;
	}
	public Color getEdge1() {
		return edge1;
	}
	public void setEdge1(Color edge1) {
		this.edge1 = edge1;
	}
	
	
	
	
	
	
	
}
