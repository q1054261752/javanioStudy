package javanio_demo01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {

	private Selector selector;
	
	private BufferedReader clientInput = new BufferedReader(
			new InputStreamReader(System.in));
	
	public void init() throws Exception{
		
		this.selector = Selector.open();//创建选择器
		SocketChannel channel = SocketChannel.open(); //创建SocketChannel
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress("127.0.0.1", 8080)); //链接服务器
        channel.register(selector, SelectionKey.OP_CONNECT);  //注册connect事件
        
        System.out.println("客户端监听通道链接通道："  + channel);
		
	}
	
	public void start() throws Exception{
		
		while(true){
			selector.select(); //此方法会阻塞，直到至少有一个已注册的事件发生
			Iterator<SelectionKey> ite = this.selector.selectedKeys()
					.iterator();  // 获取发生事件的SelectionKey对象集合
			
			while (ite.hasNext()){
				
				SelectionKey key = ite.next();
				ite.remove(); //从集合中移除即将处理的SelectKey，避免重复处理
				if ( key.isConnectable() ){ //链接事件
					connect(key);
				}else if (key.isReadable()) { //读事件
					read(key);
				}
			}
		}
	}
	
	
	public void connect(SelectionKey key) throws Exception{
		
		SocketChannel channel = (SocketChannel) key.channel();
		if(channel.isConnectionPending() ){ //完成链接
			
			if ( channel.finishConnect() ) { //完成链接
				
				channel.configureBlocking(false); //设置成非阻塞
				channel.register(this.selector, SelectionKey.OP_READ);//注册读取事件
				String request = clientInput.readLine(); //输入客户端请求
				System.out.println("connect  --> readline");
				
				ByteBuffer outBuffer = ByteBuffer.wrap(request.getBytes());
				
				channel.write(outBuffer); //发送到服务器端
				outBuffer.clear();
				System.out.println("客户端建立链接后生成的通道： "+ channel);

			}
			else{
				
				key.cancel();
			}
			
		}
		
	}
	
	private void read(SelectionKey key) throws IOException{
		
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024); //创建读取的缓存区
		
		
		channel.read(buffer);
		String respose = new String(buffer.array()).trim();
		
		buffer.clear();
		
		System.out.println("服务端响应：" + respose);
		String nextRequest = clientInput.readLine();
		System.out.println("read  --> readline()");
		ByteBuffer outBuffer = ByteBuffer.wrap(nextRequest.getBytes());
		
		
		channel.write(outBuffer);   // 将请求发送到服务端
		
		outBuffer.clear();
		
		
		System.out.println("客户端发送数据时生成的通道： "+ channel);
		
	}
	
	public static void main(String[] args) throws Exception{
		
		System.out.println("客户端启动。。。");
		NioClient client = new NioClient();
		client.init();
		client.start();
		
		
		
	}
	
	
}
