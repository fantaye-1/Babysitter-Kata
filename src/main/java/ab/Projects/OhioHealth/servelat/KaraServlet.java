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
		int startTime = toMilitaryTime(request.getParameter("inputStartTime"),startTimeAmPm);
		String bedTimeAmPm = request.getParameter("inputBedtimeAmPm");
		int bedTime = toMilitaryTime(request.getParameter("inputBedtime"),bedTimeAmPm);
		String endTimeAmPm = request.getParameter("inputEndAmPm");
		int endTime = toMilitaryTime(request.getParameter("inputEndTime"),endTimeAmPm);
		
		PrintWriter writer = response.getWriter();
		
		if(KataMain.isStartTime(startTime)=="") {
			if(KataMain.isBedTime(startTime,bedTime) == "") {
				if(KataMain.isEndTime(startTime,bedTime,endTime) == "") {
					
					int payment = KataMain.calculatePayment(startTime,bedTime,endTime);
					writer.println("<h1>payment: " + payment + "</h1>");	
					
				}else {
					writer.println("<h1> Error: " + KataMain.isEndTime(startTime,bedTime,endTime)  + "</h1>");
				}
			}else {
				writer.println("<h1> Error: " + KataMain.isBedTime(startTime,bedTime) + "</h1>");
			}
		}else {			
			writer.println("<h1> Error: " + KataMain.isStartTime(startTime) + "</h1>");
		}
		writer.close();
	}
	
	//write test case
	//write readme file with how to run the program.
	
    public static int toMilitaryTime(String time , String amPm) {
    	int hour = Integer.parseInt(time);
    	if(amPm == "PM") {
    		if(hour < 12) {
    			hour += 12;
    		}
    	}else {// if AM
    		if(hour==12) {
    			hour +=12;
    		}
    	}    	
    	return hour;
    }
}
