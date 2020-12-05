package cn.edu.svtcc.interf;

import java.util.List;

import cn.edu.svtcc.domain.Product;
/**
 * 
 * @author Shixuanming
 *	商品的数据库访问接口
 */
public interface ProductDAO {
	public List<Product> getAllProduct(Integer category);
	public Product getProductByID(Integer id);
	public boolean updateProduct(Product pro);
	public List<Product> getAllGoods();
	public boolean addProduct(Product pro);
	public boolean deleteProductByID(Integer id);
	public List<Product> getProductByPage(Integer pageSize,Integer pageIndex);
	public List<Product> getAllProductByPage(Integer category,Integer pageSize,Integer pageIndex);
	public boolean updateProductAll(Product pro);
	public boolean updateProductAllButPic(Product pro);
	public List<Product> getProduct();
}
