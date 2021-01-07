package server;

import model.Message;
import model.Student;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class ServerControl {
    private DatagramSocket datagramSocket;
    private int serverPort = 8080;
    private DatagramPacket datagramPacket = null;
    private StudentDao dao = new StudentDao();

    public ServerControl(){
        openServer(serverPort);
        while (true){
            listening();
        }
    }

    private void openServer(int serverPort) {
        try{
            datagramSocket = new DatagramSocket(serverPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void listening(){
        Message message = receiveData();
        Message response;
        switch (message.getType()){
            case FIND:
                String name = (String) message.getObject();
                ArrayList<Student> result = dao.findStudentByName(name);
                response = new Message(result, Message.MessageType.FIND);
                sendData(response);
                break;

            case UPDATE:
                Student student = (Student) message.getObject();
                response = new Message(dao.updateStudent(student), Message.MessageType.UPDATE);
                sendData(response);
                break;
        }
    }

    private Message receiveData() {
        Message message = new Message();
        try{
            byte[] receiveData = new byte[1024];
            datagramPacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(datagramPacket);

            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            message = (Message) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    private void sendData(Message message){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(message);
            oos.flush();

            InetAddress inetAddress = datagramPacket.getAddress();
            int clientPort = datagramPacket.getPort();

            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, clientPort);
            datagramSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
