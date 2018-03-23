/*
 * CSC172 lab18
 * Xiaoyu Zhang
 * xzhang81@u.rochester.edu
 */
public class lab18 {

	public static void main(String[] args) {
		Graph myGraph1 = new Graph(7,true);
		 Edge E[] = new Edge[12];
		 E[0] = new Edge(0,1);	 E[1] = new Edge(0,3);
		 E[2] = new Edge(1,3);	 E[3] = new Edge(1,4);
		 E[4] = new Edge(2,0);   E[5] = new Edge(2,5);
		 E[6] = new Edge(3,2);   E[7] = new Edge(3,5);
		 E[8] = new Edge(3,6);   E[9] = new Edge(3,4);
		 E[10] = new Edge(4,6);   E[11] = new Edge(6,5);
		 for(int i = 0; i<E.length ;i++){
		 	myGraph1.insert(E[i]);
		 }
		 System.out.println("The print of directed graph in figure 9.10 ");
		 System.out.println("Edge is " + myGraph1.edges());
		 myGraph1.show();
		 System.out.println();
		 
		 Graph myGraph2 = new Graph(5,false);
		 Edge E2[] = new Edge[7];
		 E2[0] = new Edge(0,1); E2[1] = new Edge(0,3); E2[2] = new Edge(0,4);
		 E2[3] =new Edge(1,3);  E2[4] =new Edge(1,2);
		 E2[5] =new Edge(2,3);  E2[6] =new Edge(4,2);
		 for(int i = 0; i<E2.length ;i++){
			 	myGraph2.insert(E2[i]);
			 }
		 System.out.println("The print of undirected graph in figure 9.62 ");
		 System.out.println("Edge is " + myGraph2.edges());
		 myGraph2.show();
		 System.out.println();
		 
		 
		 System.out.println("Use undirected graph in figure 9.62 to show delete() work");
		 System.out.println("delete edge(4,0) and edge(4,2). (E,A) and (E,C)in book");
		 myGraph2.delete(E2[6]);
		 myGraph2.delete(E2[2]);
		 System.out.println("The print graph after deletion. (node4 is empty)");
		 myGraph2.show();
		 
		
	}
}

class Edge {
	public final int v, w; // an edge from v to w
	public Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}
	
}


class Graph {
	private int vertexCount, edgeCount;
	boolean directed; // false for undirected graphs, true for directed
	private boolean adj[][];
	public Graph(int numVerticies, boolean isDirected) { 
		this.adj = new boolean[numVerticies][numVerticies];
		this.directed = isDirected;
		this.vertexCount = numVerticies;
		this.edgeCount = 0;
	}
	
	public boolean isDirected() { 
		// your code here 
		return this.directed;
	}
	public int vertices() { 
		return vertexCount;
	}
	public int edges() { 
		return edgeCount;
	}
	public void insert(Edge e) { 
		
		if(directed)
		{
			if( !connected(e.v, e.w)){
				//System.out.println("	" + e.v +" " + e.w);
				adj[e.v][e.w] = true;
				edgeCount++;
			}
		}else{
		//	System.out.println("	" + e.v +" " + e.w);
			if( !connected(e.v, e.w)){
				adj[e.v][e.w] = true;
				adj[e.w][e.v] = true;
				edgeCount++;
			}
	
			
		}
		
	}
	public void delete(Edge e) { 
		if(directed)
		{
			if(connected(e.v, e.w)){
				adj[e.v][e.w] = false;
				edgeCount--;
			}
		}else{
			if(connected(e.v, e.w)){
				adj[e.v][e.w] = false;
				edgeCount--;
			}
			if(connected(e.w, e.v)){
				adj[e.w][e.v] = false;
				edgeCount--;
			}
			
		}
	}
	public boolean connected(int node1, int node2) { 
		if(directed)
		{
			return adj[node1][node2];
		}else{
			if ((adj[node1][node2] != true) && (adj[node2][node1]) != true)
				return false;
			else
				return true;
			
		}
		
		
	
	}
	public AdjList getAdjList(int vertex) { 
		return new AdjArray(vertex);

	}
	
	 public void show () {
		 for (int s = 0; s < vertices(); s++) {
		 System.out.print((s+0) + ": ");
		 AdjList A = getAdjList(s);
		 for (int t = A.begin(); !A.end(); t = A.next()) // use of iterator
		 System.out.print((t+0) + " ");
		 System.out.println();
		 }
	}
	
	private class AdjArray implements AdjList {
		private int v; // what vertex we are interested in
		private int i; // so we can keep track of where we are
		public AdjArray(int v) {
		// write the code for the constructors
		// save the value of the vertex passed in
		// (that will be where the iterator starts)
		// start the “i” counter at negative one
			this.v = v;
			this.i = -1;
		}
		public int next() { // perhaps the trickiest method
		// use a for loop to advance the value of “i”
		// “for (++i; i < vertices(); i++)”
		// and search the appropriate row return the index
		// of the next true value found
		// “if (connected(v,i) == true) return i;”
		 // if the loop completes without finding anything return -1
			for (++i; i < vertices(); i++)
			{
				if(connected(v,i))
					return i;
			}
			return -1;
		}
		 public int begin() {
		// reset “i” back to negative one
		// return the value of a call to “next”
			 this.i = -1;
			 return next();
		 }
		 public boolean end() {
		// if “i” is less than the number of vertices return false
			return (i >=  vertices());
		 }
		 

	}
}

interface AdjList {
	int begin();
	int next();
	boolean end();
}
