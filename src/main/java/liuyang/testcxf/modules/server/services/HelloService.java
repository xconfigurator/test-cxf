package liuyang.testcxf.modules.server.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuyang
 * @scine 2021/6/2
 */
@Service
@Slf4j
@Getter
public class HelloService implements HelloInterface{
    @Override
    public String sayHello(String name, int age) {
        return "hello, cxf : " + name + " " + age;
    }

    /*
    @Override
    public String sayHello() {
        return "foo";
    }
     */

    @Override
    public String foo() {
        return "foo";
    }
}
