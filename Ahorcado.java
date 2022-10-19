package proyecto_ahorcado_auxiliar;
class Ahorcado
{
	private	int cantidad_desaciertos;
	private int cantidad_letras;
	private int cantidad_intentos;
	private String palabra;
	private char vector_adivinar_palabra[];
	private char vector_letras_invalidas[];
	private char matriz_grafica[][];
	public Ahorcado(String palabra_ingresada,int cantidad)
	{
		this.cantidad_desaciertos = 0;
		this.cantidad_letras = cantidad;
		this.cantidad_intentos = 7;
		this.palabra =  palabra_ingresada;
		this.vector_adivinar_palabra = new char[this.cantidad_letras];
		this.vector_letras_invalidas = new char[this.cantidad_intentos];
		this.matriz_grafica = new char[5][5];
		this.cargarMatrizGrafica();
		this.cargarVectorPalabra();
		this.cargarVectorLetrasInvalidas();
		System.out.println("Inicialmente, la palabra se encuentra de esta forma: ");
		this.mostrarPalabra();
		System.out.println("Inicialmente la horca se encuentra de esta forma: ");
		this.mostrarMatrizGrafica();
	}
	public void arriesgar(char caracter_ingresado)
	{
		if (this.letraAcertada(caracter_ingresado) == false)
		{
			this.cantidad_intentos--;
			this.cantidad_desaciertos++;
			this.insertarParteDelCuerpo();
			insertarLetraEquivocada(this.cantidad_desaciertos - 1,caracter_ingresado);	
		}
		System.out.println("Actualmente la palabra tiene esta forma: ");
		this.mostrarPalabra();
		System.out.println("Actualmente la horca tiene esta forma: ");
		this.mostrarMatrizGrafica();	
		this.mostrarVectorLetrasInvalidas();
		System.out.println("Te quedan "+this.cantidad_intentos+" intentos.");
		if (this.cantidad_intentos == 0)
			this.juegoFinalizado(false);
		if (totalLetrasAcertadas() == true)
			this.juegoFinalizado(true);
	}
	public void arriesgar(String palabra_ingresada)
	{
		boolean ganaste;
		if (this.palabra.equals(palabra_ingresada))
		{
			this.insertarPalabraCompleta(palabra_ingresada);
			ganaste = true;
		}
		else
		{
			this.insertarCuerpoCompleto();
			ganaste = false;
		}
		this.mostrarPalabra();
		this.mostrarMatrizGrafica();
		this.juegoFinalizado(ganaste);
	}
	public boolean partidaTerminada()
	{
		boolean terminado;
		terminado = false;
		if (this.cantidad_intentos == 0)
			terminado = true;
		if (this.totalLetrasAcertadas() == true)
			terminado = true;
		if (this.muniecoCompleto() == true)
			terminado = true;
		return(terminado);
	}
	private void cargarMatrizGrafica()
	{
		for (int i = 0;i<5;i++)
		{
			for (int j = 0;j<5;j++)
				this.matriz_grafica[i][j] = ' ';
		}
		this.matriz_grafica[0][1] = '-';
		for (int i = 0;i<5;i++)
		{
			if (i == 0)
				this.matriz_grafica[i][0] = '*';
			else
			{
				if (i == 4)
					this.matriz_grafica[i][0] = '-';
				else
					this.matriz_grafica[i][0] = '|';
			}
		}
	}
	private void cargarVectorPalabra()
	{
		for (int i = 0;i<this.cantidad_letras;i++)
			this.vector_adivinar_palabra[i] = '-';
	}
	private void cargarVectorLetrasInvalidas()
	{
		for (int i = 0;i<7;i++)
			this.vector_letras_invalidas[i] = ' ';
	}
	private void mostrarMatrizGrafica()
	{
		for (int i = 0;i<5;i++)
		{
			for (int j = 0;j<5;j++)
				System.out.print(this.matriz_grafica[i][j]);
			System.out.println();
		}
	}
	private void mostrarPalabra()
	{
		for (int i = 0;i<this.cantidad_letras;i++)
			System.out.print(this.vector_adivinar_palabra[i]+" ");
		System.out.println();
	}
	private void mostrarVectorLetrasInvalidas()
	{
		System.out.println("Las letras invalidas ingresadas son: ");
		for (int i = 0;i<7;i++)
			System.out.print(this.vector_letras_invalidas[i]+" ");
		System.out.println();
	}
	private void insertarLetraEquivocada(int pos,char caracter_ingresado)
	{
		this.vector_letras_invalidas[pos] = caracter_ingresado;
	}
	private void insertarParteDelCuerpo()
	{
		if (this.parteCentralDelCuerpoCompleta() == false)
			this.insertarParteDelCuerpoCentro();
		else
		{
			if (this.parteIzquierdaVacia() == true)
				this.insertarParteDelCuerpoLateral(1,1,'/');
			else
				this.insertarParteDelCuerpoLateral(1,3,'\134');
		}
	}
	private void insertarParteDelCuerpoLateral(int aux_fila,int aux_columna,char caracter)
	{
		if (this.matriz_grafica[aux_fila][aux_columna] == ' ')
			this.matriz_grafica[aux_fila][aux_columna] = caracter;
		else
		{
			int aux;
			aux = aux_fila + 2;
			this.matriz_grafica[aux][aux_columna] = caracter;
		}
	}
	private void insertarParteDelCuerpoCentro()
	{
		if (this.matriz_grafica[0][2] == ' ')
			this.matriz_grafica[0][2] = 'O';
		else
		{
			if (this.matriz_grafica[1][2] == ' ')
				this.matriz_grafica[1][2] = '|';
			else
				this.matriz_grafica[2][2] = '|';
		}
	}
	private boolean parteCentralDelCuerpoCompleta()
	{
		boolean central_completo;
		if (this.matriz_grafica[2][2] == '|')
			central_completo = true;
		else
			central_completo = false;
		return(central_completo);
	}
	private boolean parteIzquierdaVacia()
	{
		boolean izquierda_vacia;
		if (this.matriz_grafica[3][1] == ' ')
			izquierda_vacia = true;
		else
			izquierda_vacia = false;
		return(izquierda_vacia);
	}
	private boolean letraAcertada(char caracter_ingresado)
	{
		boolean letra_valida;
		letra_valida = false;
		char array_palabra[] = this.palabra.toCharArray();
		for (int i = 0;i<this.palabra.length();i++)
		{
			if (caracter_ingresado == array_palabra[i])
			{
				this.vector_adivinar_palabra[i] = array_palabra[i];
				letra_valida = true;
			}
		}
		return(letra_valida);
	}
	private boolean totalLetrasAcertadas()
	{
		boolean palabra_acertada;
		palabra_acertada = true;
		for (int i = 0;i<this.cantidad_letras;i++)
		{
			if (this.vector_adivinar_palabra[i] == '-')
				palabra_acertada = false;
		}
		return(palabra_acertada);
	}
	private boolean muniecoCompleto()
	{
		boolean completo;
		completo = false;
		if (this.matriz_grafica[3][3] == '\134')
			completo = true;
		return(completo);
	}
	private void insertarCuerpoCompleto()
	{
		this.matriz_grafica[0][2] = 'O';
		this.matriz_grafica[1][2] = '|';
		this.matriz_grafica[1][1] = '/';
		this.matriz_grafica[1][3] = '\134';
		this.matriz_grafica[2][2] = '|';
		this.matriz_grafica[3][1] = '/';
		this.matriz_grafica[3][3] = '\134';
	}
	private void insertarPalabraCompleta(String palabra_ingresada)
	{
		char array_palabra[] = palabra_ingresada.toCharArray();		
		for(int i = 0;i<palabra_ingresada.length();i++)
			this.vector_adivinar_palabra[i] = array_palabra[i];
	}
	private void juegoFinalizado(boolean victoria)
	{
		if (victoria == true)
			System.out.println("Fin del juego. Haz ganado!");
		else
		{
			System.out.println("Fin del juego. Perdiste!");
			System.out.println("La palabra correcta es: "+this.palabra);
		}
	}
}