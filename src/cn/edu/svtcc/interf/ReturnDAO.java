package cn.edu.svtcc.interf;

import cn.edu.svtcc.domain.Order;
/**
 * 
 * @author Shixuanming
 *	数据回滚的访问接口
 */
public interface ReturnDAO {
	public boolean reDatabase(Order order);
}
