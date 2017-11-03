# java-toolkit
# fast-tookit&fast-tookit3d
基于java将一些项目中常用的模块案例组件化

fast-tookit
---
- 基于jdk开发 不依赖第三方代码 (基于jdk1.6)

fast-tookit3d
---
- 依赖fast-tookit
- 将其他第三方优秀lib组件化,整理并提供更简洁的api处理方案

--- 
maven仓库
- toolkit & toolkit3d 版本号要对应使用
```xml
<dependency>
  <groupId>com.thankjava.toolkit</groupId>
  <artifactId>fast-toolkit</artifactId>
  <version>1.0.4</version>
</dependency>

<dependency>
  <groupId>com.thankjava.toolkit3d</groupId>
  <artifactId>fast-toolkit3d</artifactId>
  <version>1.0.4</version>
</dependency>
```
## 更新备注
* 1.0.3 调整async.http模块
	* 该模块解析超文本数据为byteArray不再自动通过content-type判断，提高解析可靠性
	* 返回参数通过getDataString|getDataByteArray自动将返回数据解析成字符串或byte数组数据
* 1.0.4 修正maven结构
	* 修正maven结构导致发布到中央库的maven依赖失败

---
## java-tookit 介绍（基于jdk实现，不需要引入其他组件）
---
- [参考博客](https://www.thankjava.com/java/ef0d959aada9993d0d1469411f6086ec)
- 基于jdk AOP 实现对接口的AOP处理，可以实现对一个接口调用前，调用后的处理
- 一个处理业务的接口IBusiness，其exe函数传入一个字符串
```java
public interface IBusiness {
	``
	public String exe(String str);
```
- 在它的实现中引入@Befor 和 @After切片 并把传入的参数返回回去
```java
public class BusinessImpl implements IBusiness{
	
	@Before(cutMethod = "before",cutClass = CutPoint.class)
	@After(cutMethod = "after",cutClass = CutPoint.class)
	public String exe(String str){
		return str;
	}
```
- IBusiness接口的切片函数
```java
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
		System.out.println("我将在Business执行exe 方法之前执行 并且 exe原来传递的参数: "
					+orgiStr+ " 将被我修改为 abc");
		
		
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
		System.out.println("我将在Business执行exe 方法后执行 并且exe原来返回的参数: " 
				+ resutlt + " 将被我修改为 result");
		return param;
	}
	
}
```
- 利用AOP模块让切片运行起来
```java
public class ProxyTest {

	public static void main(String[] args) {
	
		/**
		 * 基于JDK代理的AOP是通过面向接口层面实现
		 */
		IBusiness iBusiness = AopProxyFactory.createProxyObject(IBusiness.class, new BusinessImpl());
		
		System.out.println("最终函数获得的返回值: " + iBusiness.exe("1"));
	}
```
## 输出结果
- 我将在Business执行exe 方法之前执行 并且 exe原来传递的参数: 1 将被我修改为 abc
- 我将在Business执行exe 方法后执行 并且exe原来返回的参数: 1 将被我修改为 result
- 最终函数获得的返回值: result

---
- 反射模块，封装了一些常用的反射工具函数，值得推荐的是该模块含有一个 **BeanCopier**实现JavaBean的常用属性对等复制
- Entity
```java
public class Entity1 {

	private String str;
	private Boolean bol;
	private Integer inte;
	private List<String> listStr;
	private String[] strArr;
	
```
- VO

```java
public class Vo1 {

	private String str;
	private boolean bol;
	private int inte;
	private List<String> listStr;
	private String[] strArr;
```

- 使用**BeanCopier** 将Entity里面的属性值全部复制给VO，并且支持对象的属性还是另外一个对象，也支持枚举
```java
		Entity1 e1 = new Entity1();
		e1.setBol(false);
		
		List<String> listStr = new ArrayList<>();
		listStr.add("你好");
		e1.setListStr(listStr);
		String[] strArr = new String[]{"aa","bb","cc"};
		e1.setStrArr(strArr);
		
		Vo1 v1 = BeanCopier.copy(e1, Vo1.class);
		System.out.println(JSONObject.toJSONString(v1));
```
---
- MD5 3DS RSA 算法

---
- 线程池，基于 ScheduledExecutorService 的源生任务处理
