package Command;

import java.io.Serializable;

import MVC.DrawingModel;
import geometry.Point;

public class UpdatePointCommand implements Command,Serializable  {
	
	private Point oldPoint= new Point();
	private Point newPoint = new Point();
	private DrawingModel model;
	
	

	public UpdatePointCommand(Point oldPoint, Point newPoint,DrawingModel model) {
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
		this.model=model;
	}

	@Override
	public void execute() {
		model.getSelectedShapes().add(newPoint);
		model.getSelectedShapes().remove(oldPoint);
		newPoint.setSelected(true);
		oldPoint.setSelected(false);
		
		model.remove(oldPoint);
		model.add(newPoint);
		
	}

	@Override
	public void unexecute() {
		model.getSelectedShapes().add(oldPoint);
		model.getSelectedShapes().remove(newPoint);
		oldPoint.setSelected(true);
		newPoint.setSelected(false);
		
		model.remove(newPoint);
		model.add(oldPoint);
		
	}

	@Override
	public String toString() {
		return "Edited [oldPoint=" + oldPoint.toString() + ", newPoint=" + newPoint.toString() + "]"+"\n";
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}


	
	

}
