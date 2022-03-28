import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;
public class juego {
    Scanner e = new Scanner(System.in);

    int vida;



    {System.out.println("Pon las columnas: ");}
    int y=e.nextInt();

    { System.out.println("Cantidad de generaciones");}
    int generacion =e.nextInt();

    { System.out.println("Vecions minimos");}
    int vecinomin =e.nextInt();

    { System.out.println("Vecinos maximos");}
    int vecinosmax =e.nextInt();

    { System.out.println("Vecinos revividos");}
    int revivir =e.nextInt();

    { System.out.println("Vecinos muertos por sobrepoblacion");}
    int muertos =e.nextInt();

    { System.out.println("Vecinos muertos por estar solos");}
    int solos =e.nextInt();

    char tabla[][] = new char[x][y];
    char tablaevolucion[][] = new char[x][y];

    int generacionescont=0;
    Random aleatorio = new Random();
    public static void main(String[] args) {

        juego p =new juego();
        p.principal();
    }
    public void principal() {


        crearMapa();


        Colonias(x, y);

        while (generacionescont<generacion){

            evolucion();

            generaciones();

            evolucionInicio();

            vaciarArray(tablaevolucion);

            generacionescont++;
        }

        System.out.println("Se acabó!!!");

    }
    public void crearMapa() {
        for(int x=0; x<tabla.length;x++) {
            for(int y = 0; y<tabla[x].length;y++) {
                tabla[x][y] = '-';
            }
        }
        for(int x=0; x<tablaevolucion.length;x++) {
            for(int y = 0; y<tablaevolucion[x].length;y++) {
                tablaevolucion[x][y] = '-';
            }
        }
    }
    public void Colonias(int x, int y) {

        System.out.println("Col·locar manual (m) o automatico(a)");
        char opcion = e.next().charAt(0);
        switch (opcion) {
            case 'A':
            case 'a':
                System.out.println("Pon el nombre de colonias que quieres");
                int colonias = e.nextInt();
                int medidacolonia = 0;
                for (int cont = 0; cont < colonias; cont++) {
                    int posx = aleatorio.nextInt((tabla.length - 1) - 1) + 1;
                    int posy = aleatorio.nextInt((tabla[0].length - 1) - 1) + 1;
                    do {
                        int posacabar;
                        int poscol;
                        posacabar = aleatorio.nextInt((posx + 4) - (posx - 4)) + (posx - 4);

                        while((posacabar < 1) || (posacabar > tabla.length - 1)) {
                            posacabar = aleatorio.nextInt((posx + 4) - (posx - 4)) + (posx - 4);
                        }

                        poscol = aleatorio.nextInt((posy + 4) - (posy - 4)) + (posy - 4);
                        while((poscol < 1) || (poscol > tabla.length - 1)) {
                            poscol = aleatorio.nextInt((posy + 4) - (posy - 4)) + (posy - 4);
                        }

                        tabla[posacabar][poscol] = 'A';
                        medidacolonia++;
                    }

                    while (medidacolonia < 5);
                    medidacolonia = 0;
                }
                break;
            case 'M':
            case 'm':
                int m=0;
                System.out.println("Colocar");
                int colocs=e.nextInt();
                while (m < colocs){
                    System.out.println("Pon X");
                    int fila=e.nextInt();
                    while (fila>=x) {
                        System.out.println("Te has pasado. Pon X");
                        fila=e.nextInt();
                    }
                    System.out.println("Pon Y");
                    int columna=e.nextInt();
                    while (columna>=y) {
                        System.out.println("Te has pasado. Pon Y");
                        columna=e.nextInt();
                    }
                    if (tabla[fila][columna]=='0') {
                        tabla[fila][columna]='1';
                    }
                    else if (tabla[fila][columna]!='0'){
                        System.out.println("Esta lleno");
                    }
                    m++;
                }

                break;
        }
    }
    public int Vecinos(int x, int y) {
        int vecinos = 0;
        if(tabla[x-1][y-1] == '1') {
            vecinos++;
        }
        if(tabla[x-1][y] == '1') {
            vecinos++;
        }
        if(tabla[x-1][y+1] == '1') {
            vecinos++;
        }
        if(tabla[x][y+1] == '1') {
            vecinos++;
        }
        if(tabla[x+1][y+1] == '1') {
            vecinos++;
        }
        if(tabla[x+1][y] == '1') {
            vecinos++;
        }
        if(tabla[x+1][y-1] == '1') {
            vecinos++;
        }
        if(tabla[x][y-1] == '1') {
            vecinos++;
        }
        return vecinos;

    }
    public void evolucion() {
        for (int f=1;f<x-1;f++) {
            for (int c=1;c<y-1;c++) {

                if (tabla[f][c] == '1') {
                    if (Vecinos(f,c) == vecinomin || Vecinos(x,y) == vecinosmax){
                        tablaevolucion[f][c] = '1';
                    }

                    else{
                        tablaevolucion[f][c] = '0';
                    }
                }
                if ((tabla[f][c] == '0') && (Vecinos(x,y) == revivir)) {
                    tablaevolucion[f][c] = '1';
                }

            }
        }
    }

    public void vaciarArray (char [][] array) {
        for (int x = 0; x < tabla.length; x++) {
            for (int y = 0; y < tabla[x].length; y++) {
                array[x][y] = '-';
            }
        }
    }

    public void evolucionInicio () {
        for (int i = 0; i < tablaevolucion.length; i++) {
            for (int j = 0; j < tablaevolucion[i].length; j++) {
                tabla[i][j] = tablaevolucion[i][j];
            }
        }
    }
    public void generaciones() {
        System.out.println("Generacion" + " " + generacionescont);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ie) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println(Arrays.deepToString(tabla).replace("],", "],\n"));
        System.out.println();
    }

}
