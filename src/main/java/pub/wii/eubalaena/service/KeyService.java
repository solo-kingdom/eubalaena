package pub.wii.eubalaena.service;

import org.springframework.stereotype.Service;
import pub.wii.eubalaena.model.Key;
import pub.wii.eubalaena.storage.mysql.entity.UserEntity;
import pub.wii.eubalaena.storage.mysql.mapper.KeyMapper;

import java.util.List;

@Service
public class KeyService {

    KeyMapper keyMapper;

    public KeyService(KeyMapper keyMapper) {
        this.keyMapper = keyMapper;
    }

    public void insert(Key key) {
        keyMapper.insert(key);
    }

    public Key get(Key key) {
        return keyMapper.get(key);
    }

    public List<Key> list() {
        return keyMapper.list();
    }

    public Key check(Key key) {
        return keyMapper.check(key);
    }
}