package com.muyou.ricecake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TipAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(TipAdminApplication.class, args);
//        RiceCakeNacosApplication.run(AppConstant.APPLICATION_ADMIN_NAME.concat(CustomerApplicationConstant.APPLICATION_CUSTOMER_NAME),TipAdminApplication.class,args);
    }
}
