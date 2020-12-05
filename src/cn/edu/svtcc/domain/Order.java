package cn.edu.svtcc.domain;

/**
 * 订单实体类
 * @author Shixuanming
 *
 */
public class Order {
	//订单id
	private String orderId;
	//用户名
	private String username;
	//订单日期
	private String orderTime;
	//总价
	private double totalPrice;
	//商品种类
	private Integer productNum;
	//地址
	private String address;
	//收件人
	private String reciever;
	//电话
	private String phone;
	public boolean equals(Object obj) {
		if(obj instanceof Order) {
			Order order = (Order)obj;
			if(this.orderId.equals(order.orderId)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public int hashCode() {
		return this.orderId.hashCode();
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", username=" + username + ", orderTime=" + orderTime + ", totalPrice="
				+ totalPrice + ", productNum=" + productNum + ", address=" + address + ", reciever=" + reciever
				+ ", phone=" + phone + "]";
	}
	
}
