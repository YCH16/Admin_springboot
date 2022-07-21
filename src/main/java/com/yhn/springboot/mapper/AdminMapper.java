package com.yhn.springboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhn.springboot.entity.Admin;
import com.yhn.springboot.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
   /* @Select("select * from sys_user;")
    List<User> finaAll();

    @Insert("insert INTO sys_user(username,password,nickname,email,phone,address) VALUES(#{username}," +
            "#{password},#{nickname},#{email},#{phone},#{address});")
    int insert(User user);


    int update(User user);

    @Delete("delete from sys_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    @Select("select * from sys_user where username like concat('%',#{username},'%') limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String username);

    @Select("select count(*) from sys_user where username like concat('%',#{username},'%')")
    Integer selectTotal(String username);*/
   @Select("select * from manager ")
   Admin getbyusername();
   Admin selectByUsernameAndPassword(Admin admin);
}
