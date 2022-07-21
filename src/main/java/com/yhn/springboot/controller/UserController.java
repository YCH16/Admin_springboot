package com.yhn.springboot.controller;

import com.yhn.springboot.entity.Student;
import com.yhn.springboot.entity.Studentinfo;
import com.yhn.springboot.entity.Title;
import com.yhn.springboot.mapper.UserMapper;
import com.yhn.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;



    @PostMapping
    public Integer save(@RequestBody Title title) {
        // 新增或者更新
        return userService.save(title);
    }

    // 查询所有数据
    @GetMapping
    public List<Title> findAll() {
        List<Title> all = userMapper.findAll();
        return all;
    }

    @GetMapping("/single")
    public String getmname(@RequestParam String major){
        String mno=userMapper.getmno(major);
        return mno;
    }


    //删除
    @DeleteMapping("/{sid}")
    public Integer delete(@PathVariable Integer sid) {
        return userMapper.deleteById(sid);
    }

    //批量删除题目
    @PostMapping("/delsubjects")
    public Integer deleteBatchsubject(@RequestBody String sids) {
        return userMapper.deletesubjects(sids);
    }

    //批量删除试卷
    @PostMapping("/delpapers")
    public Integer deleteBatchpaper(@RequestBody String pids) {
        return userMapper.deletepapers(pids);
    }

//    @DeleteMapping("/batch/{sid}")
//    public boolean deleteBatch(@RequestBody List<Integer> sids) {
//        return userMapper.deleteById()
//    }

    // 分页查询
    //  接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
//    limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String mname,
                                        @RequestParam String type,
                                        @RequestParam String content) {
        pageNum = (pageNum - 1) * pageSize;
        content = "%" + content + "%";
        mname = "%" + mname + "%";
        type = "%" + type + "%";
        List<Title> data = userMapper.selectPage(pageNum, pageSize,mname,type,content);
        Integer total = userMapper.selectTotal(mname,type,content);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }


    @GetMapping("/paper")
    public Map<String, Object> findpaperPage(@RequestParam Integer pageNum,
                                             @RequestParam Integer pageSize,
                                             @RequestParam String username,
                                             @RequestParam String classname,
                                             @RequestParam String truename,
                                             @RequestParam String pid,
                                             @RequestParam String coll) {
        pageNum = (pageNum - 1) * pageSize;
        Integer total = userMapper.selectpaperTotal(username,classname,truename,pid,coll);
        username = "%" + username + "%";
        classname = "%" + classname + "%";
        truename = "%" + truename + "%";
        pid = "%" + pid + "%";
        coll = "%" + coll + "%";
        List<Studentinfo> data = userMapper.selectpaperPage(pageNum, pageSize,username,classname,truename,pid,coll);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @GetMapping("/answer")
    public List<Title> lookanswerPage(@RequestParam Integer pid) {
        List<Title> ans = userMapper.selectanswerPage(pid);
        return ans;
    }

}