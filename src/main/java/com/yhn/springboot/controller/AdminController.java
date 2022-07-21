package com.yhn.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.yhn.springboot.common.Constants;
import com.yhn.springboot.common.Result;
import com.yhn.springboot.controller.dto.AdminDTO;
import com.yhn.springboot.entity.Admin;
import com.yhn.springboot.mapper.AdminMapper;
import com.yhn.springboot.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/admin")
public class AdminController {
        @Resource AdminMapper adminMapper;
        @Autowired
        private AdminService adminService;
        //新增和修改
        @PostMapping()
        public boolean save(@RequestBody Admin admin) {//新增或更新

            return adminService.saveAdmin(admin);
        }
        //login
        @PostMapping("/login")
        public Result login(@RequestBody AdminDTO adminDTO) {
                String username = adminDTO.getUsername();
                String password = adminDTO.getPassword();
                if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
                        return Result.error(Constants.CODE_400,"参数错误");
                }
                AdminDTO dto = adminService.login(adminDTO);
                return Result.success(dto);
               /* if (!checkParam(admin)) {
                        return Result.error("-1", "缺少必要参数");
                }
                Admin dbUser = adminMapper.selectByUsernameAndPassword(admin);
                if (dbUser == null) {
                        return Result.error("-1", "账号或密码错误");
                }
                return Result.success(dbUser);*/
        }

        private boolean checkParam(Admin admin) {
                return admin.getUsername() != null && admin.getPassword() != null;
        }
        //login

        //查询所有数据
        @GetMapping("/get")
        public Admin findALl(){
            return adminMapper.getbyusername();
        }
        //删除数据
        /*@DeleteMapping("/{id}")
        public boolean delete(@PathVariable Integer id){
            return adminService.removeById(id);
        }*/

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
        /*@GetMapping("/page")
        public IPage<Admin> findPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam(defaultValue = "") String mname){//页id，页大小
            IPage<Major> page = new Page<>(pageNum,pageSize);
            QueryWrapper<Major> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("mname",mname);
            return majorService.page(page,queryWrapper);*/



}
