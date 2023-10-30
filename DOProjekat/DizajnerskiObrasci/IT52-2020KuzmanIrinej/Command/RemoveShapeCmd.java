package Command;

import java.io.Serializable;

import MVC.DrawingModel;
import geometry.Shape;

public class RemoveShapeCmd  implements Command,Serializable{
	private Shape shape;
	private DrawingModel model;
	
	public RemoveShapeCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		model.remove(shape);
		
	}

	@Override
	public void unexecute() {
		model.add(shape);

	}
	
	@Override
	public String toString() {
		return "Removed " + shape.toString()+"\n";
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
}
