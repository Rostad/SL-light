/**
 * Container class for updating the current cost of getting to a station X from another station Y by traversing a route
 * connecting X to Y
 * @author Martin Jonasson
 *
 */
public class RouteInfo {

	private int cost;
	private int id;
	private Station previousStation;
	
	public RouteInfo(int cost, int id, Station station){
		this.cost = cost;
		this.id = id;
		previousStation = station;
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getID(){
		return id;
	}
	
	public Station getPreviousStation(){
		return previousStation;
	}
}
