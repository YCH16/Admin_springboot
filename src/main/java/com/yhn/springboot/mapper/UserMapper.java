package com.yhn.springboot.mapper;


import com.yhn.springboot.entity.Student;
import com.yhn.springboot.entity.Studentinfo;
import com.yhn.springboot.entity.Title;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper{

    @Select("SELECT * from subject")
    List<Title> findAll();

    @Insert("INSERT into subject(mno,score,type,content,optiona,optionb,optionc,optiond,optione,radio) VALUES (#{mno},#{score}, #{type}, #{content}, #{optiona},#{optionb},#{optionc},#{optiond},#{optione},#{radio})")
    int insert(Title title);

    //    @Update("update subject SET major=#{major},type=#{type},content=#{content},A=#{A},B=#{B},C=#{C},D=#{D},E=#{E} WHERE sid = #{sid}")
    int update(Title title);

    @Select("SELECT mno from major where mname=#{major}")
    String getmno(String major);

    Integer deletesubjects(@Param("sids") String sids);  //批量删除试题

    Integer deletepapers(@Param("pids") String pids);  //批量删除试卷

    @Delete("delete from subject where sid = #{sid}")
    Integer deleteById(@Param("sid") Integer sid);

    @Select("SELECT * FROM (select subject.score,subject.sid,type.type,subject.content,major.mname from subject,major,type where major.mno=subject.mno and type.tid=subject.type and major.mname like #{mname} and subject.content like #{content} and type.type like #{type})AS tab LIMIT #{pageNum},#{pageSize}")
    List<Title> selectPage(Integer pageNum, Integer pageSize,String mname,String type,String content);

    @Select("SELECT count(*) FROM (select subject.score,subject.sid,type.type,subject.content,major.mname from subject,major,type where major.mno=subject.mno and type.tid=subject.type and major.mname like #{mname} and subject.content like #{content} and type.type like #{type})AS tab")
    Integer selectTotal(String mname,String type,String content);

    //    where major like #{major} limit #{pageNum}, #{pageSize}
    @Select("select * from student,answer where student.username=CONVERT(answer.username USING utf8) COLLATE utf8_czech_ci and answer.username like #{username} and classname like #{classname} and truename like #{truename} and pid like #{pid} and coll like #{coll} group by pid limit #{pageNum}, #{pageSize}")
    List<Studentinfo> selectpaperPage(Integer pageNum, Integer pageSize, String username, String classname, String truename, String pid, String coll);

    //    where major like concat('%', #{major}, '%')
    @Select("SELECT COUNT(tab.pid) FROM (select student.username,student.truename,student.gender,student.tele,student.coll,student.classname,answer.aid,answer.sid,answer.pid,answer.studentkey,answer.time from student,answer where student.username=CONVERT(answer.username USING utf8) COLLATE utf8_czech_ci and student.username like concat('%', #{username}, '%') and classname like concat('%', #{classname}, '%') and truename like concat('%', #{truename}, '%') and pid like concat('%', #{pid}, '%') and coll like concat('%', #{coll}, '%') GROUP BY pid) AS tab")
    Integer selectpaperTotal(String username,String classname,String truename,String pid,String coll);

    @Select("select * from answer,subject where answer.pid=#{pid} and answer.sid=subject.sid")
    List<Title> selectanswerPage(Integer pid);

}