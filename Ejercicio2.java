package inversamatriz;
import java.util.Scanner;

public class InversaMatriz {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numCarx = 0;
        int numCary = 0;
        System.out.print("Introduzca el número de caracteres de filas de la primera matriz: ");
        numCarx = scan.nextInt();
        System.out.print("Introduzca el número de caracteres de columnas de la primera matriz: ");
        numCary = scan.nextInt();
        double M1[][] = new double[numCarx][numCary];
        for(int x=0; x<numCarx; x++){
            for(int i=0; i<numCary; i++){
                System.out.print("x"+(i+1)+" = ");
                M1[x][i]=scan.nextInt();
            }

        }
        System.out.print("Introduzca el número de caracteres de filas de la segunda matriz: ");
        numCarx = scan.nextInt();
        System.out.print("Introduzca el número de caracteres de columnas de la segunda matriz: ");
        numCary = scan.nextInt();
        double M2[][] = new double[numCarx][numCary];
        for(int x=0; x<numCarx; x++){
            for(int i=0; i<numCary; i++){
                System.out.print("x"+(i+1)+" = ");
                M2[x][i]=scan.nextInt();
            }
        }


    }

    public static double[][] matrizInversa(double[][] matriz) {
        double det=1/determinante(matriz);
        double[][] nmatriz=matrizAdjunta(matriz);
        multiplicarMatriz(det,nmatriz);
        return nmatriz;
    }

    public static void multiplicarMatriz(double n, double[][] matriz) {
        for(int i=0;i<matriz.length;i++)
            for(int j=0;j<matriz.length;j++)
                matriz[i][j]*=n;
    }

    public static double[][] matrizAdjunta(double [][] matriz){
        return matrizTranspuesta(matrizCofactores(matriz));
    }

    public static double[][] matrizCofactores(double[][] matriz){
        double[][] nm=new double[matriz.length][matriz.length];
        for(int i=0;i<matriz.length;i++) {
            for(int j=0;j<matriz.length;j++) {
                double[][] det=new double[matriz.length-1][matriz.length-1];
                double detValor;
                for(int k=0;k<matriz.length;k++) {
                    if(k!=i) {
                        for(int l=0;l<matriz.length;l++) {
                            if(l!=j){
                                int indice1=k<i ? k : k-1 ;
                                int indice2=l<j ? l : l-1 ;
                                det[indice1][indice2]=matriz[k][l];
                            }
                        }
                    }
                }
                detValor=determinante(det);
                nm[i][j]=detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return nm;
    }

    public static double[][] matrizTranspuesta(double [][] matriz){
        double[][]nuevam=new double[matriz[0].length][matriz.length];
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz.length; j++)
                nuevam[i][j]=matriz[j][i];
        }
        return nuevam;
    }

    public static double determinante(double[][] matriz){
        double det;
        if(matriz.length==2){
            det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }
        double suma=0;
        for(int i=0; i<matriz.length; i++){
        double[][] nm=new double[matriz.length-1][matriz.length-1];
            for(int j=0; j<matriz.length; j++){
                if(j!=i){
                    for(int k=1; k<matriz.length; k++){
                        int indice=-1;
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;
                            nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0)
                suma+=matriz[i][0] * determinante(nm);
            else
                suma-=matriz[i][0] * determinante(nm);
        }
        return suma;
    }
}