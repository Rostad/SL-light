/**
 * This class contains information about scheduling for when and which vehicle arrives and leaves at a station
 * and methods to calculate the time in minutes until the next arrival.
 * 
 * @author Martin Jonasson
 *
 */
public class LineStation {

	
	private int startingHour;
	private int lastHour;
	private int[] timeTable;
	private int identification;
	
	/**
	 * Constructor for LineStation
	 * 
	 * @param identification 	Line identification, used to tell that different 
	 * 							LineStations are implicitly linked by the Route Class
	 * @param startingHour		An int between 0 - 24 specifying the hour of the first arrival
	 * @param lastHour			An int between 0 - 24 specifying the hour of the last arrival
	 * @param timeTable			An array of ints between 0 - 60 specifying what minute an arrival happens
	 */
	public LineStation(int identification, int startingHour, int lastHour, int[] timeTable){
		if(!(0 <= startingHour && startingHour <= 24)){
			throw new IllegalArgumentException();
		}
		if(!(0 <= lastHour && lastHour <= 24)){
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < timeTable.length; i++){
			if(!(0 <= timeTable[i] && timeTable[i] <= 60))
				throw new IllegalArgumentException();
		}
		this.identification = identification;
		this.startingHour = startingHour;
		this.lastHour = lastHour;
		this.timeTable = timeTable;
	}
	
	/**
	 * Checks if the line is still active at the time specified.
	 * 
	 * 
	 * @param hour 		The hour a user is scheduled to be present at a station
	 * @param minutes	The minute a user is scheduled to be present at a station
	 * @return			Returns true if the line is still running at the scheduled time a user is at the station
	 */
	private Boolean isRunning(int hour, int minute){
		if(startingHour < hour && (hour < lastHour || hour == lastHour && minute < timeTable[timeTable.length - 1])){
			return true;
		}
		return false;
	}
	/**
	 * Calculates and returns the time until the first scheduled departure if the line has stopped running for the day
	 * or hasn't started for the day.
	 * 
	 * @param hour		The hour a person is scheduled to be present at a station
	 * @param minutes	The minute a user is scheduled to be present at a station
	 * @return			Returns the time in minutes until the time of the first arrival of the day
	 */
	
	private int timeUntilFirstDeparture(int hour, int minutes){
		if(hour <= startingHour && minutes < timeTable[0]){
			return ((startingHour - hour) * 60) + timeTable[0] - minutes;
		} else if( hour < startingHour && minutes > timeTable[timeTable.length - 1]){
			return ((startingHour - hour - 1) * 60) + (60 - minutes + timeTable[0]);
		} else if( hour > lastHour && minutes < timeTable[0]){
			return ((24 - hour + startingHour) * 60) + timeTable[0] - minutes;
		} else if( hour >= lastHour && minutes > timeTable[timeTable.length - 1]){
			return ((24 - hour - 1 + startingHour) * 60) + (60 - minutes + timeTable[0]);
		}
		return 0;
		
		
		
		
	}
	
	
	/**
	 * Calculate and returns the amount of minutes until the next arrival during the hour.
	 * 
	 * @param minutes	The minute the user is scheduled to be present at a station
	 * @return			Returns the time in minutes until the next arrival
	 */
	private int getTimeUntilNextArrival(int minutes){
		for(int i = 0; i < timeTable.length; i++){
			if(minutes <= timeTable[i])
				return timeTable[i] - minutes;
		}
		return minutes;
	}
	/**
	 * Calculates and returns the time until the next scheduled departure.
	 * 
	 * @param hour 		The hour a user is scheduled to be present at a station
	 * @param minutes	The minute a user is scheduled to be present at a station
	 * @return
	 */
	public int getWeight(int hour, int minutes){
		if(isRunning(hour, minutes)){
			if(minutes > timeTable[timeTable.length - 1]){
				int timeUntilNextHour = 60 - minutes;
				return timeTable[0] + timeUntilNextHour;
			} else {
				return getTimeUntilNextArrival(minutes);
			}
		}
		return timeUntilFirstDeparture(hour, minutes);
	}
	
	public int getID(){
		return identification;
	}
}
