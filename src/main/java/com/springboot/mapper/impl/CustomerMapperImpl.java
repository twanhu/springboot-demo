package com.springboot.mapper.impl;

import com.springboot.bean.Customer;
import com.springboot.mapper.CustomerMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 数据层组件
 */
@Repository     //标识成数据层组件（Spring）-> Spring会自动创建该类的对象（单例），并管理到Spring容器中
//默认的名字就是类名首字母小写形式 -> customerServiceImplNew
//@Service(value = "cmi")
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer searchByUsernameAndPassword(String username, String password) {

        System.out.println("CustomerMapperImpl: 数据库的查询操作......");
        //JDBC
        //MyBatis,不需要自己写实现类,只写接口+SQL即可
        //......
        return new Customer(username, password, 18, "男");
        //return null;
    }
}

