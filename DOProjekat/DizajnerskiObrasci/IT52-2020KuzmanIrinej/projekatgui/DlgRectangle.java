package projekatgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtSirina;
	private int sirina,visina;
	private JTextField txtVisina;
	private int x,y;
	
	public JButton btnEdgeColor;
	public JButton btnInnerColor;
	JLabel lblEdgeColor;
	JLabel lblChooseInnerColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setTitle("Rectangle information");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblCenterX = new JLabel("UpperLeft X  cordinate:");
		txtX = new JTextField();
		txtX.setColumns(10);
		JLabel lblCenterY = new JLabel("UpperLeft Y  cordinate:");
		txtY = new JTextField();
		txtY.setColumns(10);
		JLabel lblRadius = new JLabel("Width:");
		txtSirina = new JTextField();
		txtSirina.setColumns(10);
		
		txtVisina = new JTextField();
		txtVisina.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Height:");
		
		btnEdgeColor = new JButton("");
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
	 			Color chooseColor= jcc.showDialog(null, "Pick a color.", btnEdgeColor.getBackground());
	 			btnEdgeColor.setBackground(chooseColor);
			}
		});
		
		btnInnerColor = new JButton("");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
	 			Color chooseColor= jcc.showDialog(null, "Pick a color.", btnInnerColor.getBackground());
	 			btnInnerColor.setBackground(chooseColor);
			}
		});
		
		lblEdgeColor = new JLabel("Choose edge color:  ");
		
		lblChooseInnerColor = new JLabel("Choose inner color:  ");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(113, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblCenterY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblCenterX, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(28))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblRadius, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
										.addComponent(lblEdgeColor, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblChooseInnerColor, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
									.addGap(18)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtVisina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSirina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(83))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEdgeColor))
							.addGap(115))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCenterX))
					.addGap(8)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCenterY))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSirina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRadius))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtVisina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEdgeColor))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblChooseInnerColor))
					.addContainerGap(54, Short.MAX_VALUE))
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
						sirina = Integer.parseInt(txtSirina.getText());
						visina = Integer.parseInt(txtVisina.getText());
						x = Integer.parseInt(txtX.getText());
						y = Integer.parseInt(txtY.getText());
						if(sirina<0 || visina<0 || x<0 || y<0) {
							JOptionPane.showMessageDialog(okButton, "Enter pozitive numbers!!");
							dispose();
						}
						setVisible(false);
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
						
						setVisible(false);
					
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	

	public int getSirina() {
		return sirina;
	}

	public void setSirina(int sirina) {
		this.sirina = sirina;
	}

	public int getVisina() {
		return visina;
	}

	public void setVisina(int visina) {
		this.visina = visina;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtSirina() {
		return txtSirina;
	}

	public void setTxtSirina(JTextField txtSirina) {
		this.txtSirina = txtSirina;
	}

	public JTextField getTxtVisina() {
		return txtVisina;
	}

	public void setTxtVisina(JTextField txtVisina) {
		this.txtVisina = txtVisina;
	}

	public int getX1() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY1() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JLabel getLblEdgeColor() {
		return lblEdgeColor;
	}

	public void setLblEdgeColor(JLabel lblEdgeColor) {
		this.lblEdgeColor = lblEdgeColor;
	}

	public JLabel getLblChooseInnerColor() {
		return lblChooseInnerColor;
	}

	public void setLblChooseInnerColor(JLabel lblChooseInnerColor) {
		this.lblChooseInnerColor = lblChooseInnerColor;
	}

	

	

}
