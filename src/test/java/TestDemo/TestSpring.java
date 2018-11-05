package TestDemo;
import Dao.UserImpMapper;
import Domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath*:/SpringConfig.xml")

public class TestSpring  {
//    @Test
    public static void main(String[] args) {
        System.out.println("--------------START");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringConfig.xml");
        UserImpMapper userImpMapper = (UserImpMapper) applicationContext.getBean("userImpMapper");
        User user = userImpMapper.selectUserById(1);
        System.out.println(user.getUsername()+"===="+user.getPassword()+"===="+user.getUserId()+"===="+user.getLastIp());
        System.out.println("--------------END");
    }
}
