package com.iesochoa.sabrinabouragba.ud06herenciajavafx.model;

public class Profesor extends Persona{
    private int sueldo=0;

    //constructor por defecto
    public Profesor(String dni, String nombre, int edad) {
        super(dni, nombre, edad);
    }

    //getter y setter de sueldo
    public int getSueldo(){
        return sueldo;
    }
    public void setSueldo(int sueldo){
        if (sueldo>=0){
            this.sueldo=sueldo;
        }else{
            this.sueldo=0;
        }

    }
}
