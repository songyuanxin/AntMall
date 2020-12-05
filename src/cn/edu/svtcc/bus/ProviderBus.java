package cn.edu.svtcc.bus;

import java.util.List;

import cn.edu.svtcc.dao.ProviderDAOimp;
import cn.edu.svtcc.domain.Provider;
import cn.edu.svtcc.interf.ProviderDAO;

public class ProviderBus {
	public static List<Provider> getAllProvider(){
		ProviderDAO pDAO = new ProviderDAOimp();
		List<Provider> providerList = pDAO.getAllProvider();
		return providerList;
	}
	public static boolean addProvider(Provider provider) {
		ProviderDAO pDAO = new ProviderDAOimp();
		boolean r = pDAO.addProvider(provider);
		return r;
	}
	public static Provider getProviderById(Integer id) {
		ProviderDAO pDAO = new ProviderDAOimp();
		Provider provider = pDAO.getProviderById(id);
		return provider;
	}
	public static boolean updateProvider(Provider provider) {
		ProviderDAO pDAO = new ProviderDAOimp();
		boolean r = pDAO.updateProvider(provider);
		return r;
	}
	public static boolean deleteProvider(Integer id) {
		ProviderDAO pDAO = new ProviderDAOimp();
		boolean r = pDAO.deleteProvider(id);
		return r;
	}
}
