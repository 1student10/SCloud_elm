package com.elm.service;

import com.elm.po.User;

public interface UserService {
    public int GetUserByName(String username);
    public User getUserByIdByPass(User user);
    public int getUserById(String userId);
    public int saveUser(User user);
}
