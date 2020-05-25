import java.util.*;

/**
 * Node class to be used in a travel planer system where a user can specify time of departure, 
 * destination and place of departure to get a path.
 * 
 * @author Martin Jonasson
 *
 */
public class Station {

	/**
	 * Arbitrary large number to represent a large cost.
	 */
	public static final int LARGE_AMOUNT_OF_TIME = 43800;
	
	private String name;
	private ArrayList<Route> adjacentStations;
	private Coordinates coordinates;
	private int currentLine;
	private int currentCost;
	private Station previousStation;
	private boolean known;
	
	
	/**
	 * Constructor for Station
	 * @param x		int for longitude for the maps coordinates.
	 * @param y		int for the latitude for the maps coordinates.
	 * @param name	Name of the station
	 */
	
	public Station(int x, int y, String name){
		coordinates = new Coordinates(x, y);
		adjacentStations = new ArrayList<Route>();
		currentLine = -1;
		currentCost = LARGE_AMOUNT_OF_TIME;
		this.name = name;
		known = false;
		previousStation = null;
	}
	/**
	 * Resets information about the node such as what cost it took to get here, which line was taken to get here
	 * and from which station.
	 */
	
	public void reset(){
		currentLine = -1;
		currentCost = LARGE_AMOUNT_OF_TIME;
		known = false;
		previousStation = null;
	}
	
	
	public boolean isKnown(){
		return known;
	}

	public void setKnown(){
		known = true;
	}
	
	public int getCurrentCost(){
		return currentCost;
	}
	
	/**
	 * Updates information about the the total cost it took to get here if the new cost is lower than the old, 
	 * which line was taken to get to this station and from which station
	 * 
	 * 
	 * @param R 	Container class for information of the new cost, what line was taken to get to this station
	 * 				and from which station the line was taken.
	 */
	
	public void updateCurrentCost(RouteInfo R){
		if(R.getCost() < currentCost){
			currentCost = R.getCost();
			currentLine = R.getID();
			previousStation = R.getPreviousStation();
		}
	}
	
	public ArrayList<Route> getAdjacentStations(){
		return adjacentStations;
	}
	
	public Coordinates getCoordinates(){
		return coordinates;
	}
	
	public int getCurrentLine(){
		return currentLine;
	}
	
	public String getName(){
		return name;
	}
	
	public Station getPreviousStation(){
		return previousStation;
	}
	
	/**
	 * Adds a new route to adjacentStations or changes an existing route 
	 * in adjacentStations depending on if a route is already known.
	 * 
	 * @param cost		The cost associated with taking the new route to a station
	 * @param line		Information about departures
	 * @param station	Which station this route leads to
	 */
	
	public void addAdjacentStation(int cost, LineStation line, Station station){
		for(Route R : adjacentStations){
			if(R.getStation() == station){
				R.addLine(line);
				return;
			}
		}
		adjacentStations.add(new Route(cost, station, line));
	}
	
	
	public void enter(){
		currentCost = 0;
	}
	
}
