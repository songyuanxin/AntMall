package cn.edu.svtcc.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
/**
 * 连接池
 * @author Shixuanming
 *
 */
public class JDBCUtils{
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
//	public static final String DRIVER="com.mysql.jdbc.Driver";
//	public static final String URL="jdbc:mysql://127.0.0.1:3306/login?useSSL=false";
//	public static final String USERNAME="root";
//	public static final String PASSWORD="050817";
	public static void init() {
		try {
			InputStream stream = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties p = new Properties();
			p.load(stream);
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static BasicDataSource dataSource = new BasicDataSource();
	static {
		init();
		 dataSource.setDriverClassName(driver); // 这是要连接的数据库的驱动
		 dataSource.setUrl(url); //指定要连接的数据库地址
		 dataSource.setUsername(username); //指定要连接数据的用户名
		 dataSource.setPassword(password); //指定要连接数据的密码
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
}
