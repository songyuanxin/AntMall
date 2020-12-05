package cn.edu.svtcc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.svtcc.domain.Category;
import cn.edu.svtcc.interf.CategoryDAO;

public class CategoryDAOimp implements CategoryDAO{

	@Override
	public List<Category> getAllCategory() {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from categorys";
		List<Category> categoryList = null;
		try {
			categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}

	@Override
	public boolean addCategory(Category category) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert categorys(category,categoryName) values(?,?)";
		int r = 0;
		try {
			r = qr.update(sql,category.getCategory(),category.getCategoryName());
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
	public Category getCategoryById(Integer id) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from categorys where category=?";
		Category category = null;
		try {
			category = qr.query(sql, new BeanHandler<Category>(Category.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public boolean updateCategory(Category category) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update categorys set categoryName=? where category=?";
		int r = 0;
		try {
			r = qr.update(sql,category.getCategoryName(),category.getCategory());
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
	public boolean deleteCategoryById(Integer id) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from categorys where category=?";
		int r = 0;
		try {
			r = qr.update(sql,id);
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

}
