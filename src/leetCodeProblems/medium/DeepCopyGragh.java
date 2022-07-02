package leetCodeProblems.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class DeepCopyGragh {
	/*
	 *  using HashMap & DFS & Recursion
	 *  https://leetcode.com/problems/clone-graph/

	 */
	public GraphNode cloneGraph(GraphNode gNode) {

		if (gNode == null) {
			return gNode;
		}
		Map<Integer, GraphNode> clonedMap = new HashMap<>();
		return clone(gNode, clonedMap);
		
	}

	private GraphNode clone(GraphNode gNode, Map<Integer, GraphNode> clonedMap) {
        if(clonedMap.containsKey(gNode.val)) {
        	return clonedMap.get(gNode.val);
        }
        GraphNode clone = new GraphNode(gNode.val);
		clonedMap.put(clone.val, clone);
	    //DFS to clone over the neighbors
		for(GraphNode n: gNode.neighbors) {
			clone.neighbors.add(clone(n, clonedMap));
		}
		return clone;
        
	}
	 // Build the desired graph 
    public GraphNode buildGraph() 
    { 
        /* 
            Note : All the edges are Undirected 
            Given Graph: 
            1--2 
            |  | 
            4--3 
        */
        GraphNode node1 = new GraphNode(1); 
        GraphNode node2 = new GraphNode(2); 
        GraphNode node3 = new GraphNode(3); 
        GraphNode node4 = new GraphNode(4); 
        List<GraphNode> v = new ArrayList<GraphNode>(); 
        v.add(node2); 
        v.add(node4); 
        node1.neighbors = v; 
        v = new ArrayList<GraphNode>(); 
        v.add(node1); 
        v.add(node3); 
        node2.neighbors = v; 
        v = new ArrayList<GraphNode>(); 
        v.add(node2); 
        v.add(node4); 
        node3.neighbors = v; 
        v = new ArrayList<GraphNode>(); 
        v.add(node3); 
        v.add(node1); 
        node4.neighbors = v; 
        return node1; 
    } 
  	
    // BFS traversal of a graph to 
    // check if the cloned graph is correct 
    public void bfs(GraphNode source) 
    { 
        Queue<GraphNode> q = new LinkedList<GraphNode>(); 
        q.add(source); 
        HashMap<GraphNode,Boolean> visit = 
                          new HashMap<GraphNode,Boolean>(); 
        visit.put(source,true); 
        while (!q.isEmpty()) 
        { 
            GraphNode u = q.poll(); 
            System.out.println("Value of Node " + u.val); 
            System.out.println("Address of Node " + u); 
            if (u.neighbors != null) 
            { 
                List<GraphNode> v = u.neighbors; 
                for (GraphNode g : v){ 
                    if (visit.get(g) == null){ 
                        q.add(g); 
                        visit.put(g,true); 
                    } 
                } 
            } 
        } 
        System.out.println(); 
    } 

  
// Driver code 

    public static void main(String args[])  { 
    	DeepCopyGragh graph = new DeepCopyGragh(); 
        GraphNode source = graph.buildGraph(); 
        System.out.println("BFS traversal of a graph before cloning"); 
        graph.bfs(source); 
        GraphNode newSource = graph.cloneGraph(source); 
        System.out.println("BFS traversal of a graph after cloning"); 
        graph.bfs(newSource); 
    } 
} 
	
