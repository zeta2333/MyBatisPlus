package usts.pycro.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import usts.pycro.mybatisplus.pojo.User;

import java.util.Map;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-06 10:51 AM
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    @MapKey("id")
    Map<String, Object> selectMapById(@Param("id") Long id);

    /**
     * 通过年龄查询用户信息并分页
     * @param page MyBatis-Plus所提供的分页对象，必须位于第一个参数的位置
     * @param age
     * @return
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
}
