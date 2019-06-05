
> #### fast-toolkit (基于JDK功能)

- 基于JDK的接口层面AOP功能 [了解更多](https://www.thankjava.com/java/ef0d959aada9993d0d1469411f6086ec)
    
    ```
    参考  JDKAopProxyTest
    ```
    
- 常用时间处理函数

    ```
    参考 TimeUtilTest
    ```

- JDK 简单HTTP post/get 请求工具
    
    ```
    参考 JDKHttpTest
    ```
    
- IO文件追加写入,并拓展快速写入csv文件 (100W条共100MB数据 约600毫秒完成)

    ```
    参考 FileAppendWriteTest WriteCSVUtilTest
    ```

- 一些简单的不损失精度的计算

- 常用反射功能(并基于此提供Java对象PO-VO对象属性的对等复制)

    ```
    参考 BeanCopierUtilTest
    ```

- Class内部资源加载工具 加载classpath (resources) 资源

    ```
    参考 SourceLoaderUtilTest
    ```
    
- MD5、RSA、3DS 算法工具
    
    ```
    参考 RSATest ThreeDesTest
    ```
    
- 线程池，定时任务

    ```
    参考 ThreadPoolTask ThreadTaskTest
    ```
    
- 图片验证码生成

    ```
    参考ImageVerifyCodeTest
    ```
    
- 动态Java源代码执行&字节码编译器&字节码加载器 [了解更多](https://www.thankjava.com/java/ad45a9286d234105ad9165a7f43626b7)
    
    ```
    参考 ByteCodeClassLoaderTest ByteCodeCompilerTest ByteCodeInvokeTest
    ```
    
---

> #### fast-toolkit3d
-  提供基于cglib的实现的普通类AOP切片
    
    ```
    参考 CglibAopProxyTest
    ```
    
- 提供缓存Ehcache API

    ```
    参考 EhcacheManagerTest
    ```
    
- 提供数据库 Mongo&Mybatis&Redis API
    
    ```
    参考 MongodbManagerTest MyBatisManagerTest RedisManagerTest
    ```
    
- 提供基于alibaba fastjson API

    ```
    参考 FastJsonTest
    ```

- 提供基于apache async-http 模块的同步&异步http(可类浏览器携带上下文持续请求)请求api

    ```
    参考 AsyncHttpClientTest
    ```
    
- 提供电子邮件发送（含附件）API

    ```
    参考 MailUtilTest
    ```
    
- 提供RSA(base64)&Base64 API

    ```
    参考 RSABase64Test
    ```
   
- 提供基于xstream的xml处理 API

    ```
    参考 XMLBeanUtilTest
    ```
    
- 提供基于zip4j的压缩文件处理 API

    ```
    参考 Zip4jUtilTest
    ```

- 提供基于POI的Excel读取 API

    ```
    参考 POIReaderTest
    ```