package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;


public class HexagonAdapter extends Shape implements Cloneable {

	Hexagon hexagon = new Hexagon(0,0,0);
	
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public HexagonAdapter(int x,int y,int r)
	{
		this.hexagon.setX(x);
		this.hexagon.setY(y);
		this.hexagon.setR(r);
		
	}

	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		hexagon.setX(hexagon.getX()+byX);
		hexagon.setY(hexagon.getY()+byY);
		
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		
	}
	public void setSelected(boolean state)
	{
		hexagon.setSelected(state);
	}
	
	public boolean isSelected()
	{
		return hexagon.isSelected();
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public void setAreaColor(Color color)
	{
		hexagon.setAreaColor(color);
	}
	
	public void setBorderColor(Color color)
	{
		hexagon.setBorderColor(color);
	}
	
	public Color getAreaColor()
	{
		return hexagon.getAreaColor();
	}
	public Color getBorderColor()
	{
		return hexagon.getBorderColor();
	}
	
	public HexagonAdapter clone()
	{
		HexagonAdapter hexagon1 = new HexagonAdapter(0,0,0);
		hexagon1.getHexagon().setX(this.hexagon.getX());
		hexagon1.getHexagon().setY(this.hexagon.getY());
		hexagon1.getHexagon().setR(this.hexagon.getR());
		hexagon1.setAreaColor(this.hexagon.getAreaColor());
		hexagon1.setBorderColor(this.hexagon.getBorderColor());
		return hexagon1;
	}
	
	public void setX(int x)
	{
		this.hexagon.setX(x);
	}
	public void setY(int y)
	{
		this.hexagon.setY(y);
	}
	public void setR(int r)
	{
		this.hexagon.setR(r);
	}
	
	public int getX()
	{
		return this.hexagon.getX();
	}
	public int getY()
	{
		return this.hexagon.getY();
	}
	public int getR()
	{
		return this.hexagon.getR();
	}

	@Override
	public String toString() {
		return "Hexagon [X="+hexagon.getX() +", Y="+ hexagon.getY()+", R="+hexagon.getR()+", Edge Color="+ getBorderColor()+", Inner Color="+ getAreaColor()+"]";
	}
	
	
	
}
