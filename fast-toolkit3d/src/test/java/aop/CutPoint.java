package aop;

import org.thankjava.toolkit3d.aop.entity.AopParam;


/**
 * 切片处理类
 * 被拦截的方法 如果配置了切片 将自动执行该切片里面的方法
* <p>Function: CutPoint</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年8月17日 下午7:58:08
* @version 1.0
 */
public class CutPoint {

	
	/**
	 * before 案例
	 * 传入和返回值必须为AopParam类型
	* <p>Function: before</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月17日 下午7:58:46
	* @version 1.0
	* @param param
	* @return
	 */
	public AopParam before(AopParam param){
		//通过该方法获取原始被拦截的方法的传入参数
		String orgiStr = (String)param.getParams()[0];
		
		//修改被拦截的方法所能获得的参数 并且在after 切片中也只能获得被修改后的参数
		param.setParams(new Object[]{"abc"});
		System.out.println("我将在Business执行exe 方法之前执行 并且 exe原来传递的参数: " +orgiStr+ " 将被我修改为 abc");
		
		
		//可以设置被拦截的方法到底是否需要执行 (该属性只有Before切片有用)
//		param.setInvokeProxyMethod(false);
		//如果设置被拦截的方法不要执行，并setResult值 则被拦截的方法的执行结果就是 param.result
//		param.setResult("Result");
		return param;
	}
	
	
	public AopParam after(AopParam param){
		
		//获取原始参数或者是Before切片设置的参数
//		String orgiStr = (String)param.getParams()[0];
		
		//获取被拦截的方法执行后的返回结果
		String resutlt = (String) param.getResult();
		//设置返回值 如果你想修改被拦截的方法的源生返回值
		param.setResult("result");
		System.out.println("我将在Business执行exe 方法后执行 并且exe原来返回的参数: " + resutlt + " 将被我修改为 result");
		return param;
	}
	
}
