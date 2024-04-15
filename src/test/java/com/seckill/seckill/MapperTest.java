package com.seckill.seckill;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.seckill.seckill.dao.GoodsMapper;
import com.seckill.seckill.dao.UserMapper;
import com.seckill.seckill.dao.testMapper;
import com.seckill.seckill.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private testMapper testMapper;
    
    @Test
    public void testSelectUser() {
        User user = testMapper.selectById(2);
        System.out.println(user);
    }


    @Autowired
    private GoodsMapper goodsMapper;
    @Test
    public void testSelectMultipleGoods() {
        List<Integer> l = new ArrayList<>();
        l.add(2);
        l.add(3);
        l.add(1);

    }
}
