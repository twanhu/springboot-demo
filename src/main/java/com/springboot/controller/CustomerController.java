package com.springboot.controller;

import com.springboot.bean.Customer;
import com.springboot.service.CustomerService;
import com.springboot.service.impl.CustomerServiceImplNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

class MyFactoryUtils {

    public static CustomerService newInstance() {
        //return new CustomerServiceImpl();
        return new CustomerServiceImplNew();
    }
}

/**
 * 控制层
 */
//@Controller     //标识为控制层（Spring）
@RestController     //标识为控制层，将请求方法的值返回,相当于 @Controller + @ResponseBody
public class CustomerController {

    //直接在类中创建对象，不好，写死了，不能修改成其他(业务层)对象
    //CustomerServiceImpl customerService = new CustomerServiceImpl();

    //接口 + 工厂模式（定义了一个创建对象的类，由这个类来封装实例化对象的行为）
    //CustomerService customerService = MyFactoryUtils.newInstance();

    //SpringBoot
    @Autowired  //从Spring容器中找到对应类型的对象，注入过来
    @Qualifier("customerServiceImplNew")  //明确指定将哪个对象注入过来，不能注入多个对象
            CustomerService customerService;

    /**
     * http://localhost:8080/login?username=wanhu&password=123456
     */
    @GetMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        //业务(服务)处理
        //在每个方法中创建业务层对象，不好，多个方法需要创建多个业务层对象
        //CustomerServiceImpl customerService = new CustomerServiceImpl();
        String result = customerService.doLogin(username, password);
        return result;
    }

    /**
     * 常见的状态码:
     *
     *  200 : 表示请求处理成功且响应成功
     *  302 : 表示进行重定向
     *  400 : 表示请求参数有误
     *  404 : 表示请求地址或者资源不存在
     *  405 : 表示请求方式不支持
     *  500 : 表示服务器端处理异常.
     */

    /**
     * 请求方式 : GET   POST    PUT    DELETE ......
     * <p>
     * GET : 读
     * <p>
     * POST : 写
     * <p>
     * http://localhost:8080/requestmethod
     */
    //@RequestMapping(value = "requestmethod", method = RequestMethod.GET)
    //@PostMapping("requestmethod")
    @GetMapping("requestmethod")
    public String requestmethod() {
        return "success";
    }

    /**
     * 客户端请求：http://localhost:8080/HelloWorld
     * <p>
     * 请求处理方法
     *
     * @RequestMapping : 将客户端的请求与方法进行映射
     * @ResponseBody : 将方法的返回值处理成字符串（json字符串返回给客户端）
     */
    @RequestMapping("HelloWorld")
    //@ResponseBody
    public String helloWorld() {
        return "hello world";
    }

    /**
     * 请求参数：
     * 1、地址栏中的kv格式的参数
     * 2、嵌入到地址栏的参数
     * 3、封装到请求体中的参数
     */

    /**
     * 1、地址栏中的kv格式的参数
     * <p>
     * http://localhost:8080/paramkv?username=wanhu&age=22
     *
     * @RequestParam : 将kv格式的请求参数映射到方法对应的形参上，
     * 如果请求参数名与方法形参名一致，可以直接进行参数值的映射，可以省略@RequestParam
     */
    @RequestMapping("paramkv")
    public String paramkv(@RequestParam("username") String name, @RequestParam("age") Integer age) {

        return "username = " + name + ", age = " + age;
    }

    /**
     * 2、嵌入到地址栏中的参数
     * <p>
     * http://localhost:8080/parampath/wanhu/19?address=beijing
     *
     * @PathVariable : 将请求路径中的参数映射到请求方法对应的形参上
     */
    @RequestMapping("parampath/{username}/{age}")
    public String parampath(@PathVariable("username") String username,
                            @PathVariable("age") Integer age,
                            @RequestParam("address") String address) {

        return "username = " + username + ", age = " + age + ", address = " + address;
    }

    /**
     * 3、封装到请求体中的参数
     * <p>
     * http://localhost:8080/parambody
     * <p>
     * 请求体中的参数
     * username=wanhu
     * password=123456
     * <p>
     * 如果请求参数名与方法名的形参名不一致，需要通过@RequestParam来标识获取
     * 如果一致，可以直接映射
     *
     * @RequestBody : 将请求体中的json格式的参数映射到对象对应的属性上
     */
    @RequestMapping("parambody")
    public Customer parambody(@RequestBody Customer customer) {
        return customer;    //转换成json字符串返回
    }

//    @RequestMapping("parambody")
//    public String parambody(String username, String password) {
//
//        return "username = " + username + ", password = " + password;
//    }

}
