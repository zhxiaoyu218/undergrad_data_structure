/*
 * CSC172 Project 4
 * Xiaoyu Zhang
 * xzhang81@u.rochester.edu
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.awt.Graphics;
import java.awt.Graphics2D;  
import java.awt.geom.Ellipse2D;  
import java.awt.geom.Line2D;  
import java.awt.geom.Rectangle2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.awt.Color;
import java.awt.BasicStroke;
import javax.imageio.ImageIO;  


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.PriorityQueue; 

import java.util.Comparator;




public class Proj4 {
	public static Graph createFromFile(String filename) {	 
		 int verticeCount = 0; 
		 int EdgeCount = 0;
		 Graph NewGraph = new Graph();
		 File InputFile = new File(filename);
		 
		 try{			
				BufferedReader reader= new BufferedReader(new FileReader(InputFile));			  
				String line;
				while( (line = reader.readLine()) != null)
				{
					String temp[] = line.split("[\\p{Space}]+"); 
					if(temp[0].compareTo("i") == 0)
					{
						NewGraph.insertVertice(temp,verticeCount);
						verticeCount ++;		
					}
					else
					{
						NewGraph.insertEdge(temp);
						EdgeCount ++;
					}
				}
			    reader.close();
			}catch(FileNotFoundException e){
				System.out.println("File not found");//if the fild doesn't exist then tell the user
			}catch(IOException e)
		 	{
				System.out.println("Error");
			}	
		
		 return NewGraph;
	}

	
	
	public static void main(String[] args) throws IOException {
		String mapFileName = args[0];
		Graph myGraph = createFromFile(mapFileName);
		System.out.println("Graph is created   !!!!!");
	
		if(args[1].compareTo("-show") == 0)
		{
			if(args[2].compareTo("-directions") == 0)
			{
				String str = args[3];
				String dist =args[4];
				myGraph.drawMap(); 			//draw map and save as png picture
				
				myGraph.Dijkstra(str);
				
				System.out.println("****    Shortest distance from "+str+" to " +dist +" is shown below     ****");
				myGraph.PrintPath(dist);
				System.out.println();
				System.out.println("****    The picture of map with shortest distance should be in your current folder, named Map_with_MWST.PNG    ****");
				myGraph.drawPath(dist);
				System.out.println();
			}
			else if( args[2].compareTo("-meridianmap") ==0)
			{
					System.out.println("****   The road of Minimum Weight Spanning Tree is shown below     ****");
					myGraph.PrintMWST();
					System.out.println();
					System.out.println("****   The picture of map with Minimum Weight Spanning Tree should be in your current folder, named Map_with_ShortestPath.PNG    ****");
					myGraph.drawMWST();
			}			
			else
			{
				System.out.println("Command line arguments is error, please follow the format that given in README file,1");
			}
			
		}
		else if(args[1].compareTo("-directions") == 0)
		{
				String str = args[2];
				String dist =args[3];
				myGraph.Dijkstra(str);				
				System.out.println("****    Shortest distance from "+str+" to " +dist +" is shown below     ****");
				myGraph.PrintPath(dist);
			
		}
		else if( args[1].compareTo("-meridianmap") ==0)
		{
				System.out.println("****   The road of Minimum Weight Spanning Tree is shown below     ****");
				myGraph.PrintMWST();
			
		}
		else if(args[1].compareTo("-showAll")==0 )
		{
			String str = args[2];
			String dist =args[3];
			myGraph.drawMap(); 			//draw map and save as png picture	
			
			myGraph.Dijkstra(str);	
			System.out.println("****    Shortest distance from "+str+" to " +dist +" is shown below     ****");
			myGraph.PrintPath(dist);
			System.out.println();
			System.out.println("****    The picture of map with shortest distance should be in your current folder, named Map_with_MWST.PNG    ****");
			myGraph.drawPath(dist);
			
			System.out.println("****   The road of Minimum Weight Spanning Tree is shown below     ****");
			myGraph.PrintMWST();
			System.out.println();
			System.out.println("****   The picture of map with Minimum Weight Spanning Tree should be in your current folder, named Map_with_ShortestPath.PNG    ****");
			myGraph.drawMWST();
	
		}
		else if( args[1].compareTo("-showmap") ==0)
		{
			myGraph.drawMap();
		}
		else
		{
			System.out.println("Command line arguments is error, please follow the format that given in README file,2");		
		}
		
		return;
	}
}


class Graph {
	public int vertexCount, edgeCount;
	public static final double INFINITY = Double.MAX_VALUE;

	List<Vertex> vertex = new ArrayList<Vertex>();
	List<Edge> MyEdge   = new ArrayList<Edge>();
	
	public double x_min= INFINITY,x_max = 0.0;
	public double y_min= INFINITY,y_max = 0.0;
	
	public Graph() { 	
		this.edgeCount   = 0;
		this.vertexCount = 0;
	}

	public int vertices() { 
		return vertexCount;
	}
	
	public int edges() { 
		return edgeCount;
	}
	
	public void insertVertice(String[] v_String,int ID)
	{
			String	temp[] = v_String; 
			Vertex temp_vertex = new Vertex(ID);
			temp_vertex.name = temp[1];
			temp_vertex.LAT = Double.parseDouble(temp[2]);
			temp_vertex.LON = Double.parseDouble(temp[3]);

			this.vertex.add(temp_vertex);
			this.vertexCount++;
			
			if( temp_vertex.LAT < x_min)
				x_min =temp_vertex.LAT; 
			
			if( temp_vertex.LAT > x_max)
				x_max =temp_vertex.LAT;
			
			if( Math.abs(temp_vertex.LON) < y_min)
				y_min = Math.abs(temp_vertex.LON);
			
			if(Math.abs(temp_vertex.LON) > y_max)
				y_max = Math.abs(temp_vertex.LON);
		
	}
	
	public void insertEdge(String[] e) 
	{ 					
		int strID = getID(e[2]);
		int endID = getID(e[3]);
		double pathCost = LonLatToDis(this.vertex.get(strID).LAT,this.vertex.get(strID).LON,this.vertex.get(endID).LAT,this.vertex.get(endID).LON);	
		Edge temp_e = new Edge(e[1],pathCost,strID,endID);
		MyEdge.add(temp_e);		
	}
	
	public double LonLatToDis(double lat1,double lon1,double lat2,double lon2)
	{
		double EarthRadius= 6371.0; // the mean of  the radius of the Earth is 6371KM
		double dlon = lon2 - lon1 ;
		double dlat = lat2 - lat1 ;
		double a = Math.pow((Math.sin(dlat/2)),2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow((Math.sin(dlon/2)),2); 	
		return EarthRadius *  2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) );
	}
	
	public int getID(String name){
		int i ;
		for(i= 0 ; i<vertices(); i++)
		{
			if((this.vertex.get(i).name) .compareTo(name) ==0)
			{				
				return i; 
			}		
		}
		return 0;
	}

	public void drawMap()throws IOException
	{
		int i, j;		
		double x = (this.x_max-this.x_min);
		double y =(this.y_max-this.y_min);
		double 		width =1100;
		double      height = 1100;
		
		double 		scale_y = 1000 / y;
		double      scale_x = 1000 / x;
		
		double 		offset_x = this.x_min;
		double		offset_y=  -(this.y_min);
		
		
		BufferedImage image = new BufferedImage((int)width,(int)height,BufferedImage.TYPE_INT_RGB); 
		Graphics2D g2d  = (Graphics2D)image.createGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, (int)width, (int)height);
		
		Line2D line;
		g2d.setStroke(new BasicStroke(1.0f));
		g2d.setColor(Color.BLACK);
		
		for(Edge temp:MyEdge)
		{		
			line=new Line2D.Double((height-50 - (this.vertex.get(temp.startID).LON  - offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.startID).LAT - offset_x)* scale_x ), (height -50- (this.vertex.get(temp.endID).LON -offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.endID).LAT - offset_x)* scale_x )); 								
			g2d.draw(line);  
		}
		
		 g2d.dispose();
		 ImageIO.write(image,"PNG", new File("Map.PNG"));  		 
	}
	
	public boolean connected(int node1, int node2) 
	{ 
		double result =0;
		for(Edge temp:MyEdge)
		{		
			if( (temp.startID== node1 && temp.endID==node2) || (temp.startID==node2 && temp.endID==node1) )
				return true;
		}
		return false;
	
	}
	

	// Dijkstra method  and its helper method
	
	public void Dijkstra(String str){
		int StrID = getID(str);
		int i ;
	
		this.vertex.get(StrID).dist = 0;
		
		while(UnknownLeft())
		{
			int tempID = SmallestUnknownDist();
			this.vertex.get(tempID).known = true;		
			AdjList A = getAdjList(tempID);
			for (i = A.begin(); !A.end(); i = A.next())	
			{
				if(!vertex.get(i).known)
				{
					double cvw = giveCost(vertex.get(i),vertex.get(tempID));
					if( (this.vertex.get(tempID).dist + cvw ) < this.vertex.get(i).dist)
					{
						this.vertex.get(i).dist = this.vertex.get(tempID).dist + cvw;
						this.vertex.get(i).path = this.vertex.get(tempID);
					}
				}			
			}		
		} 	
	}
	
	public double giveCost(Vertex v1,Vertex v2)
	{
		for(Edge temp:MyEdge)
		{	
			if( (this.vertex.get(temp.startID)==v1 && this.vertex.get(temp.endID)==v2) || (this.vertex.get(temp.startID)==v2 && this.vertex.get(temp.endID)==v1) )
				return temp.cost;
		}
		return 0;
	}
	public boolean UnknownLeft()
	{
		for(Vertex temp:vertex)
		{
			if(temp.known == false)
				return true;
		}
		return false;
	}
	public int SmallestUnknownDist()
	{
		int MinID = 10000,i;
		double MinDist = 100000000000.0;
		for(Vertex temp:vertex)  /// ????????????????????????
		{
			if(temp.known == false)
			{
				if(temp.dist < MinDist )
				{
					MinDist = temp.dist;
					MinID =  temp.ID;					
				}
			}
		}		
		return MinID;
	}
	
	public void PrintPath(String distName){
		int VtexID = getID(distName);
		if (this.vertex.get(VtexID).path != null){
			PrintPath(this.vertex.get(VtexID).path.name);
			System.out.print(" to ");
		}	
		System.out.println(this.vertex.get(VtexID).name);
	}
	
	public Edge FindEdge(Vertex v1,Vertex v2)
	{
		for(Edge temp:MyEdge)
		{		
			if( (this.vertex.get(temp.startID)==v1 && this.vertex.get(temp.endID)==v2) || (this.vertex.get(temp.startID)==v2 && this.vertex.get(temp.endID)==v1) )
				return temp;
		}
		return null;
	}
	
	public void drawPath(String distName)throws IOException{
		int VtexID = getID(distName);
		
		ArrayList<Edge> path = new ArrayList<>();	
		Vertex v1,v2;
		v1 = this.vertex.get(VtexID);
		v2 = this.vertex.get(VtexID).path;
		while(v2 != null)
		{
			path.add(FindEdge(v1,v2));
			v1 = v1.path;
			v2 = v2.path;
		}

		int i, j;		
		double x = (this.x_max-this.x_min);
		double y =(this.y_max-this.y_min);
		double 		width =1100;
		double      height = 1100;
		
		double 		scale_y = 1000 / y;
		double      scale_x = 1000 / x;
		
		double 		offset_x = this.x_min;
		double		offset_y=  -(this.y_min);
		
		
		BufferedImage image = new BufferedImage((int)width,(int)height,BufferedImage.TYPE_INT_RGB); 
		Graphics2D g2d  = (Graphics2D)image.createGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, (int)width, (int)height);
		
		Line2D line;
		g2d.setStroke(new BasicStroke(1.0f));
		g2d.setColor(Color.BLACK);
		for(Edge temp:MyEdge)
		{			
			line=new Line2D.Double((height-50 - (this.vertex.get(temp.startID).LON  - offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.startID).LAT - offset_x)* scale_x ), (height -50- (this.vertex.get(temp.endID).LON -offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.endID).LAT - offset_x)* scale_x )); 								
			g2d.draw(line);   
		}
		
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.setColor(Color.RED);
		for(Edge temp:path)
		{
			line=new Line2D.Double((height-50 - (this.vertex.get(temp.startID).LON  - offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.startID).LAT - offset_x)* scale_x ), (height -50- (this.vertex.get(temp.endID).LON -offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.endID).LAT - offset_x)* scale_x )); 								
			g2d.draw(line);  

        }
		g2d.dispose();
		ImageIO.write(image,"PNG", new File("Map_with_ShortestPath.PNG"));  

	}
		
//  *************************************    find min span tree 	************************************* 
	public ArrayList<Edge>  MWST()
	{
		Comparator<Edge> comparator = new EdgeCostComparator();	
		ArrayList<Edge> mst = new ArrayList<>();		
		DisjSets ds = new DisjSets(this.vertices());
		PriorityQueue <Edge> pq =new PriorityQueue<Edge>(this.MyEdge.size(),comparator);
		
		for(Edge temp:MyEdge)
		{	
			pq.add(temp);
		}	
		while((mst.size() != (vertices() - 1)) &&(pq.peek() != null) )
		{
			Edge e = pq.remove();
			int u = ds.find(e.startID);
		    int v = ds.find(e.endID);
			if(u != v)
			{
				mst.add(e);
				ds.union(u,v);
			}
		}

		return mst;
	}
	
	public void PrintMWST()
	{
		 ArrayList<Edge> myList = MWST();
		for(Edge tmp:myList)
		{
            System.out.println(tmp.name);
        }
		
	}
	
	public void drawMWST()throws IOException
	{
				int i, j;		
		double x = (this.x_max-this.x_min);
		double y =(this.y_max-this.y_min);
		double 		width =1100;
		double      height = 1100;
		
		double 		scale_y = 1000 / y;
		double      scale_x = 1000 / x;
		
		double 		offset_x = this.x_min;
		double		offset_y=  -(this.y_min);
		
		
		BufferedImage image = new BufferedImage((int)width,(int)height,BufferedImage.TYPE_INT_RGB); 
		Graphics2D g2d  = (Graphics2D)image.createGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, (int)width, (int)height);
		
		Line2D line;
		g2d.setStroke(new BasicStroke(1.0f));
		g2d.setColor(Color.BLACK);
		for(Edge temp:MyEdge)
		{			
			line=new Line2D.Double((height-50 - (this.vertex.get(temp.startID).LON  - offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.startID).LAT - offset_x)* scale_x ), (height -50- (this.vertex.get(temp.endID).LON -offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.endID).LAT - offset_x)* scale_x )); 								
			g2d.draw(line);    
		}
		
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.setColor(Color.RED);
		ArrayList<Edge> myList = MWST();
		for(Edge temp:myList)
		{		
			line=new Line2D.Double((height-50 - (this.vertex.get(temp.startID).LON  - offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.startID).LAT - offset_x)* scale_x ), (height -50- (this.vertex.get(temp.endID).LON -offset_y)*(-scale_y)), (width -50- (this.vertex.get(temp.endID).LAT - offset_x)* scale_x )); 								
			g2d.draw(line);  
        }
		g2d.dispose();
		ImageIO.write(image,"PNG", new File("Map_with_MWST.PNG"));  	
	}
	
	
	public AdjList getAdjList(int vertex) 
	{ 
		return new AdjArray(vertex);
	}

	private class AdjArray implements AdjList {
		private int v; // what vertex we are interested in
		private int i; // so we can keep track of where we are
		public AdjArray(int v) {
			this.v = v;
			this.i = -1;
		}
		public int next() { // perhaps the trickiest method
			for (++i; i < vertices(); i++)
			{
				if(connected(v,i))
					return i;
			}
			return -1;
		}
		 public int begin() {
			 this.i = -1;
			 return next();
		 }
		 public boolean end() {
	
			return (i >=  vertices());
		 }
		 

	}
	
}

interface AdjList {
	int begin();
	int next();
	boolean end();
}




class Vertex
{
	public final int 			ID;
    public String     		name;   // Vertex name
    public double 		LON =0.0,LAT = 0.0;
    
    public double     	dist;   
    public boolean 		known;
    public Vertex		path;
	//public boolean 		mark;
    
     public Vertex(int ID)
    { 
    	this.name = null; 
    	this.ID   = ID;
    	this. LON = 0.0;
    	this.LAT = 0.0;
    	this.path = null;
		this.known = false;
		this.dist = Double.MAX_VALUE;
		//this. mark = false;

    }

}
class Edge {
	
	public  String 	name;
	public double 	cost;
	public int startID,endID;
	
	//public boolean mark;
	
	public Edge(String name,double cost,int str,int ed) {
		this.name = name;
		this.cost = cost;
		//this. mark = false;
		this. startID = str;
		this. endID =ed;
	}

	
}
class DisjSets
{
	private int[] s;
	public DisjSets(int numEle)
	{
		s = new int[numEle];
		for(int i =0; i<s.length;i++)
			s[i] = -1;
		
	}
	public void union(int root1,int root2)
	{
		if (s[root2] < s[root1])		
			s[root1] = root2;
		else
		{
			if(s[root1] == s[root2])
				s[root1]--;
			s[root2] = root1;
		}
		
	}
	public int find(int x)
	{
		if(s[x]<0)
			return x;
		else 
			return s[x] = find(s[x]);
	}
}

class EdgeCostComparator implements Comparator<Edge>
{
    @Override
    public int compare(Edge x, Edge y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (x.cost < y.cost)
        {
            return -1;
        }
        if (x.cost > y.cost)
        {
            return 1;
        }
        return 0;
    }
}

