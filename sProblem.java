import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashSet;

public class sProblem {


//Creating a function that takes a hashmap and reduces the value of each key by 1
//If the value of a key is 0, it is removed from the hashmap
//If the hashmap is empty, the function returns false

//Validate and return the expanded nodes that can be expanded


public static String bf_Search(String problem)
{
    Queue<Object> Nodes = new LinkedList<Object>();
    //Create a hashset to store the visited nodes
    HashSet<String> visited = new HashSet<String>();

    //Adding the initial state to the queue
    //Create an inital tNode from problem
    tNode initial = new tNode(problem);
    //add
    Nodes.add(initial);
    visited.add(initial.toString());

    //while the queue is not empty loop
    while(!Nodes.isEmpty())
    {
    tNode node = (tNode) Nodes.remove();

    //Check for the goal and do actions on the current node.
    if(node.ships.isEmpty() && node.blackboxes.isEmpty()  && node.stations.containsKey(node.co_ordinates[0] + "," + node.co_ordinates[1])) 
        {
            return node.sequence;
        }
    ArrayList<tNode> children = node.expand();

        if(children != null)
    {
        for(int i = 0; i < children.size(); i++)
        {
            //if the child is not in the visited set, add it to the queue
            if(!visited.contains(children.get(i).toString()))
            {
                Nodes.add(children.get(i));
                visited.add(children.get(i).toString());
            }
        }
    }
    }
    //if it reaches here then there is no solution
    return "The output actions do not lead to a goal state";
}

public static String df_Search(String problem)
{
    LinkedList<Object> Nodes = new LinkedList<Object>();
    HashSet<String> visited = new HashSet<String>();
    //Adding the initial state to the queue
    //Create an inital tNode from problem
    tNode initial = new tNode(problem);
    //add to the stack initial 
    Nodes.addLast(initial);
    visited.add(initial.toString());
    //while the queue is not empty loop
    while(!Nodes.isEmpty())
    {
    tNode node = (tNode) Nodes.removeLast();

    //Check for the goal and do actions on the current node.
    if(node.ships.isEmpty() && node.blackboxes.isEmpty()  && node.stations.containsKey(node.co_ordinates[0] + "," + node.co_ordinates[1])) 
        {
            return node.sequence;
        }
    ArrayList<tNode> children = node.expand();

    if(children != null)
    {
        for(int i = 0; i < children.size(); i++)
        {
            //if the child is not in the visited set, add it to the queue
            if(!visited.contains(children.get(i).toString()))
            {
                Nodes.addLast(children.get(i));
                visited.add(children.get(i).toString());
            }
        }
    }
    }
    //if it reaches here then there is no solution
    return "The output actions do not lead to a goal state.";
}

//Create the main method
//"3,4;97;1,2;0,1;3,2,65;
public static void main(String[] args) {
    System.out.println(bf_Search("6,6;52;2,0;2,4,4,0,5,4;2,1,19,4,2,6,5,0,8;"));
    }

}

