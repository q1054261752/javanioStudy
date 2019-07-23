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
		
		this.selector = Selector.open();//����ѡ����
		SocketChannel channel = SocketChannel.open(); //����SocketChannel
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress("127.0.0.1", 8080)); //���ӷ�����
        channel.register(selector, SelectionKey.OP_CONNECT);  //ע��connect�¼�
        
        System.out.println("�ͻ��˼���ͨ������ͨ����"  + channel);
		
	}
	
	public void start() throws Exception{
		
		while(true){
			selector.select(); //�˷�����������ֱ��������һ����ע����¼�����
			Iterator<SelectionKey> ite = this.selector.selectedKeys()
					.iterator();  // ��ȡ�����¼���SelectionKey���󼯺�
			
			while (ite.hasNext()){
				
				SelectionKey key = ite.next();
				ite.remove(); //�Ӽ������Ƴ����������SelectKey�������ظ�����
				if ( key.isConnectable() ){ //�����¼�
					connect(key);
				}else if (key.isReadable()) { //���¼�
					read(key);
				}
			}
		}
	}
	
	
	public void connect(SelectionKey key) throws Exception{
		
		SocketChannel channel = (SocketChannel) key.channel();
		if(channel.isConnectionPending() ){ //�������
			
			if ( channel.finishConnect() ) { //�������
				
				channel.configureBlocking(false); //���óɷ�����
				channel.register(this.selector, SelectionKey.OP_READ);//ע���ȡ�¼�
				String request = clientInput.readLine(); //����ͻ�������
				System.out.println("connect  --> readline");
				
				ByteBuffer outBuffer = ByteBuffer.wrap(request.getBytes());
				
				channel.write(outBuffer); //���͵���������
				outBuffer.clear();
				System.out.println("�ͻ��˽������Ӻ����ɵ�ͨ���� "+ channel);

			}
			else{
				
				key.cancel();
			}
			
		}
		
	}
	
	private void read(SelectionKey key) throws IOException{
		
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024); //������ȡ�Ļ�����
		
		
		channel.read(buffer);
		String respose = new String(buffer.array()).trim();
		
		buffer.clear();
		
		System.out.println("�������Ӧ��" + respose);
		String nextRequest = clientInput.readLine();
		System.out.println("read  --> readline()");
		ByteBuffer outBuffer = ByteBuffer.wrap(nextRequest.getBytes());
		
		
		channel.write(outBuffer);   // �������͵������
		
		outBuffer.clear();
		
		
		System.out.println("�ͻ��˷�������ʱ���ɵ�ͨ���� "+ channel);
		
	}
	
	public static void main(String[] args) throws Exception{
		
		System.out.println("�ͻ�������������");
		NioClient client = new NioClient();
		client.init();
		client.start();
		
		
		
	}
	
	
}
