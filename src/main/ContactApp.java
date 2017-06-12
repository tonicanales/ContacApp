package main;

import util.Input;

public class ContactApp {
	
	/**
	 * Desarrolle una aplicacion para guardar contactos, la aplicacion
	 * debe permitir: 
			 -	Adicionar un contacto
			 -	Listar todos los contactos  almacenados
			 -	Listar ordenados por nombre
			 -	Buscar por nombre
			 -	Buscar por email
			 -	Editar un contacto
			 -	Eliminar un contacto
			
		Un contacto debe tener la siguiente informacion:  
			-	Nombre
			-	Tellefono
			-	Email
			
	 * @param args
	 */
	
	public static void main(String[] args) {
		int eleccion;
		
		String[] listadoTotal = Operaciones.comprobarLista(); 

		do {
			
			Operaciones.listarOpciones();
			eleccion = Input.scannInt();

			switch (eleccion){
		
			case 1:
				listadoTotal = Operaciones.adicionar(listadoTotal);
				Operaciones.listado(listadoTotal);
				
				break;
			case 2:
				Operaciones.listado(listadoTotal);
				
				break;
			case 3:
			
				Operaciones.listado(Operaciones.ordenarNombre(listadoTotal));
				
				break;
			case 4:
				Operaciones.listado(Operaciones.buscaNombre(listadoTotal));;
				
				break;
			case 5:
				Operaciones.listado(Operaciones.buscaMail(listadoTotal));;
				
				break;
			case 6:
				Operaciones.listado(Operaciones.editaContacto(listadoTotal));
				
				break;
			case 7:
				listadoTotal = Operaciones.eliminarContacto(listadoTotal);
				Operaciones.listado(listadoTotal);
				
				break;
			case 8:
				System.out.println("Hasta pronto");
				break;
			}	
		} while (eleccion!=8);
	}
}
