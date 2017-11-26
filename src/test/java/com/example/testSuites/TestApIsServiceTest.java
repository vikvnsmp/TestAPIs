package com.example.testSuites;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.AbstractTestApIsTest;
import com.example.services.UserService;
import com.example.vo.Response;
import com.example.vo.UserRequest;

public class TestApIsServiceTest extends AbstractTestApIsTest {

	@Autowired
	private UserService service;

	private UserRequest userReq;
	private String id;

	@Before
	public void setup() {
		userReq = new UserRequest();
		userReq.setfName("Thomas");
		userReq.setlName("Edison");
		userReq.setBirthDate("01-JAN-2010");
		userReq.setEmail("thomas.edison@gmail.com");
		userReq.setPinCode(404001);
		id = "1001";
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testCreateUser() {
		Response response = service.createUser(userReq);
		Assert.assertNotNull("Failure - expected not null", response);
		Assert.assertTrue(response.getValErrors().size() == 0);

	}

	@Test
	public void testCreateUserWithInvalidEmailId() {
		userReq.setEmail("thomasedison");
		Response response = service.createUser(userReq);
		Assert.assertNotNull("Failure - expected not null", response);
		Assert.assertTrue(response.getValErrors().size() == 1);

	}

	@Test
	public void testDeleteUser() {
		Response response = service.deleteUser(id);
		Assert.assertNotNull("Failure - expected not null", response);
		Assert.assertTrue(response.getValErrors().size() == 0);
		Assert.assertTrue(Integer.valueOf(response.getUserId()) == 1001);

	}

	@Test
	public void testDeleteRandomUser() {
		Response response = service.deleteUser("1005");
		Assert.assertNotNull("Failure - expected not null", response);
		Assert.assertTrue(response.getValErrors().size() == 1);
		Assert.assertTrue(Integer.valueOf(response.getUserId()) == 1005);

	}

}
