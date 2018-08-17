![license](https://img.shields.io/badge/license-Apache2.0-100000.svg)
[![org](https://img.shields.io/badge/org-@LazyKoala-blue.svg)](https://github.com/lazy-koala/)
![version](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/thankjava/toolkit/fast-toolkit/maven-metadata.xml.svg)

> ## 作用
- 基于jdk开发 不依赖第三方代码 (JDK >= 1.7)
- 将一些java项目中常用的模块案例组件化，以便提供更简洁，更方便的代码案例
- 用于不依赖大型框架快速开始进入开发的工具包

---
> ## 介绍
- fast-toolkit
    - 基于JDK功能，不引入其他第三方组件

- fast-toolkit3d
    - 依赖fast-toolkit
    - 依赖其他常用的开源jar（按实际需求进行依赖，该项目pom指定相关依赖为provided）
    - 将其他第三方优秀lib组件化，整理并提供更简洁的api处理方案

> ## 获取
maven仓库
    toolkit & toolkit3d 版本号要对应使用
```xml
<dependency>
  <groupId>com.thankjava.toolkit</groupId>
  <artifactId>fast-toolkit</artifactId>
  <version>1.0.8</version>
</dependency>
```
```xml
<dependency>
  <groupId>com.thankjava.toolkit3d</groupId>
  <artifactId>fast-toolkit3d</artifactId>
  <version>1.0.8</version>
</dependency>
```

> ## 更新备注
```
1.0.0 项目初始
1.0.1 新功能增加
1.0.2 新功能增加
1.0.3 调整async.http模块
    1.该模块解析超文本数据为byteArray不再自动通过content-type判断，提高解析可靠性
    2.返回参数通过getDataString|getDataByteArray自动将返回数据解析成字符串或byte数组
1.0.4 修正maven结构
    1.修正maven结构导致发布到中央库的maven依赖失败
1.0.5 新增功能
    1.调整redis&mongodb，并新增更多常用方法
    2.BeanCopier模块支持复制类的父级属性对等复制
    3.移除了一些简单的api
    4.新增大量数据快速追加写入文件的IO功能 100W条共100MB数据 约600毫秒完成
    5.基于上述提供大量数据的csv写入方法
1.0.6 调整功能
    1.调整mongo使用connectionString的方式进行配置，能方便连接副本集及主从复制的DB搭建形式
    2.优化AsyncHttpCliet模块代码结构
    3.升级第三方最新依赖
1.0.7 新增功能
    1.提供动态执行Java源代码功能，源代码动态编译，源代码动态加载
1.0.8 新增功能
    1.新增mongo物理分页插件
    2.新增mybatis mysql 物理分页插件
1.0.9 调整功能
    1. 优化反射工具对静态方法/类的相关支持
```
---
> ## 已实现的功能列表

- toolkit
  - 基于JDK的接口层面AOP功能 [了解更多](https://www.thankjava.com/java/ef0d959aada9993d0d1469411f6086ec)
  - 常用时间处理函数
  - JDK HTTP请求
  - IO文件追加写入,并拓展快速写入csv文件(100W条共100MB数据 约600毫秒完成)
  - 一些简单的不损失精度的计算
  - 常用反射功能(并基于此提供Java对象PO-VO对象属性的对等复制)
  - 加载工程内部资源
  - MD5、RSA、3DS
  - 线程池&定时任务
  - 图片验证码生成
  - 动态Java源代码执行的字节码编译器和字节码加载器(ClassLoader)[了解更多](https://www.thankjava.com/java/ad45a9286d234105ad9165a7f43626b7)
  
- toolkit3d
  - 基于cglib的AOP API
  - 缓存ehcache redis API
  - 数据库 Mongo&mybatis(mysql) API
  - 基于alibaba fastjson API
  - 基于 apache async-http API
  - 电子邮件发送 API
  - RSA API
  - 基于xstream的xml处理 API
  - 基于zip4j的压缩文件处理 API
