package cn.edu.svtcc.interf;

import java.util.List;

import cn.edu.svtcc.domain.OrderDetail;
/**
 * 
 * @author Shixuanming
 * 订单详情的数据库访问接口
 */
public interface OrderDetailDAO {
	public boolean putOrderDetailData(List<OrderDetail> details);
	public boolean deleteOrderDetails(String orderId);
}
