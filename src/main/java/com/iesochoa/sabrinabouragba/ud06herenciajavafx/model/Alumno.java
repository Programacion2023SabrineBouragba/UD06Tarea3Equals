package com.iesochoa.sabrinabouragba.ud06herenciajavafx.model;

public class Alumno extends Persona{
    //clase enum Curso
    private Curso curso;
    public Alumno(String dni, String nombre, int edad, Curso curso){
        super(dni, nombre, edad);
        this.curso=curso;
    }

    //getter y setter curso
    public Curso getCurso(){
        return curso;
    }
    public void setCurso(Curso curso){
        this.curso=curso;
    }
}
