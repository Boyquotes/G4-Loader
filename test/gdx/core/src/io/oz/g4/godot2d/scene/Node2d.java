package io.oz.g4.godot2d.scene;

import com.badlogic.gdx.utils.Disposable;

public interface Node2d extends Disposable {
	/**
	 * Releases all resources of this object.
	 */
	@Override
	default public void dispose() { }
}
