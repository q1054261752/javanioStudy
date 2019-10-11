package javanio_demo03buffer;

import java.nio.ByteBuffer;

public class BufferDemo09 {

	//byteBuffer.compact(); // buffer�ڲ�������1������,������д3������ 
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
//										byteBuffer.put((byte) 4);
//										byteBuffer.put((byte) 5);   //���д������ݴ���buffer�Ĵ�С��������
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
        byte b = byteBuffer.get();
        System.out.println(b);
		
		System.out.println("byteBuffer.get()2: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());  // limit �Ĵ�С���� ֮ǰ write�����ݴ�С

		System.out.println("---------------#######����byteBuffer.clear()���ָ�����ʼ̬-------------------------");
		// ����д��3�ֽڣ���ʱ��ģʽ��,ת��Ϊдģʽ(����compact()����)��limit=3��position=2.����д��ֻ�ܸ���д��һ������
		// clear()�������������������compact()������������Ķ������ݡ�תΪд��ģʽ
		byteBuffer.compact(); // buffer�ڲ�������1������,������д3������ 
//						System.out.println(byteBuffer.toString());
		System.out.println("byteBuffer.compact(): capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position()); //δ��ȡ�������ƶ����ˣ���һλ
		System.out.println("limit���ƣ�" + byteBuffer.limit());
		
		
		
	}

}
