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
public class KataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    
    public KataServlet() {
        super();       
    }	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String startTimeAmPm = request.getParameter("inputStartAmPm");
		String bedTimeAmPm = request.getParameter("inputBedtimeAmPm");
		String endTimeAmPm = request.getParameter("inputEndAmPm");
		
		PrintWriter writer = response.getWriter();
		
		int startTime;
		int bedTime;
		int endTime;
		try {
			startTime = Integer.parseInt(request.getParameter("inputStartTime"));
			bedTime = Integer.parseInt(request.getParameter("inputBedtime"));
			endTime = Integer.parseInt(request.getParameter("inputEndTime"));
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			writer.println("<h1> Please enter start, bed and end hours (1-12).</h1>");
			writer.close();
			return;
		}
		
		if(KataMain.isStartTime(startTime,startTimeAmPm).isEmpty()) {
			if(KataMain.isBedTime(bedTime,bedTimeAmPm).isEmpty() && KataMain.isTheEventSequencingCorrect(startTime,startTimeAmPm,bedTime,bedTimeAmPm)) {
				if(KataMain.isEndTime(endTime,endTimeAmPm).isEmpty() && KataMain.isTheEventSequencingCorrect(bedTime,bedTimeAmPm,endTime,endTimeAmPm)) {
					
					int payment = KataMain.calculatePayment(toMilitaryTime(startTime,startTimeAmPm), toMilitaryTime( bedTime,bedTimeAmPm) , toMilitaryTime(endTime,endTimeAmPm));
					
					writer.println("<h1> payment: " + payment + "</h1>");	
					
				}else {
					writer.println("<h1> Error on end time: " + errorOrSequence(KataMain.isEndTime(endTime,endTimeAmPm), "end time must be after bedtime") + "</h1>");
				}
			}else {
				writer.println("<h1> Error on bed time: " + errorOrSequence(KataMain.isBedTime(bedTime,bedTimeAmPm), "bedtime must be after start time") + "</h1>");
			}
		}else {			
			writer.println("<h1> Error on start time : " + KataMain.isStartTime(startTime,startTimeAmPm) + "</h1>");
		}
		writer.close();
	}	

	public static int toMilitaryTime(int hour , String amPm) {
		return KataMain.toNightHour(hour, amPm);
	}

	private static String errorOrSequence(String validationError, String sequenceError) {
		return validationError.isEmpty() ? sequenceError : validationError;
	}
	
    
}
