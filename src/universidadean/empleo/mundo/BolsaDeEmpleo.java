/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot? - Colombia)
 * Departamento de Tecnolog?a de la Informaci?n y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 11 de marzo de 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.empleo.mundo;

import java.util.ArrayList;

/**
 * Es la clase que se encarga de manejar y organizar los aspirantes <br>
 * <b>inv: </b> <br>
 * aspirantes != null <br>
 * En el vector de aspirantes no hay dos o m?s con el mismo nombre
 */
public class BolsaDeEmpleo {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la lista que contiene todos los aspirantes
     */
    private ArrayList<Aspirante> aspirantes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva bolsa de emplea sin aspirantes.
     */
    public BolsaDeEmpleo() {
        aspirantes = new ArrayList<>();
    }

    // -----------------------------------------------------------------
    // M?todos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista de aspirantes. La lista retornada no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     *
     * @return lista de aspirantes
     */
    public ArrayList<Aspirante> darAspirantes() {
        ArrayList<Aspirante> copia = new ArrayList<>(aspirantes);
        return copia;
    }

    /**
     * Agrega un nuevo aspirante a la bolsa
     *
     * @param nombreA           El nombre del aspirante - nombreA != null
     * @param profesionA        La profesi?n del aspirante - profesionA es uno de estos { ADMINISTRADOR, INGENIERO_INDUSTRIAL, CONTADOR, ECONOMISTA }
     * @param aniosExperienciaA A?os de experiencia del aspirante - aniosExperienciaA > 0
     * @param edadA             La edad del aspirante - edadA > 0
     * @param telefonoA         El tel?fono del aspirante - telefonoA != null
     * @param imagenA           La ruta a la imagen del aspirante - imagenA != null
     * @return Se retorn? true si el aspirante fue adicionado o false de lo contrario
     */

    public boolean agregarAspirante(String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA) {
        int aspiranteBuscado = buscarAspirante(nombreA);
        boolean agregado = false;
        if (aspiranteBuscado == -1) {
            Aspirante nuevoAspirante = new Aspirante(nombreA, profesionA, aniosExperienciaA, edadA, telefonoA, imagenA);
            aspirantes.add(nuevoAspirante);
            agregado = true;
        }

        return agregado;
    }

    /**
     * Organiza la lista de aspirantes por nombre usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de aspirantes est? ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre() {
        for (int i = 0; i < aspirantes.size(); ++i){
            for (int j = i+1; j < aspirantes.size()-1; ++j){
                if (aspirantes.get(i).darNombre().compareTo(aspirantes.get(j).darNombre())>0) {
                    Aspirante aux = aspirantes.get(i);
                    aspirantes.set (i,aspirantes.get(j));
                    aspirantes.set (j, aux);
                }
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por edad usando el algoritmo de selecci?n <br>
     * <b>post: </b>La lista de aspirantes est? ordenada por edad
     */
    public void ordenarPorEdad() {


        for (int i = 0; i < aspirantes.size() -1; ++i ){
            int menor = Integer.MAX_VALUE;
            int posMenor = -1;
            for (int j = i+1; j < aspirantes.size(); ++j){
                if (aspirantes.get(j).darEdad() < menor){
                    menor = aspirantes.get(j).darEdad();
                    posMenor = j;
                }
            }
            Aspirante aux = aspirantes.get(i);
            aspirantes.set(i, aspirantes.get(posMenor));
            aspirantes.set(posMenor, aux );
        }
    }

