package com.qz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("qz_user")
public class User extends BasePoJo{
    @TableId
    private String qzId;          //用户主键 不自增
    private String qzUsername;    //用户名
    private String qzPassword;    //密码
    private String qzEmail;       //邮箱
    private Date   qzBirthday;    //生日
    private String qzPhone;       //手机
    private String qzPhoto;       //头像
}
