package org.example.naco.service;

import org.example.naco.domain.User;
import org.example.naco.mapper.UserMapper;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void register(String username, String rawPassword) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("아이디를 입력해주세요.");
        }
        if (rawPassword == null || rawPassword.isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        if (rawPassword.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }
        username = username.trim();
        User existing = userMapper.findByUsername(username);
        if (existing != null) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(encoder.encode(rawPassword));
        user.setRole("USER");
        userMapper.insert(user);
    }

    @Override
    public User authenticate(String username, String rawPassword) {
        User user = userMapper.findByUsername(username);
        if (user == null) return null;
        if (!encoder.matches(rawPassword, user.getPasswordHash())) return null;
        return user;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void changeRole(Long id, String role) {
        if (id == null) throw new IllegalArgumentException("id is required");
        if (role == null || (!"USER".equals(role) && !"ADMIN".equals(role))) {
            throw new IllegalArgumentException("role must be USER or ADMIN");
        }
        userMapper.updateRole(id, role);
    }
}
