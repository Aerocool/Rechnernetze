package mouseclicker;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class Listener extends AbstractAction {
	private Gui gui;
	private MouseClicker mouseClicker;
	public Listener(Gui gui){
		this.gui = gui;
		mouseClicker = new MouseClicker(20);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("p") || e.getActionCommand().equals("Stop"))
			mouseClicker.stopClicker();
		else if(e.getActionCommand().equals("Start")){
			try{
				mouseClicker.setClickDelay(gui.getDelayInput());
			} catch(Exception e2)
			{
				mouseClicker.setClickDelay(20);
			}
			mouseClicker.startClicker();
		}
	}

}
