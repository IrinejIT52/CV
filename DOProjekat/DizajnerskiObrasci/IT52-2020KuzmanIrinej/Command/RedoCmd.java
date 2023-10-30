package Command;

import java.io.Serializable;

public class RedoCmd implements Command, Serializable {

	private Command command;
	
	public RedoCmd(Command command) {
		this.command = command;
	}

	@Override
	public void execute() {
		command.execute();

	}

	@Override
	public void unexecute() {
		command.unexecute();

	}
	
	@Override
	public String toString() {
		return "Redo " + command.toString()+"\n";
	}

}
