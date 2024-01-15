package com.sergiorocasaez.practicatienda;

/*
 * PRÁCTICA DE INVENTARIO
 * Inventario de Tienda: Escribe un programa para gestionar el inventario de una
 * tienda utilizando ArrayList. Debe permitir agregar productos con detalles como
 * nombre, precio y cantidad en stock, así como realizar operaciones como actualizar
 * inventario, buscar productos y mostrar el inventario actualizado.
 */



/*----------------------------------------------------------------------------------
------------------------------- AVISO IMPORTANTE -----------------------------------
------------------------------------------------------------------------------------
    El programa compila y es ~teóricamente~ completo y funcional. Falta darle un
    repaso y escribir bastantes comentarios. Lo subo a GitHub para poder trabajar
                              desde otro ordenador  
------------------------------------------------------------------------------------
--------------------------- FIN DEL AVISO IMPORTANTE -------------------------------
------------------------------------------------------------------------------------*/



import java.util.Scanner;
import java.util.ArrayList;

public class Practicatienda {
    
    static ArrayList<String> nombreProductos = new ArrayList<>();
    static ArrayList<Double> precioProductos = new ArrayList<>();
    static ArrayList<Integer> cantidadProductos = new ArrayList<>();
    
    static Scanner entrada = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("""
                           ----------------------------------------------------
                           ----------- ADMINISTRACIÓN DE INVENTARIO -----------
                           ----------------------------------------------------""");
        
