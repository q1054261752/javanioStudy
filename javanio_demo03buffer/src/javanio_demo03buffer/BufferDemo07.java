package javanio_demo03buffer;

import java.nio.ByteBuffer;
/**
 * 
 * @author zhangkairong
 *
 * @date 2019年10月10日 下午3:36:34
 *
 * @url https://www.cnblogs.com/aidata/p/11523627.html
 *
 */
public class BufferDemo07 {
	//继续写入3字节，此时读模式下(不调用compact()方法)，limit=3，position=2.继续写入只能覆盖写入一条数据
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
//						byteBuffer.put((byte) 4);
//						byteBuffer.put((byte) 5);   //如果写入的数据大于buffer的大小，将报错
		// Exception in thread "main" java.nio.BufferOverflowException
		// 再次查看三个指标
		System.out.println("写入3字节后: capacity容量：" + byteBuffer.capacity());
		System.out.println("position位置:" + byteBuffer.position());
		System.out.println("limit限制：" + byteBuffer.limit());
		
		System.out.println("---------------#######开始读取-------------------------");
		
		//调用 flip方法，读取buffer里的数据
		byteBuffer.flip();
		
		System.out.println("调用 flip方法: capacity容量：" + byteBuffer.capacity());
		System.out.println("position位置:" + byteBuffer.position());
		System.out.println("limit限制：" + byteBuffer.limit());  // limit 的大小等于 之前 write的数据大小

		byte a = byteBuffer.get();
        System.out.println(a);
        byte b = byteBuffer.get();
        System.out.println(b);
		
		System.out.println("byteBuffer.get()2: capacity容量：" + byteBuffer.capacity());
		System.out.println("position位置:" + byteBuffer.position());
		System.out.println("limit限制：" + byteBuffer.limit());  // limit 的大小等于 之前 write的数据大小

		System.out.println("---------------#######读取 模式进行写-------------------------");
		// 继续写入3字节，此时读模式下(不调用compact()方法)，limit=3，position=2.继续写入只能覆盖写入一条数据
		byteBuffer.put((byte) 1);
		//byteBuffer.put((byte) 2);   //再写将报错
//		System.out.println(byteBuffer.toString());
		System.out.println("读模式下写数据: capacity容量：" + byteBuffer.capacity());
		System.out.println("position位置:" + byteBuffer.position());
		System.out.println("limit限制：" + byteBuffer.limit());
	}
	
}
