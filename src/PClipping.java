import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

class Edge{
    int x1,y1,x2,y2;
    boolean v;
    Edge(int x1,int y1,int x2,int y2)
    {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;

    }
}
public class PClipping {
    public static void main(String[] args) {


        System.out.println("Enter height of the window ");
        Scanner scanner=new Scanner(System.in);
        int h=scanner.nextInt();
        System.out.println("Enter width of the window ");
        int w=scanner.nextInt();
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
        Canvas canvas=new Canvas(){

            @Override
            public void paint(Graphics graphics)
            {

                graphics.setColor(Color.GREEN);
                graphics.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,22));
                graphics.drawString("Polygon Clipping",250,30);
                graphics.setColor(Color.RED);
                graphics.drawRect(250,250,w,h);
                graphics.setColor(Color.WHITE);
                graphics.drawPolygon(x,y,n);

            }
        };
        JFrame jFrame = new JFrame("Before Clipping");
        jFrame.setSize(1000, 1000);
        jFrame.setResizable(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(canvas);
        jFrame.setVisible(true);
        canvas.setBackground(Color.BLACK);
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
               JFrame frame1=new JFrame();
                frame1.setTitle("After Clipping");
                frame1.setSize(1000 , 1000);
                frame1.setResizable(true);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Canvas canvas1=new Canvas(){

                    @Override
                    public void paint(Graphics graphics)
                    {
                        ArrayList<Edge> list=new ArrayList<>();
                        for(int i=0;i<n;i++)
                          list.add(new Edge(x[i],y[i],x[(i+1)%n],y[(i+1)%n]));



                        // LEFT PROCESSING

                        for(int i=0;i<list.size();i++)
                        {
                            Edge edge=list.get(i);
                            if (edge.v)
                                continue;
                            edge.v=true;
                            float slope=((float)edge.y2-edge.y1)/(edge.x2-edge.x1);
                            int y_point=Math.round(slope*(250-edge.x1)+edge.y1);
                            if (edge.x1<250&&edge.x2<250){
                                list.remove(i);
                                i=-1;
                            }
                            else if (edge.x1<250){
                             edge.x1=250;
                             edge.y1=y_point;
                            }
                            else if (edge.x2<250) {
                                edge.x2=250;
                                edge.y2=y_point;
                            }
                        }
                        for(Edge edge:list)
                            edge.v=false;

                        // RIGHT PROCESSING

                        for(int i=0;i<list.size();i++)
                        {
                            Edge edge=list.get(i);
                            if (edge.v)
                                continue;
                            edge.v=true;
                            float slope=((float)edge.y2-edge.y1)/(edge.x2-edge.x1);
                            int y_point=Math.round(slope*(250+w-edge.x1)+edge.y1);
                            if (edge.x1>(250+w)&&edge.x2>(250+w)){
                                list.remove(i);
                                i=-1;
                            }
                            else if (edge.x1>(250+w)){
                                edge.x1=250+w;
                                edge.y1=y_point;
                            }
                            else if (edge.x2>(250+w)) {
                                edge.x2=250+w;
                                edge.y2=y_point;
                            }
                        }

                        // TOP PROCESSING

                        for(Edge edge:list)
                            edge.v=false;

                        for(int i=0;i<list.size();i++)
                        {
                            Edge edge=list.get(i);
                            if (edge.v)
                                continue;
                            edge.v=true;
                            float slope=((float)edge.y2-edge.y1)/(edge.x2-edge.x1);
                            int x_point=Math.round(((250-edge.y1)/slope)+edge.x1);
                            if (edge.y1<250&&edge.y2<250){
                                list.remove(i);
                                i=-1;
                            }
                            else if (edge.y1<250){
                                edge.x1=x_point;
                                edge.y1=250;
                            }
                            else if (edge.y2<250) {
                                edge.x2=x_point;
                                edge.y2=250;
                            }
                        }

                        //BOTTOM PROCESSING
                        for(Edge edge:list)
                            edge.v=false;

                        for(int i=0;i<list.size();i++)
                        {
                            Edge edge=list.get(i);
                            if (edge.v)
                                continue;
                            edge.v=true;
                            float slope=((float)edge.y2-edge.y1)/(edge.x2-edge.x1);
                            int x_point=Math.round(((250+h-edge.y1)/slope)+edge.x1);
                            if (edge.y1>(250+h)&&edge.y2>(250+h)){
                                list.remove(i);
                                i=-1;
                            }
                            else if (edge.y1>(250+h)){
                                edge.x1=x_point;
                                edge.y1=250+h;
                            }
                            else if (edge.y2>(250+h)) {
                                edge.x2=x_point;
                                edge.y2=250+h;
                            }
                        }
                        graphics.setColor(Color.RED);
                        graphics.drawRect(250,250,w,h);
                        graphics.setColor(Color.WHITE);
                        for(Edge edge:list){
                            graphics.drawLine(edge.x1,edge.y1,edge.x2,edge.y2);
                        }

                    }
                };

                canvas1.setBackground(Color.BLACK);
                frame1.add(canvas1);
                jFrame.setVisible(false);
                frame1.setVisible(true);


            }
        });
    }
}
