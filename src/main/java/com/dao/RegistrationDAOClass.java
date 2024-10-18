package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.dto.RegistrationRequest;
import com.dto.RegistrationResponse;
import com.utils.ConnectionFactory;

public class RegistrationDAOClass implements RegistrationDAO {
	 private static final Logger logger = LoggerFactory.getLogger(RegistrationDAOClass.class);
	 @Override
	 public RegistrationResponse getUserById(long user_id) {
	     MDC.put("userId", String.valueOf(user_id));
	     MDC.put("method", "getUserById");
	     logger.info("Fetch user info from Database");

	     String query = "SELECT * FROM Customers WHERE user_id = ?";
	     RegistrationResponse registrationResponse = null;

	     try (Connection con = ConnectionFactory.getconnectionFactory().getConnection();
	          PreparedStatement stmt = con.prepareStatement(query)) {
	         
	         stmt.setLong(1, user_id);
	         try (ResultSet result = stmt.executeQuery()) {
	             if (result.next()) {
	                 registrationResponse = new RegistrationResponse(
	                     result.getLong("user_id"),
	                     result.getString("user_firstname"),
	                     result.getString("user_lastname"),
	                     result.getString("user_email"),
	                     result.getString("user_password"),
	                     result.getString("user_contact"),
	                     result.getString("user_address")
	                 );
	                 logger.info("Successfully fetched user details with ID: {}", user_id);
	             } else {
	                 logger.warn("No user found with user Id: {}", user_id);
	             }
	         }

	     } catch (SQLException e) {
	         logger.error("Error while getting user details", e);
	     } finally {
	         MDC.clear();
	     }

	     return registrationResponse;
	 }

	@Override
	public boolean createUser(RegistrationRequest registrationRequest) {
		String query="insert into Customers (user_firstname,user_lastname,user_email,user_password,user_contact,user_address)values(?,?,?,?,?,?);";
		MDC.put("method", "createUser");
		logger.info("New User registration : ",registrationRequest.getUser_firstname());
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			
			stmt.setString(1, registrationRequest.getUser_firstname());
			stmt.setString(2, registrationRequest.getUser_lastname());
			stmt.setString(3, registrationRequest.getUser_email());
			stmt.setString(4,registrationRequest.getUser_password());
			stmt.setString(5, registrationRequest.getUser_contact());
			stmt.setString(6, registrationRequest.getUser_address());
			int result=stmt.executeUpdate();
			con.close();
			if(result>0) {
				logger.info("Successfully Register");
				return true;
			}
			else {
				logger.error("Registration Unsuccessful.. Enter proper details for registration ");
			}
			
		}catch(SQLException e) {
			logger.error("Error : At User Registration",e );
			e.printStackTrace();
		}
		return false;
	} 

	@Override
	public boolean updateUser(RegistrationResponse registrationResponse) {
		String query="update Customers set user_firstname=?,user_lastname=?,user_email=?,user_password=?,user_contact=?,user_address=? where user_id=? ";
		MDC.put("userId", String.valueOf(registrationResponse.getUser_id()));
		MDC.put("method", "updateUser");
		logger.info("Update user details ",registrationResponse.getUser_id());
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setString(1, registrationResponse.getUser_firstname());
			stmt.setString(2,registrationResponse.getUser_lastname());
			stmt.setString(3, registrationResponse.getUser_email());
			stmt.setString(4,registrationResponse.getUser_password());
			stmt.setString(5, registrationResponse.getUser_contact());
			stmt.setString(6,registrationResponse.getUser_address());
			stmt.setLong(7,registrationResponse.getUser_id());
			int result=stmt.executeUpdate();
			con.close();
			if(result>0) {
				logger.info("Details updated Successfully for UserID : ",registrationResponse.getUser_id());
			return true;	
			}else {
				logger.error("No user found with UserID: {} : ",registrationResponse.getUser_id());
			}
			
			
		}
		catch(SQLException e) {
			logger.error("SQL Exception occurred while Updating user details",e);
			e.printStackTrace();
		}
		return false;
	} 
	@Override
	public boolean deleteUser(long user_id) {
		String query="delete from Customers where user_id=?";
		MDC.put("method", "deleteUser");
		MDC.put("userId", String.valueOf(user_id));
		logger.info("Delete user By Id : ",user_id);
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setLong(1, user_id);
			int  result=stmt.executeUpdate();
			con.close();
			if(result>0) {
				logger.info("User deleted Successfully ",user_id);
				return true;
			}else {
				logger.error("No user found with UserID: {}",user_id );
			}
			
			
		}
		catch(SQLException e) {
			logger.error("SQL Exception occurred While deleting user : ",user_id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RegistrationResponse> getAllUsers() {
		String query="select * from Customers";
		 MDC.put("method","getAllUsers");
		 logger.info("Getting All User Details");
		List<RegistrationResponse> registerUsers=new ArrayList();
		
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				Statement stmt=con.createStatement();){
			    
				ResultSet result=stmt.executeQuery(query);
				
				while(result.next()) {

					RegistrationResponse users=new RegistrationResponse(result.getLong("user_id"),result.getString("user_firstname"),result.getString("user_lastname"),
							 result.getString("user_email"),result.getString("user_password"),result.getString("user_contact"),
							 result.getString("user_address"));
					 registerUsers.add(users);
				}
				 con.close();
				 logger.info("Retrieved Users Successfully",registerUsers.size());
				 return  registerUsers;
			
			
		}catch(SQLException e) {
			logger.error("SQL Exception occurred while Retrieving Users",e);
			e.printStackTrace();
		}		
		return null;
	}
	
	@Override
	public RegistrationResponse getUserByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM Customers WHERE user_email = ? AND user_password = ?";
        
        try (Connection connection = ConnectionFactory.getconnectionFactory().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                	RegistrationResponse user= new RegistrationResponse(
   	                     result.getLong("user_id"),
   	                     result.getString("user_firstname"),
   	                     result.getString("user_lastname"),
   	                     result.getString("user_email"),
   	                     result.getString("user_password"),
   	                     result.getString("user_contact"),
   	                     result.getString("user_address")
   	                 );
                    return user;
                } else {
                    return null; // No user found
                }
            }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }

	@Override
	public RegistrationResponse getUserByEmail(String user_email) {
		 MDC.put("method", "getUserByemail");
	     logger.info("Fetch user info By Email from Database");

	     String query = "SELECT * FROM Customers WHERE user_email = ?";
	     RegistrationResponse registrationResponse = null;

	     try (Connection con = ConnectionFactory.getconnectionFactory().getConnection();
	          PreparedStatement stmt = con.prepareStatement(query)) {
	         
	         stmt.setString(1, user_email);
	         try (ResultSet result = stmt.executeQuery()) {
	             if (result.next()) {
	                 registrationResponse = new RegistrationResponse(
	                     result.getLong("user_id"),
	                     result.getString("user_firstname"),
	                     result.getString("user_lastname"),
	                     result.getString("user_email"),
	                     result.getString("user_password"),
	                     result.getString("user_contact"),
	                     result.getString("user_address")
	                 );
	                 
	                 logger.info("Successfully fetched user details with ID: {}", user_email);
	             } else {
	                 logger.warn("No user found with user Id: {}", user_email);
	             }
	         }

	     } catch (SQLException e) {
	         logger.error("Error while getting user details", e);
	     } finally {
	         MDC.clear();
	     }
		return registrationResponse;
	}
}
