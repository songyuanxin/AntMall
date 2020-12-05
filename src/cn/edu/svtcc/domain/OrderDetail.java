package cn.edu.svtcc.domain;

/**
 * 订单详情实体类
 * @author Shixuanming
 *
 */
public class OrderDetail {
	//订单详情id
	private String detailId;
	//订单id
	private String orderId;
	//产品id
	private Integer pid;
	//总价
	private double unitPrice;
	//数量
	private Integer count;
	public boolean equals(Object obj) {
		if(obj instanceof OrderDetail) {
			OrderDetail orderdetail = (OrderDetail)obj;
			if(this.detailId.equals(orderdetail.detailId)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public int hashCode() {
		return this.detailId.hashCode();
	}
}
