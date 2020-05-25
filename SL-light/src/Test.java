import java.util.ArrayList;

public class Test {

	private ArrayList<Station> Stations = new ArrayList<Station>();
	
	public static void main(String[] args){
		
		Test test = new Test();
		test.demonstration();
		
	}
	
	public void demonstration(){
		
		MyTime time = new MyTime(12, 50);
		
		
		LineStation SouthFirst = new LineStation(101, 5, 23, new int[]{17, 33, 43});
		LineStation SouthSecond = new LineStation(202, 8, 16, new int[]{23, 43});
		LineStation NorthFirst = new LineStation(202, 8, 16, new int[]{28, 48});
		LineStation WestFirst = new LineStation(303, 8, 24, new int[]{05, 20});
		
		
		Station West = new Station(54, 26, "Västra skogen");
		Station South = new Station(24, 26, "Södra skogen");
		Station North = new Station(77, 54, "Norra skogen");
		Station East = new Station(102, 66, "Östra Skogen");
		Station VeryFarAway= new Station(500, 500, "Sjottaheiti");
		Stations.add(West);
		Stations.add(South);
		Stations.add(North);
		Stations.add(East);
		Stations.add(VeryFarAway);
		
		South.addAdjacentStation(12, SouthFirst, West);
		South.addAdjacentStation(20, SouthSecond, North);
		North.addAdjacentStation(120, NorthFirst, VeryFarAway);
		West.addAdjacentStation(20, WestFirst, East);
		South.enter();
		printRoutes(South, time);
		printRoutes(West, time);
		printRoutes(North, time);
		
			
	}
	
	public void printRoutes(Station origin, MyTime time){
		ArrayList<Route> routes = origin.getAdjacentStations();
		MyTime newTime = time.getTimePlusCost(origin.getCurrentCost());
		if(routes.size() == 0)
			return;
		for(Route R : routes){
			R.goToDestination(origin.getCurrentLine(), origin.getCurrentCost(), newTime.getHour(), newTime.getMinute(), origin);
			Station current = R.getStation();
			System.out.println(current.getName() + " " + current.getCurrentCost() + " " + current.getCurrentLine()+ " " + current.getPreviousStation().getName());
		}
	}
}
