package Command;

import java.io.Serializable;
import java.util.Collections;

import MVC.DrawingModel;

public class ToFrontCmd implements Command, Serializable {

	int i;
	private DrawingModel model;
	
	public ToFrontCmd(int i, DrawingModel model) {
		this.i = i;
		this.model = model;
	}

	@Override
	public void execute() {
		Collections.swap(model.getShapes(), i, i+1);

	}

	@Override
	public void unexecute() {
		Collections.swap(model.getShapes(), i, i+1);

	}
	
	@Override
	public String toString() {
		return "To front: " + model.getShapes().get(i+1).toString()+"\n";
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
