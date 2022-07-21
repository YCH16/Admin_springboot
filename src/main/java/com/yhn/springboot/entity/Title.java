package com.yhn.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "subject")
public class Title {
    private Double sid;
    private Integer score;
    private Integer mno;
    private String type;
    private String mname;
    private String content;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String optione;
    private String radio;
    private Integer aid;
    private String username;
    private Integer pid;
    private String studentkey;
    private String time;

}












//@Data
//@TableName(value = "sys_user")
//public class User {
//
//    @TableId(type = IdType.AUTO)
//    private Integer id;
//    private String username;
//    private String password;
//    private String nickname;
//    private String email;
//    private String phone;
//    private String address;
//
//    @TableField(value = "avatar_url")  // 指定数据库的字段名称
//    private String avatar;
//
//}