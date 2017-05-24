package mouseclicker;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class MouseClicker {
	private boolean performClicks;
	private long clickDelay;
	
	MouseClicker(long delay){
		performClicks = false;
		clickDelay = delay;
	}
	
	public long getClickDelay() {
		return clickDelay;
	}

	public void setClickDelay(long clickDelay) {
		this.clickDelay = clickDelay;
	}

	public void startClicker(){
		performClicks = true;
		Thread t = new Thread(() -> {
			try {
				Robot bot = new Robot();
				Thread.sleep(5000);
				while(performClicks){
					bot.mousePress(InputEvent.BUTTON1_MASK);
					bot.mouseRelease(InputEvent.BUTTON1_MASK);
					Thread.sleep(clickDelay);
				}
			}
			catch(Exception e)
			{
				
			}
		});
		t.start();
	}
	
	public void stopClicker(){
		performClicks = false;
	}
}
