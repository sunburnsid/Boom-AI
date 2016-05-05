package student;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import game.EscapeState;
import game.ExploreState;
import game.Explorer;
import game.Node;
import game.NodeStatus;

public class Tennessee extends Explorer {
    /** Get to the orb in as few steps as possible. Once you get there, 
     * you must return from the function in order to pick
     * it up. If you continue to move after finding the orb rather 
     * than returning, it will not count.
     * If you return from this function while not standing on top of the orb, 
     * it will count as a failure.
     * 
     * There is no limit to how many steps you can take, but you will receive
     * a score bonus multiplier for finding the orb in fewer steps.
     * 
     * At every step, you know only your current tile's ID and the ID of all 
     * open neighbor tiles, as well as the distance to the orb at each of these tiles
     * (ignoring walls and obstacles). 
     * 
     * In order to get information about the current state, use functions
     * currentLocation(), neighbors(), and distanceToOrb() in ExploreState.
     * You know you are standing on the orb when distanceToOrb() is 0.
     * 
     * Use function moveTo(long id) in ExploreState to move to a neighboring 
     * tile by its ID. Doing this will change state to reflect your new position.
     * 
     * A suggested first implementation that will always find the orb, but likely won't
     * receive a large bonus multiplier, is a depth-first search.*/
    @Override public void getOrb(ExploreState state) {
        //TODO : Get the orb
    	
		HashSet<Long> visitedNodes = new HashSet<Long>(); //Nodes that have already been visited.
		
		Stack<Long> nextStep = new Stack<Long>();
		nextStep.push(state.currentLocation());
		
		LinkedList<Long> wayBack = new LinkedList<Long>();
		
		while(!nextStep.isEmpty()){
			
			Long now = nextStep.pop();
			
			if (state.distanceToOrb() == 0){return;}
			
			if (!visitedNodes.contains(now)){
				visitedNodes.add(now);

				if (now != state.currentLocation()) {
					state.moveTo(now);
					System.out.println("Front: "+now);
					wayBack.addFirst(now);
					if (state.distanceToOrb() == 0){return;}
				}

				if (onTrack(state, visitedNodes)) {
					for (NodeStatus path : state.neighbors()) { // Nodes accessible from current state
						nextStep.push(path.getId());
					}
				} else {

					while (!onTrack(state, visitedNodes)) {
						Long back = wayBack.removeFirst();
						
						if (back == state.currentLocation())
							back = wayBack.removeFirst();
						
						System.out.print("New state: "+state.currentLocation());
						System.out.println("-----> "+back);
						state.moveTo(back);
					}
					wayBack.push(state.currentLocation());
				}
			}
		}
	
	}
    
    /** Returns true if there is a NodeStatus in the neighbor of state.currentLocation()
     * that has not been visited already. Returns false if all the NodeStatus neighbors
     * have already been visited.
     * */
    private boolean onTrack(ExploreState state, HashSet<Long> visitedNodes){

    	for (NodeStatus Steve : state.neighbors()){
    		if (!visitedNodes.contains(Steve.getId())){return true;}
    	}
    	
    	return false;
    }

    /** Get out the cavern before the ceiling collapses, trying to collect as much
     * gold as possible along the way. Your solution must ALWAYS get out before time runs
     * out, and this should be prioritized above collecting gold.
     * 
     * You now have access to the entire underlying graph, which can be accessed through EscapeState.
     * currentNode() and getExit() will return Node objects of interest, and getNodes()
     * will return a collection of all nodes on the graph. 
     * 
     * Note that the cavern will collapse in the number of steps given by stepsRemaining(),
     * and for each step this number is decremented by the weight of the edge taken. You can use
     * stepsRemaining() to get the time still remaining, seizeGold() to pick up any gold
     * on your current tile (this will fail if no such gold exists), and moveTo() to move
     * to a destination node adjacent to your current node.
     * 
     * You must return from this function while standing at the exit. Failing to do so before time
     * runs out or returning from the wrong location will be considered a failed run.
     * 
     * You will always have enough time to escape using the shortest path from the starting
     * position to the exit, although this will not collect much gold. For this reason, using 
     * Dijkstra's to plot the shortest path to the exit is a good starting solution. */
    @Override public void getOut(EscapeState state) {
        //TODO: Escape from the cavern before time runs out
       
    }
    
    
}
