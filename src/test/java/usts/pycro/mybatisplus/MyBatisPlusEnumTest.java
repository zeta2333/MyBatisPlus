package usts.pycro.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import usts.pycro.mybatisplus.enums.GenderEnum;
import usts.pycro.mybatisplus.mapper.UserMapper;
import usts.pycro.mybatisplus.pojo.User;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-10 1:16 PM
 */
@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("admin");
        user.setAge(32);
        user.setGender(GenderEnum.MALE);
        int res = userMapper.insert(user);
        System.out.println("res = " + res);
    }

}
