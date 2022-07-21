package com.yhn.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhn.springboot.common.Result;
import com.yhn.springboot.entity.Student;
import com.yhn.springboot.mapper.StudentMapper;
import com.yhn.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;
    //新增和修改
    //service方法
    /*@PostMapping()
    public boolean save(@RequestBody Student student) {//新增或更新

        return studentService.saveStudent(student);
    }*/
    @PostMapping("/save")
    public Integer save(@RequestBody Student student) {//新增

        return studentMapper.insertAndsave(student);
    }
    @PostMapping("/update")
    public Integer updateAndsave(@RequestBody Student student) {//修改

        return studentMapper.update(student);
    }
    //login
    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        if (!checkParam(student)) {
            return Result.error("-1", "缺少必要参数");
        }
        Student dbUser = studentMapper.selectByUsernameAndPassword(student);
        if (dbUser == null) {
            return Result.error("-1", "账号或密码错误");
        }
        return Result.success(dbUser);
    }

    private boolean checkParam(Student student) {
        return student.getUsername() != null && student.getPassword() != null;
    }
    //login

   /* @GetMapping("/{username}")
    public Student findMyself(@PathVariable String username){
        return studentMapper.getbyusername(username);
    }*/
    //查询所有数据
    @GetMapping
    public List<Student> findALl(){
        return studentService.list();
    }
    //删除数据
    @DeleteMapping("/{username}")
    public Integer delete(@PathVariable String username){
        return studentMapper.deletebyusername(username);
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
    public IPage<Student> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String truename,
                                   @RequestParam(defaultValue = "") String username,
                                   @RequestParam(defaultValue = "") String coll,
                                   @RequestParam(defaultValue = "") String classname){//页id，页大小
        IPage<Student> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("truename",truename);
        queryWrapper.like("classname",classname);
        queryWrapper.like("username",username);
        queryWrapper.like("coll",coll);
        return studentService.page(page,queryWrapper);
    }
}
