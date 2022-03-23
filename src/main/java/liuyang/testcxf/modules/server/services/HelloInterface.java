package liuyang.testcxf.modules.server.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author liuyang
 * @scine 2021/6/2
 */
@WebService
//@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)// 如果request和response对象是类则用这个配置
public interface HelloInterface {

    // 1. 最简示例
    // public String sayHello(); // 20220315 实测 注意暴露为WebService的接口不能重名
    public String sayHello(String name, int age);

    // 2. 常用注解示例
    //@WebMethod(operationName = "xxx")// 不标这个注解照样可以按照cxf的默认方式暴露为Web Service
    //@WebResult(name="")
    //public String foo(@WebParam(name = "", partName = "") String param);
    public String foo();
}
