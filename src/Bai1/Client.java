package Bai1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            // Luồng để nhập dữ liệu từ bàn phím
            BufferedReader inputFromKeyboard = new BufferedReader(new InputStreamReader(System.in));

            // Luồng để ghi ra server
            PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);

            // Luồng để đọc dữ liệu từ server
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String clientMessage;
            String serverMessage;

            while (true) {
                // Nhập dữ liệu từ bàn phím của client và gửi tới server
                System.out.print("Client: ");
                clientMessage = inputFromKeyboard.readLine();
                outputToServer.println(clientMessage);
                if (clientMessage.equals("bye")) {
                    break;
                }

                // Nhận dữ liệu từ server
                serverMessage = inputFromServer.readLine();
                System.out.println("Server: " + serverMessage);
            }

            // Đóng kết nối
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
