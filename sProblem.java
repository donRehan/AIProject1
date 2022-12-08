import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class sProblem {


//Creating a function that takes a hashmap and reduces the value of each key by 1
//If the value of a key is 0, it is removed from the hashmap
//If the hashmap is empty, the function returns false

public static boolean reduceMap(Hashtable<String, Integer> map, Hashtable<String, Integer> boxx, tNode node) {
	if (map.isEmpty()) {
		return false;
	}
	for (String key : map.keySet()) {
		int value = map.get(key);
		if (value == 1) {
			//create blackbox
			//add the same key with a value of 20 to the blackbox hashmap of boxx
			boxx.put(key, 19);
            node.dead++; 
			map.remove(key);
		} else {
			map.put(key, value - 1);
            node.dead++; 
		}
	}
	return true;
}

public static boolean reduceBox(Hashtable<String, Integer> map) {
	if (map.isEmpty()) {
		return false;
	}
	for (String key : map.keySet()) {
		int value = map.get(key);
		if (value == 1) {
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
//up , Check that the y co-ordinate is greater than 0
if (co_ordinates[0] > 0) {
    //create a new node
    tNode up = new tNode(node.toString());
    //decrement the y co-ordinate
    up.co_ordinates[0]--;
    up.sequence = node.sequence + "up,"; 
    if(reduceMap(up.ships,up.blackboxes,up)){
		//just reduces the number of ship's passengers if possible	
	}
	if(reduceBox(up.blackboxes)){

	}
    up.performAction();
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
    if(reduceMap(down.ships,down.blackboxes,down)){
		//just reduces the number of ship's passengers if possible	
	}

	if(reduceBox(down.blackboxes)){

	}
    down.performAction();
	//add the node to the expanded nodes
	//System.out.println(down.co_ordinates[0] + ""+ down.sequence);
	//down.number = down.ships.get("3,2");
    //add the node to the expanded nodes
    //System.out.println(down.co_ordinates[0] + ""+ down.sequence);
    expanded.add(down);
}
//Check left , right , up , down
//left , Check that the x co-ordinate is greater than 0 
if (co_ordinates[1] > 0) {
    //create a new node
    tNode left = new tNode(node.toString());
    //decrement the x co-ordinate
    left.co_ordinates[1]--;
    left.sequence = node.sequence + "left,"; 
    //if passengers are dead. then cancel going in that direction.
	if(reduceMap(left.ships,left.blackboxes,left)){
		//just reduces the number of ship's passengers if possible	
	}
	if(reduceBox(left.blackboxes)){
		//reduce the number of blackbox's health if possible
	}
    left.performAction();
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
    if(reduceMap(right.ships,right.blackboxes,right)){
		//just reduces the number of ship's passengers if possible	
	}
	if(reduceBox(right.blackboxes)){

	}
    right.performAction();
    //add the node to the expanded nodes
    //System.out.println(right.co_ordinates[1] + ""+ right.sequence);
    expanded.add(right);
}
//check if expanded is empty
if (expanded.isEmpty()) {
    //return null
    return null;
} 
    //return the expanded nodes
    return expanded;

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
    tNode node = (tNode) Nodes.remove();

    //Check for the goal and do actions on the current node.
    if(node.ships.isEmpty() && node.blackboxes.isEmpty()  && node.stations.containsKey(node.co_ordinates[0] + "," + node.co_ordinates[1])) 
        {
            return node.sequence;
        }
    ArrayList<tNode> children = expand(node);

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

public static String df_Search(String problem)
{
    LinkedList<Object> Nodes = new LinkedList<Object>();
    //Adding the initial state to the queue
    //Create an inital tNode from problem
    tNode initial = new tNode(problem);
    //add to the stack initial 
    Nodes.addLast(initial);
    //while the queue is not empty loop
    while(!Nodes.isEmpty())
    {
    tNode node = (tNode) Nodes.removeLast();

    //Check for the goal and do actions on the current node.
    if(node.ships.isEmpty() && node.blackboxes.isEmpty()  && node.stations.containsKey(node.co_ordinates[0] + "," + node.co_ordinates[1])) 
        {
            return node.sequence;
        }
    ArrayList<tNode> children = expand(node);

        if(children != null)
    {
        for(int i = 0; i < children.size(); i++)
        {
            Nodes.addLast(children.get(i));
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

