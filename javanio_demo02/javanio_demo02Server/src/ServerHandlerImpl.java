import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ServerHandlerImpl implements ServerHandlerBs {
	
	private int bufferSize = 1024;
	private String localCharset = "UTF-8";
	
	
	public ServerHandlerImpl() {
	}

	public ServerHandlerImpl(int bufferSize) {
	      this(bufferSize, null);
	}

	public ServerHandlerImpl(String localCharset) {
	      this(-1, localCharset);
	}
	    
	public ServerHandlerImpl(int bufferSize, String localCharset) {
	      this.bufferSize = bufferSize > 0 ? bufferSize : this.bufferSize;
	      this.localCharset = localCharset == null ? this.localCharset : localCharset;
	}



	@Override
	public void handleAccept(SelectionKey selectionKey) throws IOException {

		 //��ȡchannel
        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        //������
        socketChannel.configureBlocking(false);
        //ע��selector
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));

        System.out.println("��������......");

		
	}

	@Override
	public String handleRead(SelectionKey selectionKey) throws IOException {
		
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();

        String receivedStr = "";

        if (socketChannel.read(buffer) == -1) {
            //û�������ݹر�
            socketChannel.shutdownOutput();
            socketChannel.shutdownInput();
            socketChannel.close();
            System.out.println("���ӶϿ�......");
        } else {
            //��channel��Ϊ��ȡ״̬
            buffer.flip();
            //���ձ����ȡ����
            receivedStr = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
            buffer.clear();

            //�������ݸ��ͻ���
            buffer = buffer.put(("received string : " + receivedStr).getBytes(localCharset));
            //��ȡģʽ
            buffer.flip();
            socketChannel.write(buffer);
            //ע��selector ������ȡ����
            socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
        }
        return receivedStr;

	}

}
