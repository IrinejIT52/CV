package Command;

import java.io.Serializable;

import MVC.DrawingModel;
import geometry.Circle;
import geometry.Point;

public class UpdateCircleCommand implements Command,Serializable {
	

	private Circle oldCircle;
	private Circle newCircle;
	private DrawingModel model;

	
	public UpdateCircleCommand(Circle oldCircle, Circle newCircle, DrawingModel model) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
		this.model=model;
	}

	


	@Override
	public void execute() {
		
		model.getSelectedShapes().add(newCircle);
		model.getSelectedShapes().remove(oldCircle);
		newCircle.setSelected(true);
		oldCircle.setSelected(false);
		
		model.remove(oldCircle);
		model.add(newCircle);
		
		
	}

	@Override
	public void unexecute() {
		model.getSelectedShapes().add(oldCircle);
		model.getSelectedShapes().remove(newCircle);
		oldCircle.setSelected(true);
		newCircle.setSelected(false);
		
		model.remove(newCircle);
		model.add(oldCircle);
		
	}

	@Override
	public String toString() {
		return "Edited [oldState=" + oldCircle.toString() + ", newState=" + newCircle.toString() + "]"+"\n";
	}




	public DrawingModel getModel() {
		return model;
	}




	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
}
