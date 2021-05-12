package pub.wii.eubalaena.storage.mysql.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import pub.wii.eubalaena.storage.mysql.entity.UserEntity;
import pub.wii.eubalaena.storage.mysql.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {
    final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void insert(UserEntity user) {
        userMapper.insert(user);
    }

    public UserEntity get(int id) {
        return userMapper.get(id);
    }

    public List<UserEntity> list() {
        return userMapper.list();
    }
}
