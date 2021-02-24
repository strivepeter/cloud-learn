
### SpringCloud 是什么？
SpringCloud 是一系列框架的有序集合。

#### 简介
Spring Cloud 利用SpringBoot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、
配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用SpringBoot的开发风格做到一键启动和部署。
* 约定优与配置
* 适合各种环境
* 隐藏组件的复杂性、提供声明式、无XML的配置方式
* 轻量级的组件
* 组件丰富、功能齐全
* 灵活、SpringCloud组成部分是解耦的
#### 组件架构图
![架构图](https://img-blog.csdnimg.cn/20210220184021508.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1N0cml2ZV9QZXRlcg==,size_16,color_FFFFFF,t_70)
### SpringCloud 版本关系
* SpringCloud是由许多子项目组成的综合项目，各个项目有着不同发布节奏。 
* 为了管理SpringCloud与各子项目的版本之间的依赖关系，发布了一个清单，其中包括了某个SpringCloud版本对应的子项目版本。 
* 为了避免SpringCloud版本号与子项目版本号混淆，SpringCloud版本采用了名称而非版本号的命名，这些版本的名字采用了伦敦地铁站的名字，根据字母表的顺序来对应版本时间顺序，例如Angel是第一个版本, Brixton是第二个版本。 
* 当SpringCloud的发布内容积累到临界点或者一个重大BUG被解决后，会发布一个"service releases"版本，简称SRX版本，比如Greenwich.SR2就是SpringCloud发布的Greenwich版本的第2个SRX版本。

### SpringCloud和SpringBoot之间的版本关系
| SpringCloud Version| SpringBoot Version |
|:------------------ |:-------------------|
|    Hoxton          | 2.2.x              |
|    Greenwich       | 2.1.x              |
|    Finchley        | 2.0.x              |
|    Edgware         | 1.5.x              |
|    Dalston         | 1.5.x              |

### SpringCloud 和子项目之间对应关系
|Component| Name| Edgware.SR6 | Greenwich.SR2|
|:------|:------|:------|:----|
|spring-cloud-bus | 消息总线 | 1.3.4.RELEASE|2.1.2.RELEASE|
|spring-cloud-commons| |2.3.6.RELEASE|2.1.2.RELEASE|
|spring-cloud-config|配置中心|1.4.7.RELEASE|2.1.3.RELEASE|
|spring-cloud-netflix| | 1.4.7.RELEASE| 2.1.2.RELEASE|
|spring-cloud-security|安全认证|1.2.4.RELEASE|2.1.3.RELEASE|
|spring-cloud-consul|服务治理|1.3.6.RELEASE|2.1.2.RELEASE|
|spring-cloud-sleuth|链路跟踪|1.3.6.RELEASE|2.1.1.RELEASE|
|spring-cloud-stream|消息服务|Ditmars.SR5|Fishtown.SR3|
|spring-cloud-zookeeper|服务治理|1.2.3.RELEASE|2.1.2.RELEASE|
|spring-boot| Boot工程|1.5.21.RELEASE|2.1.5.RELEASE|
|spring-cloud-task| |1.2.4.RELEASE|2.1.2.RELEASE|
|spring-cloud-gateway|网关|1.0.3.RELEASE|2.1.2.RELEASE|
|spring-cloud-openfeign| | | 2.1.2.RELEASE|

###### 注意:Greenwich版本是基于SpringBoot 2.1.x版本构建的，不适用于1.5.x版本。随着2019年8月SpringBoot 1.5.x版本停止维护，Edgware版本也将停止维护。

## SpringCloud 子项目简介

#### Spring Cloud Netflix
Netflix OSS 开源组件集成，包括Eureka、Hystrix、Ribbon、Feign、Zuul等核心组件。
* Eureka：服务治理组件，包括服务端的注册中心和客户端的服务发现机制；
* Ribbon：负载均衡的服务调用组件，具有多种负载均衡调用策略；
* Hystrix：服务容错组件，实现了断路器模式，为依赖服务的出错和延迟提供了容错能力；
* Feign：基于Ribbon和Hystrix的声明式服务调用组件；
* Zuul：API网关组件，对请求提供路由及过滤功能。

#### Spring Cloud Config
Spring Cloud Config 集中配置管理的组件：
* Spring Cloud Config为分布式系统中的外部配置提供服务器和客户端支持。
* 使用Config Server，您可以在所有环境中管理应用程序的外部属性。
* 客户端和服务器上的概念映射与Spring Environment和PropertySource抽象相同，因此它们与Spring应用程序非常契合，但可以与任何以任何语言运行的应用程序一起使用。
* 随着应用程序通过从开发人员到测试和生产的部署流程，您可以管理这些环境之间的配置，并确定应用程序具有迁移时需要运行的一切。
* 服务器存储后端的默认实现使用git，因此它轻松支持标签版本的配置环境，以及可以访问用于管理内容的各种工具。可以轻松添加替代实现，并使用Spring配置将其插入。

#### Spring Cloud Bus
Spring Cloud Bus 用于传播集群状态变化的消息总线。
* Spring Cloud Bus将分布式系统的节点与轻量级消息代理链接。
* 可以用于广播状态更改（例如配置更改）或其他管理指令。
* Bus就像一个扩展的Spring Boot应用程序的分布式执行器，但也可以用作应用程序之间的通信渠道。
* 当前唯一的实现是使用AMQP代理作为传输，但是相同的基本功能集（还有一些取决于传输）在其他传输的路线图上。

#### Spring Cloud Consul
Spring Cloud Consul 基于HashiCorp Consul的服务治理组件。
* 该项目通过自动配置并绑定到Spring环境和其他Spring编程模型成语，为Spring Boot应用程序提供Consul集成。
* 通过几个简单的注释，您可以快速启用和配置应用程序中的常见模式，并使用基于Consul的组件构建大型分布式系统。
* 提供的模式包括服务发现，控制总线和配置。
* 智能路由（Zuul）和客户端负载平衡（Ribbon），断路器（Hystrix）通过与Spring Cloud Netflix的集成提供。

#### Spring Cloud Security
Spring Cloud Security 依赖与SpringBoot快速启动安全服务。在2016年David Borsos在伦敦的微服务大会上提出了以下四种方案：
* 单点登录(SSO): 每个微服务都需要和认证服务交互，但这将产生大量非常琐碎的网络流量和重复的工作，当动在应用中存在数十个或更多微服务时，该方案的弊端就非常明显;
* 分布式会话(Session)方案: 该方案将用户认证的信息存储在共享存储中(如：Redis)，并使用用户会话的ID作为key来实现的简单分布式哈希映射。当用户访问微服务时，可以通过会话的ID从从共享存储中获取用户认证信息。该方案在大部分时候非常不错，但其主要缺点在于共享存储需要一定保护机制，此时相应的实现就会相对复杂;
* 客户端令牌(Token)方案: 令牌在客户端生成，并由认证服务器进行签名，令牌中包含足够的信息，以便各微服务可以使用。令牌会附加到每个请求上，为微服务提供用户身份验证。该解决方案的安全性相对较好，但由于令牌由客户端生成并保存，因此身份验证注销非常麻烦，一个折衷解决方案就是通过短期令牌和频繁检查认证服务来验证令牌是否有效等。对于客户端令牌JSON Web Tokens(JWT)是一个非常好的选择;
* 客户端令牌与API网关结合: 使用该方案意味着所有请求都通过网关，从而有效地隐藏了微服务。在请求时，网关将原始用户令牌转换为内部会话。这样也就可以网关对令牌进行注销，从而解决上一种方案存在的问题。

#### Spring Cloud Sleuth
SpringCloud应用程序的分布式请求链路跟踪，支持使用Zipkin、HTrace和基于日志（例如ELK）的跟踪。

#### Spring Cloud Stream
Spring Cloud Stream是用于构建消息驱动的微服务应用程序的框架。
* Spring Cloud Stream基于Spring Boot来创建独立的生产级Spring应用程序，并使用Spring Integration提供与消息代理的连接。
* 它提供了来自多家供应商的中间件的合理配置，并介绍了持久性发布-订阅语义，使用者组和分区的概念。
* 将@EnableBinding批注添加到应用程序中，以立即连接到消息代理，也可以将@StreamListener添加到方法中，以使其接收流处理的事件。

#### Spring Cloud Task
用于快速构建短暂、有限数据处理任务的微服务框架，用于向应用中添加功能性和非功能性的特性。

#### Spring Cloud Zookeeper
Spring Cloud Zookeeper通过自动配置并绑定到Spring Environment和其他Spring编程模型习惯用法，为Spring Boot应用程序提供Zookeeper集成。
* 通过一些简单的注释，您可以快速启用和配置应用程序内部的通用模式，并使用基于Zookeeper的组件构建大型分布式系统。
* 提供的模式包括服务发现和配置。（通过与Spring Cloud Netflix集成提供智能路由（Zuul）和客户端负载平衡（Ribbon），断路器（Hystrix）。）

#### Spring Cloud Gateway
API网关组件，对请求提供路由及过滤功能。

#### Spring Cloud OpenFeign
基于Ribbon和Hystrix的声明式服务调用组件，可以动态创建基于Spring MVC注解的接口实现用于服务调用，在SpringCloud 2.0中已经取代Feign成为了一等公民。

### 期待
