package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;



/**
 * Projekt1.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Projekt1 implements GLEventListener {
//statyczne pola okre�laj�ce rotacj� wok� osi X i Y
 private static float xrot = 0.0f, yrot = 0.0f;

 static Koparka koparka;

   public  static float ambientLight[] = { 0.3f, 0.3f, 0.3f, 1.0f };//swiat?o otaczaj?ce
   public     static float diffuseLight[] = { 0.7f, 0.7f, 0.7f, 1.0f };//?wiat?o rozproszone
   public      static float specular[] = { 1.0f, 1.0f, 1.0f, 1.0f}; //?wiat?o odbite
   public       static float lightPos[] = { 0.0f, 150.0f, 150.0f, 1.0f };//pozycja ?wiat?a
 
    public  static float ambientLight1[] = { 0.3f, 0.3f, 0.3f, 1.0f };//swiat?o otaczaj?ce
   public     static float diffuseLight1[] = { 0.7f, 0.7f, 0.7f, 1.0f };//?wiat?o rozproszone
   public      static float specular1[] = { 1.0f, 1.0f, 1.0f, 1.0f}; //?wiat?o odbite
   public       static float lightPos1[] = { 0.0f, 150.0f, 150.0f, 1.0f };//pozycja ?wiat?a
  static BufferedImage image1 = null,image2 = null, image3=null;
static Texture t1 = null, t2 = null, t3=null;

public static float x=0.0f,z=0.0f, kat=0.0f;

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Projekt1());
        frame.add(canvas);
        frame.setSize(1024,768);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        //Obs�uga klawiszy strza�ek
 frame.addKeyListener(new KeyListener()
 {
 public void keyPressed(KeyEvent e)
 {
 if(e.getKeyCode() == KeyEvent.VK_UP)
  przesun(1.0f);
 if(e.getKeyCode() == KeyEvent.VK_DOWN)
  przesun(-1.0f);
 if(e.getKeyCode() == KeyEvent.VK_RIGHT)
 kat += 1.0f;
 if(e.getKeyCode() == KeyEvent.VK_LEFT)
 kat -=1.0f;
 if(e.getKeyCode() == KeyEvent.VK_1)
 koparka.kond+=(1.5f);
 if(e.getKeyCode() == KeyEvent.VK_2)
 koparka.kond-=(1.5f); 
  if(e.getKeyCode() == KeyEvent.VK_3)
 koparka.kond2+=(1.5f);
 if(e.getKeyCode() == KeyEvent.VK_4)
 koparka.kond2-=(1.5f);
  if(e.getKeyCode() == KeyEvent.VK_5)
 koparka.kond3+=(1.5f);
 if(e.getKeyCode() == KeyEvent.VK_6)
 koparka.kond3-=(1.5f); 
   if(e.getKeyCode() == KeyEvent.VK_7)
 koparka.kond4+=(0.5f);
 if(e.getKeyCode() == KeyEvent.VK_8)
 koparka.kond4-=(0.5f); 
 
 if(e.getKeyChar()=='q'){
     ambientLight= new float[]{ambientLight[0]-0.1f, ambientLight[1]-0.1f,ambientLight[2]-0.1f,ambientLight[3]-0.01f};
     }
  if(e.getKeyChar()=='w'){
     ambientLight= new float[]{ambientLight[0]+0.1f, ambientLight[1]+0.1f,ambientLight[2]+0.1f,ambientLight[3]+0.01f};
     }
  if(e.getKeyChar()=='a'){
     diffuseLight= new float[]{diffuseLight[0]-0.1f, diffuseLight[1]-0.1f,diffuseLight[2]-0.1f,diffuseLight[3]-0.01f};
     }
  if(e.getKeyChar()=='s'){
     diffuseLight= new float[]{diffuseLight[0]+0.1f, diffuseLight[1]+0.1f,diffuseLight[2]+0.1f,diffuseLight[3]+0.01f};
     }
  if(e.getKeyChar()=='z'){
     specular= new float[]{specular[0]-0.1f, specular[1]-0.1f,specular[2]-0.1f,specular[3]-0.01f};
     }
  if(e.getKeyChar()=='x'){
     specular= new float[]{specular[0]+0.1f, specular[1]+0.1f,specular[2]+0.1f,specular[3]+0.01f};
     }
  if(e.getKeyChar()=='k'){
     lightPos= new float[]{lightPos[0]-0.1f, lightPos[1]-0.1f,lightPos[2]-0.1f,lightPos[3]-0.01f};
     }
  if(e.getKeyChar()=='l'){
     lightPos= new float[]{lightPos[0]+0.1f, lightPos[1]+0.1f,lightPos[2]+0.1f,lightPos[3]+0.01f};
     }
  
  
  
  
 }
 public void keyReleased(KeyEvent e){}
 public void keyTyped(KeyEvent e){}
 });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
   
        
        
        
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));
        
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
       koparka = new Koparka();
        // Enable VSync
       
       
        gl.setSwapInterval(1);

    //warto?ci sk?adowe o?wietlenia i koordynaty ?r�d?a ?wiat?a
    
        //(czwarty parametr okre?la odleg?o?? ?r�d?a:
        //0.0f-niesko?czona; 1.0f-okre?lona przez pozosta?e parametry)
        gl.glEnable(GL.GL_LIGHTING); //uaktywnienie o?wietlenia
        //ustawienie parametr�w ?r�d?a ?wiat?a nr. 0
        gl.glLightfv(GL.GL_LIGHT0,GL.GL_AMBIENT,ambientLight,0); //swiat?o otaczaj?ce
        gl.glLightfv(GL.GL_LIGHT0,GL.GL_DIFFUSE,diffuseLight,0); //?wiat?o rozproszone
        gl.glLightfv(GL.GL_LIGHT0,GL.GL_SPECULAR,specular,0); //?wiat?o odbite
        gl.glLightfv(GL.GL_LIGHT0,GL.GL_POSITION,lightPos,0); //pozycja ?wiat?a
        gl.glEnable(GL.GL_LIGHT0); //uaktywnienie ?r�d?a ?wiat?a nr. 0
        
          gl.glLightfv(GL.GL_LIGHT1,GL.GL_AMBIENT,ambientLight1,0); //swiat?o otaczaj?ce
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_DIFFUSE,diffuseLight1,0); //?wiat?o rozproszone
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_SPECULAR,specular1,0); //?wiat?o odbite
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_POSITION,lightPos1,0); //pozycja ?wiat?a
        gl.glEnable(GL.GL_LIGHT1); //uaktywnienie ?r�d?a ?wiat?a nr. 0
        gl.glEnable(GL.GL_COLOR_MATERIAL); //uaktywnienie ?ledzenia kolor�w
        //kolory b?d? ustalane za pomoc? glColor
        gl.glColorMaterial(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE);
        //Ustawienie jasno?ci i odblaskowo?ci obiekt�w
        float specref[] = { 1.0f, 1.0f, 1.0f, 1.0f }; //parametry odblaskowo?ci
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR,specref,0);
        
        gl.glMateriali(GL.GL_FRONT,GL.GL_SHININESS,128);

        gl.glEnable(GL.GL_DEPTH_TEST);
        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        
        try
{
image1 = ImageIO.read(getClass().getResourceAsStream("/bok.jpg"));
image2 = ImageIO.read(getClass().getResourceAsStream("/trawa.jpg"));
image3 = ImageIO.read(getClass().getResourceAsStream("/niebo.jpg"));
}
catch(Exception exc)
{
   JOptionPane.showMessageDialog(null, exc.toString());
return;
}

t1 = TextureIO.newTexture(image1, false);
t2 = TextureIO.newTexture(image2, false);
t3 = TextureIO.newTexture(image3, false);
gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE,
 GL.GL_BLEND | GL.GL_MODULATE);
gl.glEnable(GL.GL_TEXTURE_2D);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);

        
    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(98.0f, h, 1.0, 300.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        
    }

        void drzewko(GL gl){
            gl.glPushMatrix();
           gl.glColor3f(0.0f,1.0f,0.0f);
        stozek(gl);
        
          gl.glScalef(1.2f, 1.2f, 1.0f);
        gl.glTranslatef(0.0f,0.0f,1.0f);
        stozek(gl);
        
          gl.glScalef(1.4f, 1.4f, 1.0f);
         gl.glTranslatef(0.0f,0.0f,1.0f);
        stozek(gl);
        
        gl.glColor3f(0.0f,0.0f,0.0f);
          gl.glScalef(0.7f, 0.7f, 1.0f);
         gl.glTranslatef(0.0f,0.0f,1.0f);
        walec(gl);
        gl.glPopMatrix();
    }
    
    void walec(GL gl)
 {
//wywo�ujemy automatyczne normalizowanie normalnych
//bo operacja skalowania je zniekszta�ci
gl.glEnable(GL.GL_NORMALIZE);
float x,y,kat;
gl.glBegin(GL.GL_QUAD_STRIP);
for(kat = 0.0f; kat < (2.0f*Math.PI); kat += (Math.PI/32.0f))
{
x = 0.5f*(float)Math.sin(kat);
y = 0.5f*(float)Math.cos(kat);
gl.glNormal3f((float)Math.sin(kat),(float)Math.cos(kat),0.0f);
gl.glVertex3f(x, y, -1.0f);
gl.glVertex3f(x, y, 0.0f);
}
gl.glEnd();
gl.glNormal3f(0.0f,0.0f,-1.0f);
x=y=kat=0.0f;
gl.glBegin(GL.GL_TRIANGLE_FAN);
gl.glVertex3f(0.0f, 0.0f, -1.0f); //srodek kola
for(kat = 0.0f; kat < (2.0f*Math.PI); kat += (Math.PI/32.0f))
{
x = 0.5f*(float)Math.sin(kat);
y = 0.5f*(float)Math.cos(kat);
gl.glVertex3f(x, y, -1.0f);
}
gl.glEnd();
gl.glNormal3f(0.0f,0.0f,1.0f);
x=y=kat=0.0f;
gl.glBegin(GL.GL_TRIANGLE_FAN);
gl.glVertex3f(0.0f, 0.0f, 0.0f); //srodek kola
for(kat = 2.0f*(float)Math.PI; kat > 0.0f ; kat -= (Math.PI/32.0f))
{
x = 0.5f*(float)Math.sin(kat);
y = 0.5f*(float)Math.cos(kat);
gl.glVertex3f(x, y, 0.0f);
}
gl.glEnd();
}

void stozek(GL gl)
{
//wywo�ujemy automatyczne normalizowanie normalnych
gl.glEnable(GL.GL_NORMALIZE);
float x,y,kat;
gl.glBegin(GL.GL_TRIANGLE_FAN);
gl.glVertex3f(0.0f, 0.0f, -2.0f); //wierzcholek stozka
for(kat = 0.0f; kat < (2.0f*Math.PI); kat += (Math.PI/32.0f))
{
x = (float)Math.sin(kat);
y = (float)Math.cos(kat);
gl.glNormal3f((float)Math.sin(kat),(float)Math.cos(kat),-2.0f);
gl.glVertex3f(x, y, 0.0f);
}
gl.glEnd();
gl.glBegin(GL.GL_TRIANGLE_FAN);
gl.glNormal3f(0.0f,0.0f,1.0f);
gl.glVertex3f(0.0f, 0.0f, 0.0f); //srodek kola
for(kat = 2.0f*(float)Math.PI; kat > 0.0f; kat -= (Math.PI/32.0f))
{
x = (float)Math.sin(kat);
y = (float)Math.cos(kat);
gl.glVertex3f(x, y, 0.0f);
}
gl.glEnd();



}

public static void przesun(float d){
x-=d*Math.sin(kat*(3.14f/180.0f));
z+=d*Math.cos(kat*(3.14f/180.0f));
 
}

void Rysuj(GL gl, Texture t1, Texture t2, Texture t3)
  {    
      gl.glColor3f(1.0f,1.0f,1.0f);
//za�adowanie tekstury wczytanej wcze�niej z pliku krajobraz.bmp
gl.glBindTexture(GL.GL_TEXTURE_2D, t1.getTextureObject());
gl.glBegin(GL.GL_QUADS);
//�ciana przednia
gl.glNormal3f(0.0f,0.0f,-1.0f);
gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(-100.0f,100.0f,100.0f);
gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(100.0f,100.0f,100.0f);
gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(100.0f,-100.0f,100.0f);
gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(-100.0f,-100.0f,100.0f);
//�ciana tylnia
gl.glNormal3f(0.0f,0.0f,1.0f);
gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(-100.0f,-100.0f,-100.0f);
gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(100.0f,-100.0f,-100.0f);
gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(100.0f,100.0f,-100.0f);
gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(-100.0f,100.0f,-100.0f);
//�ciana lewa
gl.glNormal3f(1.0f,0.0f,0.0f);
gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-100.0f,100.0f,-100.0f);
gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(-100.0f,100.0f,100.0f);
gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(-100.0f,-100.0f,100.0f);
gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-100.0f,-100.0f,-100.0f);
//�ciana prawa
gl.glNormal3f(-1.0f,0.0f,0.0f);
gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(100.0f,-100.0f,-100.0f);
gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(100.0f,-100.0f,100.0f);
gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(100.0f,100.0f,100.0f);
gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(100.0f,100.0f,-100.0f);
gl.glEnd();

//�ciana dolna
//za�adowanie tekstury wczytanej wcze�niej z pliku niebo.bmp
 gl.glBindTexture(GL.GL_TEXTURE_2D, t2.getTextureObject());
 //ustawienia aby tekstura si� powiela�a
 gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
 gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
gl.glBegin(GL.GL_QUADS);
gl.glNormal3f(0.0f,1.0f,0.0f);
 //koordynaty ustawienia 16 x 16 kwadrat�w powielonej tekstury na �cianie dolnej
gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(100.0f,-100.0f,100.0f);
gl.glTexCoord2f(0.0f, 16.0f);gl.glVertex3f(100.0f,-100.0f,-100.0f);
gl.glTexCoord2f(16.0f, 16.0f);gl.glVertex3f(-100.0f,-100.0f,-100.0f);
gl.glTexCoord2f(16.0f, 0.0f);gl.glVertex3f(-100.0f,-100.0f,100.0f);
gl.glEnd();
     //�ciana gorna
//za�adowanie tekstury wczytanej wcze�niej z pliku trawa.bmp
gl.glBindTexture(GL.GL_TEXTURE_2D, t3.getTextureObject());
gl.glBegin(GL.GL_QUADS);
gl.glNormal3f(0.0f,-1.0f,0.0f);
gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-100.0f,100.0f,100.0f);
gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(-100.0f,100.0f,-100.0f);
gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(100.0f,100.0f,-100.0f);
gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(100.0f,100.0f,100.0f);
gl.glEnd(); 
gl.glEnd();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        
 gl.glRotatef(1.0f, 1.0f, 0.0f, 0.0f); //rotacja wok� osi X
 gl.glRotatef(kat, 0.0f, 1.0f, 0.0f); //rotacja wok� osi Y
 gl.glTranslatef(x, 80.0f, z); //przesuni�cie o 6 jednostek
  gl.glEnable(GL.GL_LIGHTING); //uaktywnienie o?wietlenia
        //ustawienie parametr�w ?r�d?a ?wiat?a nr. 0
        gl.glLightfv(GL.GL_LIGHT0,GL.GL_AMBIENT,ambientLight,0); //swiat?o otaczaj?ce
        gl.glLightfv(GL.GL_LIGHT0,GL.GL_DIFFUSE,diffuseLight,0); //?wiat?o rozproszone
        gl.glLightfv(GL.GL_LIGHT0,GL.GL_SPECULAR,specular,0); //?wiat?o odbite
        gl.glLightfv(GL.GL_LIGHT0,GL.GL_POSITION,lightPos,0); //pozycja ?wiat?a
        gl.glEnable(GL.GL_LIGHT0); 
//
         gl.glLightfv(GL.GL_LIGHT1,GL.GL_AMBIENT,ambientLight,0); //swiat?o otaczaj?ce
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_DIFFUSE,diffuseLight,0); //?wiat?o rozproszone
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_SPECULAR,specular,0); //?wiat?o odbite
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_POSITION,lightPos,0); //pozycja ?wiat?a
        gl.glEnable(GL.GL_LIGHT1);
////uaktywnienie ?r�d?a ?wiat?a nr. 0
      gl.glEnable(GL.GL_COLOR_MATERIAL); 
    
        Rysuj(gl,t1,t2,t3);
       gl.glTranslatef(0.0f,-81.0f,1.0f);
        koparka.Rysuj(gl);
//for(int i=0; i<7; i++){
//    gl.glPushMatrix();
//        for(int k=0; k<7; k++){
//            drzewko(gl);
//            gl.glTranslatef(1.8f,0.0f,0.0f);
//        }
//        gl.glPopMatrix();
//        gl.glTranslatef(0.0f,1.8f,0.0f);
//        }
//    gl.glBindTexture(GL.GL_TEXTURE_2D, t1.getTextureObject());
//gl.glBegin(GL.GL_QUADS);
////�ciana przednia
//
//gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,-1.0f,1.0f);
//gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(1.0f,-1.0f,1.0f);
//gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(1.0f,1.0f,1.0f);
//gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f,1.0f,1.0f);
////sciana tylnia
//
//gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,1.0f,-1.0f);
//gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(1.0f,1.0f,-1.0f);
//gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(1.0f,-1.0f,-1.0f);
//gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,-1.0f,-1.0f);
////�ciana lewa
//
//gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,-1.0f,-1.0f);
//gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,-1.0f,1.0f);
//gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,1.0f,1.0f);
//gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f,1.0f,-1.0f);
//////�ciana prawa
//
//gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(1.0f,1.0f,-1.0f);
//gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(1.0f,1.0f,1.0f);
//gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(1.0f,-1.0f,1.0f);
//gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(1.0f,-1.0f,-1.0f);
//////�ciana dolna
//
//gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f,1.0f);
//gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(1.0f,-1.0f,1.0f);
//gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(1.0f,-1.0f,-1.0f);
//gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,-1.0f,-1.0f);
////
//////�ciana g�rna
//
//gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,1.0f,1.0f);
//gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(1.0f,1.0f,1.0f);
//gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(1.0f,1.0f,-1.0f);
//gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(-1.0f,1.0f,-1.0f);
    } 
  
    
//ostroslup
 
// gl.glBegin(GL.GL_QUADS);
// gl.glColor3f(0.0f,0.5f,1.0f);
//gl.glVertex3f(-1.0f,-1.0f,1.0f);
//gl.glVertex3f(-1.0f,-1.0f,-1.0f);
//gl.glVertex3f(1.0f,-1.0f,-1.0f);
//gl.glVertex3f(1.0f,-1.0f,1.0f);
//gl.glEnd();
// gl.glBegin(GL.GL_TRIANGLES);
////// //sciana prawa
// gl.glColor3f(1.0f,-1.0f,1.0f);
//gl.glVertex3f(0.0f,1.0f,0.0f);
//gl.glVertex3f(1.0f,-1.0f,1.0f);
//gl.glVertex3f(1.0f,-1.0f,-1.0f);
////////sciana lewa
// gl.glColor3f(-1.0f,0.0f,1.0f);
//gl.glVertex3f(0.0f,1.0f,0.0f);
//gl.glVertex3f(-1.0f,-1.0f,-1.0f);
//gl.glVertex3f(-1.0f,-1.0f,1.0f);
////////sciana tylnia
//gl.glColor3f(1.0f,0.0f,-1.0f);
//gl.glVertex3f(0.0f,1.0f,0.0f);
//gl.glVertex3f(1.0f,-1.0f,-1.0f);
//gl.glVertex3f(-1.0f,-1.0f,-1.0f);
////////sciana przednia
//gl.glColor3f(1.0f,0.0f,1.0f);
//gl.glVertex3f(0.0f,1.0f,0.0f);
//gl.glVertex3f(-1.0f,-1.0f,1.0f);
//gl.glVertex3f(1.0f,-1.0f,1.0f);
//gl.glEnd(); 
 //walec
// float x,y,kat;
//gl.glBegin(GL.GL_TRIANGLE_FAN);
//gl.glVertex3f(0.0f,1.0f, .0f); //�rodek
//for(kat = 0.0f; kat < (2.0f*Math.PI); kat+=(Math.PI/32.0f))
//{
//x = 1.5f*(float)Math.sin(kat);
//y = 1.5f*(float)Math.cos(kat);
//gl.glVertex3f(x, 1.0f,y); //kolejne punkty
//}
//gl.glEnd();
//
//float x1,y1,kat1;
//gl.glBegin(GL.GL_TRIANGLE_FAN);
//gl.glVertex3f(0.0f,-1.0f, 0.0f); //�rodek
//for(kat1 = (float) (2.0f*Math.PI); kat1 > 0.0f; kat1-=(Math.PI/32.0f))
//{
//x1 = 1.5f*(float)Math.sin(kat1);
//y1 = 1.5f*(float)Math.cos(kat1);
//gl.glVertex3f(x1,-1.0f,y1); //kolejne punkty
//}
//gl.glEnd();
//
//float x2,y2,kat2;
//gl.glBegin(GL.GL_QUAD_STRIP);
//gl.glColor3f(1.0f,0.0f,1.0f);
//for(kat2 = 0.0f; kat2 < (2.0f*Math.PI); kat2+=(Math.PI/32.0f))
//{
//x2 = 1.5f*(float)Math.sin(kat2);
//y2 = 1.5f*(float)Math.cos(kat2);
// gl.glVertex3f(x2,1.0f,y2);
// gl.glVertex3f(x2,-1.0f,y2);//kolejne punkty
//}
// gl.glEnd();       // Flush all drawing operations to the graphics card
      //  gl.glFlush();
       
  //drzewko      

    
    
    

    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

