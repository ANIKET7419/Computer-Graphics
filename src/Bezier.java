import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Bezier {
    public static void main(String[] args) {
        int x[]=new int[4],y[]=new int[4];
        class DrawScreen extends JComponent{
            @Override
            public void paint(Graphics graphics)
            {

                for(float u=0;u<=1;u+=0.001)
                {
                   int temp_x= (int)((1-u)*(1-u)*(1-u)*x[0]+3*u*(1-u)*(1-u)*x[1]+3*(1-u)*u*u*x[2]+u*u*u*x[3]);
                   int temp_y=(int)((1-u)*(1-u)*(1-u)*y[0]+3*u*(1-u)*(1-u)*y[1]+3*(1-u)*u*u*y[2]+u*u*u*y[3]);
                   graphics.drawLine(temp_x,temp_y,temp_x,temp_y);
                }
            }
        }

        JFrame jFrame = new JFrame("Bezier Curve");
        jFrame.setSize(1000, 1000);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Enter control points ");
        Scanner scanner=new Scanner(System.in);
        for (int i=0;i<4;i++)
        {
            x[i]=scanner.nextInt();
            y[i]=scanner.nextInt();
        }
        jFrame.add(new DrawScreen());
        jFrame.setVisible(true);



    }

}
