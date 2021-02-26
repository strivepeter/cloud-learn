## Spring Cloud Ribbon：负载均衡
Ribbon 是Spring Cloud Netflix 子项目的核心组件之一，主要给服务间调用及API网关转发提供负载均衡的功能，本文将对其用法进行详细介绍。

### Ribbon简介

### 负载均衡是什么？

### RestTemplate中几种常见的请求方式

### 负载均衡策略有哪些？

### 基于Ribbon负载均衡实战

#### 创建一个user-service服务
首先我们创建一个user-service，用于给Ribbon提供服务调用
* ##### 在pom.xml中添加相关依赖
* ##### 在application.yml进行配置
* ##### 添加UserController用于提供调用接口
#### 创建一个ribbon-service模块
这里我们创建一个ribbon-service模块来调用user-service模块演示负载均衡的服务调用。
* ##### 在pom.xml中添加相关依赖
* ##### 在application.yml进行配置
* ##### 使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
* ##### 添加UserRibbonController类
#### 负载均衡的演示
### Ribbon的常用配置


#### 回顾
* [基于Eureka服务注册与发现](https://blog.csdn.net/Strive_Peter/article/details/113949635)
#### 期望
* [基于Hystrix 容错保护]()
#### 项目源码地址
* [点击可以查看源码](https://github.com/strivepeter/cloud-learn)
