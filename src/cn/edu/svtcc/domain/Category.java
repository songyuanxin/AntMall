package cn.edu.svtcc.domain;

/**
 * 类别实体类
 * @author Shixuanming
 *
 */
public class Category {
	//类别id
	private Integer category;
	//类别名称
	private String categoryName;
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
