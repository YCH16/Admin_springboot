package com.yhn.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "student")
public class Studentinfo {
    private String username;
    private String truename;
    private String gender;
    private String tele;
    private String coll;
    private String classname;
    private Integer pid;
    private String time;
}
