package pub.wii.eubalaena.storage.mysql.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pub.wii.eubalaena.storage.mysql.entity.UserEntity;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT id,username,email from user")
    List<UserEntity> list();

    @Insert("INSERT INTO user(username,password,email) VALUES(#{username},#{password},#{email})")
    void insert(UserEntity user);

    @Select("SELECT id,username,email from user where id=#{id}")
    UserEntity get(int id);
}
