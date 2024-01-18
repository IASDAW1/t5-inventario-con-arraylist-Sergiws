package com.sergiorocasaez.practicatienda;

/*
 * PRÁCTICA DE INVENTARIO
 * Inventario de Tienda: Escribe un programa para gestionar el inventario de una
 * tienda utilizando ArrayList. Debe permitir agregar productos con detalles como
 * nombre, precio y cantidad en stock, así como realizar operaciones como actualizar
 * inventario, buscar productos y mostrar el inventario actualizado.
 */

/*Falta hacerle pruebas al programa, así que me toca confiar en mi habilidad como
programador*/

import java.util.Scanner;
import java.util.ArrayList;

public class Practicatienda {
    //Declaramos variables estáticas para utilizarlas a lo largo del programa
    
    /*Declaramos variables que apuntan a diferentes ArrayLists
        - "nombreProductos" almacena Strings
        - "precioProductos" almacena números decimales
        - "cantidadProductos" almacena números enteros
    
    Al no estar utilizando clases, vamos a utilizar 3 ArrayLists para almacenar
    información de los productos de la tienda. Para acceder a un elemento, lo
    buscaremos por el nombre y utilizaremos su índice para mostrar información
    de los otros dos ArrayList. Naturalmente, almacenaremos o eliminaremos
    elementos de tres en tres, uno por cada Arraylist*/
    static ArrayList<String> nombreProductos = new ArrayList<>();
    static ArrayList<Double> precioProductos = new ArrayList<>();
    static ArrayList<Integer> cantidadProductos = new ArrayList<>();

    //Declaramos un objeto Scanner para leer la entrada del usuario
    static Scanner entrada = new Scanner(System.in);
    
    /*Declaramos variables String para almacenar la entrada del usuario a lo largo
    del programa
        - "opcion" almacenará la opción del usuario en un menu del programa
        - "nombre" almacenará el nombre del producto que el usuario quiera
        buscar, añadir o eliminar
        - "cantidad" almacenará la cantidad de producto que el usuario quiera
        añadir o eliminar
        - "precio" almacenará el valor del producto que el usuario quiera
        añadir o editar
    
    Podría usar variables de tipo int o double para "cantidad" y "precio"
    respectivamente, pero la clase Scanner da problemas de vez en cuando a la
    hora de leer números, así que los leeremos como String para convertirlos
    más tarde al tipo de dato que proceda*/
    static String opcion, nombre, cantidad, precio;
    
    public static void main(String[] args) {
        //Mostramos un título simple pero funcional al iniciar el programa
        System.out.println("""
                           ----------------------------------------------------
                           ----------- ADMINISTRACIÓN DE INVENTARIO -----------
                           ----------------------------------------------------""");
        
        /*El resto del programa se basa en funciones llamándose las unas a las
        otras, empezando por "menuPrincipal()"*/
        menuPrincipal();
    }
    
    /*"menuPrincipal()" es la función que muestra el menú principal del programa
    Es el único menú desde el cual se puede finalizar el programa*/
    static void menuPrincipal(){
        /*Mostramos una serie de opciones al usuario por pantalla
        
        Las tres comillas dobles indican a java que se está escribiendo un bloque
        de texto, así que se respetan los retornos de carro sin tener que
        escribir "\n"*/
        System.out.println("""
                           Elija la acción que desea realizar: 
                           [1] Actualizar inventario
                           [2] Buscar producto
                           [3] Mostrar inventario
                           [-1] Salir del programa""");
        System.out.print("> ");
        
        //Guardamos la opción del usuario en una variable String
        opcion = entrada.nextLine();
        System.out.println();
        
        /*Si el usuario escribe:
        1) Mostramos el menú para añadir, cambiar o eliminar productos
        2) Buscamos el producto que el usuario seleccione
        3) Mostramos el inventario actualizado completo
        -1) Terminamos el programa con una función de System
        
        Si el usuario no escribe ninguna de estas cuatro opciones, le informamos
        que la entrada no es válida y mostramos de nuevo el menu principal
        */
        switch(opcion){
            case "1" -> {menuActualizarInventario();}
            case "2" -> {buscarProducto();}
            case "3" -> {mostrarInventario();}
            case "-1" -> {System.exit(0);}
            default -> {
                System.out.println("Por favor, ingrese una opción válida\n");
                menuPrincipal();
            }
        }
    }
    
