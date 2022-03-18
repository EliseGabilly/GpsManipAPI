package com.example.manip;

public class Utils {

        public static double distance(double startLat, double startLong,
                                      double endLat, double endLong) {
            // converts to radians
            startLat = Math.toRadians(startLat);
            startLong = Math.toRadians(startLong);
            endLat = Math.toRadians(endLat);
            endLong = Math.toRadians(endLong);

            // haversine formula
            double diffLong = endLong - startLong;
            double diffLat = endLat - startLat;
            double a = Math.pow(Math.sin(diffLat / 2), 2)
                    + Math.cos(startLat) * Math.cos(endLat)
                    * Math.pow(Math.sin(diffLong / 2),2);

            double c = 2 * Math.asin(Math.sqrt(a));

            // earth in kilometers
            double r = 6371;

            // calculate the result
            return (c * r);
        }


}
