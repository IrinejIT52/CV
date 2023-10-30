 package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Drawing");   //ram gdje slikamo sa sivim platnom
		frame.setSize(800,600);
		
		Drawing drawing = new Drawing();		// panel
		frame.getContentPane().add(drawing);	//dodajemo drawing
		frame.setVisible(true);					//postavljamo ga na visible(da se vidi)
		
		
	}

	@Override
	public void paint(Graphics g) {
//		
		Point p1 = new Point(50,50);
//		p1.draw(g);
//		
//		g.setColor(Color.GREEN); // postavljanje boje
//		
		Line l1 = new Line(new Point(100,100), new Point(200,200));
//		l1.draw(g);
//		
		g.setColor(Color.BLUE);
//		l1.getStartPoint().draw(g);
//		g.setColor(Color.BLACK);
//		
		
//		r1.draw(g);
//		
		Circle c1 = new Circle(new Point(500,100),70);
//		c1.draw(g);
//		c1.getCenter().draw(g);
//		
		Donut d1 = new Donut(new Point(800,100),50,25);
//		d1.draw(g);
		
		
		
		//k1.draw(g);
		
		
		//d2.draw(g);
	
		
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.add(p1);
		shapes.add(l1);
		
//		shapes.add(2,c1);
//		Rectangle r1=new Rectangle();
		
		Iterator<Shape> it= shapes.iterator();
		while(it.hasNext()) {
			it.next().moveBy(10,0);
		}
		
		//Zadatak 2
		
//		shapes.get(2).draw(g);
//		shapes.get(shapes.size()-1).draw(g);
//		shapes.remove(1);
//		shapes.get(1).draw(g);
//		shapes.get(3).draw(g);
//		shapes.add(3, l1);
		
//		while(it.hasNext()) {
//			Shape s = it.next();
//			if(s instanceof Circle) {
//				s.draw(g);
//			}
//		}
		
		// Zadatak 4
		
//		p1.setSelected(true);
//		c1.setSelected(true);
//		d2.setSelected(true);
//		
		shapes.get(3).draw(g);
		shapes.get(0).draw(g);
		shapes.get(2).draw(g);
		
		
		//Zadatak 5
		
		HashMap<String, Shape> hmShape = new HashMap<String,Shape>();
		hmShape.put("Point", shapes.get(0));
		hmShape.put("Line", shapes.get(1));
		hmShape.put("Donut", shapes.get(3));
		hmShape.put("Circle", shapes.get(2));
		
		System.out.println(hmShape.get("Point"));
	}
	
	

	
}
