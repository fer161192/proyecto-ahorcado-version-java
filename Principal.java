package proyecto_ahorcado_auxiliar;
import java.util.Scanner;
public class Principal {

	public static void main(String[] args) {
		char caracter_ingresado;
		int opcion,cantidad_letras,respuesta;
		String palabra_ingresada;
		respuesta = 1;
		Scanner teclado = new Scanner(System.in);
		Scanner teclado2 = new Scanner(System.in);
		while (respuesta == 1)
		{
			System.out.println("Ingrese la palabra a cargar: ");
			palabra_ingresada = teclado.next();
			cantidad_letras = palabra_ingresada.length();
			Ahorcado objeto_ahorcado = new Ahorcado(palabra_ingresada,cantidad_letras);
			while (objeto_ahorcado.partidaTerminada() == false)
			{
				System.out.println("Ingrese una de las opciones: ");
				System.out.println("1: Arriesgar una letra.");
				System.out.println("2: Arriesgar una palabra entera. En esta opcion se decide si gana o pierde la partida");
				opcion = teclado.nextInt();
				switch(opcion)
				{
					case 1:
						System.out.println("Ingrese la letra a arriesgar: ");
						caracter_ingresado = teclado.next().charAt(0);
						objeto_ahorcado.arriesgar(caracter_ingresado);
						break;
					case 2:
						System.out.println("Ingrese la palabra a arriesgar: ");
						palabra_ingresada = teclado2.nextLine();
						objeto_ahorcado.arriesgar(palabra_ingresada);
						break;
					default:
						System.out.println("Opcion incorrecta!");
						System.out.println("Reingrese nuevamente.");
				}
			}
			System.out.println("Desea volver a jugar? (Ingrese 1 para el si/Ingrese 2 para el no): ");
			respuesta = teclado.nextInt();
		}
		System.out.println("Fin del programa. Adios!");
		teclado.close();
		teclado2.close();
	}

}
