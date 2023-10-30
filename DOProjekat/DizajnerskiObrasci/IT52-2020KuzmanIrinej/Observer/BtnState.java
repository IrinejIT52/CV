package Observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import MVC.DrawingFrame;

public class BtnState {
		
	private JButton edit = new JButton();
	private JButton delete = new JButton();
	private JButton undo = new JButton();
	private JButton redo = new JButton();
	private JButton bringtofront = new JButton();
	private JButton bringtoback = new JButton();
	private JButton toback = new JButton();
	private JButton tofront = new JButton();
	
	private PropertyChangeSupport pcs;
	
	public BtnState() {
		pcs = new PropertyChangeSupport(this);
	}

	public void setEdit(JButton btnEdit,boolean state) {
		this.edit.setVisible(state);
		pcs.firePropertyChange("BtnEdit",btnEdit,this.edit);
		this.edit=btnEdit;
	}

	public void setDelete(JButton btnDelete,boolean state) {
		this.delete.setVisible(state);
		pcs.firePropertyChange("BtnDelete",btnDelete,this.delete);
		this.delete=btnDelete;
	}
	
	public void setUndo(JButton btnUndo,boolean state)
	{
		this.undo.setVisible(state);
		pcs.firePropertyChange("BtnUndo", btnUndo, this.undo);
		this.undo=btnUndo;
	}
	
	public void setRedo(JButton btnRedo,boolean state)
	{
		this.redo.setVisible(state);
		pcs.firePropertyChange("BtnRedo", btnRedo, this.redo);
		this.redo=btnRedo;
	}
	public void setBringToBack(JButton btnBringToBack, boolean state) {
		this.bringtoback.setVisible(state);
		pcs.firePropertyChange("BtnBringToBack", btnBringToBack, this.bringtoback);
		this.bringtoback=btnBringToBack;
		
	}

	public void setToFront(JButton btnToFront, boolean state) {
		this.tofront.setVisible(state);
		pcs.firePropertyChange("BtnToFront", btnToFront, this.tofront);
		this.tofront=btnToFront;
		
	}

	public void setToBack(JButton btnToBack, boolean state) {
		this.toback.setVisible(state);
		pcs.firePropertyChange("BtnToBack",	btnToBack, this.toback);
		this.toback=btnToBack;
		
	}
	
	public void setBringToFront(JButton btnBringToFront, boolean state) {
		this.bringtofront.setVisible(state);
		pcs.firePropertyChange("BtnBringToFront", btnBringToFront, this.bringtofront);
		this.bringtofront=btnBringToFront;
	}
	
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);

	}
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);

	}



	
	

}
