package com.xj.service;

import com.xj.domain.Picture;
import com.xj.domain.User;

/**
 * Created by sheeran on 2017/5/28.
 * Talk is cheap show me code.
 */
public interface UserService {

    boolean isEmailExist(String email);

    boolean isPhoneExist(String phone);

    User checkLoginUser(String emailOrPhone, String password);

    void register(User user);

    void resetPassowrd(String phone, String newpassword);

    void updatePersonInfo(User user);

    void updatePersonInfo(User user,Picture picture);

    void updateGrade(int user_id, int grade);

    void deleteUser(int user_id);

    void active(String uuid);
}
