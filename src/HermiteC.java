import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class HermiteC {
    public static void main(String[] args) {
        int x[]=new int[2],y[]=new int[2];
        int tangent[]=new int[2];
        class DrawScreen extends JComponent {
            @Override
            public void paint(Graphics graphics)
            {

                for(float u=0.0f;u<=1.0;u+=0.001)
                {
                    int temp_x= (int)((2*u*u*u-3*u*u+1)*x[0]+(-2*u*u*u+3*u*u)*x[1]+(u*u*u-2*u*u+u)*tangent[0]+(u*u*u-u*u)*tangent[1]);
                    int temp_y=(int)((2*u*u*u-3*u*u+1)*y[0]+(-2*u*u*u+3*u*u)*y[1]+(u*u*u-2*u*u+u)*tangent[0]+(u*u*u-u*u)*tangent[1]);
                    graphics.drawOval(temp_x,temp_y,2,2);
                }
            }
        }

        JFrame jFrame = new JFrame("Hermite Curve");
        jFrame.setSize(1000, 1000);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Enter  points ");
        Scanner scanner=new Scanner(System.in);
        for (int i=0;i<2;i++)
        {
            x[i]=scanner.nextInt();
            y[i]=scanner.nextInt();
        }
        System.out.println("Enter tangent values ");
        for (int i=0;i<2;i++)
        {
            tangent[i]=scanner.nextInt();

        }
        jFrame.add(new DrawScreen());
        jFrame.setVisible(true);



    }

}
