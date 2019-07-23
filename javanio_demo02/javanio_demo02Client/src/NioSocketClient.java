import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioSocketClient {

	  public void start() {
	        try (SocketChannel socketChannel = SocketChannel.open()) {
	            //连接服务端socket
	            SocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
	            socketChannel.connect(socketAddress);

	            int sendCount = 0;

	            ByteBuffer buffer = ByteBuffer.allocate(1024);   //下面都共用一个buffer

	            //这里最好使用selector处理   这里只是为了写的简单
	            while (sendCount < 10) {
	                buffer.clear();   //写如buffer数据之前，先clean
	                //向服务端发送消息
	                buffer.put(("current time : " + System.currentTimeMillis()).getBytes());
	                //读取模式
	                buffer.flip();    //  读buffer里的数据之前，回溯 position读写指针
	                socketChannel.write(buffer);
	                buffer.clear();   //清空buffer

	                //从服务端读取消息
	                int readLenth = socketChannel.read(buffer);   //阻塞，等待服务端返回信息      往buffer里添加数据
	                //读取模式
	                buffer.flip();   // 回溯指针，准备读取数据
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
