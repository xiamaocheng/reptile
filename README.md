Similar to simple crawler code, it can crawl market information such as 360, Baidu, mobile phones, and Alipay. If there is a DingTalk notification, it can be sent.


# reptile

#### 介绍
reptile 是一款基于Java的智能招聘信息聚合工具，支持从360搜索、百度、手机厂商应用商店、支付宝等平台实时爬取就业市场数据。集成钉钉机器人通知功能，当发现新职位时可通过钉钉即时推送提醒。

#### 软件架构
1. 基于Java 17开发
2. 核心组件：
   - Apache HttpClient 网络请求模块
   - Jsoup HTML解析模块
   - Quartz 定时任务调度模块
   - Unirest-Java 钉钉通知模块
3. 模块化架构设计，支持通过SPI机制扩展数据源
4. 使用Maven进行依赖管理

#### 安装教程

1. 安装JDK环境
   ```bash
   brew install openjdk@17
   ```
2. 克隆仓库
   ```bash
   git clone https://gitee.com/yourname/reptile.git
   ```
3. 编译项目
   ```bash
   cd reptile && mvn clean install
   ```

#### 使用说明

1. 配置数据源（编辑src/main/resources/sources.properties）
   ```properties
   # 启用平台列表
   platforms=baidu,360,alipay
   
   # 百度配置
   baidu.interval=3600
   ```
2. 设置钉钉机器人（配置src/main/resources/dingtalk.properties）
3. 运行程序
   ```bash
   # 方式一：通过maven运行
   mvn exec:java -Dexec.mainClass="com.reptile.Main"

   # 方式二：打包后运行
   java -jar target/reptile-1.0.0.jar
   ```

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request

#### 特技

1. 基于布隆过滤器的智能去重系统
2. 数据清洗引擎支持正则表达式提取：
   ```java
   // 示例：薪资提取规则
   Pattern.compile("([0-9]+K-[0-9]+K)")
   ```
3. 内置Spring Boot可视化控制台
4. 支持多线程爬取（可配置线程池大小）
5. 提供RESTful API接口
6. [Gitee 项目看板](https://gitee.com/yourname/reptile/boards)

