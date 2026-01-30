package org.example.naco.mapper;

import org.example.naco.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User findByUsername(String username);
    List<User> findAll();
    int updateRole(@Param("id") Long id, @Param("role") String role);
    int insert(User user);
}
