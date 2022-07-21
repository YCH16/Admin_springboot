package com.yhn.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhn.springboot.entity.Major;
import com.yhn.springboot.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private MajorService majorService;
    //新增和修改
    @PostMapping()
    public boolean save(@RequestBody Major major) {//新增或更新

        return majorService.saveMajor(major);
    }

    //查询所有数据
    @GetMapping
    public List<Major> findALl(){
        return majorService.list();
    }
    //删除数据
    @DeleteMapping("/{mno}")
    public boolean delete(@PathVariable Integer mno){
        return majorService.removeById(mno);
    }

    /*//分页查询
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String username){//页id，页大小
        pageNum=(pageNum-1)*pageSize;
        username='%'+username+'%';
        List<User> data = userMapper.selectPage(pageNum,pageSize,username);
        Integer total = userMapper.selectTotal(username);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }*/

    //分页查询 mybatis-x的方式
    @GetMapping("/page")
    public IPage<Major> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String mname
                                 ){//页id，页大小
        IPage<Major> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Major> queryWrapper = new QueryWrapper<>();
        if(!"".equals(mname)){
            queryWrapper.like("mname",mname);
        }

        return majorService.page(page,queryWrapper);
    }
}
