package LifeCell;

//import java.awt.Panel;
//import java.util.Timer;
import java.util.TimerTask;

//import javax.swing.JPanel;

public class CellTimerTask extends TimerTask{
	int[][]world;
	Logic l=new Logic();
	MyPanel panel;
	
	public CellTimerTask(int[][]world,MyPanel panel) {
		super();
		this.world=world;
		this.panel=panel;
	}
	
	public void run() {
        world=l.TraverseCell(world);
        panel.setWorld(world);
        panel.repaint();
    }
	
	public int[][] getWorld() {
		return world;
	}
	public void setWorld(int[][] world) {
		this.world = world;
	}
	
}
