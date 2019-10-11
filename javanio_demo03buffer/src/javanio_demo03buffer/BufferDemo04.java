package javanio_demo03buffer;

import java.nio.ByteBuffer;

public class BufferDemo04 {

	//不调用 flip方法，读取buffer里的数据
	public static void main(String[] args) {
		// 构建一个byte字节缓冲区,容量是4
		ByteBuffer byteBuffer = ByteBuffer.allocate(4);
		// 默认写入模式, 查看三个重要的指标
		System.out.println("--------默认模式下---------");
		System.out.println("初始化: capacity容量：" + byteBuffer.capacity());
		System.out.println("position位置:" + byteBuffer.position());
		System.out.println("limit限制：" + byteBuffer.limit());
		System.out.println();
		
		System.out.println("--------写入3字节的数据---------");
		//写入3字节的数据
		byteBuffer.put((byte) 1);
		byteBuffer.put((byte) 2);
		byteBuffer.put((byte) 3);  //buffer  [1,2,3,0]    现在指针在 0  的位置
//				byteBuffer.put((byte) 4);
//				byteBuffer.put((byte) 5);   //如果写入的数据大于buffer的大小，将报错
		// Exception in thread "main" java.nio.BufferOverflowException
		// 再次查看三个指标
		System.out.println("写入3字节后: capacity容量：" + byteBuffer.capacity());
		System.out.println("position位置:" + byteBuffer.position());
		System.out.println("limit限制：" + byteBuffer.limit());
		
		System.out.println("---------------#######开始读取-------------------------");
		
		//不调用 flip方法，读取buffer里的数据
		System.out.println(byteBuffer.get());// 写入之后，不调用flip直接读取，
		                                     // 指针将在写完后的position位置继续读取，读取的为初始值 0，此时  position=limit
		
//		      System.out.println(byteBuffer.get()); // 在读的话，指针将越界，超过了buffer的大小4
		//  System.out.println(byteBuffer.get());
		
		System.out.println("不调用 flip方法，读取: capacity容量：" + byteBuffer.capacity());
		System.out.println("position位置:" + byteBuffer.position());
		System.out.println("limit限制：" + byteBuffer.limit());
	}
}
