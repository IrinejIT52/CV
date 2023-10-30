package Command;

import java.awt.Color;
import java.io.Serializable;

import MVC.DrawingModel;
import geometry.Line;
import geometry.Point;

public class UpdateLineCommand implements Command,Serializable  {
	
	
	private Line oldLine = new Line();
	private Line newLine;
	private DrawingModel model;
	
	
	public UpdateLineCommand(Line oldLine, Line newLine,DrawingModel model) {
		this.oldLine = oldLine;
		this.newLine = newLine;
		this.model = model;
		
	}

	@Override
	public void execute() {
		
		model.getSelectedShapes().add(newLine);
		model.getSelectedShapes().remove(oldLine);
		newLine.setSelected(true);
		oldLine.setSelected(false);
		
		model.remove(oldLine);
		model.add(newLine);
	
		
	}

	@Override
	public void unexecute() {
		model.getSelectedShapes().add(oldLine);
		model.getSelectedShapes().remove(newLine);
		oldLine.setSelected(true);
		newLine.setSelected(false);
		
		model.remove(newLine);
		model.add(oldLine);
		
	}

	@Override
	public String toString() {
		return "Edited [oldLine=" + oldLine.toString() + ", newLine=" + newLine.toString() + "]"+"\n";
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
}
