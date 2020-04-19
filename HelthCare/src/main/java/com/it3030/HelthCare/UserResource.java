package com.it3030.HelthCare;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Controller.PatientController;
import Controller.StockController;
import Controller.UserController;
import Model.PatientModel;
import Model.StockModel;
import Model.UserModel;


@Path("user")
public class UserResource {

	//get all users
	@GET
	@Path("allusers")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<UserModel> getAllUsers() throws Exception {
		return UserController.getInstance().getAll();
	}
	
	
	//register users
	@POST
	@Path("register")
	public String saveUser(UserModel obj) throws Exception {
		UserController.getInstance().save(obj);
		return "user Saved";
	}
	
	//Update existent users
	@PUT
	@Path("updateuser")
	public String updateUser(UserModel obj) throws Exception {
		UserController.getInstance().update(obj);
		return "user Updated";
	}

	//Delete selected user
	@DELETE
	@Path("deleteuser/{id}")
	public String deleteUser(@PathParam("id") int id) throws Exception {
		UserModel obj = new UserModel();
		obj.setId(id);
		UserController.getInstance().delete(obj);
		return "User Deleted";
	}
	
	
	//LOGIN TO THE SYSTEM
	//check whether the user is exist.if user exits....then go on to the dashboard.
	@GET
	@Path("login")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String getUsernameandPassord(UserModel obj) {
		
		String submitUsername = obj.getUsername();
		String submitPassword = obj.getPassword();
		try {
			List<UserModel> AllUsers = getAllUsers();
			for(UserModel user:AllUsers) {
				if(submitUsername == user.getUsername()) {
					if(submitPassword == user.getPassword()) {
						return "Successfully Login to the system";
					}
					else {
						return "Incorrect Password";
					}
				}
				else {
					return "Incorrect Username";
				}
			
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
	}
	
	
	
	
	

}
