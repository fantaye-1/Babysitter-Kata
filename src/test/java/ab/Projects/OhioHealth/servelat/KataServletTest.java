package ab.Projects.OhioHealth.servelat;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KataServletTest {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private StringWriter output;

	@BeforeEach
	void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		output = new StringWriter();
		when(response.getWriter()).thenReturn(new PrintWriter(output));
	}

	private String post(String start, String startAmPm, String bed, String bedAmPm, String end, String endAmPm) throws Exception {
		when(request.getParameter("inputStartTime")).thenReturn(start);
		when(request.getParameter("inputStartAmPm")).thenReturn(startAmPm);
		when(request.getParameter("inputBedtime")).thenReturn(bed);
		when(request.getParameter("inputBedtimeAmPm")).thenReturn(bedAmPm);
		when(request.getParameter("inputEndTime")).thenReturn(end);
		when(request.getParameter("inputEndAmPm")).thenReturn(endAmPm);
		new KataServlet().doPost(request, response);
		return output.toString();
	}

	@Test
	void calculatesPaymentForValidShift() throws Exception {
		String body = post("6", "PM", "11", "PM", "2", "AM");
		assertTrue(body.contains("payment: 100"), body);
	}

	@Test
	void reportsInvalidStartTime() throws Exception {
		String body = post("11", "AM", "11", "PM", "2", "AM");
		assertTrue(body.contains("Error on start time"), body);
	}

	@Test
	void explainsOutOfOrderBedtime() throws Exception {
		String body = post("9", "PM", "7", "PM", "2", "AM");
		assertTrue(body.contains("bedtime must be after start time"), body);
	}

	@Test
	void missingHourIsABadRequestInsteadOfAServerError() throws Exception {
		String body = post("", "PM", "11", "PM", "2", "AM");
		verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
		assertTrue(body.contains("Please enter"), body);
	}
}
