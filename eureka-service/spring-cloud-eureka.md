## Spring Cloud Eureka ：服务注册与发现
Eureka是Netflix开源的一款提供服务注册和发现的产品，它提供了完整的Service Registry和Service Discovery实现。也是SpringCloud体系中最重要最核心的组件之一。

#### 背景介绍
~~# 后期补充~~

#### Eureka简介
Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来实现服务注册和发现。Eureka 采用了 C-S 的设计架构。
* Eureka Server 作为服务注册功能的服务器，它是服务注册中心。而系统中的其他微服务，使用 Eureka 的客户端连接到 Eureka Server，并维持心跳连接。
* 维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。
* Spring Cloud 的一些其他模块（比如Zuul）就可以通过 Eureka Server 来发现系统中的其他微服务，并执行相关的逻辑。
* Eureka由两个组件组成：Eureka服务器和Eureka客户端。Eureka服务器用作服务注册服务器。
* Eureka的基本架构，由3个角色组成：
  * 1、Eureka Server ：提供服务注册与发现
  * 2、Service Provider：服务提供方，将自身服务注册到Eureka中，使其他服务可以找到。
  * 3、Service Consumer：服务消费方，从Eureka获取注册服务列表，从而能够消费服务。
  
#### 搭建Eureka注册中心
我们以创建并运行Eureka注册中心来看看在IDEA中创建并运行SpringCloud应用的正确姿势。

##### 使用IDEA来创建SpringCloud应用
* ###### 创建一个mic-allen-iot模块，并使用Spring Initializer初始化一个SpringBoot项目
![创建流程图]()
* ###### 创建完成后会发现pom.xml文件中已经有了eureka-server的依赖
  ```maven
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
  </dependency>
  ```
* ###### 在启动类上添加@EnableEurekaServer注解来启用Euerka注册中心功能
  ```java
  @EnableEurekaServer
  @SpringBootApplication
  public class EurekaServiceApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(EurekaServiceApplication.class, args);
      }
  }
  ```
* ###### 在配置文件application.yml中添加Eureka注册中心的配置
  ```yml
  server:
    port: 8001 #指定运行端口
  spring:
    application:
      name: eureka-server #指定服务名称
  eureka:
    instance:
      hostname: localhost #指定主机地址
    client:
      fetch-registry: false #指定是否要从注册中心获取服务（注册中心不需要开启）
      register-with-eureka: false #指定是否要注册到注册中心（注册中心不需要开启）
    server:
      enable-self-preservation: false #关闭保护模式
  ```
* ###### 运行完成后访问地址http://localhost:8001/可以看到Eureka注册中心的界面
* 如图所示：
![页面图](https://img-blog.csdnimg.cn/20210223174402220.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1N0cml2ZV9QZXRlcg==,size_16,color_FFFFFF,t_70)
#### 搭建Eureka客户端
* ###### 新建一个eureka-client模块，并在pom.xml中添加如下依赖
  ```maven
  <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  
  <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>
  ```
* ###### 在启动类上添加@EnableDiscoveryClient注解表明是一个Eureka客户端
  ```java
  @EnableDiscoveryClient
  @SpringBootApplication
  public class EurekaServiceClientApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(EurekaServiceClientApplication.class, args);
      }
  }
  ```
* ###### 在配置文件application.yml中添加Eureka客户端的配置
  ```yml
  server:
    port: 8101 #运行端口号
  spring:
    application:
      name: eureka-client #服务名称
  eureka:
    client:
      register-with-eureka: true #注册到Eureka的注册中心
      fetch-registry: true #获取注册实例列表
      service-url:
        defaultZone: http://localhost:8001/eureka/ #配置注册中心地址
  ```
* ###### 查看注册中心http://localhost:8001/发现Eureka客户端已经成功注册
* 如图所示：
![创建流程图]()

#### 给Eureka注册中心添加认证

* ###### 创建一个eureka-security-server模块，在pom.xml中添加以下依赖
  ```maven
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
  </dependency>
  
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  ```
* ###### 添加application.yml配置文件
  ```yml
  server:
    port: 8004
  spring:
    application:
      name: eureka-security-server
    security: #配置SpringSecurity登录用户名和密码
      user:
        name: peter
        password: 123456
  eureka:
    instance:
      hostname: localhost
    client:
      fetch-registry: false
      register-with-eureka: false
  ```
* ###### 添加Java配置WebSecurityConfig
  ```java
  @EnableWebSecurity
  public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
      @Override
      protected void configure(HttpSecurity http) throws Exception {
          http.csrf().ignoringAntMatchers("/eureka/**");
          super.configure(http);
      }
  }
  ```
* ###### 运行eureka-security-server，访问http://localhost:8004发现需要登录认证

* ##### eureka-client注册到有登录认证的注册中心

#### Eureka的常用配置
* ###### 配置如下：
  ```yml
  eureka:
    client: #eureka客户端配置
      register-with-eureka: true #是否将自己注册到eureka服务端上去
      fetch-registry: true #是否获取eureka服务端上注册的服务列表
      service-url:
        defaultZone: http://localhost:8001/eureka/ # 指定注册中心地址
      enabled: true # 启用eureka客户端
      registry-fetch-interval-seconds: 30 #定义去eureka服务端获取服务列表的时间间隔
    instance: #eureka客户端实例配置
      lease-renewal-interval-in-seconds: 30 #定义服务多久去注册中心续约
      lease-expiration-duration-in-seconds: 90 #定义服务多久不去续约认为服务失效
      metadata-map:
        zone: jiangsu #所在区域
      hostname: localhost #服务主机名称
      prefer-ip-address: false #是否优先使用ip来作为主机名
    server: #eureka服务端配置
      enable-self-preservation: false #关闭eureka服务端的保护机制
  ```
