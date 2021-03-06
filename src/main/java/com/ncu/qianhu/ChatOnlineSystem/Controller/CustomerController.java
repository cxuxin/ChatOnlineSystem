package com.ncu.qianhu.ChatOnlineSystem.Controller;

import com.ncu.qianhu.ChatOnlineSystem.Bean.Customer;
import com.ncu.qianhu.ChatOnlineSystem.Mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @Author: Cross
 * @Description:
 * @Date: 2018/5/28
 * @Modified by
 */

@RestController
@RequestMapping("/customer/")
public class CustomerController {

    @Autowired
    private CustomerMapper customerMapper;

//    @CrossOrigin
//    @PostMapping("/test")
//    public ResponseEntity<String> test(Customer requestCustomer) {
//        System.out.println(requestCustomer.toString());
//        try {
//            Customer queryCustomer = customerMapper.find(requestCustomer);
//            if (null == queryCustomer) {
//                return new ResponseEntity<>("您输入的用户名不存在", HttpStatus.NOT_FOUND);
//            }
//            else {
//                return new ResponseEntity<>("登录成功\t"+queryCustomer.getGender(), HttpStatus.OK);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("error",HttpStatus.NOT_FOUND);
//        }
//    }

    @CrossOrigin
    @PostMapping(value = "/login",consumes="application/json")
    public ResponseEntity<String> login(@RequestBody Customer requestCustomer) {
        System.out.println(requestCustomer.toString());
        try{
            ArrayList<Customer> queryCustomer = customerMapper.find(requestCustomer);
            if ( queryCustomer.isEmpty()) {
                return new ResponseEntity<>("username not exists", HttpStatus.OK);
            } else if (queryCustomer.get(0).getPassword().equals(requestCustomer.getPassword())) {
                return new ResponseEntity<>("login succeed\t welcome, "+queryCustomer.get(0).getName(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("password wrong", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("error",HttpStatus.OK);
        }
    }
}
