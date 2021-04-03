import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static ServerSocket server;
//    private static Socket socket;
    private static final int PORT = 8189;

    public static void main(String[] args) throws IOException {


        try {
            Socket clientSocket = null;
            ServerSocket serverSocket = new ServerSocket(8189);
            Scanner scanner = new Scanner(System.in);
//            server = new ServerSocket(PORT);
            System.out.println("Server started!");
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            clientSocket = serverSocket.accept();
            System.out.println("Клиент подключен "+ clientSocket.getRemoteSocketAddress());

//            socket = server.accept();
//            System.out.println("Client connected!");
            Thread threadVision = new Thread();
            {
                try {
                    while (true) {
                        try {
                            outputStream.writeUTF(scanner.nextLine());
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    threadVision.start();

                    while (true) {

                        String str = inputStream.readUTF();
                        if (str.equals("/end")) {
                            System.out.println("Клиент вышел с сервера");
                            outputStream.writeUTF("/end");
                            break;
                        } else {
                            System.out.println(" " + str);
                        }

                    }

//
//                        Scanner in = new Scanner(socket.getInputStream());
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


//            while (true) {
//                String str = in.nextLine();
//
//                if (str.equals("/end")) {
//                    System.out.println("Client disconnected!");
//                    break;
//                }
//
//
//                System.out.println("Client " + str);
//                out.println("Echo: " + str);
//
//            }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        server.close();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}



