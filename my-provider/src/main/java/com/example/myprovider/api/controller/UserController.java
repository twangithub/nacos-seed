package com.example.myprovider.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.myprovider.common.controller.CurdController;
import com.example.myprovider.api.entity.User;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2020-06-24
*/
@RestController
@RequestMapping("/api/user")
public class UserController extends CurdController<User> {

}
