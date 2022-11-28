# 🔱项目介绍
&emsp;&emsp;本项目是参考B站黑马程序员的《瑞吉外卖》教程所实现的一个SpringBoot+MybatisPlus+Mysql技术栈的前后端分离外卖管理系统。对于初学者较为友好，业务逻辑简单易上手，适合刚学完ssm做的一个练手项目。

## 🔷使用技术
- SpringBoot
- MySql
- Mybatis Plus

## 资料下载

- 后端API思维导图：[Xmind格式：点我下载](https://github.com/codermast/Takeout-food/blob/master/resource/%E7%91%9E%E5%90%89%E5%A4%96%E5%8D%96API%E5%89%96%E6%9E%90.xmind)
- SQL文件：[点我下载](https://github.com/codermast/Takeout-food/blob/master/resource/db_reggie.sql)
![](https://github.com/codermast/Takeout-food/blob/master/resource/瑞吉外卖API剖析.png)
## ❤️‍🩹开发进度
### V1版本
> V1版本的后台功能已经开发完毕，就剩前台的API还没有开发完毕。
- 🔺后台
  - [x] 登录模块
  - [x] 员工管理
  - [x] 分类管理
  - [x] 菜品管理
  - [x] 套餐管理
  - [x] 订单管理
- 🔻前台

## 项目部署

1. 下载本项目到服务器。

2. 修改`src/main/resources/application.yml`文件内的数据库信息
> 需要改动的就是下面我勾选的三个地方

![](https://github.com/codermast/Takeout-food/blob/master/resource/%E6%88%AA%E5%B1%8F2022-11-28%2021.59.36.png)

  - 数据库名
  - 数据库用户名
  - 数据库密码
3. 在服务器上部署时，将项目打成`jar`包，使用`java -jar 包名`进行运行
4. 访问localhost:8080/backend/index.html即可
## 参考教程

- 笔记整理：CSDN-[https://blog.csdn.net/qq_33685334](https://blog.csdn.net/qq_33685334)

- 视频教程：黑马程序员-瑞吉外卖项目[https://www.bilibili.com/video/BV13a411q753](https://www.bilibili.com/video/BV13a411q753)
