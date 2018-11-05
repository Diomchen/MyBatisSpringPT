package Dao;

import Domain.User;
import mapper.RoleMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserImpMapper {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public User selectUserById(int userid) {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("SpringConfig.xml");
//        SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate) ctx.getBean(String.valueOf(SqlSessionTemplate.class));
//        RoleMapper roleMapper = (RoleMapper) ctx.getBean(String.valueOf(RoleMapper.class));
//        RoleMapper roleMapper = (RoleMapper) ctx.getBean("RoleMapper");
//        User user = roleMapper.selectUserById(userid);
        RoleMapper roleMapper = sqlSessionTemplate.getMapper(RoleMapper.class);
        User user = roleMapper.selectUserById(userid);

        return user;
    }
//
//    public static void main(String[] args) {
//        User u = selectUserById(1);
//        System.out.println(u.toString());
//    }
}
