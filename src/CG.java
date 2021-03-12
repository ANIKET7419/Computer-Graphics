import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class CG {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the number of vertices in polygon ");
        int n=scanner.nextInt();
        System.out.println("Enter vertices");
        int x [    ] = new int[ n ];
        int y [    ] = new int[ n ];
        for (int i = 0;i<n;i++)
        {
            x[i]=scanner.nextInt();
            y[i]=scanner.nextInt();
        }

        JFrame frame=new JFrame("Graphics");
        frame.setSize(1000,1000);


        Canvas canvas=new Canvas(){

          @Override
            public void paint(Graphics graphics)
          {
              graphics.setColor(Color.WHITE);
              graphics.drawPolygon(x,y,n);
          }
        };
        canvas.setBackground(Color.BLACK);
        frame.add(canvas);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
