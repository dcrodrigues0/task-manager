package br.com.monitor.utils;

public class ConvertionUtils {

	
	public String convertMilisecondsToMinutes(long time) {
        long milliseconds = time;
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
        String millisecondsInMinutes = minutes + " Minutes";
        return millisecondsInMinutes;
	}
	
	public String convertMilisecondsToSeconds(long time) {
        long milliseconds = time;
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
        String millisecondsInMinutes = seconds + " Seconds";
        return millisecondsInMinutes;
	}
	
}
