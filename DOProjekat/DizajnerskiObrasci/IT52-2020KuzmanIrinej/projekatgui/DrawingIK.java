 package projekatgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.dnd.MouseDragGestureRecognizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class DrawingIK extends JFrame {

	
	private JPanel contentPane;
	PnlDrawing pnlDrawing = new PnlDrawing();
	public PnlDrawing panel;
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnDonut;
	JToggleButton btnSelect;
	JToggleButton tglbtnDelete;
	JToggleButton tglbtnEdit;
	int counter = 1;
	int x1,y1,x2,y2;
	
	
	private ButtonGroup btnsShapes = new ButtonGroup();
	private ButtonGroup btnsEdit = new ButtonGroup();
	
	JButton btnEdgeColor = new JButton("Choose Edge Color");
	private Color edgeColor = Color.BLACK;
	JButton btnInnerColor = new JButton("Choose Inner Color");						//color default
	private Color innerColor = Color.WHITE;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingIK frame = new DrawingIK();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DrawingIK() {
		setTitle("Kuzman Irinej IT 52/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 200, 740, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlTop, BorderLayout.NORTH);
		
		
		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		
		
		
		
		tglbtnPoint = new JToggleButton("x");
		tglbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.deSelectAll(pnlDrawing.getShapes());
			 	tglbtnEdit.setEnabled(false);
			 	tglbtnDelete.setEnabled(false);
			}
		});
		tglbtnLine = new JToggleButton("\u2014");
		tglbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.deSelectAll(pnlDrawing.getShapes());
			 	tglbtnEdit.setEnabled(false);
			 	tglbtnDelete.setEnabled(false);
			}
		});
		tglbtnRectangle = new JToggleButton("\u25AD");
		tglbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.deSelectAll(pnlDrawing.getShapes());
			 	tglbtnEdit.setEnabled(false);
			 	tglbtnDelete.setEnabled(false);
			}
		});
		tglbtnCircle = new JToggleButton("O");
		tglbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.deSelectAll(pnlDrawing.getShapes());
			 	tglbtnEdit.setEnabled(false);
			 	tglbtnDelete.setEnabled(false);
			}
		});
		tglbtnDonut = new JToggleButton("\u25CE");
		tglbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.deSelectAll(pnlDrawing.getShapes());
			 	tglbtnEdit.setEnabled(false);
			 	tglbtnDelete.setEnabled(false);
			}
		});
		btnSelect = new JToggleButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnsEdit.clearSelection();
			}
		});
		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pick a shape you want to delete.To select again, press button select.");
			}
		});
		tglbtnEdit = new JToggleButton("Edit");
		tglbtnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pick a shape you want to edit.To select again, press button select.");
				
			}
		});
		
		btnsShapes.add(tglbtnPoint);
		btnsShapes.add(tglbtnLine);
		btnsShapes.add(tglbtnRectangle);
		btnsShapes.add(tglbtnCircle);															// brn GRUPA
		btnsShapes.add(tglbtnDonut);
		btnsShapes.add(btnSelect);
		btnsEdit.add(tglbtnDelete);
		btnsEdit.add(tglbtnEdit);
		
		
		
		
		
		
		
		
		
		
		
		GroupLayout gl_pnlTop = new GroupLayout(pnlTop);
		gl_pnlTop.setHorizontalGroup(
			gl_pnlTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTop.createSequentialGroup()
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addContainerGap()
							.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(tglbtnRectangle, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addGap(52)
							.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addGap(57)
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addComponent(tglbtnDelete, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tglbtnEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEdgeColor)
						.addComponent(btnInnerColor))
					.addGap(47))
		);
		gl_pnlTop.setVerticalGroup(
			gl_pnlTop.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTop.createSequentialGroup()
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_pnlTop.createParallelGroup(Alignment.BASELINE)
									.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
									.addComponent(tglbtnRectangle, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnEdgeColor)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInnerColor))
					.addContainerGap())
				.addGroup(gl_pnlTop.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnSelect)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnDelete)
						.addComponent(tglbtnEdit))
					.addGap(22))
		);
		
		
		
		btnEdgeColor.setBackground(edgeColor);
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
	 			Color chooseColor= jcc.showDialog(null, "Pick a color.", Color.black);
	 			btnEdgeColor.setBackground(chooseColor);															//PANELE ZA BOJU
				
			}
		});
		
		
		btnInnerColor.setBackground(innerColor);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
	 			Color chooseColor= jcc.showDialog(null, "Pick a color.", Color.black);
	 			btnInnerColor.setBackground(chooseColor);
				
			}
		});
		pnlTop.setLayout(gl_pnlTop);
		
	
		pnlDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnSelect.isSelected()) {
					if(pnlDrawing.contains(e.getX(),e.getY())) {
						pnlDrawing.deSelectAll(pnlDrawing.getShapes());
						tglbtnEdit.setEnabled(false);
					 	tglbtnDelete.setEnabled(false);
					}
					
				try {
					for(Object i : pnlDrawing.getShapes()) {
						
						if(i instanceof Point) {
							int x = e.getX();
							int y = e.getY();
							
							if(((Point) i).contains(x, y) == true) {	
								pnlDrawing.deSelectAll(pnlDrawing.getShapes());
								((Point) i).setSelected(true);
								if(((Point) i).isSelected()) {
									tglbtnEdit.setEnabled(true);
									tglbtnDelete.setEnabled(true);
									if(tglbtnEdit.isSelected()) {
										
										DlgPoint dlgpoint = new DlgPoint();
										
										dlgpoint.setX1(((Point) i).getX());            
										dlgpoint.setY1(((Point) i).getY());

										dlgpoint.getTxtX().setText(Integer.toString(x));
										dlgpoint.getTxtY().setText(Integer.toString(y));
										dlgpoint.setVisible(true);
										
										pnlDrawing.getShapes().remove(i);
										Point p1 = new Point(dlgpoint.getX1(),dlgpoint.getY1(),btnEdgeColor.getBackground());
										pnlDrawing.getShapes().add(p1);  

									}
									
								if(tglbtnDelete.isSelected())
									if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected point?", "Delete Point?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
										pnlDrawing.getShapes().remove(i);
										
								}

							
							}}
						else if(i instanceof Line) {
							int x = e.getX();
							int y = e.getY();
							
							if(((Line) i).contains(x, y)== true) {
								pnlDrawing.deSelectAll(pnlDrawing.getShapes());
								((Line) i).setSelected(true);
								if(((Line) i).isSelected()) {
									tglbtnEdit.setEnabled(true);
									tglbtnDelete.setEnabled(true);
									if(tglbtnEdit.isSelected()) {
										DlgLine dlgline = new DlgLine();
										dlgline.setX1(((Line) i).getStartPoint().getX());
										dlgline.setY1(((Line) i).getStartPoint().getY());
										dlgline.setX2(((Line) i).getEndPoint().getX());
										dlgline.setY2(((Line) i).getEndPoint().getY());
										

										dlgline.getTxtSPX().setText(Integer.toString(((Line) i).getStartPoint().getX()));
										dlgline.getTxtSPY().setText(Integer.toString(((Line) i).getStartPoint().getY()));
										dlgline.getTxtEPX().setText(Integer.toString(((Line) i).getEndPoint().getX()));
										dlgline.getTxtEPY().setText(Integer.toString(((Line) i).getEndPoint().getY()));
										dlgline.setVisible(true);
										
										pnlDrawing.getShapes().remove(i);
										Line l1 = new Line(new Point(dlgline.getX1(),dlgline.getY1()),new Point(dlgline.getX2(),dlgline.getY2()),btnEdgeColor.getBackground());
										pnlDrawing.getShapes().add(l1);
										
								}}
							
								if(tglbtnDelete.isSelected()) {
										if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected line?", "Delete line?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) 
											pnlDrawing.getShapes().remove(i);
									}
									
								
								
								
								
							}}
						else if(i instanceof Rectangle) {
							int x = e.getX();
							int y = e.getY();
							
							if(((Rectangle) i).contains(x, y)== true) {
								
								pnlDrawing.deSelectAll(pnlDrawing.getShapes());
								((Rectangle) i).setSelected(true);
								if(((Rectangle) i).isSelected()) {
									tglbtnEdit.setEnabled(true);
									tglbtnDelete.setEnabled(true);
									if(tglbtnEdit.isSelected()) {
										DlgRectangle dlgrectangle = new DlgRectangle();
										dlgrectangle.setX(((Rectangle) i).getUpperLeft().getX());
										dlgrectangle.setY(((Rectangle) i).getUpperLeft().getY());
										dlgrectangle.setSirina(((Rectangle) i).getWidth());
										dlgrectangle.setVisina(((Rectangle) i).getHight());
										
										
										
										dlgrectangle.getTxtX().setText(Integer.toString(((Rectangle) i).getUpperLeft().getX()));
										dlgrectangle.getTxtY().setText(Integer.toString(((Rectangle) i).getUpperLeft().getY()));
										dlgrectangle.getTxtSirina().setText(Integer.toString(((Rectangle) i).getWidth()));
										dlgrectangle.getTxtVisina().setText(Integer.toString(((Rectangle) i).getHight()));
										dlgrectangle.setVisible(true);
										pnlDrawing.getShapes().remove(i);
										Rectangle k1 = new Rectangle(new Point(dlgrectangle.getX1(),dlgrectangle.getY1()),dlgrectangle.getSirina(),dlgrectangle.getVisina(),btnInnerColor.getBackground(),btnEdgeColor.getBackground());
										pnlDrawing.getShapes().add(k1);
										k1.setSelected(false);
									
								}}
								if(tglbtnDelete.isSelected()) {
								
										if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected rectangle?", "Delete Rectangle?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) 
											pnlDrawing.getShapes().remove(i);}

								
							}}
						else if(i instanceof Circle && !(i instanceof Donut)) {
							int x = e.getX();
							int y = e.getY();
							
							if(((Circle) i).contains(x, y)==true) {
								pnlDrawing.deSelectAll(pnlDrawing.getShapes());
								((Circle)i).setSelected(true);
								if(((Circle) i).isSelected()) {
									tglbtnEdit.setEnabled(true);
									tglbtnDelete.setEnabled(true);
									if(tglbtnEdit.isSelected()) {
										DlgCircle dlgcircle = new DlgCircle();
										dlgcircle.setX1(((Circle) i).getCenter().getX());
										dlgcircle.setY1(((Circle) i).getCenter().getY());
										dlgcircle.setRadius(((Circle) i).getRadius());
										
										
										
										dlgcircle.getTxtX().setText(Integer.toString(((Circle) i).getCenter().getX()));
										dlgcircle.getTxtY().setText(Integer.toString(((Circle) i).getCenter().getY()));
										dlgcircle.getTxtRadius().setText(Integer.toString(((Circle) i).getRadius()));
										dlgcircle.setVisible(true);
										pnlDrawing.getShapes().remove(i);
										Circle c1 = new Circle(new Point(dlgcircle.getX1(),dlgcircle.getY1()),dlgcircle.getRadius(),btnInnerColor.getBackground(),btnEdgeColor.getBackground());
										pnlDrawing.getShapes().add(c1);
										c1.setSelected(false);
									}}
									if(tglbtnDelete.isSelected()){
								
										if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected circle?", "Delete Circle?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) 
											pnlDrawing.getShapes().remove(i);}
										

								
							}}
						else if(i instanceof Donut) {
							int x = e.getX();
							int y = e.getY();
							
							if(((Donut) i).contains(x, y)==true) {
								pnlDrawing.deSelectAll(pnlDrawing.getShapes());
								((Donut) i).setSelected(true);
								if(((Donut) i).isSelected()) {
									tglbtnEdit.setEnabled(true);
									tglbtnDelete.setEnabled(true);
									if(tglbtnEdit.isSelected()) {
										DlgDonut dlgdonut = new DlgDonut();
										dlgdonut.setX(((Donut) i).getCenter().getX());
										dlgdonut.setY(((Donut) i).getCenter().getY());
										dlgdonut.setRadius(((Donut) i).getRadius());
										dlgdonut.setInnerradius(((Donut) i).getInnerRadius());
										
										((Donut) i).setSelected(true);
										
										dlgdonut.getTxtX().setText(Integer.toString(((Donut) i).getCenter().getX()));
										dlgdonut.getTxtY().setText(Integer.toString(((Donut) i).getCenter().getY()));
										dlgdonut.getTxtRadius().setText(Integer.toString(((Donut) i).getRadius()));
										dlgdonut.getTxtInnerRadius().setText(Integer.toString(((Donut) i).getInnerRadius()));
										dlgdonut.setVisible(true);
										pnlDrawing.getShapes().remove(i);
										Donut d1 = new Donut(new Point(dlgdonut.getX(),dlgdonut.getY()),dlgdonut.getRadius(),dlgdonut.getInnerradius(),btnInnerColor.getBackground(),btnEdgeColor.getBackground());
										pnlDrawing.getShapes().add(d1);
										d1.setSelected(false);
									}
							
									if(tglbtnDelete.isSelected()) {
							
										if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected donut?", "Delete Donut?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) 
											pnlDrawing.getShapes().remove(i);
										}
								
								}}
						
						
					}
					}}catch (ConcurrentModificationException e2) {
						System.out.println();
					}
					}else if(tglbtnPoint.isSelected()) {
						
						int x1 = e.getX();
						int y1 = e.getY();
						Point p1 = new Point(x1,y1,btnEdgeColor.getBackground());
						pnlDrawing.getShapes().add(p1);
						p1.setSelected(false);
						}
				 else if(tglbtnLine.isSelected()) {
					 
						if(counter > 0) {
							x1 = e.getX();
							y1 = e.getY();
						}
						if(counter < 0) {
							x2 = e.getX();
							y2 = e.getY();
							Line l1 = new Line(new Point(x1,y1),new Point(x2,y2),btnEdgeColor.getBackground());
							pnlDrawing.getShapes().add(l1);
						}
						counter = counter -counter*2;
					}
				else if(tglbtnRectangle.isSelected()) {
				
						x1 = e.getX();
						y1 = e.getY();
						DlgRectangle dlgRectangle = new DlgRectangle();
						dlgRectangle.getTxtX().setText(Integer.toString(x1));
						dlgRectangle.getTxtY().setText(Integer.toString(y1));
						dlgRectangle.setVisible(true);
						Rectangle k1 = new Rectangle(new Point(x1,y1),dlgRectangle.getSirina(),dlgRectangle.getVisina(),btnInnerColor.getBackground(),btnEdgeColor.getBackground());
						pnlDrawing.getShapes().add(k1);
		
					}
				else if(tglbtnCircle.isSelected()) {
				
						x1 =e.getX();
						y1 = e.getY();
						DlgCircle dlgCircle = new DlgCircle();
						dlgCircle.getTxtX().setText(Integer.toString(x1));
						dlgCircle.getTxtY().setText(Integer.toString(y1));
						dlgCircle.setVisible(true);
						Circle c1 = new Circle(new Point(x1,y1),dlgCircle.getRadius(),btnInnerColor.getBackground(),btnEdgeColor.getBackground());
						pnlDrawing.getShapes().add(c1);
					}
				else if(tglbtnDonut.isSelected()) {
				
						x1 = e.getX();
						y1 = e.getY();
						DlgDonut dlgDonut = new DlgDonut();
						dlgDonut.getTxtX().setText(Integer.toString(x1));
						dlgDonut.getTxtY().setText(Integer.toString(y1));
						dlgDonut.setVisible(true);
						Donut d1 = new Donut(new Point(x1,y1),dlgDonut.getRadius(),dlgDonut.getInnerradius(),btnInnerColor.getBackground(),btnEdgeColor.getBackground());
						pnlDrawing.getShapes().add(d1);
						
					}
				
					
					
					}
				
				
					
					
						
					
				
				
				
				
				
				
		
				
				
				
			

			
		
		
		
		
		
	});	
}}
		
	
	
	

	
	

	

