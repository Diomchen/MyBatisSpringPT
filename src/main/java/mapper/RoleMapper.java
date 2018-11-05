package mapper;

import Domain.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Repository表明这是一个DAO层
//@Repository
public interface RoleMapper {
    User selectUserById(@Param("userId") int userid);
}
