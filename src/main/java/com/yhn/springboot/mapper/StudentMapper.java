package com.yhn.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhn.springboot.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select * from student;")
    List<Student> finaAll();

    @Insert("insert INTO student(username,password,truename,gender,tele,coll,classname) VALUES(#{username}," +
            "#{password},#{truename},#{gender},#{tele},#{coll},#{classname});")
    int insert(Student student);

    int insertAndsave(Student student);

    int update(Student student);

    @Delete("delete from student where username = #{username}")
    Integer deletebyusername(@Param("username") String username);

    @Select("select * from student where username = #{username}")
    Student getbyusername(@Param("username") String username);

    /*@Select("select * from sys_user where username like concat('%',#{username},'%') limit #{pageNum},#{pageSize}")
    List<Student> selectPage(Integer pageNum, Integer pageSize, String username);

    @Select("select count(*) from sys_user where username like concat('%',#{username},'%')")
    Integer selectTotal(String username);*/
    //login
    /*int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);*/

    Student selectByUsernameAndPassword(Student student);


    //login
}
