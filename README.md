![license](https://img.shields.io/badge/license-Apache2.0-100000.svg)
![version](https://img.shields.io/maven-central/v/com.thankjava.toolkit/fast-toolkit)

> #### 介绍
- JDK >= 1.8 (本软件基于1.8, 第三方依赖根据其所需JDK版本而定)

    ```
    这是一个用于快速开发的支持依赖库，用于封装常用的功能，在不依赖大型框架体上用于进行快速开发或者测试。
    
    分为toolkit&toolkit3d两个子依赖包：
    
      toolkit 完全基于jdk提供函数
      
      toolkit3d封装了常用的其他工具api函数，提供更加简洁的api使用函数
      
      toolkit3d依赖toolkit，版本号要对应使用
      
    所有toolkit3d的第三方依赖均被声明为provided，你不必担心主工程依赖它后自动依赖所有jar，只需引入已使用的相关依赖。
    ```

---
- 2.0.0 此次变更内容 
    
    - 调整JDK支持版本 1.7 => 1.8
    
- [ChangeLog](https://github.com/lazy-koala/java-toolkit/blob/master/doc/ChangeLog.md)

- [API List](https://github.com/lazy-koala/java-toolkit/blob/master/doc/API.md)

---
- Maven 获取

  ```xml
  <dependency>
    <groupId>com.thankjava.toolkit</groupId>
    <artifactId>fast-toolkit</artifactId>
    <version>2.0.0</version>
  </dependency>
  ```

  ```xml
  <dependency>
    <groupId>com.thankjava.toolkit3d</groupId>
    <artifactId>fast-toolkit3d</artifactId>
    <version>2.0.0</version>
  </dependency>
  ```
  
---
> #### 关于我们

[![org](https://img.shields.io/badge/org-@LazyKoala-yellow.svg)](https://github.com/lazy-koala/)
[![author](https://img.shields.io/badge/author-@acexy-blue.svg)](https://github.com/acexy/)

