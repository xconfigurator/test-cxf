# CXF 暴露服务和调用测试台

### 测试Mobile WebService
```
2021-05-27 15:21:30.134  INFO 15804 --- [           main] l.testcxf.modules.mobile.stub.MobilTest  : URL_WSDL = http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx
2021-05-27 15:21:30.197  INFO 15804 --- [           main] o.a.c.w.s.f.ReflectionServiceFactoryBean : Creating Service {http://WebXml.com.cn/}MobileCodeWSSoapService from class liuyang.testcxf.modules.mobile.stub.MobileCodeWSSoap
2021-05-27 15:21:31.804  INFO 15804 --- [           main] l.testcxf.modules.mobile.stub.MobilTest  : info1 = 18931160972：河北 石家庄 河北电信CDMA卡
2021-05-27 15:21:31.929  INFO 15804 --- [           main] l.testcxf.modules.mobile.stub.MobilTest  : info2 = 13653387445：河北 石家庄 河北移动神州行卡
2021-05-27 15:21:32.102  INFO 15804 --- [           main] l.testcxf.modules.mobile.stub.MobilTest  : info3 = 13703297271：河北 石家庄 河北移动全球通卡
2021-05-27 15:21:32.227  INFO 15804 --- [           main] l.testcxf.modules.mobile.stub.MobilTest  : info4 = 13784355862：河北 石家庄 河北移动全球通卡
```

### 
202106011321 增加CXF最简示例


###
202203171549 增加连接阿里云https服务


#### Nginx代理方式
测试点1. 生成针对https服务的存根(使用域名)(最标准且理想的情况，但内网应用可能不会满足)
预期: 服务可以连接成功
```shell
wsdl2java -encoding utf-8 -p liuyang.testcxf.modules.client.hello.aliyun.dns.stub -client https://xconfigurator.cn/soap/hello?wsdl
```
测试点2. 生成针对https服务的存个（使用ip）
预期：可能不会成功（DNS验证问题）
```shell
wsdl2java -encoding utf-8 -p liuyang.testcxf.modules.client.hello.aliyun.ip.stub -client https://39.104.173.57/soap/hello?wsdl
```
结果：实际在运行wsdl2java的时候就会出问题：
WSDLToJava Error: org.apache.cxf.wsdl11.WSDLRuntimeException: Fail to create wsdl definition https://39.104.173.57/soap/hello?wsdl: WSDLException: faultCode=PARSER_ERROR: Problem parsing 'https://39.104.173.57/soap/hello?wsdl'.: javax.net.ssl.SSLHandshakeException: java.security.cert.CertificateException: No subject alternative names matching IP address 39.104.173.57 found
符合预期。
与是否使用CA无关。因为服务器挂的证书使用的就是真正的CA签发。
但这种方式在内网应用联通的时候应该会很普遍。需要解决：
    思路1：是否可以通过忽略dns验证来解决。
    思路2：是否可以在客户端导入证书来解决。


测试点3. 两种stub是否可以混用。（目的是验证stub与dns/ip是否有锁定关系）
TODO

#### Tomcat直接配置TLS方式验证


#### SpringBoot（内嵌Tomcat配置TLS方式）



测试点2. 利用原来的本地存根
预期：希望调整WSDL_URL之后，原有存根依然可以使用
测试点3. 利用Spring WebServices/WebServiceTemplate

