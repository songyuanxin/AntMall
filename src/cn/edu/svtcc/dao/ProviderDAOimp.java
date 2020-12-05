package cn.edu.svtcc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.svtcc.domain.Provider;
import cn.edu.svtcc.interf.ProviderDAO;

public class ProviderDAOimp implements ProviderDAO{

	@Override
	public List<Provider> getAllProvider() {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from providers";
		List<Provider> providerList = null;
		try {
			providerList = qr.query(sql, new BeanListHandler<Provider>(Provider.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return providerList;
	}

	@Override
	public boolean addProvider(Provider provider) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert providers(providerId,providerName) values(?,?)";
		int r = 0;
		try {
			r = qr.update(sql,provider.getProviderId(),provider.getProviderName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r>0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Provider getProviderById(Integer id) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from providers where providerId=?";
		Provider provider = null;
		try {
			provider = qr.query(sql, new BeanHandler<Provider>(Provider.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provider;
	}

	@Override
	public boolean updateProvider(Provider provider) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update providers set providerName=? where providerId=?";
		int r = 0;
		try {
			r = qr.update(sql,provider.getProviderName(),provider.getProviderId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r>0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteProvider(Integer id) {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from providers where providerId=?";
		int r = 0;
		try {
			r = qr.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r>0) {
			return true;
		} else {
			return false;
		}
	}

}
