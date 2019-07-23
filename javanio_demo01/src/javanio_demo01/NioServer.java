package javanio_demo01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
	
	
	private Selector selector;
	
	/**
	 * 创建 服务端监听的   端口的  channel
	 * @throws Exception
	 */
	public void init() throws Exception{ 
		this.selector = Selector.open(); //创建选择器
		
		//创建ServerSocketChannel
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false); //设置非阻塞
		ServerSocket serverSocket = channel.socket();
		InetSocketAddress address = new InetSocketAddress(8080); //绑定端口
		serverSocket.bind(address);
		channel.register(this.selector, SelectionKey.OP_ACCEPT);
		
		// sun.nio.ch.ServerSocketChannelImpl[/0:0:0:0:0:0:0:0:8080]
		System.out.println("服务端监听通道链接通道："  + channel);
		
	}
	
	public void start() throws Exception{
		
		while(true){
			
			this.selector.select(); //阻塞，直到至少有一个已注册的事件发生
			Iterator<SelectionKey> ite = this.selector.selectedKeys()
					.iterator();  //获取发生事件的SelectionKey对象集合
			
			while ( ite.hasNext()  ){
				SelectionKey key = ite.next();
				ite.remove(); //从集合中移动即将处理的
				if (key.isAcceptable() ){ //客户端请求链接事件
					accept(key);
				}else if (key.isReadable()) {//读取事件
					read(key);
				}
			}
		}
	}
	
	private void accept(SelectionKey key) throws IOException{
		
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		SocketChannel channel = server.accept(); //接受链接
		channel.configureBlocking(false); //设置为非阻塞
		channel.register(this.selector, SelectionKey.OP_READ);// 为通道注册读取事件
		
		//java.nio.channels.SocketChannel[connected local=/127.0.0.1:8080 remote=/127.0.0.1:2994]
		System.out.println("接受链接后生成的通道： "+ channel.hashCode());
		
		
	}
	
	private void read(SelectionKey key) throws IOException{
		
		SocketChannel channel = (SocketChannel) key.channel();
		System.out.println( "读取数据时生成的通道： "+ channel.hashCode() );
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		
		channel.read(buffer); //读取数据
		String request = new String(buffer.array()).trim();
		System.out.println("客户端请求：" + request );
		
		buffer.clear();
		
		//触发客户端的 read事件
		ByteBuffer outBuffer = ByteBuffer.wrap("请求收到".getBytes());
		
		
		channel.write(outBuffer);
		
		outBuffer.clear();
		
	}
	
	

	public static void main(String[] args) throws Exception {

		System.out.println("服务器启动。。。");
		NioServer server = new NioServer();
		server.init();
		server.start();
	}

}
