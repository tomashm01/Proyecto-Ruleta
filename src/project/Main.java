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
    int count=0;
    Scanner s=new Scanner(System.in);
    String dni;
    int money=500,option;
    int cant1=0,cant2=0,cant3=0;
    String type1="",type2="",type3="";
    //Warning message to the player
    System.out.println(ConsoleColors.CYAN+"YOU HAVE 3 ATTEMPS TO INPUT YOUR DNI"+ConsoleColors.RESET);
    do {//User have got 3 attemps to introduce a valid dni
      System.out.println(ConsoleColors.CYAN+"Please input your dni: "+ConsoleColors.RESET);
      dni=s.nextLine();
      if(!Jugador.validateDni(dni)){
        count++;
      }else {
    	  break;
      }
    }while(count!=3);
    if(count==3) {//Random DNI
      dni=Jugador.randomDni();
    }
    
    do {
      System.out.println();
      System.out.println("MONEY: "+money);
      System.out.println("DNI: "+dni);
      option=rouletteMenu();
      switch(option) {
        case 1:
          System.out.println("Please insert the amount to bet: ");
          cant1=s.nextInt();
          s.nextLine();
          if((money-cant1)<0) {
            System.out.println("You have not got this money");
            continue;
          }
          money-=cant1;
          Ruleta.moneyBets.add(0,cant1);
          do {
            System.out.println("Input the type: RED | BLACK");
            type1=s.nextLine();     
            type1=type1.toUpperCase();
            System.out.println(type1);
            if(!type1.equals("RED") && !type1.equals("BLACK")) {
              System.out.println(ConsoleColors.RED+"Please insert a RED OR BLACK"+ConsoleColors.RESET);
              continue;
            }
          }while(!type1.equals("RED") && !type1.equals("BLACK"));
          if(type1.equals("RED")) {
            Ruleta.optionBets.add(0,true);
            Ruleta.moneyBets.add(0,money);
          }
          else {
            Ruleta.optionBets.add(1,true);
            Ruleta.moneyBets.add(0,money);
          }
          break;
        case 2:
          System.out.println("Please insert the amount to bet: ");
          cant2=s.nextInt();
          if((money-cant2)<0) {
            System.out.println("You have not got this money");
            continue;
          }
          money-=cant2;
          Ruleta.moneyBets.add(1,cant2);
          s.nextLine();
          do {
            System.out.println("Input the type: EVEN | ODD");
            type2=s.nextLine();
            type2.toUpperCase();
            if(!type2.equals("EVEN") && !type2.equals("ODD")) {
              System.out.println(ConsoleColors.RED+"Please insert a EVEN OR ODD"+ConsoleColors.RESET);
            }
          }while(!type2.equals("EVEN") && !type2.equals("ODD"));
          if(type2.equals("EVEN")) {
            Ruleta.optionBets.add(2,true);
            Ruleta.moneyBets.add(1,money);
          }
          else {
            Ruleta.optionBets.add(3,true);
            Ruleta.moneyBets.add(1,money);
          }
          break;
        case 3:
          System.out.println("Please insert the amount to bet: ");
          cant3=s.nextInt();
          if((money-cant3)<0) {
            System.out.println("You have not got this money");
            continue;
          }
          money-=cant3;
          Ruleta.moneyBets.add(2,cant3);
          s.nextLine();
          do {
            System.out.println("Input the type: HIGH(19-36) | LOW(1-19)");
            type3=s.nextLine();
            type3.toUpperCase();
            if(!type3.equals("HIGH") && !type3.equals("LOW")) {
              System.out.println(ConsoleColors.RED+"Please insert a HIGH OR LOW"+ConsoleColors.RESET);
            }
          }while(!type3.equals("HIGH") && !type3.equals("LOW"));
          if(type3.equals("HIGH")) {
            Ruleta.optionBets.add(4,true);
            Ruleta.moneyBets.add(2,money);
          }
          else {
            Ruleta.optionBets.add(5,true);
            Ruleta.moneyBets.add(2,money);
          }
          break;
        case 4:
          String val;
          int number=Ruleta.randomBall();
          System.out.println("NUMBER ->"+number);
          for(int i=0;i<Ruleta.optionBets.size();i++) {
            if(Ruleta.optionBets.get(i).equals(true)) {
              if(i<=1) {//If the user do a color bet
                val=Ruleta.colorBall(number);
                if(type1.equals(val)) {
                  money=money+(cant1*2);
                }
              }
              else if(i>=2 && i<=3) {//If the user do a even or odd bet
                val=Ruleta.EvenOddBall(number);
                if(type2.equals(val)) {
                  money=money+(cant2*2);
                }
              }
              else {//If the user do a high or low bet
                val=Ruleta.HigherLowerThan(number);
                if(type3.equals(val)) {
                  money=money+(cant3*2);
                }
              }
            }
          }
          Ruleta.Ruleta(); //Reinicialize the roulette
          break;
        case 5:
          System.out.println(ConsoleColors.CYAN+"Goodbye");
          break;
        default:
          System.out.println(ConsoleColors.RED+"Error in the input option"+ConsoleColors.RESET);
      }
    }while(option!=5);
  }
  static int rouletteMenu() {
    Menu roulette = new Menu("--ROULETTE MENU--", "Color bet", "Even or odd bet",
        "Higher or lower bet", "Spin roulette");
    return roulette.manage();
  }
}
