****************************
   CSC172 Proj4
   README file
   Xiaoyu Zhang
   xzhang81@u.rochester.edu
****************************

This Proj4 zip file should have three files, including
	README.txt
	OUTPUT.txt
	Proj4.java


>>OUTPUT.txt
	This file include the output of Proj4.java that shows shortest path output first and the otput of MWST
		
>>Proj4.java
	usage: java Proj4 ur.txt -show -directions startIntersection endIntersection
		or: java Proj4 ur.txt -show -meridianmap
		or: without -show
		my code also have other command line argument to get all three map one time. 		
					java Proj4 ur.txt -showAll startIntersection endIntersection
				and other command line just display map
					java Proj4 ur.txt -showmap
					
	1. code Implementation:
		(1) I used adjacent list to represent the connection(road) of each vertex. an arraylist for vertex, a arraylist for Edge(road).
		(2) apply dijkstra's algorithm to find shortest path between two point. adjcent list could be used to find adjacent vertex. I have a adjacent class to find the ID of adjacent vertex
			add the cost from current vertex to dist to current vertex.
		(3) Kruskal's algorithm is used to find minimum weight spanning tree. In order to use PriorityQueue,I decleared a comparetor to compare the cost of edge. use disjoint set to check if finish
		(4) ALL drawing used graphics2D. shortest path and MWST is just add highlight on map.
		(5) the cost of edge is distance between two points. Since input file just give latitude and longitude, which are actually degree of earth, I convert the distance of two point to kilometer(KM).
			Thus, the Radius of earth choosen will really affect the length. So,the result of shortest path will slightly change base on radius of earth that used.
			the length of some road in ur might even have some length and some part of map is symmetry. So, answer is not unique
		(6) extra credit, I adject the width of line and used different color for highlight. I think my map looks beautiful.
		
	2. Output map picture:
			all map picture will appear in current folder after run code. 
			picture are all scaled based on max/min LAT and LON.
			
	3. issue:
			I got big unexpected issue in this project. I did not reaize that normal data structure cannot held huge data,like nys.txt. this is really first time I face this such problem.
			I changed at least 70% implementation to make it work. it work but not that efficiency. 2min for monroe.txt to draw map ; at least 10min for nys.txt to draw map. Other operation is longer.dijkstra is vary slow for large data file.
			I really data type of this project is really matter should be told on sheet. and code could be implemented for huge data.
			
	4. Runtime:
				if n = number of vertex
			(1) plotting the map:  O(n!) because graph is undirect(means adj[a][b] == adj[b][a]), runtime is (n! * 3) +18 time for just plotting. 3 is the number of instruction inside the two for loops. 18 is used to declear variable for draw map
			(2) find shorst path: O(E+ Vlog(V)). it first take 2n time to initial vertex. and set up the distance from all vertex to initial point. Because I use adjacent matrix, it take n time to find adjacent vertex. In order to find shortest distance, it take the number edge times.
			(3) MWST : O(E log V). I implemented disjoint set, it rank edge that perform O(v) operation  in O(V log V) times. and use v times to find edge for MWST. So, total time is 2VlogV + declearation. because total edge of MWST is V-1. so runtime is O(ElogV) or O(V logV)
								



