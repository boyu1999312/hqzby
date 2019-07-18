package com.qz.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class BasePoJo implements Serializable {
    private Date created;   //创建时间
    private Date updated;   //修改时间
}
