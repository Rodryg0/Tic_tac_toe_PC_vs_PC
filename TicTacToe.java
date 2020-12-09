
package tictactoe;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.Scanner;


public class TicTacToe {
    static Scanner leer= new Scanner(System.in);
    public int [][] Matriz;     
    static boolean turno;
    static int Dificultad=1;
    
    public TicTacToe(int[][]Matriz1){
        Matriz=Matriz1;
        generarPrimerMovimiento();
        turno=false;
         while ((gano(Matriz,1)!=1 && gano(Matriz,2)!=2) && !estaLlena(Matriz)) {
             if (turno) {
                 System.out.println("Turno 1");
                 minMax(Matriz,1);
                 pintarMatriz(Matriz);
                 turno=false;
             }else{
                    System.out.println("Turno 2");
                 minMax(Matriz,2);
                 pintarMatriz(Matriz);
                 turno=true;
             }
             
        }
         System.out.println("-----------Tablero final--------------");
         if (gano(Matriz,1)==1) {
             System.out.println("Gano el jugador 1");
        }
           if (gano(Matriz,2)==2) {
             System.out.println("Gano el jugador 2");
        }
         pintarMatriz(Matriz);
    }
    
    
    
    public static void main(String[] args) {
           int Matriz[][];
           Matriz = new int[3][3];   
           for (int i = 0; i < Matriz.length; i++) {
                for (int j = 0; j < Matriz.length; j++) {
                   Matriz[i][j]=0;   
               } 
        }
           new TicTacToe(Matriz);
    }
    
    
    public static boolean estaLlena(int tablero[][]){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j]==0) {
                    return false;
                }   
            }  
        }
        return true;
    }
    
    
    public  void generarPrimerMovimiento(){
        int M=0,N=2;
         int i = (int) Math.floor(Math.random()*(N-M+1)+M);
         int j = (int) Math.floor(Math.random()*(N-M+1)+M);
         Matriz[i][j]=1;
    }

    
     public  void pintarMatriz(int mat[][]){
       
         for(int i = 0; i < mat.length; i++){ 
         	for(int j = 0; j < mat[i].length; j++){ 
		System.out.print(mat[i][j] + "\t");	// Imprime elemento 
	    } 
	        System.out.println();	// Imprime salto de línea     
         }  
     }
     
     
     public int gano(int tablero[][],int jugador){
           int colission=0;
         //Recorrido fila uno
         for (int k = 0; k < 3; k++) {
             if (tablero[0][k]==jugador) {
               colission++; 
             }
              if (colission==3) {
                     return jugador;
                 }
         }
         colission=0;
         
         //Recorrido fila 2
           for (int k = 0; k < 3; k++) {
             if (tablero[1][k]==jugador) {
               colission++; 
             }
              if (colission==3) {
                     return jugador;
                 }
         }
            colission=0;
           //Recorrido fila 3
           for (int k = 0; k < 3; k++) {
             if (tablero[2][k]==jugador) {
               colission++; 
                 if (colission==3) {
                     return jugador;
                 }
             }
         }
           
            colission=0;
           //Recorrido Columna 1
           for (int k = 0; k < 3; k++) {
             if (tablero[k][0]==jugador) {
                 colission++; 
                 if (colission==3) {
                     return jugador;
                 }
             }
         }
           
           colission=0;
           //Recorrido Columna 2
           for (int k = 0; k < 3; k++) {
             if (tablero[k][1]==jugador) {
                 colission++; 
                 if (colission==3) {
                     return jugador;
                 }
             }
         }
           
           colission=0;
           //Recorrido Columna 3
           for (int k = 0; k < 3; k++) {
             if (tablero[k][2]==jugador) {
                 colission++; 
                 if (colission==3) {
                     return jugador;
                 }
             }
         }
           //Recorrido diagonal
           colission=0;
           for (int i = 0; i < tablero.length; i++) {
                       if (tablero[i][i]==jugador) {
                       colission++;
                       }
                        if (colission==3) {
                         return jugador;
                        }
           }
           colission=0;
           if (tablero[2][0]==jugador && tablero[1][1]==jugador && tablero[0][2]==jugador) {
             return jugador;
          }
             return 0;
     }
    
    
    public void minMax(int [][] tablero, int jugador){
        int mejoFila = 0,mejorColumna = 0;
        int max, maxActual;
        max=Integer.MIN_VALUE;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[j][i]==0) {
                    int tempFila,tempc;
                    tablero[j][i]=jugador;
                    tempc=j;
                    tempFila=i;
                    maxActual=valorMin(tablero,0,Integer.MIN_VALUE,Integer.MAX_VALUE,jugador);
                    tablero[tempc][tempFila]=0;
                    if (max<maxActual) {
                        max=maxActual;
                        mejoFila=tempFila;
                        mejorColumna=tempc;
                    }
                }
            }
         }
     
        Matriz[mejorColumna][mejoFila]=jugador;
     }
    
  

    public  int valorMin(int tablero[][],int depht, int alfa, int beta, int jugador) {
        int jugadorOpuesto=0;
        if (jugador==1) {
            jugadorOpuesto=2;
        }else{
            jugadorOpuesto=1;
        }
        if (gano(tablero,1)==1 || gano(tablero,2)==2) {
            return Heuristica(tablero);
        }else if (estaLlena(tablero)) {
            return Heuristica(tablero);
        }else  if (Dificultad<depht) {
            return Heuristica(tablero);
        }else{
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    if (tablero[j][i]==0) {
                    int tempFila,tempc;
                    tablero[j][i]=jugadorOpuesto;
                    tempc=j;
                    tempFila=i;
                    beta=min(beta,valorMax(tablero,++depht,alfa,beta,jugador));
                    tablero[tempc][tempFila]=0;
                    if (alfa>=beta) {
                      return alfa;
                    }
                }
             }
           }
        }
   
        return beta;
    }
    
     public int valorMax(int tablero[][],int depht, int alfa, int beta,int jugador) {
          if (gano(tablero,1)==1 || gano(tablero,2)==2) {
            return Heuristica(tablero);
        }else if (estaLlena(tablero)) {
            return Heuristica(tablero);
        }else{
            for (int i = 0; i < tablero.length; i++) {
               for (int j = 0; j < tablero.length; j++) {
                if (tablero[j][i]==0) {
                    int tempFila,tempc;
                    tablero[j][i]=jugador;
                    tempc=j;
                    tempFila=i;
                    alfa=max(alfa,valorMin(tablero,++depht,alfa,beta,jugador));
                    tablero[tempc][tempFila]=0;
                    if (alfa>=beta) {
                      return beta;
                    }
                }
            }
         } 
        }
  
        return alfa;
    }
     
    public static int Heuristica(int tablero[][]){
        int costo=0;
        costo=Costo(tablero,2)-Costo(tablero,1);
        return costo;
    }
    
    
    public static int Costo(int tablero[][],int jugador){
        int value=0;
        int colission=0;
        for (int k = 0; k < 3; k++) {
             if (tablero[0][k]==jugador) {
               colission++; 
                 if (colission==3) {
                     return 10;
                 }
                 if (colission==1 || colission==2) {
                     value=5;
                 }
             }
         }
        
        
         colission=0;
         
         //Recorrido fila 2
           for (int k = 0; k < 3; k++) {
             if (tablero[1][k]==jugador) {
               colission++; 
                 if (colission==3) {
                     return 10;
                 }
                  if (colission==1 || colission==2) {
                     value=5;
                 }
             }
         }
            colission=0;
           //Recorrido fila 3
           for (int k = 0; k < 3; k++) {
             if (tablero[2][k]==jugador) {
               colission++; 
                 if (colission==3) {
                     return 500;
                 }
                  if (colission==1 || colission==2) {
                     value= 10;
                 }
             }
         }
           
            colission=0;
           //Recorrido Columna 1
           for (int k = 0; k < 3; k++) {
             if (tablero[k][0]==jugador) {
                 colission++; 
                 if (colission==3) {
                     return 10;
                 }
                  if (colission==1 || colission==2) {
                     value= 5;
                 }
             }
         }
           
           colission=0;
           //Recorrido Columna 2
           for (int k = 0; k < 3; k++) {
             if (tablero[k][1]==jugador) {
                 colission++; 
                 if (colission==3) {
                     return 10;
                 }
                  if (colission==1 || colission==2) {
                     value=5;
                 }
             }
         }
           
           colission=0;
           //Recorrido Columna 3
           for (int k = 0; k < 3; k++) {
             if (tablero[k][2]==jugador) {
                 colission++; 
                 if (colission==3) {
                     return 10;
                 }
                  if (colission==1 || colission==2) {
                     value= 5;
                 }
             }
         }
           colission=0;
           //Recorrido diagonal
           for (int i = 0; i < tablero.length; i++) {
                       if (tablero[i][i]==jugador) {
                       colission++;
                       }
                        if (colission==3) {
                         return 10;
                        }
                         if (colission==1 || colission==2) {
                     value=5;
                 }
           }
           colission=0;
           if (tablero[2][0]==jugador) {
            colission++;
          }
           
           if (tablero[1][1]==jugador) {
             colission++;
           }
           
           if (tablero[0][2]==jugador) {
             colission++;
           }
           if (colission==3) {
           return 10; 
            }
            if (colission==1 || colission==2) {
                     value= 5;
            }
            colission=0;
             
        return value;
    }    
}
