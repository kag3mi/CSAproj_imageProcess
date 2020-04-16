package com.company;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;


public class Main {
    //Sets up panel
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel msglabel;


    public static void main(String[] args)throws IOException {
            int width;    //width of the image
            int height;   //height of the image

            BufferedImage image;
            // For storing image in RAM

            // READ IMAGE
            try
            {
                File input_file = new File("C:\\Users\\User458\\Desktop\\test input\\randomlady240x240.jpg"); //image file path
                //File input_file = new File(System.getProperty("user.dir") + System.getProperty("file.separator")+ "Images");
                image = ImageIO.read(input_file);
                height = image.getHeight();
                width = image.getWidth();

            /* create an object of BufferedImage type and pass
               as parameter the width,  height and image int
               type.TYPE_INT_ARGB means that we are representing
               the Alpha, Red, Green and Blue component of the
               image pixel using 8 bit integer value. */

                // Reading input file
                image = ImageIO.read(input_file);

                System.out.println("Reading complete.");

                //Arraylist for pixels.
                ArrayList<float[]> pixelsArr = new ArrayList<>();

                int count = 0;
                //getting & printing RGB values.
                for(int i=0; i<height; i++) {

                    for(int j=0; j<width; j++) {

                        count++;
                        Color c = new Color(image.getRGB(j, i));
                        int red = c.getRed();
                        int blue = c.getBlue();
                        int green = c.getGreen();
                        float[] hsv = new float[3];
                        Color.RGBtoHSB(red,green,blue,hsv);
                        pixelsArr.add(hsv);
                    }
                }
                quickSort(pixelsArr, 0,(pixelsArr.size()-1));
                for (int b = 0; b < pixelsArr.size(); b++){
                    System.out.println(pixelsArr.get(b)[0]);
                }

                //attempt to put stuffs on canvas
                Canvas canvas = new Canvas();

                // Output file path
                File output_file = new File("C:\\Users\\User458\\Desktop\\output test\\out.jpg");

                // Writing to file taking type and path as
                ImageIO.write(image, "jpg", output_file);

                System.out.println("Writing complete.");
            }//end of try block
            //error
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }

            //Creates button
            JFrame frame = new JFrame("My First GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300,300);
            JButton button1 = new JButton("Button 1");
            JButton button2 = new JButton("Button 2");
            frame.getContentPane().add(button1);
            frame.getContentPane().add(button2);

            frame.setVisible(true);
    }

    public static int partition(ArrayList<float[]> pixelsArr, int low, int high){
        float pivot = pixelsArr.get(high)[0];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++){
            // If current element is smaller than the pivot
            if (pixelsArr.get(j)[0] < pivot){
                i++;

                // swap arr[i] and arr[j]
                Collections.swap(pixelsArr,j,i);
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        Collections.swap(pixelsArr,high,i+1);

        return i+1;
    }

    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    public static void quickSort(ArrayList<float[]> pixelsArr, int low, int high){
        if (low < high){
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(pixelsArr, low, high);
            // Recursively sort elements before partition and after partition
            quickSort(pixelsArr, low, pi-1);
            quickSort(pixelsArr, pi+1, high);
        }
    }
}//main() ends here

//    public void actionPerformed(ActionEvent e) {
//        //Handle open button action.
//        if (e.getSource() == openButton) {
//            int returnVal = fc.showOpenDialog(FileChooserDemo.this);
//
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                File file = fc.getSelectedFile();
//                //This is where a real application would open the file.
//                log.append("Opening: " + file.getName() + "." + newline);
//            } else {
//                log.append("Open command cancelled by user." + newline);
//            }
//        }
//    }
//}//class ends here


//https://www.tutorialspoint.com/swing/swing_borderlayout.htm
