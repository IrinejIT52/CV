package Command;

import java.io.Serializable;

import MVC.DrawingModel;
import geometry.Point;
import geometry.Rectangle;

public class UpdateRectagleCommand implements Command,Serializable {
	
	

	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private DrawingModel model;
	
	public UpdateRectagleCommand(Rectangle oldRectangle, Rectangle newRectangle, DrawingModel model) {

		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
		this.model=model;
	}

	

	@Override
	public void execute() {
		
		model.getSelectedShapes().add(newRectangle);
		model.getSelectedShapes().remove(oldRectangle);
		newRectangle.setSelected(true);
		oldRectangle.setSelected(false);
		
		model.remove(oldRectangle);
		model.add(newRectangle);
		
	}

	@Override
	public void unexecute() {
		model.getSelectedShapes().add(oldRectangle);
		model.getSelectedShapes().remove(newRectangle);
		oldRectangle.setSelected(true);
		newRectangle.setSelected(false);
		
		model.remove(newRectangle);
		model.add(oldRectangle);
		
	}
	
	@Override
	public String toString() {
		return "Edited [oldRectangle=" + oldRectangle.toString() + ", newRectangle=" + newRectangle.toString() + "]"+"\n";
	}



	public DrawingModel getModel() {
		return model;
	}



	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
