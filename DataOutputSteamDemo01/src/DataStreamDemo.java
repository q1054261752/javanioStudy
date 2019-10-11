import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamDemo {

	public static void main(String[] args) throws IOException {
		
		
		readData();
		
		//writeData();
		
	}
	
	public static void readData() throws IOException{
		FileInputStream fis = new FileInputStream("tempfile/data.txt");
		
		//读取一个整数,需要额外功能
		DataInputStream dis = new DataInputStream(fis);
		
		int num = dis.readInt();
		System.out.println("num="+num);
		dis.close();
		
	}
	
	public static void writeData() throws IOException{
		
		FileOutputStream fos = new FileOutputStream("tempfile/data.txt");
		
		//读取一个整数，需要额外功能
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeInt(97);
		
		dos.close();
		
	}

}
