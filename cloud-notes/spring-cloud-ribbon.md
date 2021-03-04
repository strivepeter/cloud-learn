## Spring Cloud Ribbon：负载均衡
Ribbon 是Spring Cloud Netflix 子项目的核心组件之一，主要给服务间调用及API网关转发提供负载均衡的功能，本文将对其用法进行详细介绍。
### Ribbon简介
在微服务架构中，很多服务都会部署多个，其他服务去调用该服务的时候，如何保证负载均衡是个不得不去考虑的问题。负载均衡可以增加系统的可用性和扩展性，当我们使用RestTemplate来调用其他服务时，Ribbon可以很方便的实现负载均衡功能。
### 负载均衡是什么？
负载均衡是高可用网络基础架构的一个关键组成部分，有了负载均衡，我们通常可以将我们的应用服务器部署多台，然后通过负载均衡将用户的请求分发到不同的服务器用来提高网站、应用、数据库或其他服务的性能以及可靠性。
#### 为什么要用负载均衡？
* 先来看一个没有负载均衡的Web架构 如下图所示：
 ![没有负载均衡的架构图](https://img-blog.csdnimg.cn/20210303135809830.jpg)
* 在上图中，客户端之间通过网络与Web服务端相连，假想如果Web服务器宕机，那么用户访问网站时将得不到任何响应，出现单点故障问题。即使服务器可以正常工作，如果很多用户同时访问服务器，超过服务器的处理能力，那么会出现响应速度慢或者无法连接的情况，这也是用户无法接受的。
* 引入负载均衡可以有效解决上述问题，它可以将负载(工作任务)进行平衡、分摊到多个执行单元上运行。例如，Web服务器、FTP服务器、企业关键应用服务器和其他主要任务服务器等，协同完成工作任务。
* 负载均衡分为硬件负载均衡和软件负载均衡两种，具体介绍如下:
  * 硬件负载均衡的解决方案就是直接在服务器和外部网络间安装负载均衡设备，通常这种设备称为负载均衡器。由专门的设备完成专门的任务，独立于操作系统，整体性能得到大量提高，加上多样化的负载均衡策略，智能化的流量统计，可达到最佳的负载均衡效果。
  * 软件负载均衡的解决方案是指在一台或多台服务器相应的操作系统上安装一个或多个附加软件来实现负载均衡，如DNS Load Balance，CheckPoint Firewall-1 ConnectControl等，它的优点是基于特定环境，配置简单，使用灵活，成本低廉，可以满足一般的负载均衡需求。
* 无论哪种负载均衡策略，都是为了系统高可用、缓解网络压力以及扩容机器处理能力。下面看一个使用负载均衡的Web架构，具体如下图所示：
![负载均衡的架构图](https://img-blog.csdnimg.cn/20210303135922393.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1N0cml2ZV9QZXRlcg==,size_16,color_FFFFFF,t_70)

### RestTemplate中几种常见的请求方式
RestTemplate是一个HTTP客户端，使用它我们可以方便的调用HTTP接口，支持GET、POST、PUT、DELETE等方法。
#### Get请求方法
```
<T> T getForObject(String url, Class<T> responseType, Object... uriVariables);
<T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables);
<T> T getForObject(URI url, Class<T> responseType);
<T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables);
<T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables);
<T> ResponseEntity<T> getForEntity(URI var1, Class<T> responseType);  
```
* ##### getForObject方法
  返回对象为响应体中数据转化成的对象，举例如下:
  ```java
  ```
* ##### getForEntity方法
  返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等，举例如下：
  ```java
  ```
#### Post请求方法
```
<T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables);
<T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables);
<T> T postForObject(URI url, @Nullable Object request, Class<T> responseType);
<T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables);
<T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables);
<T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType);
```  
* ##### postForObject 方法
  ```java
  ```
* ##### postForEntity 方法
  ```java
  ```
#### Put 请求方法
```
void put(String url, @Nullable Object request, Object... uriVariables);
void put(String url, @Nullable Object request, Map<String, ?> uriVariables);
void put(URI url, @Nullable Object request);
```
* ##### put 方法使用
  ```java
  ```
#### Delete 请求方法
```
void delete(String url, Object... uriVariables);
void delete(String url, Map<String, ?> uriVariables);
void delete(URI url);
```
* ##### delete 方法
  ```java
  ```
### 负载均衡策略有哪些？
| 规则名称| 特点 |
|:------------------: |:-------------------:|
|AvailabilityFilteringRule|过滤掉一直连接失败的被标记为circuit tripped的后端Server， 并过滤掉那些高并发的后端Server或者使用一个AvailabilityPredicate来包含过滤server的逻辑， 其实就是检查status里记录的各个server的运行状态|
|BestAvailableRule|选择一个最小的并发请求的server，逐个考察server，如果Server被tripped了，则跳过|                            
|RandomRule|随机选择一个Server|
|ResponseTimeWeightedRule|~~已废弃，作用同WeightedResponseTimeRule~~|
|WeightedResponseTimeRule|根据响应时间加权，响应时间越长，权重越小，被选中的可能性越低|                            
|RetryRule|对选定的负载均衡策略加上重试机制， 在一个配置时间段内当选择Server不成功， 则一直尝试使用subRule的方式选择一个可用的Server|
|RoundRobinRule|轮询选择，轮询index，选择index对应位置的Server|          
|ZoneAvoidanceRule|默认的负载均衡策略，即复合判断Server所在区域的性能和Server的可用性选择Server， 在没有区域的环境下，类似于轮询(RandomRule)|
###### 其中RandomRule表示随机策略、RoundRobinRule表示轮询策略、WeightedResponseTimeRule表示加权策略、BestAvailableRule表示请求数最少策略等等。                                                                                                  

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
* #### 全局配置
  ```yml
  ribbon:
    ConnectTimeout: 1000 #服务请求连接超时时间（毫秒）
    ReadTimeout: 3000 #服务请求处理超时时间（毫秒）
    OkToRetryOnAllOperations: true #对超时请求启用重试机制
    MaxAutoRetriesNextServer: 1 #切换重试实例的最大个数
    MaxAutoRetries: 1 # 切换实例后重试最大次数
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule #修改负载均衡算法
  ```
* #### 指定服务进行配置
* ##### 与全局配置的区别就是ribbon节点挂在服务名称下面，如下是对ribbon-service调用user-service时的单独配置。
  ```yml
  user-service:
    ribbon:
      ConnectTimeout: 1000 #服务请求连接超时时间（毫秒）
      ReadTimeout: 3000 #服务请求处理超时时间（毫秒）
      OkToRetryOnAllOperations: true #对超时请求启用重试机制
      MaxAutoRetriesNextServer: 1 #切换重试实例的最大个数
      MaxAutoRetries: 1 # 切换实例后重试最大次数
      NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule #修改负载均衡算法
  ```
#### 回顾
* [【Spring Cloud实战:Eureka】服务注册与发现](https://blog.csdn.net/Strive_Peter/article/details/113949635)
#### 期望
* [【Spring Cloud实战:Hystrix】服务容错与保护]()
#### 项目源码地址
* [点击可以查看源码](https://github.com/strivepeter/cloud-learn)
 
 