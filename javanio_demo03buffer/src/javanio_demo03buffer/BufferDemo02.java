package javanio_demo03buffer;

import java.nio.ByteBuffer;

public class BufferDemo02 {

	// д�����ݺ���Ҫ�صı仯
	public static void main(String[] args) {
		// ����һ��byte�ֽڻ�����,������4
		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
		// Ĭ��д��ģʽ, �鿴������Ҫ��ָ��
		System.out.println("--------Ĭ��ģʽ��---------");
		System.out.println("��ʼ��: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());
		System.out.println();
		
		System.out.println("--------д��3�ֽڵ�����---------");
		//д��3�ֽڵ�����
		byteBuffer.put((byte) 1);
		byteBuffer.put((byte) 2);
		byteBuffer.put((byte) 3);

		
		// �ٴβ鿴����ָ��
		System.out.println("д��3�ֽں�: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());
	}
}
