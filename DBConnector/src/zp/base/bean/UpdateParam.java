package zp.base.bean;

import java.util.List;

/**
 * ʵ����²����ֶμ�ֵ
 */
public class UpdateParam {
	private String paramNames;
	private List<String> paramVals;
	
	public String getParamNames() {
		return paramNames;
	}
	public void setParamNames(String paramNames) {
		this.paramNames = paramNames;
	}
	public List<String> getParamVals() {
		return paramVals;
	}
	public void setParamVals(List<String> paramVals) {
		this.paramVals = paramVals;
	}
	
}
