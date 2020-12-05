package cn.edu.svtcc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.interf.ProductDAO;
/**
 * 实现从数据库读取商品的实现类
 * @author Shixuanming
 *
 */
public class ProductDAOimp implements ProductDAO{
	public List<Product> getAllProduct(Integer category){
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from goods where category=?";
		List<Product> pro = null;
		try {
			pro = qr.query(sql, new BeanListHandler<Product>(Product.class),category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pro!=null) {
			return pro;
		} else {
			return null;
		}
	}
	public Product getProductByID(Integer id) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from goods where pid=?";
		Product product = null;
		try {
			product = qr.query(sql,new BeanHandler<Product>(Product.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(product!=null) {
			return product;
		} else {
			return null;
		}
	}
	@Override
	public boolean updateProduct(Product pro) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update goods set pimage=?,pname=?,stuck=?,pprice=?,category=? where pid=?";
		try {
			int r = qr.update(sql,pro.getPimage(),pro.getPname(),pro.getStuck(),pro.getPprice(),pro.getCategory(),pro.getPid());
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Product> getAllGoods() {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select pid,pname,stuck,pprice,categoryName from goods join categorys on goods.category=categorys.category";
		List<Product> goodsList = null;
		try {
			goodsList = qr.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(goodsList!=null) {
			return goodsList;
		} else {
			return null;
		}
	}
	@Override
	public boolean addProduct(Product pro) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert goods(pimage,pname,stuck,pprice,category,provider) values(?,?,?,?,?,?)";
		try {
			int r = qr.update(sql,pro.getPimage(),pro.getPname(),pro.getStuck(),pro.getPprice(),pro.getCategory(),pro.getProvider());
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteProductByID(Integer id) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from goods where pid=?";
		try {
			int r = qr.update(sql,id);
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Product> getProductByPage(Integer pageSize, Integer pageIndex) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select pid,pname,stuck,pprice,categoryName,providerName from goods join categorys on goods.category=categorys.category join providers on goods.provider=providers.providerId limit ?,?";
		List<Product> goodsList = null;
		try {
			goodsList = qr.query(sql, new BeanListHandler<Product>(Product.class),(pageIndex-1)*pageSize,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(goodsList!=null) {
			return goodsList;
		} else {
			return null;
		}
	}
	@Override
	public List<Product> getAllProductByPage(Integer category, Integer pageSize, Integer pageIndex) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from goods where category=? limit ?,?";
		List<Product> pro = null;
		try {
			pro = qr.query(sql, new BeanListHandler<Product>(Product.class),category,(pageIndex-1)*pageSize,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pro!=null) {
			return pro;
		} else {
			return null;
		}
	}
	@Override
	public boolean updateProductAll(Product pro) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update goods set pimage=?,pname=?,stuck=?,pprice=?,category=?,provider=? where pid=?";
		try {
			int r = qr.update(sql,pro.getPimage(),pro.getPname(),pro.getStuck(),pro.getPprice(),pro.getCategory(),pro.getProvider(),pro.getPid());
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateProductAllButPic(Product pro) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update goods set pname=?,stuck=?,pprice=?,category=?,provider=? where pid=?";
		try {
			int r = qr.update(sql,pro.getPname(),pro.getStuck(),pro.getPprice(),pro.getCategory(),pro.getProvider(),pro.getPid());
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Product> getProduct() {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from goods";
		List<Product> productList = null;
		try {
			productList = qr.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
}
