package javanio_demo03buffer;

import java.nio.ByteBuffer;
/**
 * 
 * @author zhangkairong
 *
 * @date 2019��10��10�� ����3:36:34
 *
 * @url https://www.cnblogs.com/aidata/p/11523627.html
 *
 */
public class BufferDemo07 {
	//����д��3�ֽڣ���ʱ��ģʽ��(������compact()����)��limit=3��position=2.����д��ֻ�ܸ���д��һ������
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
        byte b = byteBuffer.get();
        System.out.println(b);
		
		System.out.println("byteBuffer.get()2: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());  // limit �Ĵ�С���� ֮ǰ write�����ݴ�С

		System.out.println("---------------#######��ȡ ģʽ����д-------------------------");
		// ����д��3�ֽڣ���ʱ��ģʽ��(������compact()����)��limit=3��position=2.����д��ֻ�ܸ���д��һ������
		byteBuffer.put((byte) 1);
		//byteBuffer.put((byte) 2);   //��д������
//		System.out.println(byteBuffer.toString());
		System.out.println("��ģʽ��д����: capacity������" + byteBuffer.capacity());
		System.out.println("positionλ��:" + byteBuffer.position());
		System.out.println("limit���ƣ�" + byteBuffer.limit());
	}
	
}
