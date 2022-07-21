package com.yhn.springboot.service;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhn.springboot.common.Constants;
import com.yhn.springboot.common.Result;
import com.yhn.springboot.controller.dto.AdminDTO;
import com.yhn.springboot.entity.Admin;
import com.yhn.springboot.exception.ServiceException;
import com.yhn.springboot.mapper.AdminMapper;
import com.yhn.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends ServiceImpl<AdminMapper, Admin> {

    public AdminDTO login(AdminDTO adminDTO) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",adminDTO.getUsername());
        queryWrapper.eq("password",adminDTO.getPassword());
        Admin one;
        try {
            one = getOne(queryWrapper);
        }
        catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(one!=null) {
            BeanUtil.copyProperties(one,adminDTO,true);
            String token = TokenUtils.genToken(one.getUsername(),one.getPassword());
            adminDTO.setToken(token);
            return adminDTO;
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    public boolean saveAdmin(Admin admin) {
       /* if (user.getId() == null) {
            return save(user);//插入数据
        }else{
            return updateById(user);*/
        return saveOrUpdate(admin);
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
