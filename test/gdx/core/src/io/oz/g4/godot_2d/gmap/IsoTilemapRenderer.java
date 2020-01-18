package io.oz.g4.godot_2d.gmap;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Matrix4;

/**
 * Godot TileMap renderer.
 */
public class IsoTilemapRenderer implements MapRenderer {
	/**
	 * Sets the projection matrix and viewbounds from the given camera. If the camera changes, you have to call this method again.
	 * The viewbounds are taken from the camera's position and viewport size as well as the scale. This method will only work if
	 * the camera's direction vector is (0,0,-1) and its up vector is (0, 1, 0), which are the defaults.
	 *
	 * @param camera the {@link OrthographicCamera}
	 */
	@Override
	public void setView(OrthographicCamera camera) {

	}

	/**
	 * Sets the projection matrix for rendering, as well as the bounds of the map which should be rendered. Make sure that the
	 * frustum spanned by the projection matrix coincides with the viewbounds.
	 *
	 * @param projectionMatrix
	 * @param viewboundsX
	 * @param viewboundsY
	 * @param viewboundsWidth
	 * @param viewboundsHeight
	 */
	@Override
	public void setView(Matrix4 projectionMatrix, float viewboundsX, float viewboundsY, float viewboundsWidth, float viewboundsHeight) {

	}

	/**
	 * Renders all the layers of a map.
	 */
	@Override
	public void render() {

	}

	/**
	 * Renders the given layers of a map.
	 *
	 * @param layers the layers to render.
	 */
	@Override
	public void render(int[] layers) {

	}
}
