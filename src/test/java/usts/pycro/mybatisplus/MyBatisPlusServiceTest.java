package usts.pycro.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import usts.pycro.mybatisplus.pojo.User;
import usts.pycro.mybatisplus.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-06 1:56 PM
 */
@SpringBootTest
public class MyBatisPlusServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetCount() {
        // 查询总记录数
        // SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testBatchSave() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("p" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        // 批量添加
        boolean b = userService.saveBatch(list);
        System.out.println("添加结果：" + b);
    }
}
