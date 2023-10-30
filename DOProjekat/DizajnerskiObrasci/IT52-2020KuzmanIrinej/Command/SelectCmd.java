package Command;

import java.io.Serializable;

import MVC.DrawingModel;
import geometry.Shape;

public class SelectCmd implements Command, Serializable  {

	private Shape shape;
	private DrawingModel model;
	
	
	public SelectCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		shape.setSelected(true);
		model.getSelectedShapes().add(shape); 
	}

	@Override
	public void unexecute() {
		shape.setSelected(false);
		model.getSelectedShapes().remove(shape);
	}

	@Override
	public String toString() {
		return "Selected " + shape.toString()+"\n";
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	

}
