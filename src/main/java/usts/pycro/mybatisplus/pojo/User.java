package usts.pycro.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-06 10:38 AM
 */
@Data
// 设置实体类所对应的表名
@TableName("t_user")
public class User {

    // 将属性所对应的字段设置为主键
    // @TableId的value属性用于指定主键的字段
    @TableId(value = "uid",type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;
}
