package Command;

import java.io.Serializable;

import MVC.DrawingModel;

public class UndoCmd implements Command, Serializable {

	private Command command;

	public UndoCmd(Command command) {
		this.command = command;
	}

	@Override
	public void execute() {
		command.unexecute();

	}

	@Override
	public void unexecute() {
		command.execute();

	}
	
	@Override
	public String toString() {
		return "Undo " + command.toString()+"\n";
	}

}
