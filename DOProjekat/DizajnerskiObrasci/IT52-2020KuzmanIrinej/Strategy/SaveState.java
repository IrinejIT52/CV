package Strategy;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import Command.Command;

import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface SaveState {
	
	void save(List<Command> obj, File savefile);
	void saveImage(JPanel view, String fileName);
	void saveLog(TextArea log, String fileName);
}
