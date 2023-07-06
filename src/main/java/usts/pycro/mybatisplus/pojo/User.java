package usts.pycro.mybatisplus.pojo;

import lombok.Data;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-06 10:38 AM
 */
@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;
}
