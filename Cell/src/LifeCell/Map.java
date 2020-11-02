package LifeCell;

public class Map {
	private int map[][];
	public Map(int height,int width) 
	{
		super();
		map=new int[height][width];
	}
	
	public int[][] getMap() {
		return map;
	}
	
	public void setMap(int[][] map) {
		this.map = map;
	}

}
