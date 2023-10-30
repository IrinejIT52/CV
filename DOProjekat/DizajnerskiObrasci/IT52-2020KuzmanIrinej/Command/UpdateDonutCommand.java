package Command;

import java.io.Serializable;

import MVC.DrawingModel;
import geometry.Donut;
import geometry.Point;

public class UpdateDonutCommand implements Command,Serializable {
	

	private Donut oldDonut;
	private Donut newDonut;
	private DrawingModel model;

	

	public UpdateDonutCommand(Donut oldDonut, Donut newDonut, DrawingModel model) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
		this.model=model;
	}

	@Override
	public void execute() {
		
		model.getSelectedShapes().add(newDonut);
		model.getSelectedShapes().remove(oldDonut);
		newDonut.setSelected(true);
		oldDonut.setSelected(false);
		
		model.remove(oldDonut);
		model.add(newDonut);
		
		
	}

	@Override
	public void unexecute() {
		model.getSelectedShapes().add(oldDonut);
		model.getSelectedShapes().remove(newDonut);
		oldDonut.setSelected(true);
		newDonut.setSelected(false);
		
		model.remove(newDonut);
		model.add(oldDonut);
		
	}
	
	@Override
	public String toString() {
		return "Edited [oldDonut=" + oldDonut.toString() + ", newDonut=" + newDonut.toString() + "]"+"\n";
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
