package com.app.hotel.common.util;

public class ValidationUtil {
    public static boolean isValidGuestCount(int guestCount, int maxOccupancy) {
        return guestCount <= maxOccupancy;
    }
}
