import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.IOException;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");

            byte[] receiveData = new byte[1024];
            System.out.println("Server is running... Waiting for data.");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                int dataLength = receivePacket.getLength();

                String receivedText = new String(receivePacket.getData(), 0, dataLength);

                if (receivedText.equals("END")) {
                    System.out.println("File received successfully.");
                    break;
                }

                fileOutputStream.write(receivePacket.getData(), 0, dataLength);
            }

            fileOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
