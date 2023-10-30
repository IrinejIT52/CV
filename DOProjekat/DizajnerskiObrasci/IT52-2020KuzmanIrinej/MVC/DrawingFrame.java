package MVC;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.border.LineBorder;

import Observer.BtnState;
import Observer.BtnsChangeState;
import geometry.Shape;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import java.awt.ScrollPane;
import javax.swing.DropMode;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import javax.swing.SwingConstants;

public class DrawingFrame extends JFrame {
	
	private DrawingController controller;
	private DrawingView view = new DrawingView();
	
	public DrawingController getController() {
		return controller;
	}
	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	public DrawingView getView() {
		return view;
	}
	public void setView(DrawingView view) {
		this.view = view;
	}
	
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnDonut;
	JToggleButton tglbtnHexagon;
	
	JToggleButton tglbtnSelect;
	
	public JButton btnInnerColor;
	public JButton btnEdgeColor;
	
	
	JButton btnEdit;
	JButton btnDelete;
	
	JButton btnRedo;
	JButton btnUndo;
	
	JButton btnBringToFront;
	JButton btnBringToBack;
	JButton btnToFront;
	JButton btnToBack;
	
	
	BtnState state = new BtnState();
	BtnsChangeState changeState = new BtnsChangeState();
	private JPanel loggerpanel;
	private JSeparator separator;
	TextArea logger;
	
	
	public void deselectAll()
	{
		controller.deselectAll();
		
	}
	
	public DrawingFrame() {
		
		setTitle("IrinejKuzmanIT52/2020");
		
		
		
		
		// ocitava e gdje smo kliknuli
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);
		
		
	
		
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(pnlButtons, BorderLayout.NORTH);
		
		tglbtnPoint = new JToggleButton("x");
		tglbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deselectAll();
				
			}
		});
		tglbtnLine = new JToggleButton("\u2014");
		tglbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deselectAll();
				
			}
		});
		tglbtnRectangle = new JToggleButton("\u25AD");
		tglbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				deselectAll();
				
			}
		});
		tglbtnCircle = new JToggleButton("O");
		tglbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				deselectAll();
			
			}
		});
		tglbtnDonut = new JToggleButton("\u25CE");
		tglbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				deselectAll();
				
			}
		});
		
		tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		tglbtnHexagon = new JToggleButton("\u2B21");
		tglbtnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				deselectAll();
				controller.getModel().getSelectedShapes().clear();
			}
		});
		
		btnEdit = new JButton("Edit");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				state.setRedo(btnRedo, true);
				controller.btnEditClicked(e);
			}
		});
		
		
		btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				state.setRedo(btnRedo, true);
				controller.btnDeleteClicked(e);
			}
		});
		
		
		
		btnEdit.setVisible(false);
		btnDelete.setVisible(false);
		
		
		ButtonGroup btnsShapes = new ButtonGroup();
		btnsShapes.add(tglbtnPoint);
		btnsShapes.add(tglbtnLine);
		btnsShapes.add(tglbtnRectangle);
		btnsShapes.add(tglbtnCircle);
		btnsShapes.add(tglbtnDonut);
		btnsShapes.add(tglbtnSelect);
		btnsShapes.add(tglbtnHexagon);
		
		
		
		btnInnerColor = new JButton("");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
	 			Color chooseColor= jcc.showDialog(null, "Pick a color.", btnInnerColor.getBackground());
	 			btnInnerColor.setBackground(chooseColor);
			}
		});
		
		
		btnEdgeColor = new JButton("");
		btnEdgeColor.setBackground(Color.black);
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
	 			Color chooseColor= jcc.showDialog(null, "Pick a color.",btnEdgeColor.getBackground());
	 			btnEdgeColor.setBackground(chooseColor);
			}
		});
		
		JLabel lblInnerColor = new JLabel("Choose inner color:");
		
		JLabel lblEdgeColor = new JLabel("Choose edge color:");
		
		
		
		btnUndo = new JButton("\u2B05");
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controller.btnUndoClicked(e);
				btnsShapes.clearSelection();
			}
		});
		
		btnRedo = new JButton("\u27A1");
		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controller.btnRedoClicked(e);
				btnsShapes.clearSelection();
			}
		});
		
		btnRedo.setVisible(false);
		btnUndo.setVisible(false);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					controller.btnSaveClicked();
			}
		});
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					controller.btnLoadClicked();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnExecuteLoad = new JButton("Load command");
		btnExecuteLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExecuteLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					controller.btnLoadbyCmdClicked();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		btnBringToFront = new JButton("Bring to Front");
		btnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					controller.btnBringToFrontClicked();
			}
		});
		btnBringToFront.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnBringToFront.setVisible(false);
		
		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.btnBringToBackClicked();
			}
			
		});
		
		btnBringToBack.setVisible(false);
		
		btnToFront = new JButton("To Front");
		btnToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.btnToFrontClicked();
			}
		});
		
		btnToFront.setVisible(false);
		
		btnToBack = new JButton("To Back");
		btnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.btnToBackClicked();
			}
		});
		
		btnToBack.setVisible(false);
		
		
		
		
		
		
		//////////////// layout BTN-a
		GroupLayout gl_pnlButtons = new GroupLayout(pnlButtons);
		gl_pnlButtons.setHorizontalGroup(
			gl_pnlButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlButtons.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnHexagon, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnRectangle, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete))
						.addComponent(tglbtnSelect, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(116)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addGroup(gl_pnlButtons.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblEdgeColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlButtons.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnInnerColor)
								.addComponent(btnEdgeColor)))
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
					.addGap(50)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnLoad, 0, 0, Short.MAX_VALUE)
						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExecuteLoad)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnToFront, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnBringToFront, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnToBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnBringToBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		gl_pnlButtons.setVerticalGroup(
			gl_pnlButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlButtons.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
									.addComponent(tglbtnLine)
									.addComponent(tglbtnPoint)
									.addComponent(tglbtnRectangle)
									.addComponent(tglbtnSelect))
								.addGroup(gl_pnlButtons.createSequentialGroup()
									.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pnlButtons.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnEdgeColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblEdgeColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
							.addGap(10)
							.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
									.addComponent(tglbtnCircle)
									.addComponent(tglbtnDonut)
									.addComponent(tglbtnHexagon)
									.addComponent(btnEdit)
									.addComponent(btnDelete))
								.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnUndo)
									.addComponent(btnRedo))))
						.addGroup(gl_pnlButtons.createSequentialGroup()
							.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSave)
								.addComponent(btnToBack)
								.addComponent(btnToFront))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLoad)
								.addComponent(btnExecuteLoad)
								.addComponent(btnBringToFront)
								.addComponent(btnBringToBack))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlButtons.setLayout(gl_pnlButtons);
		
		loggerpanel = new JPanel();
		getContentPane().add(loggerpanel, BorderLayout.SOUTH);
		loggerpanel.setLayout(new BorderLayout(0, 0));
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		loggerpanel.add(separator, BorderLayout.NORTH);
		
		logger = new TextArea("\n");
		logger.setRows(5);
		logger.setEditable(false);
		loggerpanel.add(logger, BorderLayout.CENTER);
		///////////////
		
		
		
		
		
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}
	public JButton getBtnDelete() {
		return btnDelete;
	}
	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
}
