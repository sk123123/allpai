package com.allpai.ribbit;

import com.allpai.ribbit.ceshi.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/24 0024 0:26
 * 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApRibbitmqApplicationTest {

    @Autowired
    private Sender sender;

    @Test
    public void hello(){
        sender.send();
    }

}