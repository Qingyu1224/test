package LifeCell;

//import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import java.util.Timer;

public class gui extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyPanel panel;
	private Logic g;
	private Map m;
	public int [][]world=new int[Math.abs(Const.height)][Math.abs(Const.width)];
	private int height=Math.abs(Const.height);
	private int width=Math.abs(Const.width);
	private int size=Math.abs(Const.cellSize);
	private boolean STARTED=false;		//use to control the button Start, Pause and Clear 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					gui frame = new gui();
					frame.setTitle("LifeGame");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public gui() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 700);
		panel = new MyPanel(world);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		
		//Click to change the cell status
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(STARTED==false)
				{
					world=panel.getWorld();
					int x=e.getX();
					int y=e.getY();
					int j=x/size;
					int i=y/size;
					if(i<height&&j<width)
					{
						world[i][j]=(world[i][j]+1)%2;
						panel.setWorld(world);
						repaint();
					}
				}
			}
		});
		
		panel.setLayout(null);
		
		//create the object that the game need
		m=new Map(height, width);
		g=new Logic();	
		//Timer
		CellTimer timer=new CellTimer(world,panel);
		
		JLabel StatusLabel = new JLabel("END");
		StatusLabel.setBounds(230, 617, 40, 15);
		panel.add(StatusLabel);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(STARTED==false)
				{
					world=panel.getWorld();
					timer.setWorld(world);
					panel.setWorld(world);
					timer.start();
					STARTED=true;
					StatusLabel.setText("START");
				}
			}
		});
		panel.setLayout(null);
		btnNewButton.setBounds(288, 617, 97, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m.setMap(g.ClearCell(m.getMap()));
				world=m.getMap();
				panel.setWorld(world);
				timer.setWorld(world);
				if(STARTED==true)
				{
					timer.close();
					STARTED=false;
					StatusLabel.setText("END");
				}
				panel.repaint();
			}
		});
		btnNewButton_1.setBounds(511, 617, 97, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Pause");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(STARTED==true)
				{
					timer.close();
					STARTED=false;
					StatusLabel.setText("END");
				}				
			}
		});
		btnNewButton_2.setBounds(399, 617, 97, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Random");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(STARTED==false)
				{
					m=g.InitMap();
					world=m.getMap();
					panel.setWorld(world);
					timer.setWorld(world);
					panel.repaint();
					setVisible(true);	
				}				
			}			
		});
		btnNewButton_3.setBounds(10, 617, 97, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Help");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new helpGUI();
			}
			
		});
		btnNewButton_4.setBounds(117, 617, 97, 23);
		panel.add(btnNewButton_4);
		
		
	}
}

class MyPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int [][]world;
	MyPanel(int[][]world)
	{
		super();
		this.world=world;
	}
	@Override
	//Rewrite the paint() of JPanel
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.black); 
		
		//Draw Cell frame
		for(int i=0;i<Math.abs(Const.height);i++)
		{
			for(int j=0;j<Math.abs(Const.width);j++)
			{
				
				{
					g.drawRect(j*Math.abs(Const.cellSize),Math.abs(i*Const.cellSize) , Math.abs(Const.cellSize),Math.abs(Const.cellSize));
				}
			}
		}
		
		//Draw Cell
		for(int i=0;i<Math.abs(Const.height);i++)
		{
			for(int j=0;j<Math.abs(Const.width);j++)
			{
				if(world[i][j]==1)
				{
					g.fillRect(j*Math.abs(Const.cellSize),Math.abs(i*Const.cellSize) , Math.abs(Const.cellSize),Math.abs(Const.cellSize));
				}
			}
		}
		
	}
	
	public int[][] getWorld() 
	{
		return world;
	}
	
	public void setWorld(int[][] world) 
	{
		this.world = world;
	}
}
