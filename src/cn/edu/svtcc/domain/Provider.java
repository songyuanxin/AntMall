package cn.edu.svtcc.domain;

/**
 * 供应商实体类
 * @author Shixuanming
 *
 */

public class Provider {
	//供应商id
	private Integer providerId;
	//供应商名称
	private String providerName;
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
}
