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
		
		//��ȡһ������,��Ҫ���⹦��
		DataInputStream dis = new DataInputStream(fis);
		
		int num = dis.readInt();
		System.out.println("num="+num);
		dis.close();
		
	}
	
	public static void writeData() throws IOException{
		
		FileOutputStream fos = new FileOutputStream("tempfile/data.txt");
		
		//��ȡһ����������Ҫ���⹦��
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeInt(97);
		
		dos.close();
		
	}

}
