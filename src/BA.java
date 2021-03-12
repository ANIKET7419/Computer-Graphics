import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
public class BA {
    static int x1,x2,y1,y2;
    public static void main(String[] args) {
        System.out.println("Enter value of x1");
        Scanner scanner=new Scanner(System.in);
        x1=scanner.nextInt();
        System.out.println("Enter value of y1");
        y1=scanner.nextInt();
        System.out.println("Enter value of x2");
        x2=scanner.nextInt();
        System.out.println("Enter value of y2");
        y2=scanner.nextInt();
        if(x1>x2)
        {
            int temp=x2;
            x2=x1;
            x1=temp;
            temp=y2;
            y2=y1;
            y1=temp;
        }
        JFrame jFrame=new JFrame("B A ");
        jFrame.setSize(1000,1000);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas= new Canvas(){

            @Override
            public void paint(Graphics graphics)
            {
                graphics.setColor(Color.WHITE);
                 int t_x1=x1;
                 int t_y1=y1;
                 if(t_x1!=x2) {
                     int dx = x2 - x1;
                     int dy = y2 - y1;
                     int p = 2 * dy - dx;
                     while (t_x1 < x2) {
                         graphics.drawOval(t_x1, t_y1, 2, 2);
                         t_x1 = t_x1 + 1;
                         if (p < 0) {
                             p += 2 * dy;
                         } else {
                             t_y1++;
                             p += (2 * dy - 2 * dx);
                         }
                     }
                 }
                 else
                 {
                     while(t_y1!=y2)
                     {
                         graphics.drawOval(t_x1,t_y1,2,2);
                         t_y1++;
                     }
                 }




            }
        };
        canvas.setBackground(Color.BLACK);
        jFrame.add(canvas);
        jFrame.setVisible(true);
    }
}
