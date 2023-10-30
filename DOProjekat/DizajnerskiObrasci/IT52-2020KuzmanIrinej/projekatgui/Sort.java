package projekatgui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import geometry.Rectangle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;

public class Sort extends JFrame {

	private JPanel contentPane;
	public static DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	public JList<Rectangle> lstStack;
	public static DefaultListModel<Rectangle> dlmSort = new DefaultListModel<Rectangle>();
	public JScrollPane scrStack;
	private JButton btnRemove;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sort frame = new Sort();
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
	public Sort() {
		setTitle("Kuzman Irinej IT 52/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		
		
		
		
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				Rectangle temp;
			    Rectangle[] ob = new Rectangle[dlm.getSize()];                  //Sorter za Jlistu
			    for(int i = 0 ; i <dlm.getSize(); i++ )
			        ob[i] = dlm.getElementAt(i);
			    int n=ob.length;
			    for(int i=0;i<n;i++)
			        for(int j=0;j<n-i-1;j++)
			        {
			            if(ob[j].area()<(ob[j+1].area())) 
			            {
			             temp=ob[j];
			             ob[j]=ob[j+1];
			             ob[j+1]=temp;
			            }

			          }
			    dlm.removeAllElements();
			    for(int i=0;i<n;i++)
			      dlm.add(0,(Rectangle)ob[i]);
				
			    
			    
			                                     
				
				
			}}
			
		);
		
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
					
					dlmSort.add(0,a);
					
				}
				
			}
		});
		
		scrStack = new JScrollPane();
		
		btnRemove = new JButton("Remove");
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
				
				}}}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addComponent(btnAdd)
							.addGap(45)
							.addComponent(btnSort)
							.addGap(34)
							.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(scrStack, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrStack, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnSort)
						.addComponent(btnRemove))
					.addContainerGap())
		);
		
		
		lstStack = new JList<Rectangle>();
		scrStack.setViewportView(lstStack);
		lstStack.setModel(dlm);
		contentPane.setLayout(gl_contentPane);
	}
	}; 

	

	




