package Command;

import java.io.Serializable;

import MVC.DrawingModel;
import geometry.HexagonAdapter;

public class UpdateHexagonCommand implements Command,Serializable  {
	
	
	private HexagonAdapter oldHex;
	private HexagonAdapter newHex;
	private DrawingModel model;
	
	public UpdateHexagonCommand(HexagonAdapter oldHex, HexagonAdapter newHex, DrawingModel model) {
		this.oldHex = oldHex;
		this.newHex = newHex;
		this.model=model;
	}
	@Override
	public void execute() {
		
		model.getSelectedShapes().add(newHex);
		model.getSelectedShapes().remove(oldHex);
		newHex.setSelected(true);
		oldHex.setSelected(false);
		
		model.remove(oldHex);
		model.add(newHex);
		
	}
	@Override
	public void unexecute() {
		model.getSelectedShapes().add(oldHex);
		model.getSelectedShapes().remove(newHex);
		oldHex.setSelected(true);
		newHex.setSelected(false);
		
		model.remove(newHex);
		model.add(oldHex);
		
	}
	
	@Override
	public String toString() {
		return "Edited [oldHexagon=" + oldHex.toString() + ", newHexagon=" + newHex.toString() + "]"+"\n";
	}
	public DrawingModel getModel() {
		return model;
	}
	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
