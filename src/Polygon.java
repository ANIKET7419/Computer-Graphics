import javax.swing.*;
import java.awt.*;
import java.util.*;
class Vertex
{
    int x,y;
}
class Node{
    float onebym;
    float x_of_y_min;
    int  y_max;
    @Override
    public String toString()
    {return "("+x_of_y_min+" , "+y_max+", "+onebym+" )";
    }



}
public class Polygon {





 static int y_min,y_max;
 static HashSet<String> traversed=new HashSet<>();
 static HashMap<Vertex,ArrayList<Vertex>> edges=new HashMap<>();
    static Vertex[] read() {


        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.println("Enter Number of vertices");
        n = scanner.nextInt();
        Vertex v[]=new Vertex[n];
        for (int i = 0; i < n; i++){
            v[i]=new Vertex();
            v[i].x=scanner.nextInt();
            v[i].y=scanner.nextInt();
        }




        return v;


    }



    static void searchAndInsert(HashMap<Vertex,ArrayList<Vertex>>edges,ArrayList<Node> list,int y, Graphics graphics)
    {


        for(Map.Entry<Vertex,ArrayList<Vertex>> entry:edges.entrySet()) {
            Vertex start = entry.getKey();
            if (start.y == y) {

                ArrayList<Vertex> connected = entry.getValue();
                for (Vertex temp : connected) {
                    String a = start.x + "_" + start.y + "_" + temp.x + "_" + temp.y;
                    if (!(traversed.contains(a))) {
                        String b = temp.x + "_" + temp.y + "_" + start.x + "_" + start.y;
                        traversed.add(a);
                        traversed.add(b);
                        Node t = new Node();
                        float dy = start.y - temp.y;
                        float dx = start.x - temp.x;
                        t.onebym = dx / dy;
                        if (start.y > temp.y) {
                            t.y_max = start.y;
                            t.x_of_y_min = temp.x;

                        } else if (start.y < temp.y) {
                            t.y_max = temp.y;
                            t.x_of_y_min = start.x;
                        } else {
                            graphics.drawLine(start.x, start.y, temp.x, temp.y);
                            continue;
                        }
                        list.add(t);
                    }
                }
            }
        }
    }


static int k=0;
    static boolean testing(int x,int y,HashMap<Vertex,ArrayList<Vertex>> map)
    {

        for(Map.Entry<Vertex,ArrayList<Vertex>> entry:map.entrySet())
        {
            Vertex v=entry.getKey();
            if (v.x==x&&v.y==y)
            {

                ArrayList<Vertex> vi=entry.getValue();
                return (vi.get(0).y <= y || vi.get(1).y >= y) && (vi.get(0).y >= y || vi.get(1).y <= y);

            }

        }
        return false;
    }
    public static void main(String[] args) {


        ArrayList<ArrayList<Node>> GET = new ArrayList<>();
        ArrayList<Node> AET = new ArrayList<>();
        Vertex[] v = read();
        if (v.length<3)
        {
            System.out.println("Provided Polygon is wrong");
            System.exit(100);
        }

        for(int i=0;i<v.length;i++)
        {
            ArrayList<Vertex> list=new ArrayList<>();
            if(i!=0&&i!=v.length-1)
            {
                list.add(v[i-1]);
                list.add(v[i+1]);
            }
            else  if(i==0)
            {
                list.add(v[i+1]);
                list.add(v[v.length-1]);
            }
            else if(i==v.length-1)
            {
                list.add(v[i-1]);
                list.add(v[0]);
            }
            edges.put(v[i],list);
        }
        y_min = Integer.MAX_VALUE;
        y_max = Integer.MIN_VALUE;
        for (int i = 0; i < v.length; i++){
            y_max=Math.max(y_max,v[i].y);
            y_min=Math.min(y_min,v[i].y);
        }

        class DrawScreen extends  JComponent{
            @Override
            public void paint(Graphics graphics) {
               graphics.setColor(Color.BLACK);
                for (int i = y_min; i <= y_max; i++) {
                    ArrayList<Node> list = new ArrayList<>();
                    searchAndInsert(edges, list, i, graphics);
                    GET.add(list);
                }
                //AET PART
                for(int i=y_min;i<=y_max;i++)
                {


                    for(int j=0;j<AET.size();j++)
                    {
                        if (AET.get(j).y_max==i)
                        {
                            AET.remove(j);
                            j=-1;
                        }

                    }
                    for(Node n:GET.get(i-y_min))
                    {
                        if (AET.size()==0)
                        {
                            AET.add(n);
                        }
                        else {
                            boolean flag1=false;
                            for (int j = 0; j < AET.size(); j++) {
                                if (n.x_of_y_min < AET.get(j).x_of_y_min) {
                                    flag1=true;
                                    AET.add(j, n);
                                    break;
                                }

                            }
                            if (!flag1)
                            {
                            AET.add(n);
                            }
                        }

                    }
                    Color[]colors={Color.BLACK,Color.BLACK,Color.BLACK,Color.MAGENTA,Color.MAGENTA,Color.MAGENTA,Color.BLUE,Color.BLUE,Color.BLUE,Color.GREEN,Color.GREEN,Color.GREEN,Color.RED,Color.RED,Color.RED};
                    ArrayList<Vertex> points=new ArrayList<>();
                    for(int j=0;j<AET.size();j++) {
                        int x = Math.round(AET.get(j).x_of_y_min);
                        Vertex vertex=new Vertex();
                        if (Math.abs(AET.get(j).x_of_y_min - x) <= 0.000001) {
                            boolean flag = testing(x, i, edges);
                            vertex.x=x;
                            vertex.y=i;
                            points.add(vertex);
                            if (flag) {
                                points.add(vertex);
                            }

                        }
                        else
                        {
                            vertex.x=x;
                            vertex.y=i;
                            points.add(vertex);

                        }



                    }

                    graphics.setColor(colors[(k=(k+1)%colors.length)]);
                    for (int j=0;j<points.size()-1;j++) {
                        graphics.drawLine(points.get(j).x, points.get(j).y, points.get(j + 1).x, points.get(j + 1).y);
                    }
                    for (Node node : AET) {
                        node.x_of_y_min  += node.onebym;
                    }






                }
               /* int x[]=new int[v.length];
                int y[]=new int[v.length];
                int k=0;
                for(Vertex v1:v)
                {
                    x[k]=v1.x;
                    y[k]=v1.y;
                    k++;
                }
                graphics.drawPolygon(x,y,v.length);
                */


            }

        }

        JFrame jFrame = new JFrame("Polygon Filling");
        jFrame.setSize(1000, 1000);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(new DrawScreen());
        jFrame.setVisible(true);
    }
}
