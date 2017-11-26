package com.example.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserUtility {

	public static Integer ACTIVE = 1;
	public static Integer NOT_ACTIVE = 2;

	/* To validate date format */
	public static boolean validateDateFormat(String dateToValidate) {
		boolean result = false;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		// To make strict date format validation
		formatter.setLenient(false);
		Date parsedDate = null;
		try {
			parsedDate = formatter.parse(dateToValidate);
			System.out.println("++validated DATE TIME ++" + formatter.format(parsedDate));
			result = true;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	/* To validate provided date as date of birth is not a future date */
	public static boolean validateDateOfBirth(String dateToValidate) {
		boolean result = false;
		String PATTERN = "dd-MMM-yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		dateFormat.applyPattern(PATTERN);
		String date1 = dateFormat.format(Calendar.getInstance().getTime());

		Date currentDate = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		formatter.setLenient(false);
		Date dob = null;
		try {
			dob = formatter.parse(dateToValidate);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		if (dateToValidate != null && dateToValidate != ""
				&& (dateToValidate.equals(date1) || (dob != null && dob.before(currentDate)))) {
			result = true;
		}
		return result;
	}

	/* To validate email id */
	public static boolean validateEmailId(String inputEmailId) {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		boolean result = false;
		result = inputEmailId.matches(EMAIL_REGEX);

		return result;
	}

}
