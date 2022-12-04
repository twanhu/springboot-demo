package com.springboot.service.impl;

import com.springboot.mapper.CustomerMapper;
import com.springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    //标识成业务层(服务层)组件 (Spring)  -> Spring会自动创建该类的对象（单例），并管理到Spring容器中
//默认的名字就是类名首字母小写形式 -> customerServiceImpl
//@Service(value = "csi")
public class CustomerServiceImpl implements CustomerService {

    @Override
    public String doLogin(String username, String password) {

        System.out.println("CustomerServiceImpl : 复杂的业务处理");
        //数据非空校验
        //数据格式的校验
        //......
        //调用数据层，比对数据库中的数据是否一致

        return "ok";
    }

}
