package javanio_demo03buffer;

import java.nio.ByteBuffer;

public class BufferDemo02 {

	// 写入数据后，三要素的变化
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
		byteBuffer.put((byte) 3);

		
		// 再次查看三个指标
		System.out.println("写入3字节后: capacity容量：" + byteBuffer.capacity());
		System.out.println("position位置:" + byteBuffer.position());
		System.out.println("limit限制：" + byteBuffer.limit());
	}
}
