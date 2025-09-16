package com.du.mybatis0916.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Board {
    private int id;
    private String title;
    private String content;
    private Date createdAt;

}

