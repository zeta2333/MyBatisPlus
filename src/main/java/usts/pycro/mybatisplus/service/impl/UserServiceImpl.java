package usts.pycro.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import usts.pycro.mybatisplus.mapper.UserMapper;
import usts.pycro.mybatisplus.pojo.User;
import usts.pycro.mybatisplus.service.UserService;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-06 1:54 PM
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
