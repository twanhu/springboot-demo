package com.springboot.service.impl;

import com.springboot.bean.Customer;
import com.springboot.mapper.CustomerMapper;
import com.springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service    //标识成业务层(服务层)组件 (Spring)  -> Spring会自动创建该类的对象（单例），并管理到Spring容器中
//默认的名字就是类名首字母小写形式 -> customerServiceImplNew
//@Service(value = "csin")
public class CustomerServiceImplNew implements CustomerService {

    @Autowired  //从Spring容器中找到对应类型的对象，注入过来
    @Qualifier("customerMapperImpl")  //明确指定将哪个对象注入过来，不能注入多个对象
    CustomerMapper customerMapper;

    @Override
    public String doLogin(String username, String password) {

        System.out.println("CustomerServiceImplNew : 复杂的业务处理");
        //数据非空校验
        //数据格式的校验
        //......
        //调用数据层，比对数据库中的数据是否一致
        Customer customer = customerMapper.searchByUsernameAndPassword(username, password);
        if (customer != null) {
            return "ok";
        } else {
            return "error";
        }
    }

}
