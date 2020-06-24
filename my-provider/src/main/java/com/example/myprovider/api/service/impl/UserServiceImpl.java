package com.example.myprovider.api.service.impl;

import com.example.myprovider.api.entity.User;
import com.example.myprovider.api.mapper.UserMapper;
import com.example.myprovider.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CodeGenerator
 * @since 2020-06-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
