package io.oz.g4.godot_2d.map;


import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;

public abstract class TilemapLoader<P extends AssetLoaderParameters<Tilemap>> extends AsynchronousAssetLoader<Tilemap, P> {

	public static class Parameters extends AssetLoaderParameters<Tilemap> {

	}

	public TilemapLoader(FileHandleResolver resolver) {
		super(resolver);
	}
}
