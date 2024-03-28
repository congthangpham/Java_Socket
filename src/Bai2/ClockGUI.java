package Bai2;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClockGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(timeLabel, BorderLayout.CENTER);

        frame.setVisible(true);

        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                outputToServer.println("time");

                String currentTime = inputFromServer.readLine();
                timeLabel.setText(currentTime);

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
