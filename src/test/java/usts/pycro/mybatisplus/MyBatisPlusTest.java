package usts.pycro.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import usts.pycro.mybatisplus.mapper.UserMapper;
import usts.pycro.mybatisplus.pojo.User;

import java.util.Arrays;
import java.util.List;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-06 10:55 AM
 */
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testListUser() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        // user.setId(233L);
        user.setName("Pycro");
        user.setAge(23);
        user.setEmail("pycro@qq.com");
        int res = userMapper.insert(user);
        System.out.println("res = " + res);
        System.out.println(user.getId());

    }

    @Test
    public void testDelete() {
        // 通过id删除记录
        // DELETE FROM user WHERE id=?
        /* int res = userMapper.deleteById(1676790938317455361L);
        System.out.println("res = " + res); */

        // 通过map删除记录（map中的所有键值对是并列关系）
        // DELETE FROM user WHERE name = ? AND age = ?
       /*  Map<String, Object> map = new HashMap<>();
        map.put("name", "Pycro");
        map.put("age", 23);
        int res = userMapper.deleteByMap(map);
        System.out.println("res = " + res); */

        // 通过idList批量删除记录
        // DELETE FROM user WHERE id IN ( ? , ? , ? )
        int res = userMapper.deleteBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println("res = " + res);
    }

    @Test
    public void testUpdate() {
        // user中不设置的属性不会出现在sql语句中
        User user = new User();
        user.setId(4L);
        user.setAge(12);
        // UPDATE user SET age=? WHERE id=?
        // UPDATE user SET name=?, age=?, email=? WHERE id=?
        int res = userMapper.updateById(user);
        System.out.println("res = " + res);
    }

    @Test
    public void testSelect() {
        // 通过id查询
        // SELECT id,name,age,email FROM user WHERE id=?
        /* User user = userMapper.selectById(1L);
        System.out.println(user); */

        // 通过idList批量查询
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        /* List<User> userList = userMapper.selectBatchIds(Arrays.asList(1L, 3L, 5L));
        userList.forEach(System.out::println); */

        // 通过map条件查询
        /* Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 28); */
        // SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        /* List<User> userList = userMapper.selectByMap(map);
        System.out.println(userMapper.getClass());
        userList.forEach(System.out::println); */

        // 查询所有数据
        // SELECT id,name,age,email FROM user
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        // Map<String, Object> map = userMapper.selectMapById(1L);
        // System.out.println(map);
    }
}
