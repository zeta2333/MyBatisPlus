package usts.pycro.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import usts.pycro.mybatisplus.enums.GenderEnum;

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
    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;

    // 指定属性所对应的字段名
    @TableField("user_name")
    private String name;

    private Integer age;

    private GenderEnum gender;

    private String email;

    // 逻辑删除
    @TableLogic
    private Integer isDeleted;
}
