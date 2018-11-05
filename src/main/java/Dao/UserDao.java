package Dao;

import Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
//import springBootP.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

//    public int getMatchCount(String username,String password){
//        String query = "select count(*) from user where username = ? and password = ?";
//        return jdbcTemplate.queryForObject(query,new Object[]{username,password},Integer.class);
//    }

    public User finduserByUserName(final String username){
        String query = "select * from user where username = ?";
        final User user = new User();
        jdbcTemplate.query(query, new Object[]{username}, new RowCallbackHandler() {
//            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user){
        String query = "update user set lastVisit = ?,lastIp = ?,credits=? where userId = ? ";
        jdbcTemplate.update(query,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }


}
