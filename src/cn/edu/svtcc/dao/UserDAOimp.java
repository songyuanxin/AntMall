package cn.edu.svtcc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.domain.User;
import cn.edu.svtcc.interf.UserDAO;
/**
 * 实现登录验证和注册方法的实现类
 * @author Shixuanming
 *
 */
public class UserDAOimp implements UserDAO{
	public boolean addUser(User user) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert users(userName,pwd,phone) values(?,?,?)";
		try {
			qr.update(sql,user.getUsername(),user.getPwd(),user.getPhone());
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public User login(User user) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from users where userName=? and pwd=?";
		User users = null;
		try {
			users = qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPwd());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users!=null) {
			return users;
		} else {
			return null;
		}
	}
	@Override
	public boolean rePwd(User user) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update users set pwd=? where username=?";
		int r = 0;
		try {
			r = qr.update(sql,user.getPwd(),user.getUsername());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r>0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean editUser(User user) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update users set phone=? where username=?";
		int r = 0;
		try {
			r = qr.update(sql,user.getPhone(),user.getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r>0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public User getUser(String username) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from users where username=?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
