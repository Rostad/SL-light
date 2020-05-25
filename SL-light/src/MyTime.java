
public class MyTime {

	private int hour;
	private int minute;
	
	public MyTime(int hour, int minute){
		this.hour = hour;
		this.minute = minute;
		if(minute > 60)
			updateMinute();
		if(hour > 24)
			updateHour();
	}
	
	public MyTime getTimePlusCost(int cost){
		int newMinute = minute + cost;
		return new MyTime(hour, newMinute);
	}
	
	
	private void updateMinute(){
		int hoursToAdd = minute / 60;
		minute = minute % 60;
		hour += hoursToAdd;
	}
	
	private void updateHour(){
		hour =- 24;
	}
	
	public int getHour(){
		return hour;
	}
	public int getMinute(){
		return minute;
	}
}
