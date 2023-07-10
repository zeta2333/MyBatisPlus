package usts.pycro.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import usts.pycro.mybatisplus.mapper.ProductMapper;
import usts.pycro.mybatisplus.mapper.UserMapper;
import usts.pycro.mybatisplus.pojo.Product;
import usts.pycro.mybatisplus.pojo.User;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-10 10:43 AM
 */
@SpringBootTest
public class MyBatisPlusPluginsTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testPaginate() {
        Page<User> page = new Page<>(2, 3);
        // selectPage方法对传入的page对象直接修改，将查询结果及相关数据存入page对象
        userMapper.selectPage(page, null);
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());
    }

    @Test
    public void testPageVo() {
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPageVo(page, 20);
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());
    }

    @Test
    public void testProduct01() {
        // 小李查询商品价格
        Product prodLi = productMapper.selectById(1);
        System.out.println("小李查询商品价格为：" + prodLi.getPrice());

        // 小王查询商品价格
        Product prodWang = productMapper.selectById(1);
        System.out.println("小王查询商品价格为：" + prodWang.getPrice());

        // 小李使商品价格+50
        prodLi.setPrice(prodLi.getPrice() + 50);
        productMapper.updateById(prodLi);

        // 小王使商品价格-30
        prodWang.setPrice(prodWang.getPrice() - 30);
        int res = productMapper.updateById(prodWang);
        if (res == 0) {
            // 操作失败，重试
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
        }

        // 老板查看商品价格
        Product prodBoss = productMapper.selectById(1);
        System.out.println("老板查看商品价格为：" + prodBoss.getPrice());
    }
}
