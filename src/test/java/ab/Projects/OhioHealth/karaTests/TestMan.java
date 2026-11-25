package ab.Projects.OhioHealth.karaTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ab.Projects.OhioHealth.kara.KataMain;

class TestMan {

	@ParameterizedTest
	@CsvSource({ "5,PM,5", "11,PM,11", "12,AM,12", "1,AM,13", "4,AM,16" })
	void mapsValidTimesOntoNightScale(int hour, String amPm, int expected) {
		assertEquals(expected, KataMain.toNightHour(hour, amPm));
	}

	@ParameterizedTest
	@CsvSource({ "4,PM", "12,PM", "5,AM", "11,AM", "0,PM", "13,AM", "5,XM" })
	void rejectsTimesOutsideTheShiftWindow(int hour, String amPm) {
		assertEquals(-1, KataMain.toNightHour(hour, amPm));
		assertFalse(KataMain.isStartTime(hour, amPm).isEmpty());
		assertFalse(KataMain.isBedTime(hour, amPm).isEmpty());
		assertFalse(KataMain.isEndTime(hour, amPm).isEmpty());
	}

	@Test
	void acceptsBoundaryTimes() {
		assertEquals("", KataMain.isStartTime(5, "PM"));
		assertEquals("", KataMain.isBedTime(11, "PM"));
		assertEquals("", KataMain.isEndTime(4, "AM"));
	}

	@Test
	void previouslyAcceptedInvalidTimesAreNowRejected() {
		// 11 AM start and 2 PM / noon bedtimes used to pass validation.
		assertFalse(KataMain.isStartTime(11, "AM").isEmpty());
		assertFalse(KataMain.isBedTime(2, "PM").isEmpty());
		assertFalse(KataMain.isBedTime(12, "PM").isEmpty());
	}

	@Test
	void sequencingFollowsTheNightOrder() {
		assertTrue(KataMain.isTheEventSequencingCorrect(5, "PM", 8, "PM"));
		assertTrue(KataMain.isTheEventSequencingCorrect(11, "PM", 12, "AM"));
		assertTrue(KataMain.isTheEventSequencingCorrect(12, "AM", 1, "AM"));
		assertTrue(KataMain.isTheEventSequencingCorrect(9, "PM", 3, "AM"));
		// 1 AM is after midnight, so midnight cannot come after it.
		assertFalse(KataMain.isTheEventSequencingCorrect(1, "AM", 12, "AM"));
		assertFalse(KataMain.isTheEventSequencingCorrect(8, "PM", 6, "PM"));
		assertFalse(KataMain.isTheEventSequencingCorrect(2, "AM", 10, "PM"));
	}

	@Test
	void calculatesPayment() {
		// 5PM-8PM at $12 + 8PM-11PM at $8
		assertEquals(60, KataMain.calculatePayment(5, 8, 11));
		// 6PM-11PM at $12 + 11PM-12AM at $8 + 12AM-2AM at $16
		assertEquals(100, KataMain.calculatePayment(6, 11, 14));
		// full shift, bedtime at 9PM: 4*12 + 3*8 + 4*16
		assertEquals(136, KataMain.calculatePayment(5, 9, 16));
		// bedtime at midnight
		assertEquals(116, KataMain.calculatePayment(5, 12, 14));
	}
}
