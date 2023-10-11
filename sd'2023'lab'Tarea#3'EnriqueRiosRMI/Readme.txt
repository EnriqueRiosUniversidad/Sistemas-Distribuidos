Link del video: https://drive.google.com/file/d/1x7YxXtH-j-D4BzcCDaw2WOG_eDm3gk4g/view?usp=sharing

Cumpli con todos los items a ser evaluados exepto por: 
	Correr diferentes instancias en diferentes hosts (puede virtualizar)

Implemente las clases: 
	ICliente.java
	IServer.java
	
	ChatServer.java

	ChatCliente.java
	ChatClienteGUI.java

Basicamente mi proyecto funciona ejecutando el comando ANT dentro de la carpeta rais, dentro del Build.xml
se encuentra todo el codigo necesario para dar un puerto al servidor y al clinete, como tambien un Host "localHost" 
en este caso.

Mi codigo compila las interfaces (cre al archivo interfaces.jar), crea un jar con ellas
crea el classpatch con dicha libreria interfaces.jar
Se ejecuta el RMIregistry
Compila el servidor y lo ejecuta en el puerto 5592
se compila el ChatCliente.java
empaquetamos en un jar el chatCliente
Ejecuta el bat start_clients.bat 
este llama 3 veces a run_clientes, y ejecuta 3 instancias de clientes.


Mi mayor dificultad fue crear la parte GUI y luego hacer funcionar el XML
