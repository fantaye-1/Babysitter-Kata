package ab.Projects.OhioHealth.kara;

/**
 * Babysitter payment rules.
 *
 * Times are given as a 12-hour clock hour (1-12) plus "AM"/"PM". A shift must fall inside
 * the 5:00PM - 4:00AM window. Internally hours are mapped to a single night scale where
 * 5PM = 5, midnight (12 AM) = 12 and 4AM = 16, so later times always have larger values.
 */
public class KataMain {

	/** Returns the night-scale hour (5..16) or -1 if the time is outside 5PM-4AM or malformed. */
	public static int toNightHour(int hour, String amPm) {
		if (hour < 1 || hour > 12 || amPm == null) {
			return -1;
		}
		if (amPm.equals("PM")) {
			// 12 PM is noon, 1-4 PM are before the earliest start.
			return (hour >= 5 && hour <= 11) ? hour : -1;
		}
		if (amPm.equals("AM")) {
			if (hour == 12) {
				return 12; // midnight
			}
			return hour <= 4 ? hour + 12 : -1;
		}
		return -1;
	}

	public static String isStartTime(int hour, String amPm) {
		return toNightHour(hour, amPm) < 0 ? "you can not start earlier than 5:00PM or later than 4:00AM" : "";
	}

	public static String isBedTime(int hour, String amPm) {
		return toNightHour(hour, amPm) < 0 ? "wrong input it can't be bedtime" : "";
	}

	public static String isEndTime(int hour, String amPm) {
		return toNightHour(hour, amPm) < 0 ? "you can not leave later than 4:00AM or before 5:00PM" : "";
	}

	/** True when the second time comes strictly after the first within the same night. */
	public static boolean isTheEventSequencingCorrect(int h1, String h1AmPm, int h2, String h2AmPm) {
		int first = toNightHour(h1, h1AmPm);
		int second = toNightHour(h2, h2AmPm);
		return first >= 0 && second >= 0 && first < second;
	}

	/**
	 * Payment for full hours, using night-scale hours (see {@link #toNightHour}):
	 * $12/h start to bedtime, $8/h bedtime to midnight, $16/h midnight to end.
	 */
	public static int calculatePayment(int startTime, int bedTime, int endTime) {
		int payment = (bedTime - startTime) * 12;

		if (bedTime <= 12) {
			if (endTime <= 12) {
				payment += (endTime - bedTime) * 8;
			} else {
				payment += (12 - bedTime) * 8;
				payment += (endTime - 12) * 16;
			}
		} else {
			payment += (endTime - bedTime) * 16;
		}
		return payment;
	}
}
