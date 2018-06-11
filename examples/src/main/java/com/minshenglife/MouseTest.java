package com.minshenglife;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * @author meixinbin
 */
public class MouseTest {
	public static void main(String[] args) throws AWTException {
		try {
			Robot robot = new Robot();
			robot.setAutoDelay(100);
			robot.mouseMove(500,500);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseMove(100,10);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseMove(100,30);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
