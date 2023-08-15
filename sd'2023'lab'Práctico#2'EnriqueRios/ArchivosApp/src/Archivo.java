//package pruebas_tarea2;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.*;
import org.apache.commons.io.FileUtils;

/*Crea un archivo file y lee el texto en el archivo
 * de no existir devuelve null
 * */
public class Archivo {
	/*Lee un archivo File, devuelve su contenido*/
	public static String leer(File file) {
		try {
			//File file = new File(fichero_name);
			String texto = FileUtils.readFileToString(file, "UTF-8");
			return texto;
		}
		
		catch (Exception e) {
			return null;
		}	
	}
	public static boolean crearFichero(File file) {
		
		try {
			FileUtils.touch(file);
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static boolean escribirFichero(File file){
		try {
			FileUtils.writeStringToFile(file, "Fichero de pruebas escrito correctamente");;
			return true;
		} catch (IOException e) {
			return false;
		}
		
	}
	
	
	
	
}


