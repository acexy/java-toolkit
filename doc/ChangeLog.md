> ### ChangeLog
---
- 1.1.x

    - 1.1.3
    
        - 调整RedisManager初始化可通过redis.properties的redis.db.index属性来指定redis默认数据库
        - JSON模块新增 toFormatJSONString() 输出美化后的json字符串
        - RSA模块拓展API功能提升
        
            ```
            1. 允许指定加解密 paddming algorithm
            
                RSA/ECB/PKCS1Padding
                RSA/ECB/OAEPWithSHA-1AndMGF1Padding
                RSA/ECB/OAEPWithSHA-256AndMGF1Padding
                
            2. 允许指定加验签 模式 algorithm
            
                SHA1withRSA
                SHA256withRSA
                
            ```

    - 1.1.2
    
        - 允许线程池模块中，自定义线程组&线程的名字以便日志输出
        - 调整了Database模块，统一使用BasicDBManagerBuilder管理数据库实例
        - 新增POI Excel读取功能，支持各种配置(包括指定单元格特殊格式读取, 自定义格式化, 指定范围读取等特殊功能)

    - 1.1.1
    
        - 调整AOP模块，修复AOP未正确返回Befor切片的自定义返回结果
    
    - 1.1.0
    
        - 移除部分过期API重新定义1.1.x
        - 重新调整了部分API命名&结构，统一API调用方法风格
        - 调整基于JDK代理&Cglib实现的AOP方法切片，并修复的切片配置错误造成的bug
        - 调整IO模块
        - 调整反射模块BeanCopierUtil (JavaBean对象对等复制属性)
        - 调整了基于JDK的RSA算法，提供加密和验签功能，提供Base64版本的RSA
        - 调整了邮件发送模块代码结构
        - 升级第三方依赖至最新可用版本
        - 新增基于Apache的Base64编解码工具
---
- 1.0.x

    - 1.0.10

        - 调整async模块，公共的请求和响应方式，更加完善的请求参数限定
        - 调整async模块，提供更加丰富的http-client功能定制，例如取消证书校验，关闭警告日志
        - 调整async模块，新增异步调用api 包括在一个http-client中可使用上下文持续请求或者单独不传送上下文请求
        - 调整async模块，新增发送二进制数据(包括byte数组或文件)
        - 增加jdk原始io常用模块（待拓展）

    - 1.0.9 
    
        - 优化反射工具对静态方法/类的相关支持
        - MongoDriverManager新增删除相关封装，优化api
        - 调整了BeanCopier的属性追加函数
        - 调整线程池初始化的默认参数
        
    - 1.0.8
     
        - 新增mongo物理分页插件
        - 新增mybatis mysql 物理分页插件
        
    - 1.0.7
     
        - 提供动态执行Java源代码功能，源代码动态编译，源代码动态加载
    
    - 1.0.6
     
        - 调整mongo使用connectionString的方式进行配置，能方便连接副本集及主从复制的DB搭建形式
        - 优化AsyncHttpCliet模块代码结构
        - 升级第三方最新依赖
    
    - 1.0.5
     
        - 调整redis&mongodb，并新增更多常用方法
        - BeanCopier模块支持复制类的父级属性对等复制
        - 移除了一些简单的api
        - 新增大量数据快速追加写入文件的IO功能 100W条共100MB数据 约600毫秒完成
        - 基于上述提供大量数据的csv写入方法
    
    - 1.0.4 
        
        - 修正maven结构导致发布到中央库的maven依赖失败
    
    - 1.0.3 
    
        - 该模块解析超文本数据为byteArray不再自动通过content-type判断，提高解析可靠性
        - 返回参数通过getDataString|getDataByteArray自动将返回数据解析成字符串或byte数组
    
    - 1.0.2 
        
        - 新功能增加
        
    - 1.0.1 
    
        - 新功能增加
    
    - 1.0.0
     
        - 项目初始