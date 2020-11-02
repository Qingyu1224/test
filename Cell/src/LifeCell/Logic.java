package LifeCell;

import java.util.Random;

public class Logic 
{
	int width=Const.width;
	int height=Const.height;
	
	//�Ե�ͼ������������ʼ��
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

	//��������λ�õ�ϸ������Χ��ϸ����
	public int AroundCell(int a[][], int i,int j) 
	{
		int num=0;
		//��߽�
		if(j!=0)
		{
			num+=a[i][j-1];
		}
		
		//�ұ߽�
		if(j!=width-1)
		{
			num+=a[i][j+1];
		}
		
		//�ϱ߽�
		if(i!=0)
		{
			num+=a[i-1][j];
		}
		
		//�±߽�
		if(i!=height-1)
		{
			num+=a[i+1][j];
		}
		
		//���Ͻ�
		if(i!=0&&j!=0)
		{
			num+=a[i-1][j-1];
		}
		
		//���Ͻ�
		if(i!=0&&j!=width-1)
		{
			num+=a[i-1][j+1];
		}

		//���½�
		if(i!=height-1&&j!=0)
		{
			num+=a[i+1][j-1];
		}
		
		//���½�
		if(i!=height-1&&j!=width-1)
		{
			num+=a[i+1][j+1];
		}

		return num;
	}
	
	//������ͼ�е�ÿһ��ϸ��������������жϺ͸ı�
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
	
	//���ڽ�������������,ʹ��ȫ��Ԫ�ؾ�Ϊ0
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
