package Command;

import java.io.Serializable;

import MVC.DrawingModel;
import geometry.Shape;

public class DeselectCmd implements Command, Serializable  {

	
	private Shape shape;
	private DrawingModel model;
	
	
	public DeselectCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		shape.setSelected(false);
		model.getSelectedShapes().remove(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(true);
		model.getSelectedShapes().add(shape);
	}

	public String toString() {
		return "Deselected " + shape.toString()+"\n";
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
}
