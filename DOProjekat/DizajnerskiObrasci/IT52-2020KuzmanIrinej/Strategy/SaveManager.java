package Strategy;

import java.awt.TextArea;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import Command.Command;

public class SaveManager implements SaveState {
	private SaveState saveState;
	
	public SaveManager(SaveState saveState)
	{
		this.saveState=saveState;
	}



	@Override
	public void save(List<Command> obj, File savefile) {
		saveState.save(obj, savefile);
		
	}


	@Override
	public void saveImage(JPanel view, String fileName) {
		saveState.saveImage(view, fileName);
		
	}



	@Override
	public void saveLog(TextArea logger,String fileName) {
		saveState.saveLog(logger,fileName);
		
	}


}
