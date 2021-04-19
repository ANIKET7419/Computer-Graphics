import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ThreeD {
    static int [][] matrixMul(int A[][],int B[][])
    {

        int R[][]=new int[A.length][B[0].length];
        for(int i=0;i<A.length;i++)
        {
            for (int j=0;j<B[0].length;j++)
            {
                for (int k=0;k<A[0].length;k++){
                    R[i][j]+=A[i][k]*B[k][j];
                }

            }
        }
        return R;
    }


    static   void Xconverter(int x[]){

        for (int i=0;i<x.length;i++){
            x[i]+=650;
        }
    }
    static void Yconverter(int y[]){
        for(int i=0;i<y.length;i++){
            y[i]=-y[i];
            y[i]+=350;
        }
    }

    static int [][] convertToMatrix(int x[],int y[],int z[]){
        int temp_matrix[][]=new int[4][4];
        for (int i=0;i<4;i++){
            temp_matrix[i][0]=x[i];
            temp_matrix[i][1]=y[i];
            temp_matrix[i][2]=z[i];
            temp_matrix[i][3]=1;
        }
        return temp_matrix;
    }
    static void convertFromMatrix(int matrix[][],int x[],int y[],int z[]){
        for (int i=0;i<4;i++){
            x[i]=matrix[i][0];
            y[i]=matrix[i][1];
            z[i]=matrix[i][2];
        }
    }


    static int[][] rotation(int angle,int matrix[][]){
        int result[][];
        int transformationMatrix[][]={{(int)Math.round(Math.cos(Math.toRadians(angle))),(int)Math.round(Math.sin(Math.toRadians(angle))),0,0},{-((int)Math.round(Math.sin(Math.toRadians(angle)))),(int)Math.round(Math.cos(Math.toRadians(angle))),0,0},{0,0,1,0},{0,0,0,1}};
        result=matrixMul(matrix,transformationMatrix);
        return result;
    }


    static int[][] reflexion(int matrix[][]){
            int result[][];
            int transformation_Matrix[][]={{1,0,0,0},{0,1,0,0},{0,0,-1,0},{0,0,0,1}};
            result =matrixMul(matrix,transformation_Matrix);
            return result;
    }



    static int [][]scaling(int factor,int matrix[][]){


        int result[][];
        int transformationMatrix[][]={{factor,0,0,0},{0,factor,0,0},{0,0,factor,0},{0,0,0,1}};
        result=matrixMul(matrix,transformationMatrix);
        return result;
    }

    static int [][]translation(int x,int y,int z,int matrix[][]){
        int result[][];
        int transformationMatrix[][]={{1,0,0,0},{0,1,0,0},{0,0,1,0},{x,y,z,1}};
        result=matrixMul(matrix,transformationMatrix);
        return result;
    }


    static void displayMenu(){
        System.out.println("************ MENU *************** ");
        System.out.println("1. Reflexion ");
        System.out.println("2. Rotation ");
        System.out.println("3. Scaling ");
        System.out.println("4. Translation ");
        System.out.println();
        System.out.println("Enter your choice ..... ");
    }

    public static void main(String[] args) {
        JFrame jFrame=new JFrame("3-D TRANSFORMATION  ");
        jFrame.setSize(1400,1000);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int n;
        System.out.println("Enter number of vertices ");
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        int x[]=new int[n];
        int y[]=new int[n];
        int z[]=new int[n];
        for (int i=0;i<n;i++)
        {
            x[i]=scanner.nextInt();
            y[i]=scanner.nextInt();
            z[i]=scanner.nextInt();
        }
        displayMenu();
        int resultant[][]=null;
        int choice=scanner.nextInt();
        int trans_1,trans_2;
        switch (choice){
            case 1:{
                resultant=reflexion(convertToMatrix(x,y,z));
            }
            break;
            case 2:{
                System.out.println("Enter angle (Q) ");
                trans_1=scanner.nextInt();
                resultant=rotation(trans_1,convertToMatrix(x,y,z));
            }
            break;
            case 3:{
                System.out.println("Enter scaling factor ");
                trans_1=scanner.nextInt();
                resultant=scaling(trans_1,convertToMatrix(x,y,z));
                break;
            }
            case 4:{
                System.out.println("Enter x value ");
                trans_1=scanner.nextInt();
                System.out.println("Enter y value ");
                trans_2=scanner.nextInt();
                System.out.println("Enter z value ");
                int z1=scanner.nextInt();
                resultant=translation(trans_1,trans_2,z1,convertToMatrix(x,y,z));
            }
        }
        int res_x[]=new int[x.length];
        int res_y[]=new int[y.length];
        int res_z[]=new int[z.length];
        convertFromMatrix(resultant,res_x,res_y,res_z);
        Xconverter(res_x);
        Yconverter(res_y);
        Xconverter(x);
        Yconverter(y);
        Canvas canvas= new Canvas(){

            @Override
            public void paint(Graphics graphics)
            {

                graphics.setColor(Color.YELLOW);
                graphics.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));
                graphics.drawString("3 D TRANSFORMATION ",10,10);
                graphics.setColor(Color.WHITE);
                graphics.fillPolygon(x, y, n);
                graphics.setColor(Color.RED);
                graphics.drawLine(650,0,650,1000);
                graphics.drawLine(0,350,1400,350);
                graphics.setColor(Color.GREEN);
                graphics.drawString(" ( 0 , 0 )",650,345);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                graphics.fillPolygon(res_x,res_y,n);

            }
        };
        canvas.setBackground(Color.BLACK);
        jFrame.add(canvas);
        jFrame.setVisible(true);
    }
}
