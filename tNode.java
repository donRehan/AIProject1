import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Iterator;

//3,4 CoastGuardCarry=97 Boat=1,2 Station=0,1 Ship=3,2,Passengers=65;
//always subtract 1 from the co ordinates as the documentation is wrong

public class tNode {
    //store the length and width of the grid
    public int[] length_width = new int[2];
    //tuple to store the coordinates of the bot
    public int[] co_ordinates = new int[2];
    String[] Split;
    int max_passengers; 
    int passengers_carried = 0;
    String sequence = "";
	int boxescarried = 0;
    int dead = 0;

    //hashtable takes the coordinates of the the ship as the key and an integer as the value representing the number of its passengers
    public Hashtable<String, Integer> ships = new Hashtable<String, Integer>();
    public Hashtable<String, Integer> blackboxes = new Hashtable<String, Integer>();
	//stations is a hashtable with String as a key and a boolean as a value
    public Hashtable<String, Boolean> stations = new Hashtable<String, Boolean>();

    public void retrieve(){
        //if the coordinates are return a blackbox then remove it from the blackbox hashmap
        String coordinates = this.co_ordinates[0] + "," + this.co_ordinates[1];
        if (this.blackboxes.containsKey(coordinates)) {
            this.blackboxes.remove(coordinates);
            this.boxescarried++;
            this.sequence += "retrieve,";
        }
    }

