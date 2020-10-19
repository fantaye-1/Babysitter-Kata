package ab.Projects.OhioHealth.servelat;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ab.Projects.OhioHealth.kara.KataMain;

/**
 * Servlet implementation class KaraServelet
 */
@WebServlet("/KaraServlet")
public class KaraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    
    public KaraServlet() {
        super();       
    }	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String startTimeAmPm = request.getParameter("inputStartAmPm");
		int startTime  = Integer.parseInt(request.getParameter("inputStartTime"));
		
		String bedTimeAmPm = request.getParameter("inputBedtimeAmPm");
		int bedTime = Integer.parseInt(request.getParameter("inputBedtime"));
		
		String endTimeAmPm = request.getParameter("inputEndAmPm");
		int endTime = Integer.parseInt(request.getParameter("inputEndTime"));
		
		PrintWriter writer = response.getWriter();
		
		if(KataMain.isStartTime(startTime,startTimeAmPm)=="") {
			if(KataMain.isBedTime(bedTime,bedTimeAmPm) == "" && KataMain.isTheEventSequencingCorrect(startTime,startTimeAmPm,bedTime,bedTimeAmPm)) {
				if(KataMain.isEndTime(endTime,endTimeAmPm) == "" && KataMain.isTheEventSequencingCorrect(bedTime,bedTimeAmPm,endTime,endTimeAmPm)) {
					
					int payment = KataMain.calculatePayment(toMilitaryTime(startTime,startTimeAmPm), toMilitaryTime( bedTime,bedTimeAmPm) , toMilitaryTime(endTime,endTimeAmPm));
					
					writer.println("<h1> payment: " + payment + "</h1>");	
					
				}else {
					writer.println("<h1> Error on end time: " + KataMain.isEndTime(endTime,endTimeAmPm)  + "</h1>");
				}
			}else {
				writer.println("<h1> Error on bed time: " + KataMain.isBedTime(bedTime,bedTimeAmPm) + "</h1>");
			}
		}else {			
			writer.println("<h1> Error on start time : " + KataMain.isStartTime(startTime,startTimeAmPm) + "</h1>");
		}
		writer.close();
	}	

	public static int toMilitaryTime(int hour , String amPm) {    	
    	if(amPm.equals("AM")){
    		if(hour < 5) {
    			hour += 12;
    		}
    	}   	
    	return hour;
    }
	
    
}
