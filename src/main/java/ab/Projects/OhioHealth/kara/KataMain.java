package ab.Projects.OhioHealth.kara;

public class KataMain {

	public static String isStartTime(int startTime) {
		String response = "";
		if( !isWorkingTime(startTime) ) {
			response = "Start time must be between 5pm and 4am";
		}		
		return response;		
	}

	public static String isBedTime(int startTime, int bedTime) {
		String response = "";
		if( !isWorkingTime(bedTime) ||  (bedTime > startTime)) {
			response = "bed time must be greater than start time and less than 4AM";
		}		
		return response;		
	}
	public static String isEndTime(int startTime, int bedTime, int endTime) {
		String response = "";
		if( !isWorkingTime(bedTime) ||  (endTime < startTime) || (endTime < bedTime)) {
			response = "end tim must be greater than start time and less than 4AM";
		}		
		return response;		
	}
	
	public static Boolean isWorkingTime(int hour) {
		boolean response = true;	

		if((hour > 4 ) && (hour < 17)) {
			response = false;
		}
		return response;
	}
	public static int calculatePayment(int startTime, int bedTime, int endTime) {

		int payment = 0;
		if(bedTime == 0) {
			if(endTime < 12) { //TODO <= mehonun
				payment = (endTime - startTime)*12;
			}else {
				payment = (bedTime-startTime)*12;
				payment += (endTime - 12)*16;
			}
			
		}else {
			payment = (bedTime - startTime)*12;
			
			if(bedTime < 12) {
				if(endTime < 12) { //TODO <= mehonun
					payment += (endTime - bedTime)*8;
				}else {
					payment +=(12 - bedTime)*8;
					payment += (endTime - 12)*16;
				}								
			}else {
				payment +=(endTime - bedTime)*16;
			}		
		}		
		return payment;				
	}
}
