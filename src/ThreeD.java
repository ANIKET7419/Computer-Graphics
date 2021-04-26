import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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



    static int[][] orthographicP(int resultant[][],int choice){

        int result[][]=new int[resultant.length][2];
        int temp[][]=null;
        if (choice==0){
           temp=matrixMul(resultant,new int[][]{{1,0,0,0},{0,1,0,0},{0,0,0,0},{0,0,0,1}});
           for (int i=0;i<resultant.length;i++)
           {
               result[i][0]=temp[i][0];
               result[i][1]=temp[i][1];
           }
        }
        else if (choice==1){

            temp=matrixMul(resultant,new int[][]{{0,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}});
            for (int i=0;i<resultant.length;i++)
            {
                result[i][0]=temp[i][1];
                result[i][1]=temp[i][2];
            }
        }
        else{
            temp=matrixMul(resultant,new int[][]{{1,0,0,0},{0,0,0,0},{0,0,1,0},{0,0,0,1}});
            for (int i=0;i<resultant.length;i++)
            {
                result[i][0]=temp[i][0];
                result[i][1]=temp[i][2];
            }
        }
        return result;

    }



    static int [][] convertToMatrix(int x[],int y[],int z[]){
        int temp_matrix[][]=new int[x.length][4];
        for (int i=0;i<x.length;i++){
            temp_matrix[i][0]=x[i];
            temp_matrix[i][1]=y[i];
            temp_matrix[i][2]=z[i];
            temp_matrix[i][3]=1;
        }
        return temp_matrix;
    }
    static void convertFromMatrix(int matrix[][],int x[],int y[]){
        for (int i=0;i<matrix.length;i++){
            x[i]=matrix[i][0];
            y[i]=matrix[i][1];
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
            int transformation_Matrix[][]={{1,0,0,0},{0,-1,0,0},{0,0,1,0},{0,0,0,1}};
            result =matrixMul(matrix,transformation_Matrix);
            return result;
    }

    static void Draw(Graphics graphics, HashMap<Integer, ArrayList<Integer>> map,int x[], int y[]){

        for(Map.Entry<Integer,ArrayList<Integer>> record:map.entrySet()){

            int i=record.getKey();
            for(int j:record.getValue()){
                graphics.drawLine(x[i],y[i],x[j],y[j]);
            }

        }

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

    public static void displayPMenu(){
        System.out.println("************* MENU *************");
        System.out.println("1. Parallel Projection - Orthographic [ Z=0 Plane  ] ");
        System.out.println("2. Parallel Projection - Orthographic [ X=0 Plane ] ");
        System.out.println("3. Parallel Projection - Orthographic [ Y=0 Plane ] ");
        System.out.println("4. Perspective Projection ");
        System.out.println("\nEnter your choice ");
    }




   static  int[][] perspectiveP(int A[][]){



        float B[][]={{1,0,0,0},{0,1,0,0},{0,0,0,0.01f},{0,0,0,1}};

       float R[][]=new float[A.length][B.length];
       for(int i=0;i<A.length;i++)
       {
           for (int j=0;j<B[0].length;j++)
           {
               for (int k=0;k<A[0].length;k++){
                   R[i][j]+=A[i][k]*B[k][j];
               }
               R[i][j]=R[i][j]/(0.01f*A[i][2]+1);
           }
       }
       int result[][]=new int[A.length][2];
       for (int i=0;i<A.length;i++){
           result[i][0]=(int)R[i][0];
           result[i][1]=(int)R[i][1];
       }
       return result;
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
        System.out.println("Enter number of edges ");
        int e=scanner.nextInt();
        System.out.println("Enter edges for all entered vertices in form vertex number1 , vertex number 2 ");
        HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
        for (int i=0;i<e;i++){
            String ver=scanner.next();
            String _t[]=ver.split(",");

        if (map.containsKey(Integer.parseInt(_t[0]))){
            map.get(Integer.parseInt(_t[0])).add(Integer.parseInt(_t[1]));
        }
        else{
            ArrayList<Integer> list=new ArrayList<>();
            list.add(Integer.parseInt(_t[1]));
            map.put(Integer.parseInt(_t[0]),list);
        }
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
        displayPMenu();
        int co[][]=null;
        int co1[][]=null;
        int ch=scanner.nextInt();
        switch (ch){
            case 1:{
              co=orthographicP(resultant,0);
              co1=orthographicP(convertToMatrix(x,y,z),0);
              break;
            }
            case 2:{
               co= orthographicP(resultant,1);
                co1=orthographicP(convertToMatrix(x,y,z),1);
               break;
            }
            case 3:{
                co=orthographicP(resultant,2);
                co1=orthographicP(convertToMatrix(x,y,z),2);
                break;
            }
            case 4:{
                co=perspectiveP(resultant);
                co1=perspectiveP(convertToMatrix(x,y,z));
            }
        }
        int res_x[]=new int[x.length];
        int res_y[]=new int[y.length];
        convertFromMatrix(co,res_x,res_y);
        convertFromMatrix(co1,x,y);
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
                graphics.setColor(Color.RED);
                graphics.drawLine(650,0,650,1000);
                graphics.drawLine(0,350,1400,350);
                graphics.setColor(Color.WHITE);
                Draw(graphics,map,x,y);
                graphics.setColor(Color.GREEN);
                graphics.drawString(" ( 0 , 0 )",650,345);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Draw(graphics,map,res_x,res_y);

            }
        };
        canvas.setBackground(Color.BLACK);
        jFrame.add(canvas);
        jFrame.setVisible(true);
    }
}
