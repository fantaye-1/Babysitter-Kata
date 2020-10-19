
	package ab.Projects.OhioHealth.kara;

	public class KataMain {	
		
		public static String isStartTime(int hour,String amPm) {
			String response ="";
			if(amPm.equals("PM") && ((hour < 5 ) || (hour == 12)) ) {
				response = "you can not starts no earlier than 5:00PM";
			}else if (amPm.equals("AM") && ((hour > 4 ) && (hour < 11))) {// am
				response = "You can't start at this time:";
			}
			return response;
		}
		
		public static String isBedTime(int hour, String amPm) {
			String response ="";
			if(amPm.equals("PM") && ((hour < 5 ) && (hour >= 12)) ) {
				response = "wrong input it can't be bedtime";
			}else if (amPm.equals("AM") && ((hour > 4 ) && (hour < 12))) {// am
				response = "wrong input it can't be bedtime";
			}
			return response;	
		}
		
		public static String isEndTime(int hour, String amPm) {
			String response = "";	

			if(amPm.equals("AM") && ((hour > 4 ) && (hour < 12)) ) {
				response ="you can not leaves no later than 4:00AM" + "\n Action will be reported";
			}else if (amPm.equals("PM") && ((hour < 5 ) || (hour >= 12)) ) {// am
				response="You can't end or start at this time:";
			}
			return response;
		}

		public static boolean isTheEventSequencingCorrect(int h1, String h1AmPm, int h2, String h2AmPm) {
			boolean isCorrect =  false;
			//pm  //am
			if(h1AmPm.equals(h2AmPm) && (h1 < h2 || h1 == 12) ) {
				isCorrect = true;
			} else if((h1AmPm.equals("PM") && h2AmPm.equals("AM")) && (h1 > h2 || h2 == 12 )) {
				isCorrect = true;
			}			
			return isCorrect;			
		}	
		
		public static int calculatePayment(int startTime, int bedTime, int endTime) {
			System.out.println(startTime + "  " + bedTime + " "+ endTime);
			int payment = 0;			

				payment = (bedTime - startTime)*12;
				
				if(bedTime <= 12) { 
					if(endTime <= 12) { 
						payment += (endTime - bedTime)*8;
					}else {
						payment += Math.abs( (12 - bedTime))*8;
						payment +=Math.abs( (endTime - 12))*16;
					}								
				}else {
					payment +=Math.abs((endTime - bedTime))*16;
				}				
			
			return payment;					
		}
	}

