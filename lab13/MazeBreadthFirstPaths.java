import java.util.Observable;
/** 
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields: 
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
	private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze; 

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);  
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s; 
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int v) {
    	announce(); 
    	Queue<Integer> x = new Queue<Integer>();
    	for (int i = 0; i < maze.V(); i++) distTo[i] = Integer.MAX_VALUE;
    	marked[v] = true;
    	distTo[v] = 0;
    	x.enqueue(v);
    	
    	while(!x.isEmpty()) {
    		int y = x.dequeue();
    		for (int z : maze.adj(y)) {
    			if (!marked[z]) {
    				announce();
    				if (y == t) {
    		             targetFound = true;
    		         }

    		         if (targetFound) {
    		             return;
    		         }
    				edgeTo[z] = y;
    				distTo[z] = distTo[y] + 1;
    				marked[z] = true;
    				x.enqueue(z);
    			}
    		}
    	}
    }


    @Override
    public void solve() {
        bfs(s);
    }
} 

