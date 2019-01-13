package Distribucion_Carga;

public class Haversiano {

    // Basado en formato ejemplo "https://rosettacode.org/wiki/Haversine_formula#Java"
    public static double calcularDistancia(double latitud_usuario, double longitud_usuario, double latitud_destino, double longitud_destino){
        double earthRadius = 6372.8;
        double difLat = Math.toRadians(latitud_destino - latitud_usuario);
        double difLon = Math.toRadians(longitud_destino - longitud_usuario);
        double latUsuarioRad = Math.toRadians(latitud_usuario);
        double latDestinoRad = Math.toRadians(latitud_destino);

        double a = Math.pow(Math.sin(difLat / 2),2) + Math.pow(Math.sin(difLon / 2),2) * Math.cos(latUsuarioRad) * Math.cos(latDestinoRad);

        // Devuelve valor distancia entre ambos puntos (ubicación actual y ubicación post) en km
        return earthRadius * 2 * Math.asin(Math.sqrt(a));

    }
}
