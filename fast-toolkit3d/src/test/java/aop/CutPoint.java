package aop;

import org.thankjava.toolkit3d.utils.aop.entity.AopParam;

public class CutPoint {

	public AopParam before(AopParam param){
		String orgiStr = (String)param.getParams()[0];
		param.setParams(new Object[]{"abc"});
		System.out.println("我将在Business执行exe 方法之前执行 并且 exe原来传递的参数: " +orgiStr+ " 将被我修改为 abc");
		param.setInvokeProxyMethod(false);
		return param;
	}
	
	
	public AopParam after(AopParam param){
		String resutlt = (String) param.getResult();
		param.setResult("result");
		System.out.println("我将在Business执行exe 方法后执行 并且exe原来返回的参数: " + resutlt + " 将被我修改为 result");
		return param;
	}
	
}
