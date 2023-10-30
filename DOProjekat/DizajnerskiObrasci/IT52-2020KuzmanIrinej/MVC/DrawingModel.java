package MVC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Command.Command;
import geometry.Point;
import geometry.Shape;

public class DrawingModel implements Serializable {
	
	private List<Shape> shapes = new ArrayList<>();
	private List<Shape> selectedShapes = new ArrayList<>();
	
	

	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void add(Shape p)
	{
		shapes.add(p);
	}
	public void remove(Shape p)
	{
		shapes.remove(p);
	}
	
	
	
	
	private List<Command> cmd = new ArrayList<>();
	private List<Command> savedcmd = new ArrayList<>();
	
	public List<Command> getCmd() {
		return cmd;
	}

	public void setCmd(List<Command> cmd) {
		this.cmd = cmd;
	}
	
	public void addCmd(Command command)
	{
		cmd.add(command);
	}
	
	public void removeCmd(Command command)
	{
		cmd.remove(command);
	}

	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}
	
	public void addSelected(Shape p)
	{
		selectedShapes.add(p);
	}
	public void removeSelected(Shape p)
	{
		selectedShapes.remove(p);
	}

	public List<Command> getSavedcmd() {
		return savedcmd;
	}

	public void setSavedcmd(List<Command> savedcmd) {
		this.savedcmd = savedcmd;
	}
	
	
}