        menuPrincipal();
    }
    
    static void menuPrincipal(){
        String opcion;
        
        System.out.println("""
                           Elija la acción que desea realizar: 
                           [1] Actualizar inventario
                           [2] Buscar producto
                           [3] Mostrar inventario
                           [-1] Salir del programa""");
        System.out.print("> ");
        
        opcion = entrada.nextLine();
        System.out.println();
        
        switch(opcion){
            case "1" -> {menuActualizarInventario();}
            case "2" -> {buscarProducto();}
            case "3" -> {mostrarInventario();}
            case "-1" -> {System.exit(0);}
            default -> {System.out.println("Por favor, ingrese una opción válida");}
        }
    }
    
    static void menuActualizarInventario(){
        String opcion;
        
        System.out.println("""
                           Elija la acción que desea realizar en el inventario:
                           [1] Añadir producto
                           [2] Cambiar producto
                           [3] Eliminar producto
                           [-1] Volver a la pantalla anterior""");
        System.out.print("> ");
        
        opcion = entrada.nextLine();
        System.out.println();

        
        switch(opcion){
            case "1" -> {añadirProducto();}
            case "2" -> {menuCambiarProducto();}
            case "3" -> {}
            case "-1" -> {menuPrincipal();}
            default -> {
                System.out.println("Por favor, ingrese una opción válida");
                System.out.println();
                menuActualizarInventario();
            }

        }
    }
    
    static void añadirProducto(){
        System.out.println("Escriba el nombre del nuevo producto: ");
        System.out.print("> ");
        
        String nombre = entrada.nextLine();
        System.out.println();
        
        if(nombreProductos.contains(nombre)){
            System.out.println("El producto ["+nombre+"] ya existe en el inventario");
            System.out.println("¿Desea aumentar la cantidad en stock de ["+nombre+"]? (S/N)");
            System.out.print("> ");
            
            String opcion = entrada.nextLine();
            
            switch(opcion){
                case "S","s" -> {
                    System.out.println("Ingrese la cantidad de producto que desesa añadir");
                    System.out.print("> ");
                    
                    String cantidad = entrada.nextLine();
                    System.out.println();
                    
                    int indiceProducto = nombreProductos.indexOf(nombre);
                    
                    int nuevaCantidad = cantidadProductos.get(indiceProducto) + Integer.valueOf(cantidad);
                    
                    cantidadProductos.set(indiceProducto, nuevaCantidad);
                    
                    menuActualizarInventario();
                }
                case "N","n" -> {
                    menuActualizarInventario();
                }

            }
        }
        else{
            nombreProductos.add(nombre);
            
            System.out.println("Escriba el precio del nuevo producto en euros");
            System.out.print("> ");
            
            String precio = entrada.nextLine();
            System.out.println();
            
            precioProductos.add(Double.valueOf(precio));
            
            System.out.println("Escriba la cantidad de producto que desea añadir");
            System.out.print("> ");
            
            String cantidad = entrada.nextLine();
            System.out.println();
            
            cantidadProductos.add(Integer.valueOf(cantidad));
            
            menuActualizarInventario();
        }
    }
    
    static void menuCambiarProducto(){
        System.out.println("Escriba el nombre del producto que quiere editar");
        System.out.print("> ");
        
        String nombre = entrada.nextLine();
        System.out.println();
        
        if(!nombreProductos.contains(nombre)){
            System.out.println("\nNo existe tal producto en el inventario");
            menuActualizarInventario();
        }
        else{
            int indice = nombreProductos.indexOf(nombre);
            cambiarProducto(indice);
        }
    }
    
    static void cambiarProducto(int indice){
        System.out.println("""
                           Elija la acción que desea realizar:
                           [1] Cambiar el nombre del producto
                           [2] Cambiar el precio del producto
                           [3] Cambiar el nombre y el precio del producto
                           [-1] Volver a la pantalla anterior""");
        System.out.print("> ");
            
        String opcion = entrada.nextLine();
        System.out.println();
            
        switch(opcion){
            case "1" -> {editarNombre(indice);}
            case "2" -> {editarPrecio(indice);}
            case "3" -> {editarNombre(indice); editarPrecio(indice);}
            case "-1" -> {menuCambiarProducto();}
            default -> {
                System.out.println("Por favor, ingrese una opción válida\n");
                cambiarProducto(indice);
                }
        }
        
        menuActualizarInventario();
    }
    
    static void editarNombre(int indice){
        System.out.println("Escriba el nuevo nombre del producto ["+nombreProductos.get(indice)+"]");
        System.out.print("> ");
        
        String nombre = entrada.nextLine();
        
        nombreProductos.set(indice, nombre);
        System.out.println("Operación realizada con éxito\n");
    }
    
    static void editarPrecio(int indice){
        System.out.println("Escriba el nuevo precio del producto ["+nombreProductos.get(indice)+"]");
        System.out.print("> ");
        
        String precio = entrada.nextLine();
        
        precioProductos.set(indice, Double.valueOf(precio));
        System.out.println("Operación realizada con éxito\n");
    }
    
    static void menuEliminarProducto(){
        System.out.println("""
                           Elija la acción que desea realizar:
                           [1] Eliminar cantidad de producto
                           [2] Eliminar producto
                           [-1] Volver a la pantalla anterior""");
        System.out.print("> ");
        
        String opcion = entrada.nextLine();
        System.out.println();
        
        System.out.println("Escriba el nombre del producto que desea eliminar");
        System.out.print("> ");
        
        String nombre = entrada.nextLine();
        
        if(!nombreProductos.contains(nombre)){
            System.out.println("\nNo existe un producto con ese nombre\n");
            menuEliminarProducto();
        }
        
        int indice = nombreProductos.indexOf(nombre);
        
        switch(opcion){
            case "1" -> {eliminarCantidad(indice);}
            case "2" -> {eliminarProducto(indice);}
            case "-1" -> {
                System.out.println();
                menuActualizarInventario();
            }
            default -> {
                System.out.println("Por favor, ingrese una opción válida\n");
                menuEliminarProducto();
            }
        }
    }
    
    static void eliminarCantidad(int indice){
        System.out.println("["+nombreProductos.get(indice)+"] - "
                + cantidadProductos.get(indice)+" unidad/es");
        System.out.println("Ingrese cuántas unidades desea eliminar");
        System.out.print("> ");
        
        String cantidad = entrada.nextLine();
        
        int resultado = cantidadProductos.get(indice) - Integer.valueOf(cantidad);
        
        cantidadProductos.set(indice, resultado);
        
        if(cantidadProductos.get(indice) < 0){
            cantidadProductos.set(indice, 0);
            System.out.println("Cantidad negativa de producto. Igualado a cero");
        }
        
        System.out.println("Operación realizada con éxito\n");
        
        menuEliminarProducto();
    }
    
    static void eliminarProducto(int indice){
        System.out.println("¿Está seguro de que desea eliminar ["+nombreProductos.get(indice)
                            + "] de forma permanente? (S/N)");
        System.out.print("> ");
        
        String opcion = entrada.nextLine();
        
        switch(opcion){
            case "S","s" -> {
                nombreProductos.remove(indice);
                precioProductos.remove(indice);
                cantidadProductos.remove(indice);
                System.out.println("Operación realizada con éxito\n");
                menuEliminarProducto();
            }
            case "N","n" -> {
                System.out.println();
                menuEliminarProducto();
            }
            default -> {
                System.out.println("Por favor, ingrese una opción válida\n");
                eliminarProducto(indice);
            }
        }
    }
    
    static void buscarProducto(){
        System.out.println("Escriba el nombre del producto que desea buscar");
        System.out.print("> ");
        
        String nombre = entrada.nextLine();
        System.out.println();
        
        if(!nombreProductos.contains(nombre)){
            System.out.println("No se encontró ningún producto con ese nombre\n");
            menuPrincipal();
        }
        
        int indice = nombreProductos.indexOf(nombre);
        
        System.out.println("Precio: "+precioProductos.get(indice)+"€");
        System.out.println("Cantidad en stock: "+cantidadProductos.get(indice)+" unidad/es\n");
        
        menuPrincipal();
    }
    
    static void mostrarInventario(){
        System.out.println("[NOMBRE] - [CANTIDAD] - [PRECIO]€");
        for(int i=0 ; i<nombreProductos.size() ; i++){
            System.out.print(nombreProductos.get(i));
            System.out.print(" - ");
            System.out.print(cantidadProductos.get(i));
            System.out.print(" - ");
            System.out.println(precioProductos.get(i)+"€\n");
        }
        
        entrada.nextLine();
        
        menuPrincipal();
    }
    
}
