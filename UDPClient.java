import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            FileInputStream fileInputStream = new FileInputStream("filetosend.txt");
            byte[] sendData = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(sendData)) != -1) {
                DatagramPacket sendPacket = new DatagramPacket(sendData, bytesRead, serverAddress, 9876);
                socket.send(sendPacket);
            }

            // Send "END" to tell server the file is done
            byte[] endData = "END".getBytes();
            DatagramPacket endPacket = new DatagramPacket(endData, endData.length, serverAddress, 9876);
            socket.send(endPacket);

            fileInputStream.close();
            socket.close();

            System.out.println("File sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
