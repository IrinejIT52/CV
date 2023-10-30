package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class FillShape extends Shape {
	
	private Color InnerColor;
	
	
	
	public Color getInnerColor() {
		return InnerColor;
	}
	public void setInnerColor(Color InnerColor) {
		this.InnerColor = InnerColor;
	}
	

	

	
	

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
