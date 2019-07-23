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
	 * ���� ����˼�����   �˿ڵ�  channel
	 * @throws Exception
	 */
	public void init() throws Exception{ 
		this.selector = Selector.open(); //����ѡ����
		
		//����ServerSocketChannel
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false); //���÷�����
		ServerSocket serverSocket = channel.socket();
		InetSocketAddress address = new InetSocketAddress(8080); //�󶨶˿�
		serverSocket.bind(address);
		channel.register(this.selector, SelectionKey.OP_ACCEPT);
		
		// sun.nio.ch.ServerSocketChannelImpl[/0:0:0:0:0:0:0:0:8080]
		System.out.println("����˼���ͨ������ͨ����"  + channel);
		
	}
	
	public void start() throws Exception{
		
		while(true){
			
			this.selector.select(); //������ֱ��������һ����ע����¼�����
			Iterator<SelectionKey> ite = this.selector.selectedKeys()
					.iterator();  //��ȡ�����¼���SelectionKey���󼯺�
			
			while ( ite.hasNext()  ){
				SelectionKey key = ite.next();
				ite.remove(); //�Ӽ������ƶ����������
				if (key.isAcceptable() ){ //�ͻ������������¼�
					accept(key);
				}else if (key.isReadable()) {//��ȡ�¼�
					read(key);
				}
			}
		}
	}
	
	private void accept(SelectionKey key) throws IOException{
		
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		SocketChannel channel = server.accept(); //��������
		channel.configureBlocking(false); //����Ϊ������
		channel.register(this.selector, SelectionKey.OP_READ);// Ϊͨ��ע���ȡ�¼�
		
		//java.nio.channels.SocketChannel[connected local=/127.0.0.1:8080 remote=/127.0.0.1:2994]
		System.out.println("�������Ӻ����ɵ�ͨ���� "+ channel.hashCode());
		
		
	}
	
	private void read(SelectionKey key) throws IOException{
		
		SocketChannel channel = (SocketChannel) key.channel();
		System.out.println( "��ȡ����ʱ���ɵ�ͨ���� "+ channel.hashCode() );
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		
		channel.read(buffer); //��ȡ����
		String request = new String(buffer.array()).trim();
		System.out.println("�ͻ�������" + request );
		
		buffer.clear();
		
		//�����ͻ��˵� read�¼�
		ByteBuffer outBuffer = ByteBuffer.wrap("�����յ�".getBytes());
		
		
		channel.write(outBuffer);
		
		outBuffer.clear();
		
	}
	
	

	public static void main(String[] args) throws Exception {

		System.out.println("����������������");
		NioServer server = new NioServer();
		server.init();
		server.start();
	}

}
