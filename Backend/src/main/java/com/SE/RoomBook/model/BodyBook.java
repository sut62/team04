package com.SE.RoomBook.model;

import java.sql.Date;

import lombok.*;

// model for  request body
@Data
public class BodyBook {
    private Long customerid;
    private Long roomid;
    private String[] purosebook;
    private Date start;
    private Date end;
}
