package com.example.IServices;

import com.example.vo.Response;
import com.example.vo.UserRequest;

public interface IUserService {

	public Response createUser(UserRequest userReq);

	public Response updateUser(String id, int pinCode, String birthDate);

	public Response deleteUser(String id);
}
