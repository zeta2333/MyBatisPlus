package usts.pycro.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import usts.pycro.mybatisplus.mapper.UserMapper;
import usts.pycro.mybatisplus.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-07 11:54 AM
 */
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        // 查询用户名包含a，年龄在20到30之间，邮箱信息不为null的记录
        // SELECT uid AS id,user_name AS name,age,email,is_deleted
        // FROM t_user
        // WHERE is_deleted=0
        // AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", "a").between("age", 20, 30).isNotNull("email");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test02() {
        // 查询用户信息，按照年龄降序排序，若年龄相同，则按照id升序排序
        // SELECT uid AS id,user_name AS name,age,email,is_deleted
        // FROM t_user
        // WHERE is_deleted=0
        // ORDER BY age DESC,uid ASC
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age").orderByAsc("uid");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testDelete() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNull("email");
        int res = userMapper.delete(wrapper);
        System.out.println("res = " + res);
    }

    @Test
    public void testUpdate() {
        // 将(年龄大于20并且用户名中包含有a)或邮箱为nulL的用户信息修改
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20).like("user_name", 'a').or().isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("xm@123.com");
        int update = userMapper.update(user, wrapper);
        System.out.println("update = " + update);
    }

    @Test
    public void testUpdate2() {
        // 将用户名中包含有a且 (年龄大于20或邮箱为null) 的用户信息修改
        // Lambda 中的条件优先执行
        // UPDATE t_user SET user_name=?, email=?
        // WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setName("小红");
        user.setEmail("xm@123.com");
        int res = userMapper.update(user, wrapper);
        System.out.println("res = " + res);
    }

    @Test
    public void testSelect2() {
        // SELECT user_name,age,email FROM t_user WHERE is_deleted=0
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("user_name", "age", "email");
        List<Map<String, Object>> list = userMapper.selectMaps(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testSubQuery() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("uid", "select uid from t_user where uid <= 100");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);

    }

    @Test
    public void testUpdateWrapper() {
        // updateWrapper：可以设置修改的条件，也可以设置需要修改的字段
        // 将用户名中包含有a且 (年龄大于20或邮箱为null) 的用户信息修改
        // UPDATE t_user SET user_name=?,email=?
        // WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("user_name", "mosaic").set("email", "msk@123.com");
        int res = userMapper.update(null, updateWrapper);
        System.out.println("res = " + res);
    }

    @Test
    public void testMultiCondition() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 判断username不为null，不为空串，不为空白符
        if (StringUtils.isNotBlank(username)) {
            wrapper.like("user_name", username);
        }
        if (ageBegin != null) {
            wrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            wrapper.le("age", ageEnd);
        }
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);

    }

    @Test
    public void testQueryByCondition() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(username), "user_name", username).ge(ageBegin != null, "age", ageBegin).le(ageEnd != null, "age", ageEnd);
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testLambdaQW() {
        // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
        // WHERE is_deleted=0 AND (user_name LIKE ? AND age <= ?)
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(username), User::getName, username).ge(ageBegin != null, User::getAge, ageBegin).le(ageEnd != null, User::getAge, ageEnd);
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testLambdaUW() {
        // updateWrapper：可以设置修改的条件，也可以设置需要修改的字段
        // 将用户名中包含有a且 (年龄大于20或邮箱为null) 的用户信息修改
        // UPDATE t_user SET user_name=?,email=?
        // WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        wrapper.set(User::getName, "mosaic")
                .set(User::getEmail, "msk@123.com");
        int res = userMapper.update(null, wrapper);
        System.out.println("res : " + res);

    }
}
