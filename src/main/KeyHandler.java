package main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class KeyHandler {

	public boolean up, down, left, right;
	GamePanel panel;

	public KeyHandler(GamePanel gamePanel) {

		this.panel = gamePanel;

		setupKeyBinding("pressed W", "upPressed", true);
		setupKeyBinding("released W", "upReleased", false);
		setupKeyBinding("pressed S", "downPressed", true);
		setupKeyBinding("released S", "downReleased", false);
		setupKeyBinding("pressed A", "leftPressed", true);
		setupKeyBinding("released A", "leftReleased", false);
		setupKeyBinding("pressed D", "rightPressed", true);
		setupKeyBinding("released D", "rightReleased", false);
	}

	private void setupKeyBinding(String keyStroke, String actionName, boolean pressedState) {
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyStroke), actionName);

		panel.getActionMap().put(actionName, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (actionName) {
				case "upPressed":
					up = pressedState;
					break;

				case "downPressed":
					down = pressedState;
					break;

				case "leftPressed":
					left = pressedState;
					break;

				case "rightPressed":
					right = pressedState;
					break;

				case "upReleased":
					up = pressedState;
					break;

				case "downReleased":
					down = pressedState;
					break;

				case "leftReleased":
					left = pressedState;
					break;

				case "rightReleased":
					right = pressedState;
					break;
				}
			}
		});
	}

}
