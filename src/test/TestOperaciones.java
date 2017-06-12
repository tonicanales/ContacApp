package test;

import org.junit.Assert;
import org.junit.Test;

import main.Operaciones;

public class TestOperaciones {
	
	
	@Test
	public void testaddNuevoRegistro(){
		
		String[] listadoGeneral = {"carro", "perro", "gato"};
		String nuevoRegistro = "pez";
		
		String[] result = Operaciones.addNuevoRegistro(listadoGeneral, nuevoRegistro); 

		Assert.assertEquals(listadoGeneral.length+1, result.length);
		
		Assert.assertEquals(listadoGeneral[0], result[0]);
		Assert.assertEquals(nuevoRegistro, result[3]);
	
	}
	
	
	@Test
	public void ordenarNombre(){
		
		String[] listadoGeneral = {"carro", "perro", "gato"};
	
		String[] result = Operaciones.ordenarNombre(listadoGeneral); 

		
		
		Assert.assertEquals(listadoGeneral.length, result.length);	
		Assert.assertEquals("carro",listadoGeneral[0]);
		Assert.assertEquals("perro",listadoGeneral[1]);
		Assert.assertEquals("gato",listadoGeneral[2]);
		
		
		Assert.assertEquals(listadoGeneral[0], result[0]);
		Assert.assertEquals(listadoGeneral[2], result[1]);
		Assert.assertEquals(listadoGeneral[1], result[2]);
		

	}
	
	@Test
	public void eliminarContacto(){
		
		String[] listadoGeneral = {"carro", "perro", "gato", "pez"};
	
		String[] results = Operaciones.removerContacto(listadoGeneral, 0);
		
		

		Assert.assertEquals(listadoGeneral.length-1, results.length);	
		Assert.assertEquals("carro",listadoGeneral[0]);
		Assert.assertEquals("perro",listadoGeneral[1]);
		Assert.assertEquals("gato",listadoGeneral[2]);
		Assert.assertEquals("pez",listadoGeneral[3]);
		
		
		
		
		Assert.assertEquals(listadoGeneral[1], results[0]);
		Assert.assertEquals(listadoGeneral[2], results[1]);
		Assert.assertEquals(listadoGeneral[3], results[2]);
		
		
		results = Operaciones.removerContacto(listadoGeneral, 3);
		Assert.assertEquals(listadoGeneral.length-1, results.length);	
		Assert.assertEquals(listadoGeneral[0], results[0]);
		Assert.assertEquals(listadoGeneral[1], results[1]);
		Assert.assertEquals(listadoGeneral[2], results[2]);
		
		
		
	

	}
	
	
	
	@Test
	public void buscarNombres(){
		
		String[] listadoGeneral = {"carro", "perro", "gato", "pez", "camello"};
		String nombre = "p";

		String[] results = Operaciones.buscarNombres(listadoGeneral, nombre);

		Assert.assertEquals("carro",listadoGeneral[0]);
		Assert.assertEquals("perro",listadoGeneral[1]);
		Assert.assertEquals("gato",listadoGeneral[2]);
		Assert.assertEquals("pez",listadoGeneral[3]);
		Assert.assertEquals("camello",listadoGeneral[4]);
		
		Assert.assertEquals(results.length,2);
		Assert.assertEquals(listadoGeneral[1], results[0]);
		Assert.assertEquals(listadoGeneral[3], results[1]);

	}
	
	
}
