package projekatgui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;
import javax.swing.text.html.HTMLDocument.Iterator;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.util.ArrayList;


public class PnlDrawing extends JPanel {

		JPanel panel = new JPanel();
		private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public PnlDrawing()  {
		
		
		
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel, BorderLayout.CENTER);
		setBackground(Color.white);
		
	}
		 
		 

		
	@Override
	public void paint(Graphics g) {
		super.paint(g);																				//Iterator za crtanje 
		java.util.Iterator<Shape> it = shapes.iterator();
		while(it.hasNext())
			it.next().draw(g);
		
		repaint();
	
		
	}
	



	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	



	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void deSelectAll(ArrayList<Shape> shapes) {
		java.util.Iterator<Shape> it =shapes.iterator();
		while(it.hasNext()) {
			it.next().setSelected(false);
		}
	}
	
	
	
	
	
	
	
	
	

}
	
	






	




	
		


	
	

