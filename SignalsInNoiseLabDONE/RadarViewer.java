import javax.swing.JFrame;
import java.util.Scanner;

/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class RadarViewer
{
    /**
     * main method for the program which creates and configures the frame for the program
     *
     */
    public static void main(int rows,int cols, int dX, int dY) throws InterruptedException
    {
        // create the radar, set the monster location, and perform the initial scan
        Scanner in=new Scanner(System.in);
        final int ROWS = 100;
        final int COLS = 100;
        System.out.println("chose a dx value");
        dX = in.nextInt();
        System.out.println("chose a dx value");
        dY = in.nextInt();
        System.out.println("Chose x initial value");
        rows = in.nextInt();
        System.out.println("Chose y initial value");
        cols = in.nextInt();
        Radar radar = new Radar(ROWS, COLS,dX,dY);
        radar.setNoiseFraction(0.10);
        radar.scan(3,3);
        
        JFrame frame = new JFrame();
        
        frame.setTitle("Signals in Noise Lab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a frame contains a single component; create the radar component and add it to the frame
        RadarComponent component = new RadarComponent(radar);
        frame.add(component);
        
        // set the size of the frame to encompass the contained component
        frame.pack();
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        // perform 100 scans of the radar wiht a slight pause between each
        // after each scan, instruct the Java Run-Time to redraw the window
        for(int i = 0; i < 100; i++)
        {
            Thread.sleep(100); // sleep 100 milliseconds (1/10 second)
            
            radar.scan(dX*i,dY*i);
            
            frame.repaint();
        }
        System.out.println("The Dx is"+" "+radar.getVelocity()[0]);
        System.out.println("The Dy is"+" "+radar.getVelocity()[1]);
    }

}
