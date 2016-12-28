package radom;


import java.util.concurrent.ConcurrentHashMap;

import com.thankjava.toolkit.radom.Sequence;
import com.thankjava.toolkit.thread.ThreadPool;
import com.thankjava.toolkit.thread.ThreadUtil;

public class SequenceTest {

	static ConcurrentHashMap<String, String> generateConcurrentTestKV = new ConcurrentHashMap<String, String>();
	static ConcurrentHashMap<String, String> uuidConcurrentTestKV = new ConcurrentHashMap<String, String>();
	
	public static void main(String[] args) {

		ThreadUtil.runWhenJVMExit(new Thread(new Runnable() {
			public void run() {
				System.out.println("uuidConcurrentTestKV size: " + uuidConcurrentTestKV.size());
				System.out.println("generateConcurrentTestKV size: " + generateConcurrentTestKV.size());
			}
		}));
		
		System.out.println(Sequence.uuid()[0]);
		System.out.println(Sequence.generateUnique());
		System.out.println(Sequence.generateChar(10));
		System.out.println(Sequence.generateNum(10));
		
		//----并发测试
		uuidConcurrentTest();
		generateConcurrentTest();
		
		
	}

	static void uuidConcurrentTest(){
		for (int i = 0; i < 1010; i++) {
			new ThreadPool().execute(new Runnable() {
				public void run() {
					String seq = Sequence.uuid()[0];
					System.out.println(seq);
					uuidConcurrentTestKV.put(seq, "value");
				}
			});
		}
	}
	
	static void generateConcurrentTest(){
		for (int i = 0; i < 1010; i++) {
			new ThreadPool().execute(new Runnable() {
				public void run() {
					String seq = Sequence.generateUnique().toString();
					System.out.println(seq);
					generateConcurrentTestKV.put(seq, "value");
				}
			});
		}
	}
}
