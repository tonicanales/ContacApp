package main;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.FileHelper;
import util.Input;

public class Operaciones {
	
	public final static  String FILE_NAME = "listado";

	public static String[] adicionar(String[] listadoGeneral){
		String[] listado = listadoGeneral;	
		String nuevoRegistro =  scanNuevoRegistro();
		if (comprobacionesNuevo(nuevoRegistro)){
		listado =  addNuevoRegistro(listadoGeneral,nuevoRegistro); 
		escribir(listado,FILE_NAME);
		return listado;

		} else {
		return listadoGeneral;
		}
	}
	
	public static void listado(String[] listado){  
		for (int i=0; i< listado.length; i++){
			imprimirLinea(listado[i], i);
		}
	}
	
	public static String[] ordenarNombre(String[] listado){
		String[] copyListado = new String[listado.length];
		for (int i=0; i< listado.length; i++){
			 copyListado[i] = listado[i]; 
		}
	    Arrays.sort(copyListado);
	    return copyListado; 
	}

	public static String[] buscaNombre(String[] listado){
			String nombre = pedirDatos();
			return (buscarNombres(listado, nombre));
	}
	
	public static String[] buscaMail(String[] listado){
			String mail = pedirDatos();
			return (buscarMail(listado, mail));
	}
	
	public static String[] editaContacto(String[] listado){
		String[] nuevalista = listado;
	    int id = scanIdRegistro(listado); 
		String nuevoRegistro = scanNuevoRegistro();
		
		
		if (comprobacionesEditar(listado, nuevoRegistro, id-1)){
			nuevalista[id-1]=nuevoRegistro;
			escribir(nuevalista,FILE_NAME);
			return nuevalista;
		} else {
			return listado;
		}
		
	}
	public static String[] eliminarContacto(String[] listado){		    
	    int index = scanIdRegistro(listado); 
		String[] nuevoListado = removerContacto(listado, index);
		escribir(nuevoListado,FILE_NAME);
		return nuevoListado;
	}

	/******************************** user interface  ***********************************/
	public static void listarOpciones() {
		System.out.println("\nEscoge una opción");
		System.out.println("1. Adicionar un contacto\n2. Listar todos los contactos almacenados\n3. Listar ordenados por nombre\n4. Buscar por nombre\n"
				+ "5. Buscar por email\n6. Editar un contacto\n7. Eliminar un contacto\n8. Salir");
	}

	public static String[] comprobarLista(){
		String[] listadoTotal = FileHelper.readFile(FILE_NAME);
		if (listadoTotal==null){
				 listadoTotal = new String[0];
		}
		return listadoTotal;
	}
	
	private static void escribir(String[] registros, String nomArchivo) {
		FileHelper.writeFile(registros, nomArchivo);
	}

	private static void imprimirLinea(String linea, int i){
		String[] separada = linea.split(";"); 
		System.out.println("Id: " + (i+1) + "\tNombre: " + separada[0] + "\tTeléfono: " + separada[1]+ "\tMail: " + separada[2]);	
		
	}

	private static String scanNuevoRegistro() {
		System.out.println("Introduce nombre y apellido");
		String nombre = Input.scannLine();
		System.out.println("Introduce el teléfono");
		String telefono = Input.scannLine();
		System.out.println("Introduce el mail");
		String mail = Input.scannLine();
		String nuevoRegistro = nombre + ";" + telefono + ";" + mail;
		return nuevoRegistro;
		
	}

	private static String pedirDatos(){
		System.out.println("Introduce datos:");
		String dato = Input.scannLine().toLowerCase();
		return dato;
	}
	
	private static int scanIdRegistro(String[] lista) {
		for (int i=0; i< lista.length; i++){
			imprimirLinea(lista[i],i);
		}
		System.out.println("Introduce Id");
		int id = Input.scannInt();
		return id;  
	}
	
	
	/*********************** algoritmos **********************************************************/
	
	private static boolean comprobacionesNuevo(String registroacomprobar){
		boolean comprobacion = false;
		String[] separada = registroacomprobar.split(";"); 
		if (separada[0] !=null && separada[1] !=null && separada[2] !=null && checkEmail(separada[2])){
			comprobacion = true;
			return comprobacion;
		}
			 
		else 
			System.out.println("Datos no válidos");
			return comprobacion;
			
	}

	private static boolean comprobacionesEditar(String[] lista, String registroacomprobar, int registro){
		boolean comprobacion = false;
		String[] separada = registroacomprobar.split(";");
		String[] registroseparado = lista[registro].split(";");
		if (separada[0] ==null && separada[1] ==null && separada[2] ==null && !checkEmail(separada[2])){
			System.out.println("Datos no válidos");
			return comprobacion;
		} 			 
		else {
			if (separada[0]==null) {
				separada[0]=registroseparado[0];
			}
			if (separada[1]==null) {
				separada[1]=registroseparado[1];
			}
			if (separada[2]==null) {
				separada[2]=registroseparado[2];
			}
			comprobacion = true;
			return comprobacion;
		}	
			
	}

	public static String[] addNuevoRegistro( String[] listadoGeneral, String nuevoRegistro) {
		String[] registrosbis = new String[listadoGeneral.length+1];
		for (int i=0; i<listadoGeneral.length; i++){
			registrosbis[i]=listadoGeneral[i];
		}
		registrosbis[listadoGeneral.length]=nuevoRegistro;	
		return registrosbis;
	}

	public static String[] removerContacto(String[] listado, int ind){
		String[] nuevoListado = new String[listado.length-1];
		for (int i = 0, j=0; i<nuevoListado.length; i++, j++){
			if(i==ind-1) 
				j++;
			nuevoListado[i]=listado[j];
		}
		return nuevoListado; 
   }
	
	public static String[] buscarNombres(String[] listado, String nombre){
		int contador = 0;
		for (int i=0; i< listado.length; i++){
			String[] separada = listado[i].split(";");
			if(nombre.length()<=separada[0].length()){
				String subnombre = separada[0].substring(0, nombre.length()).toLowerCase();
				if (nombre.equals(subnombre)){
					contador++;
				}
			}
		}	
		
		String[] nombres = new String[contador];
		for (int i=0, j=0; i< listado.length; i++){
			String[] separada = listado[i].split(";");	
			if(nombre.length()<=separada[0].length()){
				String subnombre = separada[0].substring(0, nombre.length()).toLowerCase();
				if (j<nombres.length && nombre.equals(subnombre)){
					nombres[j] = listado[i];
					j++;
				}	
			}
		}
		return nombres;
	}
	
	public static String[] buscarMail(String[] listado, String nombre){
		int contador = 0;
		for (int i=0; i< listado.length; i++){
			String[] separada = listado[i].split(";");
			if(nombre.length()<=separada[2].length()){
				String subnombre = separada[2].substring(0, nombre.length()).toLowerCase();
				if (nombre.equals(subnombre)){
					contador++;
				}
			}
		}	
		
		String[] nombres = new String[contador];
		for (int i=0, j=0; i< listado.length; i++){
			String[] separada = listado[i].split(";");	
			if(nombre.length()<=separada[2].length()){
				String subnombre = separada[2].substring(0, nombre.length()).toLowerCase();
				if (j<nombres.length && nombre.equals(subnombre)){
					nombres[j] = listado[i];
					j++;
				}	
			}
		}
		return nombres;
	}
	
	
	private static boolean checkEmail (String email) {
		boolean mailvalido = false;
		Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        Matcher mather = pattern.matcher(email);
 
        if (mather.find() == true) {
            mailvalido = true;
        }
        return mailvalido;
    }

	

}
