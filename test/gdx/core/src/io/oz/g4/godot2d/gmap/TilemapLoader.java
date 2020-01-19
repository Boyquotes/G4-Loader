package io.oz.g4.godot2d.gmap;


import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;

public abstract class TilemapLoader<P extends AssetLoaderParameters<Tilemap>> extends AsynchronousAssetLoader<Tilemap, P> {

	public TilemapLoader(FileHandleResolver resolver) {
		super(resolver);
	}
}
