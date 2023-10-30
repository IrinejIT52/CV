package Command;

import java.io.Serializable;
import java.util.Collections;

import MVC.DrawingModel;
import geometry.Shape;

public class BringToFrontCmd implements Command, Serializable {

	int i;
	int n;
	private DrawingModel model;
	
	


	public BringToFrontCmd(int i, int n, DrawingModel model) {
		this.i = i;
		this.n = n;
		this.model = model;
	}

	@Override
	public void execute() {
		Collections.swap(model.getShapes(), i, n);
		
	}

	@Override
	public void unexecute() {
		Collections.swap(model.getShapes(), i,n);

	}
	
	@Override
	public String toString() {
		return "Brought to front: " + model.getShapes().get(n).toString()+"\n";
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