    /*"menuActualizarInventario" es el menú desde el que se puede añadir, editar
    o cambiar los productos del inventario*/
    static void menuActualizarInventario(){
        //Mostramos una serie de opciones al usuario por pantalla
        System.out.println("""
                           -- ACTUALIZAR INVENTARIO --
                           Elija la acción que desea realizar en el inventario:
                           [1] Añadir producto
                           [2] Cambiar producto
                           [3] Eliminar producto
                           [-1] Volver a la pantalla anterior""");
        System.out.print("> ");
        
        //Guardamos la opción del usuario en una variable String
        opcion = entrada.nextLine();
        System.out.println();

        /*Si el usuario escribe:
        1) Añadimos un nuevo producto con las especificaciones del usuario
        2) Mostramos el menú de edición de productos al usuario
        3) Mostramos el menú de eliminación de productos al usuario
        -1) Volvemos al menú principal
        
        Si el usuario no escribe ninguna de estas cuatro opciones, le informamos
        que la entrada no es válida y mostramos de nuevo el menú de actualizar
        el inventario*/
        switch(opcion){
            case "1" -> {añadirProducto();}
            case "2" -> {cambiarProducto();}
            case "3" -> {menuEliminarProducto();}
            case "-1" -> {menuPrincipal();}
            default -> {
                System.out.println("Por favor, ingrese una opción válida\n");
                menuActualizarInventario();
            }

        }
    }
    
    /*"añadirProducto" es la función con la que el usuario puede añadir productos
    al inventario
    En esta función se pueden crear productos nuevos o aumentar la cantidad de 
    productos ya existentes*/
    static void añadirProducto(){
        //Pedimos al usuario que escriba el nombre del producto que quiere añadir
        System.out.println("Escriba el nombre del nuevo producto: ");
        System.out.print("> ");
        
        //Guardamos la entrada del usuario en una variable String
        nombre = entrada.nextLine();
        System.out.println();
        
        /*Si el producto que quiere añadir el usuario ya existe en el inventario,
        preguntamos si quiere aumentar la cantidad de este producto*/
        if(nombreProductos.contains(nombre)){
            System.out.println("El producto ["+nombre+"] ya existe en el inventario");
            System.out.println("¿Desea aumentar la cantidad en stock de ["+nombre+"]? (S/N)");
            System.out.print("> ");
            
            //Guardamos la opción del usuario en una variable String
            opcion = entrada.nextLine();
            
            switch(opcion){
                /*Si el usuario quiere aumentar la cantidad del producto,
                preguntamos la cantidad*/
                case "S","s" -> {
                    System.out.println("Ingrese la cantidad de producto que desesa añadir");
                    System.out.print("> ");
                    
                    //Guardamos la cantidad en una variable String
                    cantidad = entrada.nextLine();
                    System.out.println();
                    
                    //Obtenemos el índice del producto con su nombre
                    int indiceProducto = nombreProductos.indexOf(nombre);
                    
                    /*Cambiamos la cantidad del ArrayList, ya que sabemos su índice.
                    Sumamos la cantidad que estaba almacenada originalmente con
                    la cantidad ingresada por el usuario (convertida de String
                    a entero)*/
                    int nuevaCantidad = cantidadProductos.get(indiceProducto) + Integer.valueOf(cantidad);
                    cantidadProductos.set(indiceProducto, nuevaCantidad);
                    
                    //Volvemos al menú de actualizar el inventario
                    menuActualizarInventario();
                }
                /*Si el usuario no quiere aumentar la cantidad del producto,
                volvemos directamente al menú de actualizar el inventario*/
                case "N","n" -> {
                    menuActualizarInventario();
                }

            }
        }
        /*Si no existe el producto escrito por el usuario, lo añadimos al
        inventario junto con su precio y cantidad*/
        else{
            //Añadimos el nombre del producto al ArrayList de nombres
            nombreProductos.add(nombre);
            
            //Preguntamos al usuario el precio del producto nuevo
            System.out.println("Escriba el precio del nuevo producto en euros");
            System.out.print("> ");
            
            //Guardamos el precio en una variable String
            precio = entrada.nextLine();
            System.out.println();
            
            //Añadimos el precio al ArrayList de precios (convertido de String a double)
            precioProductos.add(Double.valueOf(precio));
            
            //Preguntamos al usuario la cantidad en stock del producto nuevo
            System.out.println("Escriba la cantidad de producto que desea añadir");
            System.out.print("> ");
            
            //Guardamos la cantidad en una variable String
            cantidad = entrada.nextLine();
            System.out.println();
            
            //Añadimos la cantidad al ArrayList de cantidades (convertido de String a int)
            cantidadProductos.add(Integer.valueOf(cantidad));
            
            //Volvemos al menú de actualizar el inventario
            menuActualizarInventario();
        }
    }
    
