package cliente;

import servidor.server;

public class cliente {
    public static void main(String[] args) {
        System.out.println("Cliente: Iniciando cliente.");
        System.out.println("Cliente: Cliente listo.");

        System.out.println("");
        server.conectar();

        System.out.println("Cliente: Solicitando al servidor la suma de 25 y 35");
        System.out.println("Cliente: Resultado= " + server.sumar(25, 35));
        System.out.println("Cliente: Solicitando al servidor la resta de 25 y 35");
        System.out.println("Cliente: Resultado= " + server.restar(25, 35));

        System.out.println("");
        server.desconectar();
    }
}