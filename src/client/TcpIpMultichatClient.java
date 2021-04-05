/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TcpIpMultichatClient {
	public static void main(String[] args) {
		String nick = "연구원1";
		try {
			String serverIp = "www.ist-seok17.kro.kr";
			Socket socket = new Socket(serverIp, 7777);
			System.out.println("서버에 연결되었습니다.");
			
			Thread sender = new Thread(new ClientSender(socket, nick));
			Thread recevier = new Thread(new ClientReceiver(socket));
			
			sender.start();
			recevier.start();
		}catch (ConnectException ce) {
			ce.printStackTrace();
		}catch(Exception e) {}
		}
	
	static class ClientSender extends Thread{
		Socket socket;
		DataOutputStream out;
		String name;
		
		public ClientSender(Socket socket, String name) {
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				this.name = name;
			}catch (Exception e) {}
		}
		public void run() {
			Scanner sc = new Scanner(System.in);
			try {
				if(out!=null) {
					out.writeUTF(name);
				}
				while(out!=null){
					out.writeUTF("["+name+"] "+sc.nextLine());
				}
			}catch (IOException e) {}
		}
	}
	static class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream in;
		
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			}catch (IOException e) {}
		}
		public void run(){
			while(in!=null) {
			try {
				System.out.println(in.readUTF());
			}catch (IOException e) {}
			}
		}
	}
}
	

