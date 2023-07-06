package usts.pycro.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
