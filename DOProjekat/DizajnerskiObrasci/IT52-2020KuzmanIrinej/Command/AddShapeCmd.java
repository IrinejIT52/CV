package Command;

import java.io.Serializable;

import MVC.DrawingModel;
import geometry.Shape;

public class AddShapeCmd implements Command, Serializable {
	
	public AddShapeCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	private Shape shape;
	private DrawingModel model;
	

	@Override
	public void execute() {
		shape.setSelected(false);
		model.getSelectedShapes().remove(shape);
		model.add(shape);
		
	}
	@Override
	public void unexecute() {
		model.remove(shape);
		
	}
	@Override
	public String toString() {
		return "Paint " + shape.toString()+"\n";
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
