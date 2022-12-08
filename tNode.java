import java.util.Hashtable;

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
	int retrieve = 0;

    //hashtable takes the coordinates of the the ship as the key and an integer as the value representing the number of its passengers
    public Hashtable<String, Integer> ships = new Hashtable<String, Integer>();
    public Hashtable<String, Integer> blackboxes = new Hashtable<String, Integer>();
	//stations is a hashtable with String as a key and a boolean as a value
    public Hashtable<String, Boolean> stations = new Hashtable<String, Boolean>();

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
        this.blackboxes.put(b0xString, health);
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
