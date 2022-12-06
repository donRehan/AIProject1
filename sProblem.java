import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Hashtable;

public class sProblem {


//Creating a function that takes a hashmap and reduces the value of each key by 1
//If the value of a key is 0, it is removed from the hashmap
//If the hashmap is empty, the function returns false

public static boolean reduceMap(Hashtable<String, Integer> map) {
	if (map.isEmpty()) {
		return false;
	}
	for (String key : map.keySet()) {
		int value = map.get(key);
		if (value == 1) {
			//create blackbox
			map.remove(key);
		} else {
			map.put(key, value - 1);
		}
	}
	return true;
}

//Validate and return the expanded nodes that can be expanded
public static ArrayList expand(tNode node) {

//get the node's co-ordinates and length and width
int[] co_ordinates = node.co_ordinates;
int[] length_width = node.length_width;

//create a new arraylist to store the expanded nodes
ArrayList<tNode> expanded = new ArrayList<tNode>();

//Check left , right , up , down
//left , Check that the x co-ordinate is greater than 0 
if (co_ordinates[1] > 0) {
    //create a new node
    tNode left = new tNode(node.toString());
    //decrement the x co-ordinate
    left.co_ordinates[1]--;
    left.sequence = node.sequence + "left,"; 
    //if passengers are dead. then cancel going in that direction.
	if(reduceMap(left.ships)){
		//just reduces the number of ship's passengers if possible	
	}
    //System.out.println(left.co_ordinates[1] + ""+ left.sequence);
    //add the node to the expanded nodes
    expanded.add(left);
}

//right , Check that the x co-ordinate is less than the width
if (co_ordinates[1] < length_width[1] - 1) {
    //create a new node
    tNode right = new tNode(node.toString());
    //increment the x co-ordinate
    right.co_ordinates[1]++;
    right.sequence = node.sequence + "right,"; 
    if(reduceMap(right.ships)){
		//just reduces the number of ship's passengers if possible	
	}
    //add the node to the expanded nodes
    //System.out.println(right.co_ordinates[1] + ""+ right.sequence);
    expanded.add(right);
}

//up , Check that the y co-ordinate is greater than 0
if (co_ordinates[0] > 0) {
    //create a new node
    tNode up = new tNode(node.toString());
    //decrement the y co-ordinate
    up.co_ordinates[0]--;
    up.sequence = node.sequence + "up,"; 
    if(reduceMap(up.ships)){
		//just reduces the number of ship's passengers if possible	
	}
    //add the node to the expanded nodes
    //System.out.println(up.co_ordinates[0] + ""+ up.sequence);
    expanded.add(up);
}

//down , Check that the y co-ordinate is less than the height
if (co_ordinates[0] < length_width[0] - 1) {
    //create a new node
    tNode down = new tNode(node.toString());
    //increment the y co-ordinate
    down.co_ordinates[0]++;
    down.sequence =  node.sequence +"down,"; 
    if(reduceMap(down.ships)){
		//just reduces the number of ship's passengers if possible	
	}
	down.number = down.ships.get("3,2");
    //add the node to the expanded nodes
    //System.out.println(down.co_ordinates[0] + ""+ down.sequence);
    expanded.add(down);
}

//check if expanded is empty
if (expanded.isEmpty()) {
    //return null
    return null;
} 
    //return the expanded nodes
    return expanded;

}

//Create goalTest Method too.
//return String if goal is reached
//return null if goal is not reached
public static String goalTest(tNode node) {
    //get the node's co-ordinates , check if they return a value in the ships
    int[] co_ordinates = node.co_ordinates;
    String shipString = co_ordinates[0] + "," + co_ordinates[1];
    ///pass co_ordinates to the ships hashtable to get value
    if(node.ships.get(shipString) != null){
        int vall = node.ships.get(shipString);
        int diff = node.max_passengers - node.passengers_carried;
        if(diff >= vall){
            node.passengers_carried += vall;
            //node.ships.remove(shipString);
            return node.sequence + " " + node.number;
        }
        else{
            //replace the value by value - the diff
            node.ships.put(shipString, vall - diff);
            node.passengers_carried = node.max_passengers;
            return "You made it ! , " + node.passengers_carried + " passengers carried"+ " new value for " + shipString + " is " + node.ships.get(shipString);
        }
    }
   
    return null;

}

public static String bf_Search(String problem)
{
    Queue<Object> Nodes = new LinkedList<Object>();
    //Adding the initial state to the queue
    //Create an inital tNode from problem
    tNode initial = new tNode(problem);
    //add
    Nodes.add(initial);

    //while the queue is not empty loop
    while(!Nodes.isEmpty())
    {
    //later this will be cNode and the first tNode is going to be before the loop
    tNode node = (tNode) Nodes.remove();
	//System.out.println(node.toString());
    //Add the steps taken this far.
    //solution = solution + node.sequence;
    //System.out.println("sequence: " + solution);
    //print the co_ordiantes
    //System.out.println("co_ordinates: " + node.co_ordinates[0] + "," + node.co_ordinates[1]);
    //Call goal test on the node
    String goal = goalTest(node);
    if(goal != null)
    {
        //if goal test is true then return the solution
        // => create solution the right format.
		//System.out.println(node.passengers_carried);
		//show the number of passengers in the ship
        return goal;
    }


	

    //if goal test is false then expand the node
    //expand the node
    ArrayList<tNode> children = expand(node);

    //if the node is not null then add the children to the queue
    if(children != null)
    {
        for(int i = 0; i < children.size(); i++)
        {
            Nodes.add(children.get(i));
        }
    }
    }
    //if it reaches here then there is no solution
    return "No Solution";
}

//Create the main method
public static void main(String[] args) {
    System.out.println(bf_Search("3,4;97;1,2;0,1;3,2,65;"));
    }

}

