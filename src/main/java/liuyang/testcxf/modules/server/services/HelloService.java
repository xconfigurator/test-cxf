package liuyang.testcxf.modules.server.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
        log.info("sayHello invoked begin");
        // 测试超时 begin
        /*try {
            TimeUnit.MILLISECONDS.sleep(10001);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }*/
        // 测试超时 end
        log.info("sayHello invoked end");
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
