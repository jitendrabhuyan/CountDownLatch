import java.util.concurrent.CountDownLatch;

/**
 * if you change the sStartingLatch count number larger than 10
 * this program will never run till Thread* compete execution
 * or if you change the sEndingLatch counter number larger than 3
 * this program will never come to the end line,hence you every 
 * count number should be equivalent with the number that will 
 * count down 
 */
public class CountDownLatchTest {
	
	
	public class Thread1 extends Thread {
		
		public CountDownLatch mStartingLatch;
		public CountDownLatch mEndingLatch;
		
		public Thread1(CountDownLatch pStartingLatch,CountDownLatch pEndingLatch){
			this.mStartingLatch = pStartingLatch;
			this.mEndingLatch = pEndingLatch;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Thread1 started but waiting on the starting latch");
			
			try {
				mStartingLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Thread1 completed execution");
			
			mEndingLatch.countDown();
		}
	}
	
	
	public class Thread2 extends Thread {
		
		public CountDownLatch mStartingLatch;
		public CountDownLatch mEndingLatch;
		
		public Thread2(CountDownLatch pStartingLatch,CountDownLatch pEndingLatch){
			this.mStartingLatch = pStartingLatch;
			this.mEndingLatch = pEndingLatch;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Thread2 started but waiting on the starting latch");
			
			try {
				mStartingLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Thread2 completed execution");
			
			mEndingLatch.countDown();
		}
	}

	public class Thread3 extends Thread {
		
		public CountDownLatch mStartingLatch;
		public CountDownLatch mEndingLatch;
		
		public Thread3(CountDownLatch pStartingLatch,CountDownLatch pEndingLatch){
			this.mStartingLatch = pStartingLatch;
			this.mEndingLatch = pEndingLatch;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Thread3 started but waiting on the starting latch");
			
			try {
				mStartingLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Thread3 completed execution");
			
			mEndingLatch.countDown();
		}
	}
	
	public static CountDownLatch sStartingLatch = new CountDownLatch(10);
	public static CountDownLatch sEndingLatch = new CountDownLatch(3);
	
	
	
	
	
	public static void main(String[] args) {
		
		Thread1 t1 = new CountDownLatchTest().new Thread1(sStartingLatch,sEndingLatch);
		
		Thread2 t2 = new CountDownLatchTest().new Thread2(sStartingLatch,sEndingLatch);
		
		Thread t3 = new CountDownLatchTest().new Thread3(sStartingLatch,sEndingLatch);
		
		t1.start();
		t2.start();
		t3.start();
		
		System.out.println("make the starting latch ready");
		
		for(int i = 0;i<10;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sStartingLatch.countDown();
		}
		
		
		try {
			sEndingLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("all thread completed execution");
		
		
	}

}
