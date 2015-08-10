package com.hand.Exam2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class myThread extends Thread{@Override
	public void run() {
		try {
			ServerSocket server = new ServerSocket(12345);
			Socket socket = server.accept();
			System.out.println("有客户端连上服务器，开始传送文件……");
			PrintWriter  writer = new PrintWriter(
	                new OutputStreamWriter(
	                         socket.getOutputStream(),"UTF-8"));	
			
			FileInputStream fis = new FileInputStream(new File("server.pdf"));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);			
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			writer.write(sb.toString());
			writer.flush();
			System.out.println("文件传送完毕。");
			writer.close();
			br.close();
			isr.close();
			fis.close();
			
		} catch (IOException e) {
			System.out.println("客户端断开了连接。");
		}
	}
}
public class Server {
	public static void main(String[] args){
		new myThread().start();
		
		
	}
}
