package com.du.mybatis0917.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Board {
    private Integer id;
    private String title;
    private String content;
    private Date createdAt;
}
