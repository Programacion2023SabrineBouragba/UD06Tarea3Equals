package com.iesochoa.sabrinabouragba.ud06herenciajavafx.model;

import java.util.Objects;

public class Persona {
    protected String dni;
    protected String nombre;
    protected int edad;

    //constructor por defecto
    public Persona(String dni, String nombre, int edad){
        this.dni=dni;
        this.nombre=nombre;
        this.edad=edad;
    }

    //getter y setter dni
    public String getDni(){
        return dni;
    }
    public void setDni(String dni){
        //si el dni es incorrecto
        if (!esCorrectoNIF(dni)){
            dni="Sin DNI";
        }else {
            this.dni = dni;
        }
    }

    //getter y setter nombre
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    //getter y setter nombre
    public int getEdad(){
        return edad;
    }
    public void setEdad(int edad){
        this.edad=edad;
    }

    public static boolean esCorrectoNIF(String NIF){
        NIF= NIF.toUpperCase();

        //commprobamos q no este nulo o que exceda 9 caracteres
        if (NIF.length()!=9){
            return false;
        }else {
            String numDni = "";
            /*vamos a recorrer los 8 digitos,
            comprobando en todo momento que se trata de digitos y no letras*/
            //desde la posicion 0, hasta la posicion 7
            for (int i=0; i<8; i++){
                //si el caracter actual, i, es diferente de digito, devolver falso
                if(!Character.isDigit(NIF.charAt(i))){
                    return false;
                }else{  //si el caracter es un digito, almacenar, para utilizar despues para averiguar la laetra
                    numDni+= NIF.charAt(i);
                }

            }

            //convertir del String NIF, el caracter 8(java empieza a contar desde 0), convertirlo en un caracter
            char letra= NIF.charAt(8);

            //comprobar que el char sea una letra
            if (!Character.isLetter(letra)){
                return false;
            }else{
                //crear array con las letras correctas que puede tener un DNI
                char[] letrasDni= {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
                /*como numdni es un string, tengo que pasarlo a int primero, y despues calcular el modulo*/
                int numero= Integer.parseInt(numDni);
                //con el nuevo int, calculo el modulo para averiguar la letra correcta
                int modulo= numero%23;

                //en base al modulo obtenido, obtener letra del array, ejemplo, si el modulo es 3, me devolvera 'A'
                char letraCalculada= letrasDni[modulo];

                //si la letra proporcioanada en el dni, coincide con la del array, devolver true
                return letra== letraCalculada;
            }

        }
    }

    /**metodo comprueba si los dni son iguales
     * @param o objeto con el que vamos a comprobar
     * @return true si el objeto "o" es igual a el objeto Persona*/
    @Override
    public boolean equals(Object o){
        //si el elemento seleccionado es igual al objeo o
        if (this==o) return true;

        //si el objeto es null or pertenece a otra clase
        if (o==null||getClass()!= o.getClass()) return false;

        //hace que el objeto o pertenezca a la clase Persona
        Persona persona=(Persona) o;

        //comprueba que sean diferentes el dni del objeto actual, con el dni del objeto o
        return Objects.equals(dni, persona.dni);
    }

}
