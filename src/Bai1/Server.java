package Bai1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running...");

            // Chấp nhận kết nối từ client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Tạo luồng đầu vào từ client
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Tạo luồng đầu ra tới client
            PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

            // Luồng để nhập dữ liệu từ bàn phím
            BufferedReader inputFromKeyboard = new BufferedReader(new InputStreamReader(System.in));

            // Luồng để ghi ra console của server
            PrintWriter outputToConsole = new PrintWriter(System.out, true);

            String clientMessage;
            String serverMessage;

            while (true) {
                // Nhận dữ liệu từ client
                clientMessage = inputFromClient.readLine();
                if (clientMessage.equals("bye")) {
                    break;
                }
                outputToConsole.println("Client: " + clientMessage);

                // Nhập dữ liệu từ bàn phím của server và gửi tới client
                outputToConsole.print("Server: ");
                serverMessage = inputFromKeyboard.readLine();
                outputToClient.println(serverMessage);
            }

            // Đóng kết nối
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
