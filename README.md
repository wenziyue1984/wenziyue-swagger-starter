# wenziyue-swagger-starter 使用说明

> 基于 **springdoc-openapi 1.x + Swagger UI** 的零配置封装，

> 一键为 Spring Boot 项目生成在线接口文档（OpenAPI 3）。



------



## 1️⃣ 引入依赖

首先在settings.xml中添加以下认证信息
```xml
<server>
    <id>wenziyue-uid</id>
    <username>你的GitHub用户名</username>
    <password>你的GitHub Token（建议只赋予 read:packages 权限）</password>
</server>
```

再在 `pom.xml` 中添加 GitHub 仓库地址：

```xml
<!-- pom.xml 中添加仓库地址（id 要与上面保持一致） -->
<repositories>
    <repository>
        <id>wenziyue-uid</id>
        <url>https://maven.pkg.github.com/wenziyue1984/wenziyue-swagger-starter</url>
    </repository>
</repositories>
```
然后引入依赖：

```xml
<dependency>
    <groupId>com.wenziyue</groupId>
    <artifactId>wenziyue-swagger-starter</artifactId>
    <version>1.0.0（请使用最新版本）</version>
</dependency>
```

------



## 2️⃣ 最小可用配置

```yml
wenziyue:
  swagger:
    title: Wenziyue Blog API
    description: 博客系统接口文档
    version: 0.1.0
    base-package: com.wenziyue.blog.web   # 只扫描指定包，逗号分隔支持多包
    enable-jwt: true                      # 是否全局添加 BearerAuth 头，默认 true

# swagger-ui 配置
springdoc:
	swagger-ui:
  	url: /v3/api-docs          # 明确告诉 UI 去这里拿接口定义
```

启动后访问：http://localhost:8080/swagger-ui/index.html



------



## 3️⃣ 如何关闭Swagger

 Springdoc 的两个总开关：

```yml
springdoc:
  api-docs:
    enabled: false       # 禁用 /v3/api-docs 端点
  swagger-ui:
    enabled: false       # 禁用 /swagger-ui/** 资源
```

只要在任何 profile（例如 *prod*）里写上面两行，即可彻底屏蔽接口文档。

推荐用法是在开发、测试、生产环境使用不同配置文件，其中开发、测试环境打开swagger开关，生产环境关闭：

生产环境配置文件（`application-prod.yml`）

```yml
# swagger-ui 配置
springdoc:
  api-docs:
    enabled: false
  swagger-ui:
    enabled: false
    url: /v3/api-docs 
```

测试开发环境配置文件（`application-dev.yml`或`application-test.yml`）

```yml
# swagger-ui 配置
springdoc:
  api-docs:
    enabled: true
  swagger-ui:
    enabled: true
    url: /v3/api-docs
```



------

