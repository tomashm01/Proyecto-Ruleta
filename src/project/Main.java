package project;

/**
 * Primero se va a mostrar el credito arriba a la derecha (0 Créditos) El menú será: Valor
 * predeterminado: 500 creditos
 * 
 * v1.0: (El dineroInvertido es individual en cada opcion -> lista de dinero) (Habrá otra lista con
 * las distintas opciones -> [i] [i+1] -> lista de booleanos) Ruleta 500 Creditos --- 1ºApostar al
 * color listaDinero[0] = dinero; listaBoleanos[0-1] = true; -> el otro false
 * 
 * 2ºApostar a par o impar (Lo mismo pero diciendo si es par o impar y multiplica por 2 igual)
 * listaDinero[1] = dinero; listaBoleanos[2-3] = true; -> el otro false
 * 
 * 3ºSi el numero está entre 1 y 18 o 19 al 36 listaDinero[2] = dinero; listaBoleanos[4-5] = true;
 * -> el otro false
 * 
 * 4º Girar la ruleta ---- Genera un número: | 5 | ----- Actualizar el crédito de arriba a la
 * derecha.
 * 
 * Cuando no tienes creditos: Añade una opcion: Reiniciar juego
 * 
 * v2.0: Añadir metodo de pago cuando no tienes creditos v2.1: Añadir ultimos numeros v2.2: Añadir
 * Contador limitado v2.3: Añadir borrar todas las apuestas v2.4: Añadir numero animado
 * 
 * v3.0: Añadir segundo juego
 * 
 * 
 * Next update: Añadir ultimas 10-15 tiradas -> numero y color
 * 
 * Next update: Contador limitado (45 segundos)
 * 
 * Next update: borrar todas las apuestas -> todo a 0 y false (lista de jugar ruleta)
 * 
 * Next update: Número moviendose con diferentes colores Si sale el 0 pierdes todo array de colores
 * con numeros -> a mano tristemente
 * 
 * Proximo juego: 3 columnas, le das al boton -> si las 3 filas son iguales ganas tragaperras 3
 * listas cada lista 5 elementos diferentes si los 3 elementos coinciden ganas y si no pierdes
 */
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    
  }
  static int rouletteMenu() {
    Menu roulette = new Menu("--ROULETTE MENU--", "Color bet", "Even or odd bet",
        "Higher or lower bet", "Spin roulette");
    return roulette.manage();
  }
}
