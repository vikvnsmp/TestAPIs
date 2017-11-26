package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.IServices.IUserService;
import com.example.vo.Response;
import com.example.vo.UserRequest;

@RestController
@RequestMapping("/TestAPIs")
public class UserController {

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseBody
	public Response createUser(@RequestBody UserRequest userReq) {
		Response response = userService.createUser(userReq);
		return response;
	}

	@RequestMapping(value = "/updateUser/{id}/{pinCode}/{birthDate}", method = RequestMethod.PUT)
	@ResponseBody
	public Response updateUser(@PathVariable String id, @PathVariable int pinCode, @PathVariable String birthDate) {
		Response response = userService.updateUser(id, pinCode, birthDate);
		return response;
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Response deleteUser(@PathVariable String id) {
		Response response = userService.deleteUser(id);
		return response;
	}

}
