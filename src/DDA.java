import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class DDA {
    static float x1,x2,y1,y2;
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
           float temp=x2;
           x2=x1;
           x1=temp;
           temp=y2;
           y2=y1;
           y1=temp;
       }
        JFrame jFrame=new JFrame("DDA");
        jFrame.setSize(1000,1000);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas=new Canvas(){

          @Override
          public void paint(Graphics graphics)
          {
             graphics.setColor(Color.GREEN);
              graphics.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,22));
              graphics.drawString("DDA Algorithm ",20,40);
              graphics.setColor(Color.WHITE);
              float slope=(y2-y1)/(x2-x1);
              float t_x1=x1;
              float t_y1=y1;
              if(slope>1)
              {
                  for(int i=0;i<Math.max(Math.abs(x2-x1),Math.abs(y2-y1));i++) {
                      t_x1 = t_x1 + (1 / slope);
                      t_y1 = t_y1 + 1;
                      graphics.fillOval(Math.round(t_x1),Math.round(t_y1),2,2);
                  }
              }
              else if (slope==1)
              {
                  for(int i=0;i<Math.max(Math.abs(x2-x1),Math.abs(y2-y1));i++) {
                      t_x1 = t_x1 + 1;
                      t_y1 = t_y1 + 1;
                      graphics.fillOval(Math.round(t_x1),Math.round(t_y1),2,2);

                  }
              }
              else
              {
                  for(int i=0;i<Math.max(Math.abs(x2-x1),Math.abs(y2-y1));i++) {
                      t_x1 = t_x1 + 1;
                      t_y1 = t_y1 + slope;
                      graphics.fillOval(Math.round(t_x1),Math.round(t_y1),2,2);

                  }
              }

          }
        };
        canvas.setBackground(Color.BLACK);
        jFrame.add(canvas);
        jFrame.setVisible(true);
    }
}
