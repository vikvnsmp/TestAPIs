package com.example.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.IServices.IUserService;
import com.example.utility.UserUtility;
import com.example.vo.Error;
import com.example.vo.Response;
import com.example.vo.User;
import com.example.vo.UserRequest;

@Repository
public class UserService implements IUserService {

	private static Map<String, User> userMap = new HashMap<String, User>();
	private static Map<String, Integer> emailIdMap = new HashMap<String, Integer>();
	private static int counter = 1000;

	@Override
	public Response createUser(UserRequest userReq) {

		Response response = new Response();
		List<Error> valErrors = new ArrayList<Error>();
		boolean validationFailed = false;

		if (userReq.getEmail() != null && userReq.getEmail() != "") {
			if (emailIdMap.get(userReq.getEmail()) == null
					|| emailIdMap.get(userReq.getEmail()) == UserUtility.NOT_ACTIVE) {

				if (!UserUtility.validateDateFormat(userReq.getBirthDate())) {

					Error error = new Error();
					error.setCode("400");
					error.setField("birthDate");
					error.setMessage("Date format should be dd-MMM-yyyy");

					valErrors.add(error);
					response.setValErrors(valErrors);
					validationFailed = true;
				}
				if (!UserUtility.validateDateOfBirth(userReq.getBirthDate())) {

					Error error = new Error();
					error.setCode("400");
					error.setField("birthDate");
					error.setMessage("Birthdate cannot be a future date.");

					valErrors.add(error);
					response.setValErrors(valErrors);
					validationFailed = true;
				}
				if (!UserUtility.validateEmailId(userReq.getEmail())) {

					Error error = new Error();
					error.setCode("400");
					error.setField("Email");
					error.setMessage("Invalid Email Id.");

					valErrors.add(error);
					response.setValErrors(valErrors);
					validationFailed = true;
				}

				if (!validationFailed) {
					emailIdMap.put(userReq.getEmail(), UserUtility.ACTIVE);
					counter = counter + 1;
					User user = new User();

					user.setId("" + counter);
					user.setfName(userReq.getfName());
					user.setlName(userReq.getlName());

					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
					formatter.setLenient(false);
					Date dob = null;
					try {
						dob = formatter.parse(userReq.getBirthDate());
					} catch (ParseException e) {
						System.out.println(e.getMessage());
					}

					user.setBirthDate(dob);
					user.setPinCode(userReq.getPinCode());
					user.setEmail(userReq.getEmail());

					user.setActive(true);
					userMap.put(user.getId(), user);

					response.setResMsg("Record created.");
					response.setUserId(user.getId());
					response.setValErrors(valErrors);

				}
			} else if (emailIdMap.get(userReq.getEmail()) == UserUtility.ACTIVE) {

				Error error = new Error();
				error.setCode("400");
				error.setField("Email");
				error.setMessage("Email Id " + userReq.getEmail() + " already exists.");

				valErrors.add(error);
				response.setValErrors(valErrors);
				validationFailed = true;
			}
		} else {
			Error error = new Error();
			error.setCode("400");
			error.setField("Email");
			error.setMessage("Email Id required.");

			valErrors.add(error);
			response.setValErrors(valErrors);
			validationFailed = true;
		}

		if (validationFailed) {
			response.setResMsg("An error occured while record creation.");
			response.setUserId("");
		}

		return response;
	}

	@Override
	public Response updateUser(String id, int pinCode, String birthDate) {

		Response response = new Response();
		List<Error> valErrors = new ArrayList<Error>();
		boolean validationFailed = false;

		if (id != null && id != "" && userMap.get(id) != null) {

			if (!UserUtility.validateDateFormat(birthDate)) {

				Error error = new Error();
				error.setCode("400");
				error.setField("birthDate");
				error.setMessage("Date format should be dd-MMM-yyyy");

				valErrors.add(error);
				response.setValErrors(valErrors);
				validationFailed = true;
			}
			if (!UserUtility.validateDateOfBirth(birthDate)) {

				Error error = new Error();
				error.setCode("400");
				error.setField("birthDate");
				error.setMessage("Birthdate cannot be a future date.");

				valErrors.add(error);
				response.setValErrors(valErrors);
				validationFailed = true;
			}

			if (!validationFailed) {
				User user = userMap.get(id);
				user.setPinCode(pinCode);

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				formatter.setLenient(false);
				Date dob = null;
				try {
					dob = formatter.parse(birthDate);
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}

				user.setBirthDate(dob);
				userMap.remove(id);
				userMap.put(user.getId(), user);
			}

		} else {
			Error error = new Error();
			error.setCode("404");
			error.setField("id");
			error.setMessage("Provided user Id doesn't exsit.");

			valErrors.add(error);
			response.setValErrors(valErrors);
			validationFailed = true;
		}

		if (validationFailed) {
			response.setResMsg("An error occured while updating record.");
			response.setUserId(id);
		} else {
			response.setResMsg("Record updated.");
			response.setUserId(id);
			response.setValErrors(valErrors);
		}

		return response;
	}

	@Override
	public Response deleteUser(String id) {

		Response response = new Response();
		List<Error> valErrors = new ArrayList<Error>();

		if (id != null && id != "" && userMap.get(id) != null) {
			User user = userMap.get(id);
			user.setActive(false);
			emailIdMap.put(user.getEmail(), UserUtility.NOT_ACTIVE);
			userMap.put(user.getId(), user);

			response.setResMsg("Record deleted.");
			response.setUserId(id);
			response.setValErrors(valErrors);
		} else {
			response.setResMsg("An error occured while deleting record.");
			response.setUserId(id);

			Error error = new Error();
			error.setCode("404");
			error.setField("id");
			error.setMessage("Provided user Id doesn't exsit.");

			valErrors.add(error);
			response.setValErrors(valErrors);
			return response;
		}

		return response;
	}

}
