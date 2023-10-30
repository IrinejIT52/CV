package projekatgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import geometry.Rectangle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class Stack extends JFrame {

	private JPanel contentPane;
	public  DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	public JList<Rectangle> lstStack;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stack frame = new Stack();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public Stack() {
		setTitle("Kuzman Irinej IT 52/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlgStack = new DlgStack();
				dlgStack.setVisible(true);
				Rectangle a = new Rectangle();
				a.setX(Integer.parseInt(dlgStack.txtX.getText()));
				a.setY(Integer.parseInt(dlgStack.txtY.getText()));
				a.setWidth(Integer.parseInt(dlgStack.txtSirina.getText()));
				a.setHeight(Integer.parseInt(dlgStack.txtVisina.getText()));
				if(dlgStack.isOk == true) {
					dlm.add(0,a); // index 0! a rectangle toString u rectangle k.
					
					Sort.dlm.add(0, a); //za sort
					
				}
				
			}
		});
		pnlSouth.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dlm.isEmpty()) {
					JOptionPane.showMessageDialog(btnRemove, "List is empty");
				}else {
				DlgStack dlgStack = new DlgStack();
				
					String[] split = dlm.getElementAt(0).toString().split(" ");
					dlgStack.txtX.setText(split[1]);
					dlgStack.txtY.setText(split[3]);
					dlgStack.txtSirina.setText(split[5]);
					dlgStack.txtVisina.setText(split[7]);
					dlgStack.setVisible(true);
					
					if(dlgStack.isOk == true) {
						     
						dlm.remove(0);         
						Sort.dlm.remove(0);
	
			}}}}
		);
		pnlSouth.add(btnRemove);
		
		
		
		
		
		
		
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{0, 0};
		gbl_pnlCenter.rowHeights = new int[]{0, 0};
		gbl_pnlCenter.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		
		JScrollPane scrStack = new JScrollPane();
		GridBagConstraints gbc_scrStack = new GridBagConstraints();
		gbc_scrStack.fill = GridBagConstraints.BOTH;
		gbc_scrStack.gridx = 0;
		gbc_scrStack.gridy = 0;
		pnlCenter.add(scrStack, gbc_scrStack);
		
		lstStack = new JList<Rectangle>();
		scrStack.setViewportView(lstStack); // dlm pokazuje default izgled
		lstStack.setModel(dlm);
		
		
		JPanel pnlEast = new JPanel();
		contentPane.add(pnlEast, BorderLayout.EAST);
		
		JPanel pnlWest = new JPanel();
		contentPane.add(pnlWest, BorderLayout.WEST);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		
		
	}

	
	}


