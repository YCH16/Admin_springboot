package com.yhn.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhn.springboot.entity.Major;
import com.yhn.springboot.mapper.MajorMapper;

import org.springframework.stereotype.Service;

@Service
public class MajorService extends ServiceImpl<MajorMapper, Major> {
    public boolean saveMajor(Major major) {
       /* if (user.getId() == null) {
            return save(user);//插入数据
        }else{
            return updateById(user);*/
        return saveOrUpdate(major);
    }

    /*@Autowired
    private UserMapper userMapper;*/

    /*public int save(User user){
        if(user.getId()==null){// 没有id则新增
            return userMapper.insert(user);
        }else{
            return userMapper.update(user);
        }
    }*/
}