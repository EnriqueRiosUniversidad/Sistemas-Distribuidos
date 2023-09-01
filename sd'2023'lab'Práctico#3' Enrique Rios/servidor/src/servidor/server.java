package servidor;

public class server  {
	public static void conectar(){
		System.out.println("Cargando peticion....");
		System.out.println("Conectando...");
		System.out.println("Server conectado exitosamente");
	}
	
	public static void desconectar() {
		System.out.println("Cargando peticion....");
		System.out.println("Cerrando procesos");
		System.out.println("Server desconectado exitosamente");
	}
	
	public static int sumar(int A, int B) {
		int suma= A + B;
		return suma;
		
	}
	
	public static int restar(int A, int B){
		int resultado= A + B;
		return resultado;
		
	}
	
}