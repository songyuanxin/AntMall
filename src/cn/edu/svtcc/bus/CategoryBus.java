package cn.edu.svtcc.bus;

import java.util.List;

import cn.edu.svtcc.dao.CategoryDAOimp;
import cn.edu.svtcc.domain.Category;
import cn.edu.svtcc.interf.CategoryDAO;

public class CategoryBus {
	public static List<Category> getAllCategory(){
		CategoryDAO cDAO = new CategoryDAOimp();
		List<Category> categoryList = cDAO.getAllCategory();
		return categoryList;
	}
	public static boolean addCategory(Category category) {
		CategoryDAO cDAO = new CategoryDAOimp();
		boolean r = cDAO.addCategory(category);
		return r;
	}
	public static Category getCategoryById(Integer id) {
		CategoryDAO cDAO = new CategoryDAOimp();
		Category category = cDAO.getCategoryById(id);
		return category;
	}
	public static boolean updateCategory(Category category) {
		CategoryDAO cDAO = new CategoryDAOimp();
		boolean r = cDAO.updateCategory(category);
		return r;
	}
	public static boolean deleteCategoryById(Integer id) {
		CategoryDAO cDAO = new CategoryDAOimp();
		boolean r = cDAO.deleteCategoryById(id);
		return r;
	}
}
