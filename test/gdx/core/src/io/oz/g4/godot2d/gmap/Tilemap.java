package io.oz.g4.godot2d.gmap;

import com.badlogic.gdx.maps.Map;

import io.oz.g4.godot2d.scene.Node2d;

public class Tilemap extends Map implements Node2d {
	public GodotMapLayers getLayers() {
		return null;
	}

	public TileSets getTileSets() {
		return null;
	}
}
