package com.service;

import java.util.List;

import com.dao.RegistrationDAOClass;
import com.dto.RegistrationRequest;
import com.dto.RegistrationResponse;

public class RegistrationService {
	private final RegistrationDAOClass registrationDao;
	
	public RegistrationService() {
		 registrationDao=new RegistrationDAOClass();
	}
	public List<RegistrationResponse> getAllUsers(){

		return registrationDao.getAllUsers();
	}
	public boolean updateUser(RegistrationResponse registrationResponse) {
		return registrationDao.updateUser(registrationResponse);
	}

	
	public boolean createUser(RegistrationRequest registrationRequest) {
		return registrationDao.createUser(registrationRequest);
	}
	
	public 	RegistrationResponse getUserById(long user_id) {
		return registrationDao.getUserById(user_id);
		
	}
	
	public boolean deleteUser(long user_id) {
		return registrationDao.deleteUser(user_id);
	}
	
	public RegistrationResponse getUserByEmailAndPassword(String email, String password) {
		return registrationDao.getUserByEmailAndPassword(email, password);
	}
}	
//	public static void main(String[] args) {
//		RegistrationService rs=new RegistrationService();
//		
////		create user....
//		
////		System.out.println(rs.createUser(new RegistrationRequest("Prasad Kumar","Rangisetti","prasad@gmail","Prasad123",799346496,"Bhimavaram")));
////		System.out.println(rs.createUser(new RegistrationRequest("Sai Kiran","Jakkula","saikiran@gmail","Sai123",768087126,"uppal")));
////		System.out.println(rs.createUser(new RegistrationRequest("Babulal","shaik","babulal@gmail","Babulal123",949479728,"Rajamandry")));
////		
////		 Update.. user details....
////		
////		RegistrationResponse updatedetails=new RegistrationResponse(1,"Prasad Kumar","Rangisetti","prasad@gmail","prasad",7993464964,"Bhimavaram");
////		System.out.println(rs.updateUser(updatedetails));
////		
////		 get user details by user ID.....
//		
//		RegistrationResponse user=rs.getUserById(1);
//		System.out.println(user);
////		
////		 get all users details...
////		
////		List<RegistrationResponse> users=rs.getAllUsers();
////		for(RegistrationResponse user:users) {
////			System.out.println(user.toString());
////		}
////		
////		Delete user by ID....
////		long userid=3;
////		boolean delete=rs.deleteUser(userid);
////		if(delete) {
////			System.out.println("User deleted Successfully");
////		}else {
////			System.out.println("Error");
////		}
//		
//	}
//}

