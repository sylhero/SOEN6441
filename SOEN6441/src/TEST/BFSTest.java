
package TEST;

import java.util.ArrayList;

import tilemap.TileMap;

public class BFSTest
{
// Graph adjacency matrix (unweighted graph)
static private int[][] graph = new TileMap("/Users/yulongsong/Documents/workspace/SOEN6441/resources/gamemaps/testMap1.map").getMap();

//-------------------------------------------------------------- 
public static void main (String[] args)
{
ArrayList<Integer> Start = new ArrayList();
Start.add(0); // The start node
ArrayList<ArrayList> Queue = new ArrayList();
Queue.add(Start); // inserted in the initial queue of paths as [[Start]]
// Use one search at a time (comment the others) 
// ArrayList Path = depth_first(Queue,4); // Depth first search 
ArrayList Path = breadth_first(Queue,4); // Breadth first search
// ArrayList Path = uni_cost(Queue,4); // Uniform cost search
System.out.println(Path);
// System.out.println(path_cost(Path));
}
//--------------------------------------------------------------
// Adds to the queue paths extending the first path in the queue 
public static ArrayList<ArrayList> extend (ArrayList<Integer> Path)
{
ArrayList<ArrayList> NewPaths = new ArrayList();
for (int i=0;i<graph.length;i++)
if (graph[Path.get(0)][i]>0 && !Path.contains(i))
{
ArrayList Path1 = (ArrayList)Path.clone();

System.out.println(Path1+"=====jjjj====");
Path1.add(0,i);
NewPaths.add(Path1);
System.out.println(NewPaths+"====NewPaths==");
}
return NewPaths;
}
//--------------------------------------------------------------
// Appends y to x and returns the result in a new ArrayList z 
public static ArrayList append (ArrayList x, ArrayList y) 
{
ArrayList z = (ArrayList)x.clone();
for (int i=0;i<y.size();i++) z.add(y.get(i));
return z;
}

//--------------------------------------------------------------
// Breadth first search for a path from Start to Goal. 
// The Start node must be in the initial queue of paths as [[Start]] 
public static ArrayList<ArrayList> breadth_first(ArrayList<ArrayList> Queue, int Goal) 
{
if (Queue.size()==0) return Queue; // path not found
if ((Integer)Queue.get(0).get(0) == Goal) // if Goal is the first node in the first path
return Queue.get(0); // return path
else // replace the first path with all its extensions and put them at the end of the queue
{ 
ArrayList<ArrayList> NewPaths = extend(Queue.get(0));
Queue.remove(0);
return breadth_first(append(Queue,NewPaths),Goal);
}
}
//--------------------------------------------------------------
public static int path_cost (ArrayList<Integer> Path)
{
int cost = 0;
for (int i=0;i<Path.size()-1;i++)
cost = cost + graph[Path.get(i+1)][Path.get(i)];
return cost;
}
public static void sort(ArrayList<ArrayList> Queue)
{
int out, in;
for(out=Queue.size()-1; out>=1; out--)
for(in=0; in<out; in++)
if( path_cost(Queue.get(in)) > path_cost(Queue.get(in+1)) )
swap(Queue, in, in+1);
}
//--------------------------------------------------------------
private static void swap(ArrayList<ArrayList> a, int one, int two)
{
ArrayList<Integer> temp = a.get(one);
a.set(one,a.get(two));
a.set(two,temp);
}

}
