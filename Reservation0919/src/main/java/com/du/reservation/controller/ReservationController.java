package com.du.reservation.controller;

import com.du.reservation.model.Reservation;
import com.du.reservation.model.ReservationItem;
import com.du.reservation.service.MyServiceService;
import com.du.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MyServiceService serviceService;

    // 예약 목록
    @GetMapping
    public String list(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservation_list";
    }

    // 새 예약 등록 폼
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("services", serviceService.getAllServices());
        return "reservation_form";
    }

    // 예약 등록 처리
    @PostMapping
    public String create(Reservation reservation, @RequestParam("serviceId") List<Long> serviceIds, @RequestParam("quantity") List<Integer> quantities) {
        reservation.setReservationDate(new Date());  // 현재 날짜 자동 설정
        reservationService.addReservation(reservation);

        for (int i = 0; i < serviceIds.size(); i++) {
            ReservationItem item = new ReservationItem();
            item.setReservationId(reservation.getId());
            item.setServiceId(serviceIds.get(i));
            item.setQuantity(quantities.get(i));
            reservationService.addReservationItem(item);
        }

        return "redirect:/reservations";
    }

    // 예약 상세 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
//        Reservation reservation = reservationService.getAllReservations()
//                .stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
        Reservation reservation = reservationService.getReservationById(id);

        if (reservation == null) {
            return "redirect:/reservations";
        }

        List<ReservationItem> items = reservationService.getItemsByReservationId(id);
        model.addAttribute("reservation", reservation);
        model.addAttribute("items", items);
        return "reservation_detail";
    }

    // 예약 삭제
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }
}

