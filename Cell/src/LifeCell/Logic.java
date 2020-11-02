package LifeCell;

import java.util.Random;

public class Logic 
{
	int width=Const.width;
	int height=Const.height;
	
	//对地图数组进行随机初始化
	public Map InitMap() 
	{
		Map m=new Map(height,width);
		Random r = new Random();
		int temp[][]=m.getMap();
		for(int i=0;i<height;i++) 
		{
			for(int j=0;j<width;j++) 
			{
				if(r.nextInt(100)>Const.cellProbability)
				temp[i][j]=1;
			}
		}
		m.setMap(temp);	
		return m;
	}

	//返回输入位置的细胞的周围活细胞数
	public int AroundCell(int a[][], int i,int j) 
	{
		int num=0;
		//左边界
		if(j!=0)
		{
			num+=a[i][j-1];
		}
		
		//右边界
		if(j!=width-1)
		{
			num+=a[i][j+1];
		}
		
		//上边界
		if(i!=0)
		{
			num+=a[i-1][j];
		}
		
		//下边界
		if(i!=height-1)
		{
			num+=a[i+1][j];
		}
		
		//左上角
		if(i!=0&&j!=0)
		{
			num+=a[i-1][j-1];
		}
		
		//右上角
		if(i!=0&&j!=width-1)
		{
			num+=a[i-1][j+1];
		}

		//左下角
		if(i!=height-1&&j!=0)
		{
			num+=a[i+1][j-1];
		}
		
		//右下角
		if(i!=height-1&&j!=width-1)
		{
			num+=a[i+1][j+1];
		}

		return num;
	}
	
	//遍历地图中的每一个细胞，并对其进行判断和改变
	public int[][] TraverseCell(int[][]m) 
	{
		int Case=0;
		int temp[][]=new int[height][width];
		for(int i=0;i<height;i++) 
		{
			for(int j=0;j<width;j++) 
			{
				temp[i][j]=0;
				Case=AroundCell(m, i, j);
				if(Case==3)
				{
					temp[i][j]=1;
				}
				else if (Case==2)
				{
					temp[i][j]=m[i][j];
				}
			}
		}
		return temp;
	}
	
	//用于将输入的数组清空,使其全部元素均为0
	public int[][] ClearCell(int[][]m1) 
	{
		int m[][]=m1;
		for(int i=0;i<height;i++)
		{
			for(int j=0; j<width; j++)
			{
				m[i][j]=0;
			}
		}
		return m;
	}
}
