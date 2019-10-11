package javanio_demo03buffer;

import java.nio.ByteBuffer;

public class BufferDemo05 {

	// ���� flip����, ��ȡ����
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
		byteBuffer.put((byte) 3);  //buffer  [1,2,3,0]    ����ָ���� 0  ��λ��
//						byteBuffer.put((byte) 4);
//						byteBuffer.put((byte) 5);   //���д������ݴ���buffer�Ĵ�С��������
		// Exception in thread "main" java.nio.BufferOverflowException
		// �ٴβ鿴����ָ��
		System.out.println("д��3�ֽں�: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());
		
		System.out.println("---------------#######��ʼ��ȡ-------------------------");
		
		//���� flip��������ȡbuffer�������
		byteBuffer.flip();
		
		System.out.println("���� flip����: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());  // limit �Ĵ�С���� ֮ǰ write�����ݴ�С

		byte a = byteBuffer.get();
	    System.out.println(a);
		
	    System.out.println("byteBuffer.get()1: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());  // limit �Ĵ�С���� ֮ǰ write�����ݴ�С

		byte b = byteBuffer.get();
	    System.out.println(b);
		
	    System.out.println("byteBuffer.get()2: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());  // limit �Ĵ�С���� ֮ǰ write�����ݴ�С

		byte c = byteBuffer.get();
	    System.out.println(c);
		
	    System.out.println("byteBuffer.get()3: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());  // limit �Ĵ�С���� ֮ǰ write�����ݴ�С

		byte d = byteBuffer.get();
	    System.out.println(d);
		
	    System.out.println("byteBuffer.get()4: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());  // limit �Ĵ�С���� ֮ǰ write�����ݴ�С

	}

}
