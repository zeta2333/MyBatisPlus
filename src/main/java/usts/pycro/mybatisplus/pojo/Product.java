package usts.pycro.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-10 11:39 AM
 */
@Data
public class Product {

    private Long id;

    private String name;

    private Integer price;

    @Version // 标识乐观锁版本号的字段
    private Integer version;
}
