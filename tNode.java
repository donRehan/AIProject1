import java.util.Hashtable;

//to be cleaned before submission

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

    //hashtable takes the coordinates of the the ship as the key and an integer as the value representing the number of its passengers
    public Hashtable<String, Integer> ships = new Hashtable<String, Integer>();
    
    //constructor that takes a string as an argument
    public tNode(String s) {

        //split by ;
    this.Split = s.split(";");
    this.max_passengers = Integer.parseInt(Split[1]); 
    //width then height
    this.length_width[0] = Integer.parseInt(Split[0].split(",")[1]);
    this.length_width[1] = Integer.parseInt(Split[0].split(",")[0]);
    //System.out.println(this.length_width[0] + " " + this.length_width[1]);

    //from the 2nd elemnt in split add into this co-ordinates
    this.co_ordinates[0] = Integer.parseInt(Split[2].split(",")[0]);
    this.co_ordinates[1] = Integer.parseInt(Split[2].split(",")[1]);
    //System.out.println(this.co_ordinates[0] + " " + this.co_ordinates[1]);
    //from the 4th element in split add into this ships
    //split by ,
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


    //method to return a string representing node elements
    public String toString(){
    //3,4 CoastGuardCarry=97 Boat=1,2 Station=0,1 Ship=3,2,Passengers=65;
    String s = "";
    s += this.length_width[1] + "," + this.length_width[0] + ";";
    s  += this.Split[1] + ";";
    s += this.co_ordinates[0] + "," + this.co_ordinates[1] + ";";
    s   += this.Split[3] + ";";
    for (String ship : this.ships.keySet()) {
        s += ship.charAt(0) + "," + ship.charAt(2) + "," + this.ships.get(ship) + ",";
        }
    //s += Blackboxes <<==
    return s;

    }

}
