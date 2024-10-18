package com.dao;

import java.util.List;

import com.dto.RegistrationRequest;
import com.dto.RegistrationResponse;


public interface RegistrationDAO {
	public RegistrationResponse getUserByEmail(String user_email);
	public RegistrationResponse getUserById(long user_id);
	public boolean createUser(RegistrationRequest registrationRequest);
	public boolean updateUser(RegistrationResponse registrationResponse);
	public boolean deleteUser(long user_id);
	public List<RegistrationResponse> getAllUsers();
	public RegistrationResponse getUserByEmailAndPassword(String email, String password);

}
