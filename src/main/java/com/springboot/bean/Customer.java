package com.springboot.bean;

import lombok.*;

//需要在IDEA中安装LomBok插件
@NoArgsConstructor      //无参构造
@AllArgsConstructor     //全参构造
@Getter                 //getter()
@Setter                 //setter()
@ToString               //toString()
public class Customer {

    private String username;
    private String password;
    private Integer age;
    private String gender;

}