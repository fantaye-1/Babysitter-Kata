package ab.Projects.OhioHealth.kara;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class KataMain {

	public static void main(String[] args) {	
		// TODO leaves no later than 4:00AM
		// TODO gets paid $12/hour from start-time to bedtime
		// TODO gets paid $8/hour from bedtime to midnight
		// TODO gets paid $16/hour from midnight to end of job
		// TODO gets paid for full hours (no fractional hours)
		
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter"
				+ "\n 0: to clockin "
				+ "\n 1: to clockout"
				+ "\n 2: to to calculate payment");		
		int selected = scan.nextInt();
		
		
		switch(selected) {
		case 0: getStartTime(selected);
				break;
		case 1: getEndTime(selected);
				break;
		case 2: System.out.println("Enter start time");
				int start = scan.nextInt();
				System.out.println("Enter bedtime");
				int bedtime = scan.nextInt();
				System.out.println("Enter end time");
				int end = scan.nextInt();
				System.out.println(calculatePayment(start,bedtime,end));
				break;
		default:
			System.out.println("Wrong entery try later....");
		}
		
		scan.close();
		
	}
	
	private static int calculatePayment(int startTime, int bedTime, int endTime) {

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
	
	private static void getEndTime(int select) {

		String logOutTime = timeFormateAMAndPM();
		String houre = logOutTime.substring(0,2);
		String amPm= logOutTime.substring(6);

		if(amPm.equals("AM") && ((Integer.parseInt(houre) > 4 ) && (Integer.parseInt(houre) < 12)) ) {
			System.out.println("you can not leaves no later than 4:00AM" + 
					"\n Action will be reported");
		}else if (amPm.equals("PM") && ((Integer.parseInt(houre) < 5 ) || (Integer.parseInt(houre)==12)) ) {// am
			System.out.println("You can't end or start at this time:");
		}else {
			System.out.println("you have successfully loggedOut at "+ logOutTime);
		}
	}
	// clock in time make a button and check if it is earlier than 5:00pm
	public static void getStartTime(int select) {
//		LocalTime t = LocalTime.now();
//		System.out.println(t.getHour()-12);
		String loginTime = timeFormateAMAndPM();
		String houre = loginTime.substring(0,2);
		String amPm= loginTime.substring(6);
		//System.out.println(loginTime + "  houre: " +houre +" amPm "+amPm);
		
		// TODO starts no earlier than 5:00PM
		if(amPm.equals("PM") && ((Integer.parseInt(houre) < 5 ) || (Integer.parseInt(houre) == 12)) ) {
			System.out.println("you can not starts no earlier than 5:00PM");
		}else if (amPm.equals("AM") && ((Integer.parseInt(houre) > 4 ) && (Integer.parseInt(houre)< 11)) ) {// am
			System.out.println("You can't start at this time:");
		}else {
			System.out.println("you have successfully logged in at "+ loginTime);
		}
	}
	
	public static String timeFormateAMAndPM() {
    	//Displaying current time in 12 hour format with AM/PM
    	DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
    	String dateString = dateFormat.format(new Date()).toString();//.substring(6);
    	//System.out.println("Current time in AM/PM: "+dateString);
    	return dateString;
	}

}
