import java.util.*;

/**
 * Class to represent an edge between stations
 * @author Martin Jonasson
 *
 */

public class Route {
	
	private int cost;
	private Station destination;
	private ArrayList<LineStation> Lines;
	
	
	/**
	 * Constructor for Route
	 * 
	 * @param cost			The cost(time in minutes) it takes to traverse this route.
	 * @param destination	The station that this route/edge leads to
	 */
	public Route(int cost, Station destination){
		Lines = new ArrayList<LineStation>();
		this.cost = cost;
		this.destination = destination;
	}
	/**
	 * Constructor for Route, adds a LineStation to Lines at creation
	 * 
	 * @param cost			The cost(time in minutes) it takes to traverse this route.
	 * @param destination	The station that this route/edge leads to
	 * @param line			Class used to calculate added cost when traversing this route if the user 
	 * 						needs to wait for the next scheduled departure.
	 * 						
	 */
	
	public Route(int cost, Station destination, LineStation line){
		Lines = new ArrayList<LineStation>();
		this.cost = cost;
		this.destination = destination;
		Lines.add(line);
	}
	
	/**
	 * Adds a new LineStation to the collection Lines
	 * 
	 * @param Line			Class used to calculate added cost when traversing this route if the user 
	 * 						needs to wait for the next scheduled departure.
	 */
	
	public void addLine(LineStation Line){
		if(!Lines.contains(Line)){
			Lines.add(Line);
		}
	}
	
	/**
	 * Checks if Lines contain a LineStation with the given id
	 * 
	 * @param id	A number used to identify eventual LineStation in Lines
	 * @return		Returns true if a line is found that has the number id given as it's identification
	 */
	
	public boolean contains(int id){
		for(LineStation L : Lines){
			if(L.getID() == id)
				return true;
		}
		return false;
	}
	
	/**
	 * Traverses down this route updating information about which line it took to travel down this route,
	 * updates the cost of the destination station by adding together cost, currentCost and eventual time until next
	 * arrival if the line taken to the previous station is not part of this route
	 * 
	 * 
	 * @param id			The line that was taken to get to the previous station
	 * @param currentCost	The cost it took to get to the previous station
	 * @param hour			The hour the user is scheduled to arrive at the new station
	 * @param minute		The minute the user is scheduled to arrive at the new station
	 * @param from			Which station the user came from when it traversed down this route
	 */
	
	public void goToDestination(int id, int currentCost, int hour, int minute, Station from){
		if(contains(id)){
			destination.updateCurrentCost(new RouteInfo(currentCost + cost, id, from));
		} else {
			int c = Station.LARGE_AMOUNT_OF_TIME;
			LineStation current = null;
			for(LineStation L : Lines){
				if(L.getWeight(hour, minute) < c){
					c = L.getWeight(hour, minute);
					current = L;
				}
			}
			destination.updateCurrentCost(new RouteInfo(currentCost + c + cost, current.getID(), from));
		}
	}
	
	public Station getStation(){
		return destination;
	}
}
