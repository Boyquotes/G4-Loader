package io.oz.g4.proto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapRenderer;

import java.util.Iterator;

import io.oz.g4.godot2d.gmap.G4MapTileLayer;
import io.oz.g4.godot2d.gmap.IsoTilemapLoader;
import io.oz.g4.godot2d.gmap.IsoTilemapRenderer;
import io.oz.g4.godot2d.gmap.TileSet;
import io.oz.g4.godot2d.gmap.TileSets;
import io.oz.g4.godot2d.gmap.Tilemap;
import io.oz.g4.godot2d.gmap.TilemapTile;

/**Try godot scene loading.
 * asset: g01/s1.tscn
 */
public class Scene01 extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture oz;
	Tilemap tileMap;
	OrthographicCamera camera;
	MapRenderer tileMapRenderer;

	float w, h;
	int ozw, ozh;
	int dx = 0;
	int dy = 0;
	boolean keypress = false;

	@Override
	public void create () {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		oz = new Texture("oz.png");
		ozw = oz.getWidth();
		ozh = oz.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
		camera.update();
		tileMap = new IsoTilemapLoader().load("g01/w32h32.tmx");
		tileMapRenderer = new IsoTilemapRenderer(tileMap);

		MapObjects objs = tileMap.getLayers().get("static bodies").getObjects();
		for (MapObject obj: objs) {
			String s = obj.getName();
			System.out.println(s);
		}

		TileSets tilesets = tileMap.getTileSets();
		for (TileSet tset : tilesets) {
			System.out.println(String.format("ts %s size: %s", tset.getName(), tset.size()));
			Iterator<TilemapTile> it = tset.iterator();
			while(it.hasNext()){
				TilemapTile mptile = it.next();
				TextureRegion rg = mptile.getTextureRegion();
				System.out.println(String.format("%s	  xy: %s, %s; wh: %s, %s",
						mptile.getId(), rg.getRegionX(), rg.getRegionY(), rg.getRegionWidth(), rg.getRegionHeight()));
			}
		}

		G4MapTileLayer tilayer = (G4MapTileLayer) tileMap.getLayers().get("tiles01");
		int tw = tilayer.getWidth();
		int th = tilayer.getHeight();
		System.out.println(String.format("w: %s, h: %s", tw, th));
		for (int r = th - 1; r > 0; r--) {
			for (int c = 0; c < tw; c ++) {
				G4MapTileLayer.Cell cell = tilayer.getCell(c, r);
				TilemapTile t = cell.getTile();
				if (t.getId() == 79)
					System.out.print("   ");
				else if (t.getId() >= 230)
					System.out.print("[O]");
				else
					System.out.print("[ ]");
			}
			System.out.println("|");
		}

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (keypress)
			camera.translate(dx, dy);
		camera.update();


		tileMapRenderer.setView(camera);
		tileMapRenderer.render();

		batch.begin();
		batch.draw(oz, w / 2 - ozw, w / 2 - ozh);
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.LEFT)
			dx = 1;
		if(keycode == Input.Keys.RIGHT)
			dx = -1;
		if(keycode == Input.Keys.UP)
			dy = 1;
		if(keycode == Input.Keys.DOWN)
			dy = -1;

		if(keycode == Input.Keys.NUM_1)
			tileMap.getLayers().get(0).setVisible(!tileMap.getLayers().get(0).isVisible());
		if(keycode == Input.Keys.NUM_2)
			tileMap.getLayers().get(1).setVisible(!tileMap.getLayers().get(1).isVisible());

		keypress = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
//		if(keycode == Input.Keys.LEFT)
//			camera.translate(-32,0);
//		if(keycode == Input.Keys.RIGHT)
//			camera.translate(32,0);
//		if(keycode == Input.Keys.UP)
//			camera.translate(0,+32);
//		if(keycode == Input.Keys.DOWN)
//			camera.translate(0,-32);
//		if(keycode == Input.Keys.NUM_1)
//			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
//		if(keycode == Input.Keys.NUM_2)
//			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		keypress = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void dispose () {
		batch.dispose();
		oz.dispose();
	}
}
