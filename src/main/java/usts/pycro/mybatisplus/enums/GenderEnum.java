package usts.pycro.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-10 1:03 PM
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue // 将注解所标识的属性的值存储到数据库中
    private final Integer gender;

    private final String genderName;
}
