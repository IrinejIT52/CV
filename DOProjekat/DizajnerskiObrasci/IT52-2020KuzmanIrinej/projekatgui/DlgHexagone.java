package projekatgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgHexagone extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textHexagoneX;
	private JTextField textHexagoneY;
	private JTextField textHexagoneR;
	
	private int x;
	private int y;
	private int r;
	
	JButton btnEdgeColor;
	JButton btnInnerColor;
	
	JLabel lblEdgeColor;
	JLabel lblChooseInnerColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagone dialog = new DlgHexagone();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagone() {
		setTitle("Hexagone information");
		setModal(true);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(82, 39, 24, 13);
		contentPanel.add(lblX);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(82, 62, 24, 13);
		contentPanel.add(lblY);
		
		JLabel lblR = new JLabel("R:");
		lblR.setBounds(82, 88, 24, 13);
		contentPanel.add(lblR);
		
		textHexagoneX = new JTextField();
		textHexagoneX.setBounds(115, 36, 96, 19);
		contentPanel.add(textHexagoneX);
		textHexagoneX.setColumns(10);
		
		textHexagoneY = new JTextField();
		textHexagoneY.setText("");
		textHexagoneY.setBounds(115, 59, 96, 19);
		contentPanel.add(textHexagoneY);
		textHexagoneY.setColumns(10);
		
		textHexagoneR = new JTextField();
		textHexagoneR.setBounds(115, 85, 96, 19);
		contentPanel.add(textHexagoneR);
		textHexagoneR.setColumns(10);
		
		btnEdgeColor = new JButton("");
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
	 			Color chooseColor= jcc.showDialog(null, "Pick a color.", btnEdgeColor.getBackground());
	 			btnEdgeColor.setBackground(chooseColor);
			}
		});
		btnEdgeColor.setBounds(178, 111, 33, 19);
		contentPanel.add(btnEdgeColor);
		
		btnInnerColor = new JButton("");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcc = new JColorChooser();
	 			Color chooseColor= jcc.showDialog(null, "Pick a color.", btnInnerColor.getBackground());
	 			btnInnerColor.setBackground(chooseColor);
			}
		});
		btnInnerColor.setBounds(178, 140, 33, 19);
		contentPanel.add(btnInnerColor);
		
		lblChooseInnerColor = new JLabel("Choose inner color:  ");
		lblChooseInnerColor.setBounds(21, 146, 107, 13);
		contentPanel.add(lblChooseInnerColor);
		
		lblEdgeColor = new JLabel("Choose edge color:  ");
		lblEdgeColor.setBounds(21, 117, 107, 13);
		contentPanel.add(lblEdgeColor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							x = Integer.parseInt(textHexagoneX.getText());
							y = Integer.parseInt(textHexagoneY.getText());
							r = Integer.parseInt(textHexagoneR.getText());
							setVisible(false);
							
							System.out.println("dlg"+x+" "+y+" "+r);
							if(x<0 ||  y<0 || r<0) {
								JOptionPane.showMessageDialog(okButton, "Enter pozitive numbers!!");
							
								dispose();
							}
							
						}catch (Exception a) {
							JOptionPane.showMessageDialog(okButton, "Enter only numbers!!");
						}}
					}
				);
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

	public JTextField getTextHexagoneX() {
		return textHexagoneX;
	}

	public void setTextHexagoneX(JTextField textHexagoneX) {
		this.textHexagoneX = textHexagoneX;
	}

	public JTextField getTextHexagoneY() {
		return textHexagoneY;
	}

	public void setTextHexagoneY(JTextField textHexagoneY) {
		this.textHexagoneY = textHexagoneY;
	}

	public JTextField getTextHexagoneR() {
		return textHexagoneR;
	}

	public void setTextHexagoneR(JTextField textHexagoneR) {
		this.textHexagoneR = textHexagoneR;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
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
