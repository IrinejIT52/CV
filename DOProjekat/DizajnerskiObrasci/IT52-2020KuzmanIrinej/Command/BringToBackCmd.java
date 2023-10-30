package Command;

import java.io.Serializable;
import java.util.Collections;

import MVC.DrawingModel;

public class BringToBackCmd implements Command, Serializable {

	int i;
	private DrawingModel model;
	
	
	public BringToBackCmd(int i, DrawingModel model) {
		this.i = i;
		this.model = model;
	}

	@Override
	public void execute() {
		Collections.swap(model.getShapes(), i, 0);

	}

	@Override
	public void unexecute() {
		Collections.swap(model.getShapes(), i, 0);

	}
	
	@Override
	public String toString() {
		return "Brought to back: " + model.getShapes().get(0).toString()+"\n";
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
