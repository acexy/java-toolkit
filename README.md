![license](https://img.shields.io/badge/license-Apache2.0-100000.svg)
[![org](https://img.shields.io/badge/org-@LazyKoala-blue.svg)](https://github.com/lazy-koala/)
![version](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/thankjava/toolkit/fast-toolkit/maven-metadata.xml.svg)

> #### 介绍

```
JDK >= 1.7

这是一个快速入手的常用代码库，用于封装常用的功能，在不依赖大型框架体上用于进行快速开发或者测试。

分为toolkit&toolkit3d两个子依赖包：
  toolkit完全基于jdk提供函数
  toolkit3d封装了主流的其他工具api函数，提供更加简洁的api使用函数
  toolkit3d依赖toolkit，版本号要对应使用
  
所有toolkit3d的第三方依赖均被声明为provided，你不必担心主工程依赖它后自动依赖所有jar，
  你只需要对你使用到的api引入相关组件即可。
```

> #### 获取
```xml
<dependency>
  <groupId>com.thankjava.toolkit</groupId>
  <artifactId>fast-toolkit</artifactId>
  <version>1.1.0</version>
</dependency>
```

```xml
<dependency>
  <groupId>com.thankjava.toolkit3d</groupId>
  <artifactId>fast-toolkit3d</artifactId>
  <version>1.1.0</version>
</dependency>
```

> #### [ChangeLog](https://github.com/lazy-koala/java-toolkit/blob/master/doc/changelog.md)

> #### [API List](https://github.com/lazy-koala/java-toolkit/blob/master/doc/apilist.md)
