import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioSocketClient {

	  public void start() {
	        try (SocketChannel socketChannel = SocketChannel.open()) {
	            //���ӷ����socket
	            SocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
	            socketChannel.connect(socketAddress);

	            int sendCount = 0;

	            ByteBuffer buffer = ByteBuffer.allocate(1024);   //���涼����һ��buffer

	            //�������ʹ��selector����   ����ֻ��Ϊ��д�ļ�
	            while (sendCount < 10) {
	                buffer.clear();   //д��buffer����֮ǰ����clean
	                //�����˷�����Ϣ
	                buffer.put(("current time : " + System.currentTimeMillis()).getBytes());
	                //��ȡģʽ
	                buffer.flip();    //  ��buffer�������֮ǰ������ position��дָ��
	                socketChannel.write(buffer);
	                buffer.clear();   //���buffer

	                //�ӷ���˶�ȡ��Ϣ
	                int readLenth = socketChannel.read(buffer);   //�������ȴ�����˷�����Ϣ      ��buffer���������
	                //��ȡģʽ
	                buffer.flip();   // ����ָ�룬׼����ȡ����
	                byte[] bytes = new byte[readLenth];
	                buffer.get(bytes);
	                System.out.println(new String(bytes, "UTF-8"));
	                buffer.clear();


	                sendCount++;
	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
}
