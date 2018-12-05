1.运行测试类（test类），需要制定配置文件，在brp.iml中添加：
 <!--Junit测试配置文件引用类，Junit时使用-->
<sourceFolder url="file://$MODULE_DIR$/src/main/webapp/WEB-INF" type="java-test-resource" />
2.配置maven，指定setting.xml文件
3.env是配置生产环境的一些通用配置
4.工程已经配置tomcat插件，在maven setting文件添加如下：
   <server>
      <id>tomcat7</id>
      <username>tomcat</username>
      <password>tomcat</password>
    </server>

    运行：tomcat7:run-war 即可启动、debug项目
5.建议使用intellij IDEA工具进行开发