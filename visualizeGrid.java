package ai_project;

public class CoastGuard {

	public static String[][] GenGrid(String s) {

		String[] str = s.split(";");
		int gridSizeM = Integer.parseInt(str[0].substring(0, str[0].indexOf(",")));
		int gridSizeN = Integer.parseInt(str[0].substring(str[0].indexOf(",") + 1));

		String[][] grid = new String[gridSizeM][gridSizeN];
		
		int coastGuardBoatCapacity = Integer.parseInt(str[1]);
		
		int coastGuardBoatX = Integer.parseInt(str[2].substring(0, str[2].indexOf(",")));
		int coastGuardBoatY = Integer.parseInt(str[2].substring(str[2].indexOf(",") + 1));
		
		grid[coastGuardBoatX][coastGuardBoatY] = "CoastGuardBoat";  // need to add more info for CoastGuardBoat
		
		String[] stations = str[3].split(",");
		for(int i = 0 ; i < stations.length-1 ; i+=2) { //assign stations in corresponding locations
			grid[Integer.parseInt(stations[i])][Integer.parseInt(stations[i+1])] = "Station";
		}
		
		String[] ships = str[4].split(",");
		for(int j = 0; j < ships.length - 2 ; j+=3 ) { //assign ships in corresponding locations
			grid[Integer.parseInt(ships[j])][Integer.parseInt(ships[j+1])] = "Ship;"+ ships[j+2];
		}
		
		
		
		for (int i = 0; i < grid.length; i++) { //print grid
	         for (int j = 0; j < grid[i].length; j++) { 
	            System.out.print(grid[i][j] + " ");
	         }
	         System.out.println(); 
	      }
		
		
		return grid;

	}
	
	public static void main (String [] args) {
		String grid0 = "5,5;69;3,3;0,0,0,1,1,0;0,3,78,1,2,2,1,3,14,4,4,9;";
		GenGrid(grid0);
		
	}

}
