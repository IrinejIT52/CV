package MVC;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileSystemView;

import Command.AddShapeCmd;
import Command.BringToBackCmd;
import Command.BringToFrontCmd;
import Command.Command;
import Command.DeselectCmd;
import Command.RedoCmd;
import Command.RemoveShapeCmd;
import Command.SelectCmd;
import Command.ToBackCmd;
import Command.ToFrontCmd;
import Command.UndoCmd;
import Command.UpdateCircleCommand;
import Command.UpdateDonutCommand;
import Command.UpdateHexagonCommand;
import Command.UpdateLineCommand;
import Command.UpdatePointCommand;
import Command.UpdateRectagleCommand;
import Observer.BtnState;
import Observer.BtnsChangeState;
import Strategy.Save;
import Strategy.SaveManager;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import projekatgui.DlgCircle;
import projekatgui.DlgDonut;
import projekatgui.DlgHexagone;
import projekatgui.DlgLine;
import projekatgui.DlgPoint;
import projekatgui.DlgRectangle;

// ovde se prebacuje obrada kilka
public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;
	private int counter = 1;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	private int pointer = -1;
	private int n=0;
	
	private FileInputStream fis;
	private ObjectInputStream ois;
	
	
	BtnState state = new BtnState();
	BtnsChangeState changeState = new BtnsChangeState();
	
	boolean loadCmdClicked = true;
	
	private List<Command> loadCmd = new ArrayList<>();
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.frame = frame;
		this.model =model;
	}
	
	private void  deleteAllCommandsAfterPointer(int pointer)
	{
		if(model.getCmd().size()<1)
			return;
		for(int i = model.getCmd().size()-1; i>pointer;i--)
		{
			model.getCmd().remove(i);
		}
		state.setRedo(frame.btnRedo, false);
		
	}

	public void mouseClicked(MouseEvent e) {

		if(frame.tglbtnPoint.isSelected()) {
		Point p = new Point(e.getX(),e.getY(),frame.btnEdgeColor.getBackground());
		AddShapeCmd addPointCmd = new AddShapeCmd(p, model);
		addPointCmd.execute();
		
		frame.logger.append(addPointCmd.toString());
		
		deleteAllCommandsAfterPointer(pointer);
		model.getCmd().add(addPointCmd);
		model.getSavedcmd().add(addPointCmd);
		pointer++;
		frame.repaint();
		}
		else if(frame.tglbtnLine.isSelected()) {
			if(counter > 0) {
				x1 = e.getX();
				y1 = e.getY();
			}
			if(counter < 0) {
				x2 = e.getX();
				y2 = e.getY();
				Line l1 = new Line(new Point(x1,y1),new Point(x2,y2),frame.btnEdgeColor.getBackground());
				AddShapeCmd addLineCmd = new AddShapeCmd(l1, model);
				addLineCmd.execute();
				
				frame.logger.append(addLineCmd.toString());
				
				deleteAllCommandsAfterPointer(pointer);
				model.getCmd().add(addLineCmd);
				model.getSavedcmd().add(addLineCmd);
				pointer++;
				frame.repaint();
			}
			counter = counter -counter*2;
		}
		else if(frame.tglbtnRectangle.isSelected()) {
			x1 = e.getX();
			y1 = e.getY();
			DlgRectangle dlgRectangle = new DlgRectangle();
			dlgRectangle.getTxtX().setText(Integer.toString(x1));
			dlgRectangle.getTxtY().setText(Integer.toString(y1));
			dlgRectangle.getBtnEdgeColor().setVisible(false);
			dlgRectangle.getBtnInnerColor().setVisible(false);
			dlgRectangle.getLblEdgeColor().setVisible(false);
			dlgRectangle.getLblChooseInnerColor().setVisible(false);
			dlgRectangle.setVisible(true);
			
			
			Rectangle k1 = new Rectangle(new Point(x1,y1),dlgRectangle.getSirina(),dlgRectangle.getVisina(),frame.btnInnerColor.getBackground(),frame.btnEdgeColor.getBackground());
			AddShapeCmd addRectangleCmd = new AddShapeCmd(k1, model);
			addRectangleCmd.execute();
			
			frame.logger.append(addRectangleCmd.toString());
			
			deleteAllCommandsAfterPointer(pointer);
			model.getCmd().add(addRectangleCmd);
			model.getSavedcmd().add(addRectangleCmd);
			pointer++;
			frame.repaint();
		}
		else if(frame.tglbtnCircle.isSelected()) {
			x1 =e.getX();
			y1 = e.getY();
			DlgCircle dlgCircle = new DlgCircle();
			dlgCircle.getTxtX().setText(Integer.toString(x1));
			dlgCircle.getTxtY().setText(Integer.toString(y1));
			dlgCircle.getBtnEdgeColor().setVisible(false);
			dlgCircle.getBtnInnerColor().setVisible(false);
			dlgCircle.getLblEdgeColor().setVisible(false);
			dlgCircle.getLblChooseInnerColor().setVisible(false);
			dlgCircle.setVisible(true);
			Circle c1 = new Circle(new Point(x1,y1),dlgCircle.getRadius(),frame.btnInnerColor.getBackground(),frame.btnEdgeColor.getBackground());
			AddShapeCmd addCircleCmd = new AddShapeCmd(c1, model);
			addCircleCmd.execute();
			
			frame.logger.append(addCircleCmd.toString());
			
			deleteAllCommandsAfterPointer(pointer);
			model.getCmd().add(addCircleCmd);
			model.getSavedcmd().add(addCircleCmd);
			pointer++;
			frame.repaint();
		}
		else if(frame.tglbtnDonut.isSelected()) {
			x1 = e.getX();
			y1 = e.getY();
			DlgDonut dlgDonut = new DlgDonut();
			dlgDonut.getTxtX().setText(Integer.toString(x1));
			dlgDonut.getTxtY().setText(Integer.toString(y1));
			dlgDonut.getBtnEdgeColor().setVisible(false);
			dlgDonut.getBtnInnerColor().setVisible(false);
			dlgDonut.getLblEdgeColor().setVisible(false);
			dlgDonut.getLblChooseInnerColor().setVisible(false);
			dlgDonut.setVisible(true);
			Donut d1 = new Donut(new Point(x1,y1),dlgDonut.getRadius(),dlgDonut.getInnerradius(),frame.btnInnerColor.getBackground(),frame.btnEdgeColor.getBackground());
			AddShapeCmd addDonutCmd = new AddShapeCmd(d1, model);
			addDonutCmd.execute();
			
			frame.logger.append(addDonutCmd.toString());
			
			deleteAllCommandsAfterPointer(pointer);
			model.getCmd().add(addDonutCmd);
			model.getSavedcmd().add(addDonutCmd);
			pointer++;
			frame.repaint();
			
		}
		else if(frame.tglbtnHexagon.isSelected())
		{
			x1 = e.getX();
			y1 = e.getY();
			DlgHexagone dlgHexagone = new DlgHexagone();
			dlgHexagone.getTextHexagoneX().setText(Integer.toString(x1));
			dlgHexagone.getTextHexagoneY().setText(Integer.toString(y1));
			dlgHexagone.getBtnEdgeColor().setVisible(false);
			dlgHexagone.getBtnInnerColor().setVisible(false);
			dlgHexagone.getLblEdgeColor().setVisible(false);
			dlgHexagone.getLblChooseInnerColor().setVisible(false);
			dlgHexagone.setVisible(true);
	        HexagonAdapter h = new HexagonAdapter(dlgHexagone.getX(), dlgHexagone.getY(), dlgHexagone.getR());
	        h.setAreaColor(frame.btnInnerColor.getBackground());
	        h.setBorderColor(frame.btnEdgeColor.getBackground());
	        
	        AddShapeCmd addHexagoneCmd = new AddShapeCmd(h, model);
	        addHexagoneCmd.execute();
	        
	        frame.logger.append(addHexagoneCmd.toString());
	        
	        deleteAllCommandsAfterPointer(pointer);
	        model.getCmd().add(addHexagoneCmd);
	        model.getSavedcmd().add(addHexagoneCmd);
	        pointer++;
	        frame.repaint();
					
		}
		
		else if(frame.tglbtnSelect.isSelected())
		{
			
			
			x1 = e.getX();
			y1 = e.getY();
			state.addPropertyChangeListener(changeState);
			
			for(int i=model.getShapes().size();i-- > 0;)
			{
				
				if(model.getShapes().get(i).contains(x1,y1)==true)
				{
					
					if(model.getShapes().get(i).isSelected())
					{
						DeselectCmd deselectCmd = new DeselectCmd(model.getShapes().get(i),model);
						deselectCmd.execute();
						frame.logger.append(deselectCmd.toString());
						deleteAllCommandsAfterPointer(pointer);
						model.getCmd().add(deselectCmd);
						model.getSavedcmd().add(deselectCmd);
						pointer++;
						frame.repaint();
						break;
						
					}
					else 
					{
						SelectCmd selectCmd = new SelectCmd(model.getShapes().get(i),model);
						selectCmd.execute();
						frame.logger.append(selectCmd.toString());
						deleteAllCommandsAfterPointer(pointer);
						model.getCmd().add(selectCmd);
						model.getSavedcmd().add(selectCmd);
						pointer++;
						frame.repaint();
						break;
						
					
					}
				}
			}
			
			
			
			
		}
	
		

		
		if(frame.tglbtnSelect.isSelected())
		{
			if(model.getSelectedShapes().isEmpty())
			{
				state.setEdit(frame.btnEdit, false);
				state.setDelete(frame.btnDelete, false);
				state.setBringToFront(frame.btnBringToFront, false);
				state.setBringToBack(frame.btnBringToBack, false);
				state.setToBack(frame.btnToBack, false);
				state.setToFront(frame.btnToFront, false);
			}
			else if(model.getSelectedShapes().size() == 1)
			{
				state.setEdit(frame.btnEdit,true);
				state.setDelete(frame.btnDelete, true);
				state.setBringToFront(frame.btnBringToFront,true);
				state.setBringToBack(frame.btnBringToBack,true);
				state.setToFront(frame.btnToFront, true);
				state.setToBack(frame.btnToBack, true);
				
			}
			else if(model.getSelectedShapes().size()>=1)
			{
				state.setDelete(frame.btnDelete,true);
				state.setEdit(frame.btnEdit, false);
				state.setBringToFront(frame.btnBringToFront, false);
				state.setBringToBack(frame.btnBringToBack, false);
				state.setToBack(frame.btnToBack, false);
				state.setToFront(frame.btnToFront, false);
			}	
		}
		
		if(pointer == -1)
		{
			state.setUndo(frame.btnUndo, false);
		}
		else if(pointer >= 0)
		{
			state.setUndo(frame.btnUndo, true);
		}
	}



	public void btnEditClicked(MouseEvent e) {
		for(Shape i : model.getShapes())
		{
			if(i.isSelected())
			{
				if(i instanceof Point) {
					int x=((Point) i).getX();
					int y=((Point) i).getY();
	
					DlgPoint dlgpoint = new DlgPoint();
					
	
					dlgpoint.getTxtX().setText(Integer.toString(x));
					dlgpoint.getTxtY().setText(Integer.toString(y));
					dlgpoint.getBtnEdgeColor().setBackground(frame.btnEdgeColor.getBackground());
					dlgpoint.setVisible(true);
					

					Point p1 = new Point(dlgpoint.getX1(),dlgpoint.getY1(),dlgpoint.getBtnEdgeColor().getBackground());
					UpdatePointCommand updatePointCmd = new UpdatePointCommand((Point) i, p1, model);
					updatePointCmd.execute();
					
					frame.logger.append(updatePointCmd.toString());
					
					
					model.getCmd().add(updatePointCmd);
					model.getSavedcmd().add(updatePointCmd);
					pointer++;
					frame.repaint();
					
				}
				else if(i instanceof Line)
				{
					
					DlgLine dlgline = new DlgLine();

					dlgline.getTxtSPX().setText(Integer.toString(((Line) i).getStartPoint().getX()));
					dlgline.getTxtSPY().setText(Integer.toString(((Line) i).getStartPoint().getY()));
					dlgline.getTxtEPX().setText(Integer.toString(((Line) i).getEndPoint().getX()));
					dlgline.getTxtEPY().setText(Integer.toString(((Line) i).getEndPoint().getY()));
					dlgline.getBtnEdgeColor().setBackground(frame.btnEdgeColor.getBackground());
					dlgline.setVisible(true);
					
					
					Line l1 = new Line(new Point(dlgline.getX1(),dlgline.getY1()),new Point(dlgline.getX2(),dlgline.getY2()),dlgline.getBtnEdgeColor().getBackground());
					
					UpdateLineCommand updateLineCmd = new UpdateLineCommand((Line) i, l1,model);
					updateLineCmd.execute();
					
					frame.logger.append(updateLineCmd.toString());
					
					
					model.getCmd().add(updateLineCmd);
					model.getSavedcmd().add(updateLineCmd);
					pointer++;
					frame.repaint();
					
				}
				else if(i instanceof Rectangle)
				{
					DlgRectangle dlgrectangle = new DlgRectangle();

					dlgrectangle.getTxtX().setText(Integer.toString(((Rectangle) i).getUpperLeft().getX()));
					dlgrectangle.getTxtY().setText(Integer.toString(((Rectangle) i).getUpperLeft().getY()));
					dlgrectangle.getTxtSirina().setText(Integer.toString(((Rectangle) i).getWidth()));
					dlgrectangle.getTxtVisina().setText(Integer.toString(((Rectangle) i).getHight()));
					dlgrectangle.getBtnEdgeColor().setBackground(frame.btnEdgeColor.getBackground());
					dlgrectangle.getBtnInnerColor().setBackground(frame.btnInnerColor.getBackground());
					dlgrectangle.setVisible(true);
					
				
					Rectangle k1 = new Rectangle(new Point(dlgrectangle.getX1(),dlgrectangle.getY1()),dlgrectangle.getSirina(),dlgrectangle.getVisina(),dlgrectangle.getBtnInnerColor().getBackground(),dlgrectangle.getBtnEdgeColor().getBackground());
					
					UpdateRectagleCommand updateRectangleCmd = new UpdateRectagleCommand((Rectangle)i,k1,model);
					updateRectangleCmd.execute();
					
					frame.logger.append(updateRectangleCmd.toString());
					
					
					model.getCmd().add(updateRectangleCmd);
					model.getSavedcmd().add(updateRectangleCmd);
					pointer++;
					frame.repaint();
				}
				else if(i instanceof Circle && !(i  instanceof Donut))
				{
					DlgCircle dlgcircle = new DlgCircle();

					dlgcircle.getTxtX().setText(Integer.toString(((Circle) i).getCenter().getX()));
					dlgcircle.getTxtY().setText(Integer.toString(((Circle) i).getCenter().getY()));
					dlgcircle.getTxtRadius().setText(Integer.toString(((Circle) i).getRadius()));
					dlgcircle.getBtnEdgeColor().setBackground(frame.btnEdgeColor.getBackground());
					dlgcircle.getBtnInnerColor().setBackground(frame.btnInnerColor.getBackground());
					dlgcircle.setVisible(true);
					

					Circle c1 = new Circle(new Point(dlgcircle.getX1(),dlgcircle.getY1()),dlgcircle.getRadius(),dlgcircle.getBtnInnerColor().getBackground(),dlgcircle.getBtnEdgeColor().getBackground());
					
					UpdateCircleCommand updateCircleCmd = new UpdateCircleCommand((Circle)i,c1,model);
					updateCircleCmd.execute();
					
					frame.logger.append(updateCircleCmd.toString());
					
					
					model.getCmd().add(updateCircleCmd);
					model.getSavedcmd().add(updateCircleCmd);
					pointer++;
					frame.repaint();
					
				}
				else if(i instanceof Donut)
				{
					DlgDonut dlgdonut = new DlgDonut();
					
					dlgdonut.getTxtX().setText(Integer.toString(((Donut) i).getCenter().getX()));
					dlgdonut.getTxtY().setText(Integer.toString(((Donut) i).getCenter().getY()));
					dlgdonut.getTxtRadius().setText(Integer.toString(((Donut) i).getRadius()));
					dlgdonut.getTxtInnerRadius().setText(Integer.toString(((Donut) i).getInnerRadius()));
					dlgdonut.getBtnEdgeColor().setBackground(frame.btnEdgeColor.getBackground());
					dlgdonut.getBtnInnerColor().setBackground(frame.btnInnerColor.getBackground());
					dlgdonut.setVisible(true);
					
					
					
					Donut d1 = new Donut(new Point(dlgdonut.getX(),dlgdonut.getY()),dlgdonut.getRadius(),dlgdonut.getInnerradius(),dlgdonut.getBtnInnerColor().getBackground(),dlgdonut.getBtnEdgeColor().getBackground());
					
					UpdateDonutCommand updateDonutCmd = new UpdateDonutCommand((Donut)i,d1,model);
					updateDonutCmd.execute();
					
					frame.logger.append(updateDonutCmd.toString());
					
					
					model.getCmd().add(updateDonutCmd);
					model.getSavedcmd().add(updateDonutCmd);
					pointer++;
					frame.repaint();
					
					
				}
				else if(i instanceof HexagonAdapter)
				{
					DlgHexagone dlgHexagon = new DlgHexagone();

					
					dlgHexagon.getTextHexagoneX().setText(Integer.toString(((HexagonAdapter)i).getHexagon().getX()));
					dlgHexagon.getTextHexagoneY().setText(Integer.toString(((HexagonAdapter)i).getHexagon().getY()));
					dlgHexagon.getTextHexagoneR().setText(Integer.toString(((HexagonAdapter)i).getHexagon().getR()));
					dlgHexagon.getBtnEdgeColor().setBackground(frame.btnEdgeColor.getBackground());
					dlgHexagon.getBtnInnerColor().setBackground(frame.btnInnerColor.getBackground());
					dlgHexagon.setVisible(true);
					
					HexagonAdapter h1 = new HexagonAdapter(dlgHexagon.getX(),dlgHexagon.getY(),dlgHexagon.getR());
					h1.setAreaColor(dlgHexagon.getBtnInnerColor().getBackground());
					h1.setBorderColor(dlgHexagon.getBtnEdgeColor().getBackground());
					
					UpdateHexagonCommand updateHexagonCmd = new UpdateHexagonCommand(((HexagonAdapter)i), h1,model);
					updateHexagonCmd.execute();
					
					frame.logger.append(updateHexagonCmd.toString());
					
					
					model.getCmd().add(updateHexagonCmd);
					model.getSavedcmd().add(updateHexagonCmd);
					pointer++;
					frame.repaint();
					
				}
			}
		}
		
	}

	public void deselectAll()
	{
		List<Shape> selected = new ArrayList<>();
		for(int i=0;i<model.getSelectedShapes().size();i++)
		{
			selected.add(model.getSelectedShapes().get(i));
		}
		state.setEdit(frame.btnEdit, false);
		state.setDelete(frame.btnDelete, false);
		state.setBringToFront(frame.btnBringToFront, false);
		state.setBringToBack(frame.btnBringToBack, false);
		state.setToBack(frame.btnToBack, false);
		state.setToFront(frame.btnToFront, false);
		for(Shape i : selected)
		{
			DeselectCmd deselectcmd = new DeselectCmd(i,model);
			deselectcmd.execute();
			
			frame.logger.append(i.toString()+"\n");
			model.getCmd().add(deselectcmd);
			model.getSavedcmd().add(deselectcmd);
			pointer++;
			frame.repaint();
		}
		
	}

	public DrawingModel getModel() {
		return model;
	}



	public void btnDeleteClicked(MouseEvent e) {
		
		for(Shape i : model.getSelectedShapes())
		{

				RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(i, model);
				removeShapeCmd.execute();
				
				frame.logger.append(removeShapeCmd.toString());
				
				model.getCmd().add(removeShapeCmd);
				model.getSavedcmd().add(removeShapeCmd);
				pointer++;
				frame.repaint();
				
		}
		model.getSelectedShapes().clear();
		
		
		
	}
	
	


	public void btnRedoClicked(MouseEvent e) {
		
		
		if(pointer == model.getCmd().size()-1)
		{
			return;
		}
		
		Command command = model.getCmd().get(++pointer);
		RedoCmd redocmd = new RedoCmd(command);
		redocmd.execute();
		
		
		frame.logger.append(redocmd.toString());
		model.getSavedcmd().add(redocmd);
		frame.repaint();
		state.setUndo(frame.btnUndo, true);
		
		if(model.getSelectedShapes().isEmpty())
		{
			state.setEdit(frame.btnEdit, false);
			state.setDelete(frame.btnDelete, false);
			state.setBringToFront(frame.btnBringToFront, false);
			state.setBringToBack(frame.btnBringToBack, false);
			state.setToBack(frame.btnToBack, false);
			state.setToFront(frame.btnToFront, false);
		}
		else if(model.getSelectedShapes().size() == 1)
		{
			state.setEdit(frame.btnEdit,true);
			state.setDelete(frame.btnDelete, true);
			state.setBringToFront(frame.btnBringToFront,true);
			state.setBringToBack(frame.btnBringToBack,true);
			state.setToFront(frame.btnToFront, true);
			state.setToBack(frame.btnToBack, true);
			
		}
		else if(model.getSelectedShapes().size()>=1)
		{
			state.setDelete(frame.btnDelete,true);
			state.setEdit(frame.btnEdit, false);
			state.setBringToFront(frame.btnBringToFront, false);
			state.setBringToBack(frame.btnBringToBack, false);
			state.setToBack(frame.btnToBack, false);
			state.setToFront(frame.btnToFront, false);
		}	
		
	}



	public void btnUndoClicked(MouseEvent e) {
		
		Command command = model.getCmd().get(pointer--);
		UndoCmd undocmd = new UndoCmd(command);
		undocmd.execute();
		
		frame.logger.append(undocmd.toString());
		model.getSavedcmd().add(undocmd);
		
		frame.repaint();
		
		
		state.setRedo(frame.btnRedo, true);
		
		if(pointer == -1)
		{
			state.setUndo(frame.btnUndo, false);
		}
		if(model.getSelectedShapes().isEmpty())
		{
			state.setEdit(frame.btnEdit, false);
			state.setDelete(frame.btnDelete, false);
			state.setBringToFront(frame.btnBringToFront, false);
			state.setBringToBack(frame.btnBringToBack, false);
			state.setToBack(frame.btnToBack, false);
			state.setToFront(frame.btnToFront, false);
		}
		else if(model.getSelectedShapes().size() == 1)
		{
			state.setEdit(frame.btnEdit,true);
			state.setDelete(frame.btnDelete, true);
			state.setBringToFront(frame.btnBringToFront,true);
			state.setBringToBack(frame.btnBringToBack,true);
			state.setToFront(frame.btnToFront, true);
			state.setToBack(frame.btnToBack, true);
			
		}
		else if(model.getSelectedShapes().size()>=1)
		{
			state.setDelete(frame.btnDelete,true);
			state.setEdit(frame.btnEdit, false);
			state.setBringToFront(frame.btnBringToFront, false);
			state.setBringToBack(frame.btnBringToBack, false);
			state.setToBack(frame.btnToBack, false);
			state.setToFront(frame.btnToFront, false);
		}	

		
	}

	public void btnSaveClicked() {
		
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File savefile = jfc.getSelectedFile();
		
		
		deselectAll();
		n=0;
		Save save = new Save();
		SaveManager manager = new SaveManager(save);
		manager.save(model.getSavedcmd(), savefile);
		manager.saveImage(frame.getView(), "drawingImage.jpeg");
		manager.saveLog(frame.logger, "drawingLog.txt");
		deleteAllCommandsAfterPointer(pointer);
		JOptionPane.showMessageDialog(frame, "Saved!");
		frame.logger.append("Saved drawing!");
		
		}
	}



	public void btnLoadClicked() throws IOException, ClassNotFoundException {
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File savefile = jfc.getSelectedFile();
		
		
			fis = new FileInputStream(savefile);
			ois = new ObjectInputStream(fis);
			
			n=0;
			loadCmd.clear();
			model.getCmd().clear();
			pointer=-1;
			model.getShapes().clear();
			frame.logger.setText("");
			frame.repaint();
			
			

			loadCmd = (List<Command>) ois.readObject();

			ois.close();
		}	
	}

	public void btnLoadbyCmdClicked() throws ClassNotFoundException, IOException {


		state.setUndo(frame.btnUndo, true);
		while(n<loadCmd.size())
		{
			Command i=loadCmd.get(n);
			if(i instanceof AddShapeCmd)
			{
				((AddShapeCmd) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof RemoveShapeCmd)
			{
				((RemoveShapeCmd) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof UpdatePointCommand)
			{
				((UpdatePointCommand) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof UpdateLineCommand)
			{
				((UpdateLineCommand) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof UpdateRectagleCommand)
			{
				((UpdateRectagleCommand) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof UpdateCircleCommand)
			{
				((UpdateCircleCommand) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof UpdateDonutCommand)
			{
				((UpdateDonutCommand) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof UpdateHexagonCommand)
			{
				((UpdateHexagonCommand) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if (i instanceof SelectCmd)
			{
				((SelectCmd) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof DeselectCmd)
			{
				((DeselectCmd) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof BringToFrontCmd)
			{
				((BringToFrontCmd) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof BringToBackCmd)
			{
				((BringToBackCmd) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof ToFrontCmd)
			{
				((ToFrontCmd) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof ToBackCmd)
			{
				((ToBackCmd) i).setModel(model);
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof RedoCmd)
			{
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			else if(i instanceof UndoCmd)
			{
				i.execute();
				frame.repaint();
				frame.logger.append(i.toString());
				n++;
				model.getCmd().add(i);
				pointer++;
				break;
			}
			
			if(model.getSelectedShapes().isEmpty())
			{
				state.setEdit(frame.btnEdit, false);
				state.setDelete(frame.btnDelete, false);
				state.setBringToFront(frame.btnBringToFront, false);
				state.setBringToBack(frame.btnBringToBack, false);
				state.setToBack(frame.btnToBack, false);
				state.setToFront(frame.btnToFront, false);
			}
			else if(model.getSelectedShapes().size() == 1)
			{
				state.setEdit(frame.btnEdit,true);
				state.setDelete(frame.btnDelete, true);
				state.setBringToFront(frame.btnBringToFront,true);
				state.setBringToBack(frame.btnBringToBack,true);
				state.setToFront(frame.btnToFront, true);
				state.setToBack(frame.btnToBack, true);
				
			}
			else if(model.getSelectedShapes().size()>=1)
			{
				state.setDelete(frame.btnDelete,true);
				state.setEdit(frame.btnEdit, false);
				state.setBringToFront(frame.btnBringToFront, false);
				state.setBringToBack(frame.btnBringToBack, false);
				state.setToBack(frame.btnToBack, false);
				state.setToFront(frame.btnToFront, false);
			}	
		}


	
	}

	public void btnBringToFrontClicked() {
		int n = model.getShapes().size()-1;
		for(int i=0;model.getShapes().size()>i;i++)
		{
			if(model.getShapes().get(i).isSelected())
			{
				BringToFrontCmd bringtofrontCmd = new BringToFrontCmd(i,n,model);
				bringtofrontCmd.execute();
				
				frame.logger.append(bringtofrontCmd.toString());
				
				deleteAllCommandsAfterPointer(pointer);
				model.getCmd().add(bringtofrontCmd);
				model.getSavedcmd().add(bringtofrontCmd);
				pointer++;
				frame.repaint();
				break;
			}
		}
		
	}

	public void btnBringToBackClicked() {
		for(int i=0;model.getShapes().size()>i;i++)
		{
			if(model.getShapes().get(i).isSelected())
			{
				BringToBackCmd bringtobackCmd = new BringToBackCmd(i,model);
				bringtobackCmd.execute();
				
				frame.logger.append(bringtobackCmd.toString());
				
				deleteAllCommandsAfterPointer(pointer);
				model.getCmd().add(bringtobackCmd);
				model.getSavedcmd().add(bringtobackCmd);
				pointer++;
				frame.repaint();
				break;
			
			}
		}
		
	}

	public void btnToFrontClicked() {
		for(int i=0;model.getShapes().size()>i;i++)
		{
			if(model.getShapes().get(i).isSelected())
			{
				ToFrontCmd tofrontCmd = new ToFrontCmd(i,model);
				tofrontCmd.execute();
				
				frame.logger.append(tofrontCmd.toString());
				
				deleteAllCommandsAfterPointer(pointer);
				model.getCmd().add(tofrontCmd);
				model.getSavedcmd().add(tofrontCmd);
				pointer++;
				frame.repaint();
				break;
			}
		}
		
	}

	public void btnToBackClicked() {
		for(int i=0;model.getShapes().size()>i;i++)
		{
			if(model.getShapes().get(i).isSelected())
			{
				ToBackCmd tobackCmd = new ToBackCmd(i,model);
				tobackCmd.execute();
				
				frame.logger.append(tobackCmd.toString());
				
				deleteAllCommandsAfterPointer(pointer);
				model.getCmd().add(tobackCmd);
				model.getSavedcmd().add(tobackCmd);
				pointer++;
				frame.repaint();
				break;
				
				
			}
		}
		
	}
	
}



	
	
	

	
	

