package cn.edu.svtcc.bus;

import cn.edu.svtcc.dao.UserDAOimp;
import cn.edu.svtcc.domain.Order;
import cn.edu.svtcc.domain.User;
import cn.edu.svtcc.interf.UserDAO;

public class UserBus {
	public static boolean addUser(User user) {
		UserDAO uDAO = new UserDAOimp();
		if(uDAO.addUser(user)) {
			return true;
		}
		return false;
	}
	public static User login(User user) {
		UserDAO uDAO = new UserDAOimp();
		User users = uDAO.login(user);
		if(users!=null) {
			return users;
		}
		return null;
	}
	public static boolean rePwd(User user) {
		UserDAO uDAO = new UserDAOimp();
		boolean r = uDAO.rePwd(user);
		if(r) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean editUser(User user) {
		UserDAO uDAO = new UserDAOimp();
		boolean r = uDAO.editUser(user);
		if(r) {
			return true;
		} else {
			return false;
		}
	}
	public static User getUser(String username) {
		UserDAO uDAO = new UserDAOimp();
		User user = uDAO.getUser(username);
		return user;
	}
}