    public boolean reduceMap() {
        if (this.ships.isEmpty()) {
            return false;
        }
        Iterator<String> it = this.ships.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            int value = this.ships.get(key);
            if (value == 1) {
                this.blackboxes.put(key, 19);
                this.dead++;
                it.remove();
            } else {
                this.ships.replace(key, value - 1);
            }
        }
        return true;
    }

    //boolean reduceBox() that uses an iterator to reduce the value of each blackbox by 1 and removes it if the value is 0
    public boolean reduceBox()
    {
        if(this.blackboxes.isEmpty())
        {
            return false;
        }
        Iterator<String> it = this.blackboxes.keySet().iterator();
        while(it.hasNext())
        {
            String key = it.next();
            int value = this.blackboxes.get(key);
            if(value == 1)
            {
                it.remove();
            }
            else
            {
                this.blackboxes.replace(key, value - 1);
            }
        }
        return true;
    }
    

    public ArrayList expand() {
        //create a new arraylist to store the expanded nodes
        ArrayList<tNode> expanded = new ArrayList<tNode>();
        //up , Check that the y co-ordinate is greater than 0
        if (this.co_ordinates[0] > 0) {
            //create a new node
            tNode up = new tNode(this.toString());
            //decrement the y co-ordinate
            up.co_ordinates[0]--;
            up.sequence = this.sequence + "up,"; 
            if(up.reduceMap()){
                //just reduces the number of ship's passengers if possible	
            }
            if(up.reduceBox()){
        
            }
            up.performAction();
            //add the node to the expanded nodes
            //System.out.println(up.co_ordinates[0] + ""+ up.sequence);
            expanded.add(up);
        }
        //down , Check that the y co-ordinate is less than the height
        if (this.co_ordinates[0] < this.length_width[0] - 1) {
            //create a new node
            tNode down = new tNode(this.toString());
            //increment the y co-ordinate
            down.co_ordinates[0]++;
            down.sequence =  this.sequence +"down,"; 
            if(down.reduceMap()){
                //just reduces the number of ship's passengers if possible	
            }
            if(down.reduceBox()){
        
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
        if (this.co_ordinates[1] > 0) {
            //create a new node
            tNode left = new tNode(this.toString());
            //decrement the x co-ordinate
            left.co_ordinates[1]--;
            left.sequence = this.sequence + "left,"; 
            //if passengers are dead. then cancel going in that direction.
            if(left.reduceMap()){
                //just reduces the number of ship's passengers if possible	
            }
            if(left.reduceBox()){
                //reduce the number of blackbox's health if possible
            }
            left.performAction();
            //System.out.println(left.co_ordinates[1] + ""+ left.sequence);
            //add the node to the expanded nodes
            expanded.add(left);
        }
        
        //right , Check that the x co-ordinate is less than the width
        if (this.co_ordinates[1] < this.length_width[1] - 1) {
            //create a new node
            tNode right = new tNode(this.toString());
            //increment the x co-ordinate
            right.co_ordinates[1]++;
            right.sequence = this.sequence + "right,"; 
            if(right.reduceMap()){
                //just reduces the number of ship's passengers if possible	
            }
            if(right.reduceBox()){
        
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

    public void carrypassengers(String shipString){
        int vall = this.ships.get(shipString);
        int diff = this.max_passengers - this.passengers_carried;
        if(diff >= vall){
            this.passengers_carried += vall;
            //remove this ship from the hashmap
            this.ships.remove(shipString);
            //add a blackbox to the blackbox hashmap
            this.blackboxes.put(shipString, 19);
        }
        else{
            //replace the value by value - the diff
            this.ships.put(shipString, vall - diff);
            this.passengers_carried = this.max_passengers;
        }
}

public void pickup(){
    String shipString = this.co_ordinates[0] + "," + this.co_ordinates[1];
    //if I am on a ship then carry the passengers
    if(this.ships.containsKey(shipString) == true){
		this.carrypassengers(shipString);
		this.sequence += "pickup,";
    }
}

public void drop(){
	String stationString = this.co_ordinates[0] + "," + this.co_ordinates[1];
	//if I am on a station then drop off the passengers
	if(this.stations.containsKey(stationString) == true){
		this.passengers_carried = 0;
		this.sequence += "drop,";
	}
}

//add this into the main code
public void performAction()
{
	//check if I am in a station
	this.drop();
	//Check if the node is containing a ship
	this.pickup();
	//Check if the node is in a blackbox cell
	this.retrieve();
}

    //constructor that takes a string as an argument 
    public tNode(String s) {

        //split by ;
    this.Split = s.split(";");
    this.max_passengers = Integer.parseInt(Split[1]); 
    //width then height
    this.length_width[0] = Integer.parseInt(Split[0].split(",")[1]);
    this.length_width[1] = Integer.parseInt(Split[0].split(",")[0]);
    //System.out.println(this.length_width[0] + " " + this.length_width[1]);
	
	//code to store the ship locations 


    //Check if I can access element number 5 from the Split array without error
    //if I can then I will take the value in there and put it into passengers_carried
	//could be optimized way better
    if(Split.length > 5){
        this.passengers_carried = Integer.parseInt(Split[5]);
    }
	

	String[] stl0c = Split[3].split(",");
    //loop through shiploc increment by 3 as each ship has 3 values associated with it
    for (int i = 0; i < stl0c.length; i += 2) {
        //create a new array to store the co-ordinates of the ship
        int[] l0c = new int[2];
        //store the co-ordinates of the ship
        l0c[0] = Integer.parseInt(stl0c[i]);
        l0c[1] = Integer.parseInt(stl0c[i + 1]);
        //Convert ship array to a string
        String b0xString = l0c[0] + "," + l0c[1];
        boolean health = true;
        this.stations.put(b0xString, health);
    }

	//2. To string would be the 6th element
	if(Split.length > 6){
	if(!(Split[6].equals(" "))){

	//Maybe change it later to character check
    String[] b0xl0c = Split[6].split(",");
    //loop through shiploc increment by 3 as each ship has 3 values associated with it
    for (int i = 0; i < b0xl0c.length; i += 3) {
        //create a new array to store the co-ordinates of the ship
        int[] l0c = new int[2];
        //store the co-ordinates of the ship
        l0c[0] = Integer.parseInt(b0xl0c[i]);
        l0c[1] = Integer.parseInt(b0xl0c[i + 1]);
        //Convert ship array to a string
        String b0xString = l0c[0] + "," + l0c[1];
        //store the number of passengers in the ship
        int health = Integer.parseInt(b0xl0c[i + 2]);
        //add the ship and its passengers to the hashtable
        this.blackboxes.put(b0xString, health);
        //System.out.println(ship[0] + " " + ship[1] + " " + passengers);
    }
	}

	}

    //from the 2nd elemnt in split add into this co-ordinates
    this.co_ordinates[0] = Integer.parseInt(Split[2].split(",")[0]);
    this.co_ordinates[1] = Integer.parseInt(Split[2].split(",")[1]);
    //System.out.println(this.co_ordinates[0] + " " + this.co_ordinates[1]);
    //from the 4th element in split add into this ships
    //split by ,
    //check if String(Split[4]) is not equal to " "
	//System.out.println(Split[4]);
	if(!(Split[4].equals(" "))){
	//Maybe change it later to character check
    String[] shipl0c = Split[4].split(",");
    //loop through shiploc increment by 3 as each ship has 3 values associated with it
    for (int i = 0; i < shipl0c.length; i += 3) {
        //create a new array to store the co-ordinates of the ship
        int[] ship = new int[2];
        //store the co-ordinates of the ship
        ship[0] = Integer.parseInt(shipl0c[i]);
        ship[1] = Integer.parseInt(shipl0c[i + 1]);
        //Convert ship array to a string
        String shipString = ship[0] + "," + ship[1];
        //store the number of passengers in the ship
        int passengers = Integer.parseInt(shipl0c[i + 2]);
        //add the ship and its passengers to the hashtable
        this.ships.put(shipString, passengers);
        //System.out.println(ship[0] + " " + ship[1] + " " + passengers);
    }
	}
    }


    //method to return a string representing node elements
	public String toString(){
    //3,4 CoastGuardCarry=97 Boat=1,2 Station=0,1 Ship=3,2,Passengers=65;
    String s = "";
    s += this.length_width[1] + "," + this.length_width[0] + ";";
    s  += this.Split[1] + ";";
    s += this.co_ordinates[0] + "," + this.co_ordinates[1] + ";";
    s   += this.Split[3] + ";";
	if(this.ships.size() > 0){
    for (String ship : this.ships.keySet()) {
        s += ship.charAt(0) + "," + ship.charAt(2) + "," + this.ships.get(ship) + ",";
        }
	}
	else{
	s += " ;";
	}
    //s += Blackboxes <<==
	//Check if split is more than 5 before adding passengers carried
	if(this.Split.length > 5){
    s += this.Split[5] + ";";
	}
    return s;

    }

}
