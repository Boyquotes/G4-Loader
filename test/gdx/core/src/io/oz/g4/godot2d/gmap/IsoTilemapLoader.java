package io.oz.g4.godot2d.gmap;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class IsoTilemapLoader<P extends AssetLoaderParameters<Tilemap>> extends AsynchronousAssetLoader<Tilemap, P> {

	public IsoTilemapLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	public IsoTilemapLoader() {
		super(new FileHandleResolver() {
			@Override
			public FileHandle resolve(String fileName) {
				return null;
			}
		});
	}

	/**
	 * Returns the assets this asset requires to be loaded first. This method may be called on a thread other than the GL thread.
	 *
	 * @param fileName  name of the asset to load
	 * @param file      the resolved file to load
	 * @param parameter parameters for loading the asset
	 * @return other assets that the asset depends on and need to be loaded first or null if there are no dependencies.
	 */
	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, P parameter) {
		return null;
	}

	/**
	 * Loads the non-OpenGL part of the asset and injects any dependencies of the asset into the AssetManager.
	 *
	 * @param manager
	 * @param fileName  the name of the asset to load
	 * @param file      the resolved file to load
	 * @param parameter the parameters to use for loading the asset
	 */
	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, P parameter) {

	}

	/**
	 * Loads the OpenGL part of the asset.
	 *
	 * @param manager
	 * @param fileName
	 * @param file      the resolved file to load
	 * @param parameter
	 */
	@Override
	public IsoTilemap loadSync(AssetManager manager, String fileName, FileHandle file, P parameter) {
		return null;
	}

	public Tilemap load(String f) {
		return null;
	}
}
