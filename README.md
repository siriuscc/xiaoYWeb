# xiaoYWeb
xiaoY 的web端


mvn mybatis-generator:generate



SELECT * FROM xy_task WHERE TO_DAYS(start) = TO_DAYS(NOW());  


https://developer.android.com/training/scheduling/alarms


返回时List的方法，必须s结尾

后台管理需要什么功能？？

需要一个


用户管理
	用户的删除和修改
行为管理
	用户的添加












session listener 来获取当前连接数











先把系统参数面板写一下




前几天把tomcat配置崩了





JSoup 解析html 看看可不可以






1528500720765
1528439586119





+ 获取任务开始时间
+ 现在的问题是，前后端的时间没问题了，但是数据库的时间对不上



考虑是后端bean的时间转换注解的问题


先切换到本地服务器，然后看一下安卓端上传上来的数据情况

如若有问题，那就把注解的时区转换关掉看看


怀疑所有的注解转换时间都是不需要的


安卓端 上传上来的数据，时间就是多了8个小时的


安卓端的转换器改一下就好了，现在是回来的数据少了8小时

web端 输出那里的注解时区去掉看看


数据库是正确的时间，那么数据库到服务器呢？




还是安卓端在静态代码块里写了时区设置的原因

已经调整好了，先提交一把


下一个问题是

闹钟页面没有显示好闹钟信息






数据分页忘了做