    /*"menuCambiarProducto" es la función con la que el usuario puede buscar un
    producto para editar*/
    static void cambiarProducto(){
        //Preguntamos al usuario el producto que quiere editar
        System.out.println("Escriba el nombre del producto que quiere editar");
        System.out.print("> ");
        
        //Guardamos el nombre en una variable String
        nombre = entrada.nextLine();
        System.out.println();
        
        /*Si no existe el producto escrito por el usuario, informamos al usuario
        de esto y volvemos al menú de actualizar el inventario*/
        if(!nombreProductos.contains(nombre)){
            System.out.println("\nNo existe tal producto en el inventario");
            menuActualizarInventario();
        }
        /*Si existe el producto escrito por el usuario, mostramos al usuario las
        opciones para editar su nombre o precio*/
        else{
            int indice = nombreProductos.indexOf(nombre);
            menuCambiarProducto(indice);
        }
    }
    
    /*"cambiarProducto" es el menú desde el que el usuario puede editar el nombre
    y/o el precio del producto seleccionado*/
    static void menuCambiarProducto(int indice){
        //Mostramos una serie de opciones al usuario por pantalla
        System.out.println("""
                           -- CAMBIAR PRODUCTO --
                           Elija la acción que desea realizar:
                           [1] Cambiar el nombre del producto
                           [2] Cambiar el precio del producto
                           [3] Cambiar el nombre y el precio del producto
                           [-1] Volver a la pantalla anterior""");
        System.out.print("> ");
        
        //Guardamos la opción del usuario en una variable String
        opcion = entrada.nextLine();
        System.out.println();
        
        /*Si el usuario escribe:
        1) Editamos el nombre del producto seleccionado
        2) Editamos el precio del producto seleccionado
        3) Editamos el nombre y el precio del producto seleccionado
        -1) Volvemos al menú para actualizar el inventario
        
        Si el usuario no escribe una opción válida, le informamos de esto y
        volvemos a mostrar este menú*/
        switch(opcion){
            case "1" -> {editarNombre(indice);}
            case "2" -> {editarPrecio(indice);}
            case "3" -> {editarNombre(indice); editarPrecio(indice);}
            case "-1" -> {menuActualizarInventario();}
            default -> {
                System.out.println("Por favor, ingrese una opción válida\n");
                menuCambiarProducto(indice);
                }

        }
        
        /*Al terminar las ediciones, volvemos al menú para actualizar el
        inventario*/
        menuActualizarInventario();
    }
    
    /*"editarNombre" es la función con la que el usuario edita el nombre del
    producto seleccionado anteriormente*/
    static void editarNombre(int indice){
        //Preguntamos al usuario el nuevo nombre del producto
        System.out.println("Escriba el nuevo nombre del producto ["+nombreProductos.get(indice)+"]");
        System.out.print("> ");
        
        //Guardamos el nombre en una variable String
        nombre = entrada.nextLine();
        
        //Reemplazamos el nombre existente con el nombre recién escrito
        nombreProductos.set(indice, nombre);
        System.out.println("Operación realizada con éxito\n");
    }
    
