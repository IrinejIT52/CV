package projekatgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSPX;
	private JTextField txtSPY;
	private JTextField txtEPX;
	private JTextField txtEPY;
	
	private int x1,y1,x2,y2;
	private Line line = null;
	private Color color = null;
	
	public JButton btnEdgeColor;
	JLabel lblEdgeColor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setTitle("Line information");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblStartPointX = new JLabel("Start Point X cordinate:");
		JLabel lblStartPointY = new JLabel("Start Point Y cordinate:");
		JLabel lblEndPointX = new JLabel("End Point X cordinate:");
		JLabel lblEndPointY = new JLabel("End Point Y cordinate:");
		txtSPX = new JTextField();
		txtSPX.setColumns(10);
		txtSPY = new JTextField();
		txtSPY.setColumns(10);
		txtEPX = new JTextField();
		txtEPX.setColumns(10);
		txtEPY = new JTextField();
		txtEPY.setColumns(10);
		
		btnEdgeColor = new JButton("");
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
	 			Color chooseColor= jcc.showDialog(null, "Pick a color.", Color.BLACK);
	 			btnEdgeColor.setBackground(chooseColor);
			}
		});
		
		lblEdgeColor = new JLabel("Choose edge color:  ");
		
		
		
		
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(64)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblEdgeColor, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
							.addComponent(btnEdgeColor)
							.addGap(150))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStartPointX, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(lblStartPointY, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(lblEndPointX, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(lblEndPointY, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEPY, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEPX, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSPY, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSPX, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(116))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartPointX)
						.addComponent(txtSPX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartPointY)
						.addComponent(txtSPY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEPX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEndPointX))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPointY)
						.addComponent(txtEPY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEdgeColor))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						x1 = Integer.parseInt(txtSPX.getText());
						y1 = Integer.parseInt(txtSPY.getText());
						x2 = Integer.parseInt(txtEPX.getText());
						y2 = Integer.parseInt(txtEPY.getText());
						setVisible(false);
						if(x1<0 ||  y1<0 || x2<0 || y2<0) {
							JOptionPane.showMessageDialog(okButton, "Enter pozitive numbers!!");
							x1 = -100;
							y1 = -100;
							x2 = -100;
							y2= -100;
							dispose();
						}
						
					}catch (Exception a) {
						JOptionPane.showMessageDialog(okButton, "Enter only numbers!!");
					}}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtSPX() {
		return txtSPX;
	}

	public void setTxtSPX(JTextField txtSPX) {
		this.txtSPX = txtSPX;
	}

	public JTextField getTxtSPY() {
		return txtSPY;
	}

	public void setTxtSPY(JTextField txtSPY) {
		this.txtSPY = txtSPY;
	}

	public JTextField getTxtEPX() {
		return txtEPX;
	}

	public void setTxtEPX(JTextField txtEPX) {
		this.txtEPX = txtEPX;
	}

	public JTextField getTxtEPY() {
		return txtEPY;
	}

	public void setTxtEPY(JTextField txtEPY) {
		this.txtEPY = txtEPY;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}

	public JLabel getLblEdgeColor() {
		return lblEdgeColor;
	}

	public void setLblEdgeColor(JLabel lblEdgeColor) {
		this.lblEdgeColor = lblEdgeColor;
	}
	

}
