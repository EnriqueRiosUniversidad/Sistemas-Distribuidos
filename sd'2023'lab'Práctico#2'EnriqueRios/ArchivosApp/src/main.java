//package pruebas_tarea2;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.*;


public class main {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		
		String FILE_NAME = 
				".\\src\\file.txt";
		File file = new File(FILE_NAME);
		
		System.out.println("Leyendo archivo...");
		String contenidoFichero = Archivo.leer(file);
		if(contenidoFichero == null &&
				Archivo.crearFichero(file) &&
				Archivo.escribirFichero(file)
				) {
		 contenidoFichero = Archivo.leer(file);
		 System.out.println("Eror en lectura.");
		 System.out.println("Creando Fichero...");
		 System.out.println("Escribiendo Fichero...");
		 System.out.println("Reintentando lectura");
		 
		 System.out.println("Contenido del archivo: " + contenidoFichero);
			
		}else {
			System.out.println("Contenido del archivo: " + contenidoFichero);
		}
	}
}
	/*Crear un archivo
	 * Escribir Hola mundo en el 
	 * Leerlo e imprimir.
	 * */
	  //FileUtils.touch(new File(FILE_NAME));


	  //import pruebas_tarea2.Archivo;
/*
		  
		  String x;
		try {
			x = FileUtils.readFileToString(file, "UTF-8");
			System.out.println(x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Error");
			System.out.println("Tratando de crear archivo...");
			
			try {
				FileUtils.touch(file);
				System.out.println("fichero creado");
				FileUtils.write(file, "Hola Mundo");
				//FileUtils.writeStringToFile(file, "Hola Mundo");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Otra vez no funciono");
				e1.printStackTrace();
			}
		}
 */