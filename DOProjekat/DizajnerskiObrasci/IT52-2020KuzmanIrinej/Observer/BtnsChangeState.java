package Observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

public class BtnsChangeState implements PropertyChangeListener {
	private JButton edit;
	private JButton delete;
	private JButton undo;
	private JButton redo;
	private JButton bringtofront;
	private JButton tofront;
	private JButton bringtoback;
	private JButton toback;
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("BtnEdit"))
				this.edit=(JButton) evt.getNewValue();
		
		if(evt.getPropertyName().equals("BtnDelete"))
			this.delete=(JButton) evt.getNewValue();
		
		if(evt.getPropertyName().equals("BtnUndo"))
			this.undo=(JButton) evt.getNewValue();
		
		if(evt.getPropertyName().equals("BtnRedo"))
			this.redo=(JButton) evt.getNewValue();
		
		if(evt.getPropertyName().equals("BtnBringToFront"))
			this.bringtofront=(JButton) evt.getNewValue();
		
		if(evt.getPropertyName().equals("BtnBringToBack"))
			this.bringtoback=(JButton) evt.getNewValue();
		
		if(evt.getPropertyName().equals("BtnToFront"))
			this.tofront=(JButton) evt.getNewValue();
		
		if(evt.getPropertyName().equals("BtnToBack"))
			this.toback=(JButton) evt.getNewValue();
	}



	
	
}
