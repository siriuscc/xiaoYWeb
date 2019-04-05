
<!-- 
@import "你的文件" -->



# server layer



## 先搭建好ssm 框架


1. 先书写pom
2. 配置spring
3. 配置数据库连接信息
4. 创建云端数据库
5. 搭建好服务器，映射域名
6. 配置log4j
7. 创建demo测试
8. 为了节省流量，数据库迁移到本地
9. mybatis反向生成
```
mvn mybatis-generator:generate

```

## 需要在同一session下工作






数据库配置在：



vim /etc/mysql/mysql.conf.d/mysqld.cnf 


查看一下日志




























