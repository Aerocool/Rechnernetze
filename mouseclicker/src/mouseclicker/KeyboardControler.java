package mouseclicker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControler implements KeyListener {
	private Gui gui;
	public KeyboardControler(Gui gui){
		this.gui = gui;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
