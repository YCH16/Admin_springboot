package com.yhn.springboot.service;

import com.yhn.springboot.entity.Title;
import com.yhn.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    public int save(Title title) {
        if (title.getSid()==null){  // title没有sid，则表示是新增
            return userMapper.insert(title);
        } else { // 否则为更新
            return userMapper.update(title);
        }
    }

}