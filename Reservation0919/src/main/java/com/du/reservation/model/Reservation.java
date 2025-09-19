package com.du.reservation.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Reservation {
    private Long id;
    private String customerName;     // 고객명
    private Date reservationDate;    // 예약일
    private List<ReservationItem> items; // 예약 항목들
}

