package io.oz.g4.godot2d.gmap;

import java.util.Iterator;
import java.util.function.Consumer;

public class TileSet implements Iterable {
	private String setname;

	public String getName() {
		return setname;
	}

	@Override
	public Iterator iterator() {
		return null;
	}

	@Override
	public void forEach(Consumer consumer) {

	}

	public int size() {
		return 0;
	}
}
