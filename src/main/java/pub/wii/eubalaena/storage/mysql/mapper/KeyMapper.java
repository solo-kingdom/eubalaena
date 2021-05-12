package pub.wii.eubalaena.storage.mysql.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pub.wii.eubalaena.model.Key;
import pub.wii.eubalaena.storage.mysql.entity.UserEntity;

import java.util.List;

@Mapper
public interface KeyMapper {
    @Select("SELECT * FROM keyt")
    List<Key> list();

    @Insert("REPLACE INTO keyt(name,content) VALUES(#{name},#{content})")
    void insert(Key key);

    @Select("SELECT * FROM keyt WHERE name=#{name}")
    Key get(Key key);

    @Select("SELECT id FROM keyt WHERE name=#{name}")
    Key check(Key key);
}
