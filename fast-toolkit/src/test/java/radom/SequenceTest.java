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
		
		System.out.println(Sequence.generateUnique());
		System.out.println(Sequence.generateChar(10));
		System.out.println(Sequence.generateNum(10));
		

	}
}
