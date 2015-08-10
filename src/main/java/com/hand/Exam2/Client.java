package com.hand.Exam2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.text.AbstractDocument.BranchElement;
import javax.swing.text.AbstractDocument.LeafElement;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 12345);
			BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        socket.getInputStream(),"UTF-8"));
			System.out.println("客户端开始接受文件……");
			String line;
			StringBuilder sb = new StringBuilder();
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
			
			FileOutputStream fos = new FileOutputStream(new File("client.pdf"));
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter writer = new BufferedWriter(osw);
			writer.write(sb.toString());
			System.out.println("客户端接收文件完毕。");
			writer.close();
			reader.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("服务器已经断开。");
		}
		
	}

}
