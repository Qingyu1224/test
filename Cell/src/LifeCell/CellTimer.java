package LifeCell;

//import java.awt.Panel;
import java.util.Timer;

//import javax.swing.JPanel;

public class CellTimer {
	int[][]world;
	MyPanel panel;
	Timer time;
	public int[][] getWorld() {
		return world;
	}

	public void setWorld(int[][] world) {
		this.world = world;
	}

	public CellTimer(int[][]world,MyPanel panel) {
		super();
		this.world=world;
		this.panel=panel;
	}
	
	public void start() {
		time=new Timer();
		panel.setWorld(world);
		CellTimerTask task=new CellTimerTask(world,panel);
		time.schedule(task, 200L,Const.roundInterval);
	}
	
	public void close(){
		time.cancel();
	}
}
