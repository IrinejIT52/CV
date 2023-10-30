package Strategy;




import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import Command.Command;



public class Save implements SaveState {
	private FileOutputStream fops;
	ObjectOutputStream oos;
	
	@Override
	public void save(List<Command> cmds, File file) {
		 try {
	           FileOutputStream fos = new FileOutputStream(file);
	           ObjectOutputStream oos = new ObjectOutputStream(fos);

	           oos.writeObject(cmds);
	           
	           oos.close();
	        
	        } catch (IOException e) {
	            // System.out.println("Error occurred while saving");
	            e.printStackTrace();
	        }
		
		
		
		
	}

	@Override
	public void saveImage(JPanel view, String fileName) {
		File newFile = new File("C:/Users/Kuzman/Desktop/" +fileName);
		int w = view.getWidth();
		int h = view.getHeight();
		BufferedImage image = new BufferedImage(w, h,  BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		view.print(g);
	    g.dispose();
	    
	    try {
	        ImageIO.write(image, "jpeg", newFile);
	    } catch (IOException ox) {
	        ox.printStackTrace();

	}
		
	}

	@Override
	public void saveLog(TextArea log, String fileName) {
		File logger = new File("C:/Users/Kuzman/Desktop/" +fileName);
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter(new FileWriter(logger));
		    writer.write(log.toString());

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
		
	}
}
