package com.ym.yourmeal.imp;

import com.ym.yourmeal.inter.iReservation;
import com.ym.yourmeal.models.Reservation;

import java.util.ArrayList;

public class ReservationManager implements iReservation {
    public ArrayList<Reservation> reservations = new ArrayList <Reservation>();


    static ReservationManager reservationManager = null;

    public static ReservationManager getInstance() {
        if (reservationManager == null) {
            reservationManager = new ReservationManager();
        }
        return reservationManager;
    }

    @Override
    public void setReservations(Reservation reservation) {
        reservations.add(reservation);
    }

    public ArrayList <Reservation> getReservations() {
        return reservations;
    }

}