    /*"editarPrecio" es la función con la que el usuario edita el precio del
    producto seleccionado anteriormente*/
    static void editarPrecio(int indice){
        //Preguntamos al usuario el nuevo precio del producto
        System.out.println("Escriba el nuevo precio del producto ["+nombreProductos.get(indice)+"]");
        System.out.print("> ");
        
        //Guardamos el precio en una variable String
        precio = entrada.nextLine();
        
        /*Reemplazamos el precio existente con el precio recién escrito (convertido
        de String a double)*/
        precioProductos.set(indice, Double.valueOf(precio));
        System.out.println("Operación realizada con éxito\n");
    }
    
    /*"menuEliminarProducto" es el menú desde el que el usuario puede eliminar
    un producto definitivamente o una cantidad de producto*/
    static void menuEliminarProducto(){
        //Mostramos una serie de opciones al usuario por pantalla
        System.out.println("""
                           -- ELIMINAR PRODUCTO --
                           Elija la acción que desea realizar:
                           [1] Eliminar cantidad de producto
                           [2] Eliminar producto
                           [-1] Volver a la pantalla anterior""");
        System.out.print("> ");
        
        //Guardamos la opción del usuario en una variable String
        opcion = entrada.nextLine();
        System.out.println();
        
        /*Si el usuario escribe "-1", volvemos al menú para actualizar el
        inventario*/
        if(opcion.equals("-1")){
            System.out.println();
            menuActualizarInventario();
        }
        
        //Preguntamos al usuario el nombre del producto para eliminar
        System.out.println("Escriba el nombre del producto que desea eliminar");
        System.out.print("> ");
        
        //Guardamos el nombre en una variable String
        nombre = entrada.nextLine();
        
        /*Si el producto escrito por el usuario no existe en el inventario,
        le informamos de esto y volvemos al menú para elminar productos*/
        if(!nombreProductos.contains(nombre)){
            System.out.println("\nNo existe un producto con ese nombre\n");
            menuEliminarProducto();
        }
        
        //Obtenemos el índice del producto con el nombre
        int indice = nombreProductos.indexOf(nombre);
        
        /*Anteriormente preguntamos al usuario la acción que quería realizar, así
        que si ha escrito:
        1) Eliminamos una cantidad del producto seleccionado
        2) Eliminamos definitivamente el producto seleccionado
        
        Si el usuario no ha escrito una opción válida, le informamos de esto y
        volvemos al menú para eliminar productos*/
        switch(opcion){
            case "1" -> {eliminarCantidad(indice);}
            case "2" -> {eliminarProducto(indice);}
            default -> {
                System.out.println("Por favor, ingrese una opción válida\n");
                menuEliminarProducto();
            }
        }
    }
    
    /*"eliminarCantidad" es la función para eliminar cierta cantidad en stock
    de un producto del inventario seleccionado por el usuario anteriormente*/
    static void eliminarCantidad(int indice){
        /*Mostramos al usuario la cantidad en stock del producto seleccionado y
        preguntamos la cantidad que desea eliminar*/
        System.out.println("["+nombreProductos.get(indice)+"] - "
                + cantidadProductos.get(indice)+" unidad/es");
        System.out.println("Ingrese cuántas unidades desea eliminar");
        System.out.print("> ");
        
        //Guardamos la cantidad en una variable String
        cantidad = entrada.nextLine();
        
        /*Calculamos la cantidad de producto en stock que quedaría al restar la
        cantidad guardada en el ArrayList con la cantidad escrita por el usuario
        (convertida de String a int)*/
        int resultado = cantidadProductos.get(indice) - Integer.valueOf(cantidad);
        
        /*Guardamos el resultado en el ArrayList
        
        Si el usuario ha escrito un número negativo, la cantidad aumentará, pero
        voy bastante justo de tiempo*/
        cantidadProductos.set(indice, resultado);
        
        /*Si el usuario a restado más producto del que existe, igualamos la
        cantidad del producto en el ArrayList a cero para no parezca que la
        tienda está endeudada*/
        if(cantidadProductos.get(indice) < 0){
            cantidadProductos.set(indice, 0);
            System.out.println("Cantidad negativa de producto. Igualado a cero");
        }
        
        System.out.println("Operación realizada con éxito\n");
        
        //Volvemos al menú para actualizar el inventario
        menuActualizarInventario();
    }
    
