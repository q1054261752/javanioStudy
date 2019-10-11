package javanio_demo03buffer;

import java.nio.ByteBuffer;

public class BufferDemo04 {

	//������ flip��������ȡbuffer�������
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
//				byteBuffer.put((byte) 4);
//				byteBuffer.put((byte) 5);   //���д������ݴ���buffer�Ĵ�С��������
		// Exception in thread "main" java.nio.BufferOverflowException
		// �ٴβ鿴����ָ��
		System.out.println("д��3�ֽں�: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());
		
		System.out.println("---------------#######��ʼ��ȡ-------------------------");
		
		//������ flip��������ȡbuffer�������
		System.out.println(byteBuffer.get());// д��֮�󣬲�����flipֱ�Ӷ�ȡ��
		                                     // ָ�뽫��д����positionλ�ü�����ȡ����ȡ��Ϊ��ʼֵ 0����ʱ  position=limit
		
//		      System.out.println(byteBuffer.get()); // �ڶ��Ļ���ָ�뽫Խ�磬������buffer�Ĵ�С4
		//  System.out.println(byteBuffer.get());
		
		System.out.println("������ flip��������ȡ: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());
	}
}
