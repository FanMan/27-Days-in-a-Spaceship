package entity;

public class Clock {
	private int day, hour, minute;
	
	public Clock() {
		day = 27;
		hour = 12;
		minute = 53;
	}
	
	public void updateTime() {
		minute--;
		if(minute == -1) {
			minute = 59;
			
			if(hour == 0) { hour = 23; day--;}
			else { hour--; }
		}
	}
	
	public int getDay() {
		return day;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
}