    /*"eliminarProducto" es la función para eliminar definitivamente un producto
    del inventario seleccionado por el usuario anteriormente*/
    static void eliminarProducto(int indice){
        /*Preguntamos al usuario si está seguro de que desea eliminar el
        producto seleccionado*/
        System.out.println("¿Está seguro de que desea eliminar ["+nombreProductos.get(indice)
                            + "] de forma permanente? (S/N)");
        System.out.print("> ");
        
        //Guardamos la opción del usuario en una variable String
        opcion = entrada.nextLine();
        
        switch(opcion){
            /*Si el usuario quiere eliminar el producto, eliminamos el nombre,
            el precio y la cantidad del producto de sus respectivos ArrayList
            utilizando el mismo índice, ya que lo comparten*/
            case "S","s" -> {
                nombreProductos.remove(indice);
                precioProductos.remove(indice);
                cantidadProductos.remove(indice);
                System.out.println("Operación realizada con éxito\n");
                
                //Volvemos al menú para actualizar el inventario
                menuActualizarInventario();
            }
            /*Si el usuario no quiere eliminar el producto, volvemos al menú
            para actualizar el inventario*/
            case "N","n" -> {
                System.out.println();
                menuActualizarInventario();
            }
            default -> {
                /*Si el usuario no escribe una opción válida, se lo notificamos
                y volvemos a llamar a esta función*/
                System.out.println("Por favor, ingrese una opción válida\n");
                eliminarProducto(indice);
            }
        }
    }
    
    /*"buscarProducto" es la función para buscar un producto a través de su
    nombre*/
    static void buscarProducto(){
        //Preguntamos al usuario el nombre del producto que quiere buscar
        System.out.println("Escriba el nombre del producto que desea buscar");
        System.out.print("> ");
        
        //Guardamos el nombre en una variable String
        nombre = entrada.nextLine();
        System.out.println();
        
        /*Si el nombre no existe en el ArrayList de nombres, notificamos al
        usuario de que el producto no existe y volvemos al menú principal*/
        if(!nombreProductos.contains(nombre)){
            System.out.println("No se encontró ningún producto con ese nombre\n");
            menuPrincipal();
        }
        
        //Sacamos el índice del producto con su nombre
        int indice = nombreProductos.indexOf(nombre);
        
        /*Mostramos por pantalla el precio y la cantidad en stock del producto
        seleccionado por el usuario*/
        System.out.println("Precio: "+precioProductos.get(indice)+"€");
        System.out.println("Cantidad en stock: "+cantidadProductos.get(indice)+" unidad/es\n");
        
        //Volvemos al menú principal
        menuPrincipal();
    }
    
    /*"mostrarInventario" es la función que muestra el inventario actualizado
    con el nombre, cantidad y precio de los productos*/
    static void mostrarInventario(){
        System.out.println("[NOMBRE] - [CANTIDAD] - [PRECIO]€");
        for(int i=0 ; i<nombreProductos.size() ; i++){
            System.out.print(nombreProductos.get(i));
            System.out.print(" - ");
            System.out.print(cantidadProductos.get(i));
            System.out.print(" - ");
            System.out.println(precioProductos.get(i)+"€\n");
        }
        
        /*Esperamos la entrada del usuario para que el menú principal no tape
        el inventario*/
        System.out.print("Presione enter para continuar");
        entrada.nextLine();
        System.out.println();
        
        //Volvemos al menú principal
        menuPrincipal();
    }
    
}
