import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Circle {
    static int x,y,r;
    public static void main(String[] args)  {


        System.out.println("Enter value of x");
        Scanner scanner=new Scanner(System.in);
        x=scanner.nextInt();
        System.out.println("Enter value of y");
        y=scanner.nextInt();
        System.out.println("Enter value of r");
        r=scanner.nextInt();
        JFrame jFrame=new JFrame("Mid Point Circle Algorithm ");
        jFrame.setSize(1000,1000);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas= new Canvas(){

            @Override
            public void paint(Graphics graphics)
            {

                graphics.setColor(Color.WHITE);
                int p= 1-r;
                int t_x=0;
                int t_y=r;
                while(t_x<=t_y)
                {
                    graphics.setColor(Color.WHITE);
                    graphics.drawOval(t_x+x,t_y+y,2,2);
                    graphics.setColor(Color.GREEN);
                    graphics.drawOval(t_y+x,t_x+y,2,2);
                    graphics.setColor(Color.RED);
                    graphics.drawOval(-t_y+x,t_x+y,2,2);
                    graphics.setColor(Color.BLUE);
                    graphics.drawOval(t_x+x,-t_y+y,2,2);
                    graphics.setColor(Color.YELLOW);
                    graphics.drawOval(-t_x+x,-t_y+y,2,2);
                    graphics.setColor(Color.PINK);
                    graphics.drawOval(-t_y+x,-t_x+y,2,2);
                    graphics.setColor(Color.CYAN);
                    graphics.drawOval(t_y+x,-t_x+y,2,2);
                    graphics.setColor(Color.magenta);
                    graphics.drawOval(-t_x+x,t_y+y,2,2);

                    t_x++;
                    if (p<0)
                        p=p+2*t_x+1;
                    else
                    {
                        t_y--;
                        p=p+2*t_x-2*t_y+1;
                    }

                }




            }
        };
        canvas.setBackground(Color.BLACK);
        jFrame.add(canvas);
        jFrame.setVisible(true);
    }
}
