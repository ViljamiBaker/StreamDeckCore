package de.rcblum.stream.deck.items;

import java.io.IOException;

import de.rcblum.stream.deck.StreamDeck;
import de.rcblum.stream.deck.event.KeyEvent;
import de.rcblum.stream.deck.util.IconPackage;

/**
 * This  handle can be registered with the {@link StreamDeck} and will execute
 * the given executable when the stream deck button is pressed on release.
 * 
 * <br><br> 
 * 
 * MIT License
 * 
 * Copyright (c) 2017 Roland von Werden
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * @author Roland von Werden
 * @version 0.1
 *
 */
public class ExecutableItem extends AbstractStreamItem {

	private String pathToExecutable = null;

	public ExecutableItem(byte[] img, String pathToExecutable) {
		super(img);
		this.img = img;
		this.pathToExecutable = pathToExecutable;
	}

	public ExecutableItem(IconPackage iconPackage, String pathToExecutable) {
		super(iconPackage);
		this.pathToExecutable = pathToExecutable;
	}

	public ExecutableItem(byte[] img, String pathToExecutable, String text) {
		super(img, null, text);
		this.pathToExecutable = pathToExecutable;
	}

	@Override
	public byte[] getIcon() {
		// TODO Auto-generated method stub
		return this.img;
	}
	
	public void onKeyEvent(KeyEvent event) {
		switch(event.getType()) {
		case OFF_DISPLAY :
			this.offDisplay(event);
			break;
		case ON_DISPLAY:
			this.onDisplay(event);
			break;
		case PRESSED:
			this.onPress(event);
			break;
		case RELEASED_CLICKED:
			this.onRelease(event);
			this.onClick(event);
			break;
		}
	}

	public void onClick(KeyEvent event) {
		System.out.println(event.getKeyId() +": Click");
	}

	public void onPress(KeyEvent event) {
		System.out.println(event.getKeyId() +": Press");
	}

	public void onRelease(KeyEvent event) {
		System.out.println(event.getKeyId() +": Release");
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(this.pathToExecutable);
		} catch (IOException e) {
			System.out.println(event.getKeyId() +": Could nod execute " + this.pathToExecutable);
			e.printStackTrace();
		}
	}

	public void onDisplay(KeyEvent event) {
	}

	public void offDisplay(KeyEvent event) {
	}
}
