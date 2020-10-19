package ab.Projects.OhioHealth.karaTests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import ab.Projects.OhioHealth.kara.KataMain;

public class TestMan {
	

	@Test
	public void testIsStartTime() {
		 assertEquals("",KataMain.isStartTime(5,"PM"));  
	}
		
		
	@Test
	public void testIsBedTime() {
		 assertEquals("",KataMain.isBedTime(11,"PM"));  
	}
		
	
	@Test
	public void testIsEndTime() {
		 assertEquals("",KataMain.isEndTime(3,"AM"));  
	}
		
	
	@Test
	public void testIsTheEventSequencingCorrect() {
		 assertEquals(60,KataMain.calculatePayment(5,8,11));  
	}
	@Test  
    public void testCalculatePayment(){  
        assertEquals(60,KataMain.calculatePayment(5,8,11));  
        assertEquals(100,KataMain.calculatePayment(6,11,14));  
    }  
}
