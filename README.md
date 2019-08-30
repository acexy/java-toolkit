![license](https://img.shields.io/badge/license-Apache2.0-100000.svg)
![version](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/thankjava/toolkit/fast-toolkit/maven-metadata.xml.svg)

> #### 介绍
- JDK >= 1.7 (本软件基于1.7, 具体第三方依赖根据其所需JDK版本而定)

    ```
    这是一个用于快速开发的支持依赖库，用于封装常用的功能，在不依赖大型框架体上用于进行快速开发或者测试。
    
    分为toolkit&toolkit3d两个子依赖包：
    
      toolkit 完全基于jdk提供函数
      
      toolkit3d封装了常用的其他工具api函数，提供更加简洁的api使用函数
      
      toolkit3d依赖toolkit，版本号要对应使用
      
    所有toolkit3d的第三方依赖均被声明为provided，你不必担心主工程依赖它后自动依赖所有jar，只需引入已使用的相关依赖。
    ```

---
- 此次变更内容
    - XML模块 默认自动使用了XStream推荐的安全模块
    - 统一了项目的Charset枚举值及一些基础的枚举引用规范
    - 调整基于JDK的http请求方法，更多的请求返回类型，更优雅的使用方法 参见 ``JDKHttpTest.java``
    - Redis新增官方集群Cluster的连接方式 参见``RedisClusterManagerTest.java``
    
- [ChangeLog](https://github.com/lazy-koala/java-toolkit/blob/master/doc/ChangeLog.md)

- [API List](https://github.com/lazy-koala/java-toolkit/blob/master/doc/API.md)

---
- Maven 获取

  ```xml
  <dependency>
    <groupId>com.thankjava.toolkit</groupId>
    <artifactId>fast-toolkit</artifactId>
    <version>1.1.4</version>
  </dependency>
  ```

  ```xml
  <dependency>
    <groupId>com.thankjava.toolkit3d</groupId>
    <artifactId>fast-toolkit3d</artifactId>
    <version>1.1.4</version>
  </dependency>
  ```
  
---
> #### 关于我们

[![org](https://img.shields.io/badge/org-@LazyKoala-yellow.svg)](https://github.com/lazy-koala/)
[![author](https://img.shields.io/badge/author-@thankjava-blue.svg)](https://github.com/thankjava/)

