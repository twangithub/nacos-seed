# nacos-seed
如果你正在寻找一个可伸缩性的框架，即可作为单独的项目，也可作为分布式的服务框架，请花1分钟了解一下本库。
本库基于【springboot2+nacos+dubbo+mybatis-plus+代码生成】构建，体积极小，其他任何组件可随意配置。无论是公司项目，还是个人项目，开箱即用
，节省时间，从现在开始。


## 1. 项目初体验
请点击父pom.xml,右键 Mark as Maven Project 即可开始自动加载依赖，然后找到CodeGenerator类即可开启旅程。

## 2. 作为单体项目
请无视  my-api， my-consumer 模块， 并在MyProviderApplication里注释掉@EnableDubbo即可。

## 3. 作为服务者提供者或者消费者
1.安装nacos，并启动<br>
2.依次启动 my-provider，my-consumer，然后测试my-consumer里controller里的接口即可<br>
3.打包需要[_install_] my-api到maven仓库，默认为本地，然后[_package_]你需要打包的模块即可。