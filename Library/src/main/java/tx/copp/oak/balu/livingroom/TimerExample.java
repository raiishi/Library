package tx.copp.oak.balu.livingroom;

public class TimerExample {
	    public static void main(String[] args) {
	        int durationSeconds = 30;

	        for (int i = 0; i <= durationSeconds; i++) {
	            int minutes = i / 60;
	            int seconds = i % 60;

	            // Clear the line using ANSI escape code
	            System.out.print("\033[2K");     // clear entire line
	            System.out.print("\r");          // return carriage
	            System.out.printf("Elapsed time: %02d:%02d", minutes, seconds);
	            
	            try {
	                Thread.sleep(1000); // wait 1 second
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        System.out.println("\nFinished!");
	    }
	}