    /**
     * Organiza la lista de aspirantes por profesi?n usando el algoritmo de burbuja <br>
     * <b>post: </b>El conjunto de aspirantes esta ordenado por profesi?n
     */
    public void ordenarPorProfesion() {
        for (int i = 0;  i < aspirantes.size(); ++i){
            for (int j = i+1; j < aspirantes.size()-1; ++j){
                if (aspirantes.get(i).darProfesion().compareTo(aspirantes.get(j).darProfesion())>0) {
                    Aspirante aux = aspirantes.get(i);
                    aspirantes.set (i,aspirantes.get(j));
                    aspirantes.set (j, aux);
                }
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por a?os de experiencia usando el algoritmo de inserci?n <br>
     * <b>post: </b>La lista de aspirantes est? ordenada por a?os de experiencia
     */
    public void ordenarPorAniosDeExperiencia() {
        for (int i = 1; i < aspirantes.size(); i++) {
            Aspirante key = aspirantes.get(i);
            int j = i - 1;
            while ((j >- 1) && aspirantes.get(j).darAniosExperiencia() > key.darAniosExperiencia()) {
                aspirantes.set(j+1,aspirantes.get(j));
                j--;
            }
            aspirantes.set(j+1,key);
        }
    }

    /**
     * Busca un Aspirante seg?n su nombre y retorna la posici?n en la que se encuentra. <br>
     *
     * @param nombre El nombre del aspirante buscado - nombre!=null
     * @return Se retorn? la posici?n donde se encuentra un aspirante con el nombre dado.
     * Si no se encuentra ning?n aspirante con ese nombre se retorn? -1.
     */
    public int buscarAspirante(String nombre) {
        int posicion = -1;
        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darNombre().equals(nombre)){
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Busca un aspirante utilizando una b?squeda binaria. <br>
     * <b>pre: </b> La lista de aspirantes se encuentra ordenada por nombre. <br>
     *
     * @param nombre es el nombre del aspirante que se va a buscar - nombre!=null
     * @return Se retorn? la posici?n del aspirante con el nombre dado.
     * Si el aspirante no existe se retorn? -1.
     */
    public int buscarBinarioPorNombre(String nombre) {
        int posicion = -1;
        int ini = 0;
        int fin = aspirantes.size() - 1;
        int mit = 0;
        while (ini <= fin) {
            mit = (ini + fin) / 2;
            if (aspirantes.get(mit).darNombre().compareTo(nombre) < 0) {
                ini = mit + 1;
                posicion = mit;
            } else if (aspirantes.get(mit).darNombre().compareTo(nombre) > 0) {
                fin = mit - 1;
                posicion = mit;
            }
        }
        return posicion;
    }


    /**
     * Busca el aspirante que tenga la menor edad en la bolsa.
     *
     * @return Se retorn? la posici?n donde se encuentra el aspirante m?s joven.
     * Si no hay aspirantes en la bolsa se retorn? -1
     */
    public int buscarAspiranteMasJoven() {
        int posicion = -1;
        int edad = 100;
        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darEdad()<=edad){
                edad = aspirantes.get(i).darEdad();
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Busca el aspirante que tenga la mayor edad en la bolsa.
     *
     * @return Se retorn? la posici?n donde se encuentra el aspirante con m?s edad. Si no hay aspirantes en la bolsa se retorn? -1
     */
    public int buscarAspiranteMayorEdad() {
        int posicion = -1;
        int edad = 0;
        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darEdad()>=edad){
                edad = aspirantes.get(i).darEdad();
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Busca el aspirante con m?s a?os de experiencia en la bolsa.
     *
     * @return Se retorn? la posici?n donde se encuentra el aspirante con mayor experiencia. Si no hay aspirantes en la bolsa se retorn? -1
     */
    public int buscarAspiranteMayorExperiencia() {
        int posicion = -1;
        int experiencia = 0;
        for (int i = 0; i < aspirantes.size(); i++) {
            if (aspirantes.get(i).darAniosExperiencia()>=experiencia){
                experiencia = aspirantes.get(i).darAniosExperiencia();
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Contrata a un aspirante.<br>
     * <b>post: </b>Se elimin? el aspirante de la lista de aspirantes.
     *
     * @param nombre El nombre del aspirante a contratar - nombre!=null
     * @return Se retorn? true si el aspirante estaba registrado en la bolsa o false de lo contrario
     */
    public boolean contratarAspirante(String nombre) {
        boolean contratado = false;
        for (int i = 0; i < aspirantes.size(); i++) {
            if(aspirantes.get(i).darNombre().equals(nombre)){
                aspirantes.remove(i);
                contratado = true;
            }
        }
        return contratado;
    }

    /**
     * Elimina todos los aspirantes de la bolsa cuyos a?os de experiencia <br>
     * son menores a la cantidad de a?os especificada <br>
     *
     * @param aniosExperiencia La cantidad de a?os con relaci?n a la cual se van a eliminar los aspirantes. aniosExperiencia>=0
     * @return La cantidad de aspirantes que fueron eliminados
     */
    public int eliminarAspirantesPorExperiencia(int aniosExperiencia) {
        int eliminados = 0;
        for (int i = 0; i < aspirantes.size(); i++) {
            if(aspirantes.get(i).darAniosExperiencia()<=aniosExperiencia){
                aspirantes.remove(i);
                eliminados += 1;
                i = -1;
            }
        }
        return eliminados;
    }

}