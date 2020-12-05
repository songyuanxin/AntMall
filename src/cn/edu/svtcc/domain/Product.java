package cn.edu.svtcc.domain;

/**
 * 产品类
 * @author Shixuanming
 *
 */

public class Product {
	//产品id
	private Integer pid;
	//产品图片
	private String pimage;
	//产品名称
	private String pname;
	//库存
	private Integer stuck;
	//单价
	private String pprice;
	//类别id
	private Integer category;
	//类别名称
	private String categoryName;
	//供应商id
	private Integer provider;
	//供应商名称
	private String providerName;
	//必须重写这两个方法，否则Map中的containsKey()将不能判断
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Product) {
			Product pro = (Product)obj;
			if(this.pid.equals(pro.pid)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.pid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getStuck() {
		return stuck;
	}
	public void setStuck(Integer stuck) {
		this.stuck = stuck;
	}
	public String getPprice() {
		return pprice;
	}
	public void setPprice(String pprice) {
		this.pprice = pprice;
	}
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
	public Integer getProvider() {
		return provider;
	}
	public void setProvider(Integer provider) {
		this.provider = provider;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
}
