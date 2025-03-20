import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Animated3Sum {

    // Variables for array and pointers
    static Integer[] array;
    static int i = 0, j = 1, k = 2;
    static boolean found = false;

    public static void main(String[] args) {
        // Sample sorted array (You can change this)
        array = new Integer[]{1,2,9,17,18,19,20};

        // Create and set up the window
        JFrame frame = new JFrame("3-Sum Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        // Timer to update the animation
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!found && i < array.length - 2) {
                    // Perform the two-pointer search
                    while (j < k) {
                        if (array[i] + array[j] == array[k]) {
                            found = true; // Triplet found
                            break;
                        } else if (array[i] + array[j] < array[k]) {
                            j++;
                        } else {
                            k--;
                        }
                    }
                    // Move `i` forward after completing the inner loop
                    if (j >= k) {
                        i++;
                        j = i + 1;
                        k = j + 1;
                    }
                }
                // Redraw the array and pointers
                frame.repaint();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 500); // Update every 500ms

        // Override paint method for drawing
        frame.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Draw the array as bars
                int width = 50;
                int spacing = 10;
                for (int index = 0; index < array.length; index++) {
                    if (index == i) {
                        g.setColor(Color.RED); // i pointer
                    } else if (index == j) {
                        g.setColor(Color.GREEN); // j pointer
                    } else if (index == k) {
                        g.setColor(Color.BLUE); // k pointer
                    } else {
                        g.setColor(Color.GRAY); // Normal elements
                    }
                    g.fillRect(index * (width + spacing), getHeight() - 100 - array[index] * 20, width, array[index] * 20);
                    g.setColor(Color.BLACK);
                    g.drawString(array[index].toString(), index * (width + spacing) + 5, getHeight() - 110);
                }

                // Show if a triplet is found
                if (found) {
                    g.setColor(Color.BLACK);
                    g.drawString("Triplet Found!", getWidth() / 2 - 50, 50);
                }
            }
        });
    }
}
