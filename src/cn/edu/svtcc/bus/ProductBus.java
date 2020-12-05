package cn.edu.svtcc.bus;

import java.util.List;

import cn.edu.svtcc.dao.ProductDAOimp;
import cn.edu.svtcc.domain.Product;
import cn.edu.svtcc.interf.ProductDAO;

public class ProductBus {
	public static List<Product> getAllProduct(Integer category){
		ProductDAO pDAO = new ProductDAOimp();
		List<Product> proList = pDAO.getAllProduct(category);
		return proList;
	}
	public static Product getProductByID(Integer id) {
		ProductDAO pDAO = new ProductDAOimp();
		Product pro = pDAO.getProductByID(id);
		return pro;
	}
	public static boolean updateProduct(Product pro) {
		ProductDAO pDAO = new ProductDAOimp();
		boolean r = pDAO.updateProduct(pro);
		return r;
	}
	public static List<Product> getAllGoods(){
		ProductDAO pDAO = new ProductDAOimp();
		List<Product> goodsList = pDAO.getAllGoods();
		return goodsList;
	}
	public static boolean addProduct(Product pro) {
		ProductDAO pDAO = new ProductDAOimp();
		List<Product> goodsList = pDAO.getAllGoods();
		for(Product p:goodsList) {
			if(p.getPname().equals(pro.getPname())) {
				return false;
			}
		}
		boolean r = pDAO.addProduct(pro);
		return r;
	}
	public static boolean deleteProductByID(Integer id) {
		ProductDAO pDAO = new ProductDAOimp();
		List<Product> goodsList = pDAO.getAllGoods();
		for(Product p:goodsList) {
			if(p.getPid()==id) {
				boolean r = pDAO.deleteProductByID(id);
				return r;
			}
		}
		return false;
	}
	public static List<Product> getProductByPage(Integer pageSize, Integer pageIndex){
		ProductDAO pDAO = new ProductDAOimp();
		List<Product> goodsList = pDAO.getProductByPage(pageSize, pageIndex);
		return goodsList;
	}
	public static List<Product> getAllProductByPage(Integer category, Integer pageSize, Integer pageIndex){
		ProductDAO pDAO = new ProductDAOimp();
		List<Product> goodsList = pDAO.getAllProductByPage(category,pageSize, pageIndex);
		return goodsList;
	}
	public static boolean updateProductAll(Product pro) {
		ProductDAO pDAO = new ProductDAOimp();
		boolean r = pDAO.updateProductAll(pro);
		return r;
	}
	public static boolean updateProductAllButPic(Product pro) {
		ProductDAO pDAO = new ProductDAOimp();
		boolean r = pDAO.updateProductAllButPic(pro);
		return r;
	}
	public static List<Product> getProduct(){
		ProductDAO pDAO = new ProductDAOimp();
		List<Product> productList = pDAO.getProduct();
		return productList;
	}
}
