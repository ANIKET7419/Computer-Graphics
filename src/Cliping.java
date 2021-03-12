import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Cliping {
    static int rx,ry,height,width,x1,y1,x2,y2;
    public static void main(String[] args) {
        System.out.println("Rectangle");
    Scanner scanner=new Scanner(System.in);
        System.out.println("X");
        rx=scanner.nextInt();
        System.out.println("Y");
        ry=scanner.nextInt();
        System.out.println("Height");
        height=scanner.nextInt();
        System.out.println("Width");
        width=scanner.nextInt();
        System.out.println("Line");
        System.out.println("X");
        x1=scanner.nextInt();
        System.out.println("Y");
        y1=scanner.nextInt();
        System.out.println("X");
        x2=scanner.nextInt();
        System.out.println("Y");
        y2=scanner.nextInt();



    JFrame jFrame=new JFrame("Clipping ");
        jFrame.setSize(1000,1000);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Canvas canvas= new Canvas(){

        @Override
        public void paint(Graphics graphics)
        {

            graphics.setColor(Color.GREEN);
            graphics.drawString("Before Clipping, Type d to get result ",10,10);
            graphics.setColor(Color.WHITE);
            graphics.drawRect(rx,ry,width,height);
            graphics.drawLine(x1,y1,x2,y2);


        }
    };
        canvas.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar()=='d') {
                    jFrame.setVisible(false);
                    JFrame jFrame1=new JFrame("Result");
                    jFrame1.setSize(1000,1000);
                    Canvas canvas1=new Canvas(){
                        @Override
                        public void paint(Graphics graphics)
                        {
                            graphics.setColor(Color.GREEN);
                            graphics.drawString("After Clipping",10,10);
                            graphics.setColor(Color.WHITE);
                            graphics.drawRect(rx,ry,width,height);
                            int code1=getCode(x1,y1,rx,ry);
                            int code2=getCode(x2,y2,rx,ry);
                            int out_code;
                            int x,y;
                            float slope=((float)(y2-y1)/(x2-x1));
                            while(true) {
                                if ((code1 & code2) == 0) {

                                  if (code1!=0)
                                    out_code=code1;
                                  else if (code2!=0)
                                      out_code=code2;
                                  else
                                  {
                                      graphics.drawLine(x1,y1,x2,y2);
                                      break;
                                  }
                                  if (out_code==8)
                                  {
                                    x=(int)(x1+(1/slope)*(ry-y1));
                                    y=ry;
                                  }
                                  else if (out_code==10)
                                  {
                                      x=(int)(x1+(1/slope)*(ry-y1));
                                      y=ry;
                                      if (!(x>=rx&&x<=rx+width))
                                      {
                                          y=(int)(slope*(rx+width-x1))+y1;
                                          x=rx+width;
                                      }

                                  }
                                  else if (out_code==1)
                                  {
                                      y=(int)(slope*(rx-x1))+y1;
                                      x=rx;

                                  }
                                  else if (out_code==2)
                                  {
                                   y=(int)(slope*(rx+width-x1))+y1;
                                   x=rx+width;
                                  }
                                  else if (out_code==9)
                                  {
                                      y=(int)(slope*(rx-x1))+y1;
                                      x=rx;
                                      if(!(y>=ry&&y<=ry+height))
                                      {
                                          x=(int)(x1+(1/slope)*(ry-y1));
                                          y=ry;
                                      }


                                  }
                                  else if (out_code==5)
                                  {
                                      y=(int)(slope*(rx-x1))+y1;
                                      x=rx;
                                      if(!(y>=ry&&y<=ry+height))
                                      {
                                          x=(int)(x1+(1/slope)*(ry+height-y1));
                                          y=ry+height;
                                      }

                                  }
                                  else if (out_code==4)
                                  {
                                      x=(int)(x1+(1/slope)*(ry+height-y1));
                                      y=ry+height;
                                  }
                                  else
                                  {
                                      x=(int)(x1+(1/slope)*(ry+height-y1));
                                      y=ry+height;
                                      if (!(x>=rx&&x<=rx+width))
                                      {
                                          y=(int)(slope*(rx+width-x1))+y1;
                                          x=rx+width;
                                      }

                                  }
                                  if (out_code==code1)
                                  {
                                      x1=x;
                                      y1=y;
                                      code1=getCode(x1,y1,rx,ry);
                                  }
                                  else
                                  {
                                      x2=x;
                                      y2=y;
                                      code2=getCode(x2,y2,rx,ry);
                                  }



                                }
                                else
                                    break;
                            }






                        }
                        int getCode(int x1,int y1,int rx,int ry)
                        {
                            int code1;
                            if (x1<rx)
                            {
                                if (y1<ry)
                                    code1=9;
                                else if (y1>ry+height)
                                    code1=5;
                                else
                                    code1=1;
                            }
                            else if (x1>rx+width)
                            {
                                if (y1<ry)
                                    code1=10;
                                else if (y1>ry+height)
                                    code1=6;
                                else
                                    code1=2;
                            }
                            else
                            {
                                if (y1<ry)
                                    code1=8;
                                else if (y1>ry+height)
                                    code1=4;
                                else
                                    code1=0;
                            }
                            return code1;
                        }
                    };
                    canvas1.setBackground(Color.BLACK);
                    jFrame1.add(canvas1);
                    jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    jFrame1.setVisible(true);
                }
            }
        });
        canvas.setBackground(Color.BLACK);
        jFrame.add(canvas);
        jFrame.setVisible(true);
}
}
