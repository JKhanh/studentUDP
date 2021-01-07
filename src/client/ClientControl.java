package client;

import model.Message;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientControl {
    private int serverPort = 8080;
    private String serverHost = "localhost";
    private int clientPort = 9090;
    private DatagramSocket client;

    void openConnect(){
        try{
            client = new DatagramSocket(clientPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    void closeConnect(){
        client.close();
    }

    void sendData(Message message){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(message);
            oos.flush();

            InetAddress inetAddress = InetAddress.getByName(serverHost);
            byte[] sendData = baos.toByteArray();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, inetAddress, serverPort);
            client.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Message receiveData(){
        Message message = new Message();
        try{
            byte[] dataReceive = new byte[1024];
            DatagramPacket packet = new DatagramPacket(dataReceive, dataReceive.length);
            client.receive(packet);

            ByteArrayInputStream bais = new ByteArrayInputStream(dataReceive);
            ObjectInputStream ois = new ObjectInputStream(bais);
            message = (Message) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }
}
