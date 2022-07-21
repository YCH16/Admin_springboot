package com.yhn.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhn.springboot.entity.Major;

//@Mapper
public interface MajorMapper extends BaseMapper<Major> {
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
}
