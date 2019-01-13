package Distribucion_Carga;

import Calculo_Disponibilidad.Solution;

import java.util.List;

public class Solution2 {
    private Usuarios_Servidor [] servidores;
    private double resultadoEquitividad;
    private double resultadoProximidad;

    public Solution2(int size){
        servidores = new Usuarios_Servidor[size];
        for(int i = 0; i < size; i++){
            servidores[i] = new Usuarios_Servidor();
        }
        resultadoEquitividad = Double.POSITIVE_INFINITY;
        resultadoProximidad = Double.POSITIVE_INFINITY;
    }

    public boolean isSolution(int size, int contador){
        return contador == size;
    }

    public Usuarios_Servidor[] getServidores() {
        return servidores;
    }

    public void equitividadCarga(){
        double aux, mayor, menor;

        mayor = -1;
        menor = Double.POSITIVE_INFINITY;
        int size = servidores.length;
        for(int i = 0; i < size; i++){
            aux = servidores[i].getCargaActividad();

            if(aux < menor){
                menor = aux;
            }
            if(aux > mayor){
                mayor = aux;
            }
        }

        resultadoEquitividad = mayor - menor;
    }

    public void cercaniaUsuarios(){
        double aux, mayor, menor;

        mayor = -1;
        menor = Double.POSITIVE_INFINITY;
        int size = servidores.length;
        for(int i = 0; i < size; i++){
            aux = servidores[i].getDiferenciaProximidad();

            if(aux < menor){
                menor = aux;
            }
            if(aux > mayor){
                mayor = aux;
            }
        }

        resultadoProximidad = mayor - menor;
    }

    public double getResultadoEquitividad() {
        return resultadoEquitividad;
    }

    public double getResultadoProximidad() {
        return resultadoProximidad;
    }


    public void clonar (Solution2 solution){
        int size = solution.getServidores().length;

        for(int i = 0; i < size; i++){
            this.servidores[i] = solution.servidores[i];
        }

        this.resultadoEquitividad = solution.getResultadoEquitividad();
        this.resultadoProximidad = solution.getResultadoProximidad();
    }
}