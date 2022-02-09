package com.sinha.learn;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

    public static void main(String[] args) {
	    try{
            DatagramSocket socket = new DatagramSocket(5000);
            while(true){
                byte[] buffer = new byte[50];
                //below packet is a placeholder for receiving the incoming data.
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);//throws IOException
                System.out.println("Packet received is : " + new String(buffer));

                //to send data to client, not generally done in real world apps. Server mostly receives things
                byte[] serverMsg = packet.getData();
                InetAddress inetAddress = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(serverMsg, serverMsg.length,inetAddress,port);
                socket.send(packet);
            }
        }catch (SocketException e){
            System.out.println("Socket exception :" + e.getMessage());
        }catch (IOException e){
            System.out.println("IOException occurred :" + e.getMessage());
        }
    }
}
