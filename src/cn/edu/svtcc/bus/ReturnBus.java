package cn.edu.svtcc.bus;

import cn.edu.svtcc.dao.ReturnDAOimp;
import cn.edu.svtcc.domain.Order;
import cn.edu.svtcc.interf.ReturnDAO;

public class ReturnBus {
	public static boolean reDatabase(Order order) {
		ReturnDAO rDAO = new ReturnDAOimp();
		if(rDAO.reDatabase(order)) {
			return true;
		} else {
			return false;
		}
	}
}
