import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class NioSocketServer {

	 private volatile byte flag = 1;

     public void setFlag(byte flag) {
        this.flag = flag;
     }

	 public void start() {
	        //����serverSocketChannel������8888�˿�
	        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
	            serverSocketChannel.socket().bind(new InetSocketAddress(8888));
	            //����Ϊ������ģʽ
	            serverSocketChannel.configureBlocking(false);
	            //ΪserverChannelע��selector
	            Selector selector = Selector.open();
	            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

	            System.out.println("����˿�ʼ������");

	            //������Ϣ������
	            ServerHandlerBs handler = new ServerHandlerImpl(1024);

	            while (flag == 1) {
	                selector.select();
	                System.out.println("��ʼ�������� �� ");
	                //��ȡselectionKeys������
	                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
	                while (keyIterator.hasNext()) {
	                    SelectionKey key = keyIterator.next();
	                    try {
	                        //��������
	                        if (key.isAcceptable()) {
	                            handler.handleAccept(key);
	                        }
	                        //������
	                        if (key.isReadable()) {
	                            System.out.println(handler.handleRead(key));
	                        }
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                    //��������Ƴ���ǰʹ�õ�key
	                    keyIterator.remove();
	                }
	                System.out.println("���������");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	
}
