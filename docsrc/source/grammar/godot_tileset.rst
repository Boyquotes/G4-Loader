
Godot TileSet(.tres) File Format
================================

TileSet Resource
----------------

TileSet file (.tres) composed from 2 major parts.

Part I: referenced resources
____________________________

::

    [gd_resource type="TileSet" load_steps=7 format=2]

    [ext_resource path="res://asserts/t02.png" type="Texture" id=1]

    [sub_resource type="NavigationPolygon" id=1]
    vertices = PoolVector2Array( 0, 0, 96, 0, 88, 8, 96, 24, 80, 64, 64, 80, 32, 96, 8, 96, 0, 104 )
    polygons = [ PoolIntArray( 0, 1, 2, 3, 4, 5, 6, 7, 8 ) ]
    [sub_resource type="ConvexPolygonShape2D" id=2]
    points = PoolVector2Array( 104, 0, 104, 24, 120, 40, 128, 56, 120, 88, 88, 120, 64, 128, 48, 128, 24, 120, 0, 104, 0, 96, 16, 96, 56, 88, 80, 64, 88, 48, 96, 24, 88, 8, 96, 0 )

The NavigationPolygon and ConvexPolygonShape2D are created with "collition" and
"navigation" command.

.. image:: img/002-tileset_shape_polygon.png
    :width: 800px


Part II: tile definition
________________________

Tiles are defined in "id/porp-name" format, all in "[resource]" section

::

    [resource]
    0/name = "t02.png 0"
    0/texture = ExtResource( 1 )
    0/tex_offset = Vector2( 0, 0 )
    0/modulate = Color( 1, 1, 1, 1 )
    0/region = Rect2( 0, 0, 128, 128 )
    0/tile_mode = 0
    0/occluder_offset = Vector2( 0, 0 )
    0/navigation_offset = Vector2( 0, 0 )
    0/navigation = SubResource( 1 )
    0/shapes = [ {
    "autotile_coord": Vector2( 0, 0 ),
    "one_way": false,
    "one_way_margin": 1.0,
    "shape": SubResource( 2 ),
    "shape_transform": Transform2D( 1, 0, 0, 1, 0, 0 )
    } ]
    0/z_index = 0

Where *shapes* are defined as collision shape, and region must be the sprite area.

TODO: sprite

Data in TileSet Loader
----------------------

Class TileSet
_____________

scene/resources/tile_set.h

.. code-block:: cpp

    class TileSet : public Resource {
        GDCLASS(TileSet, Resource);

    public:
        struct ShapeData { ... };

        enum AutotileBindings {
            BIND_TOPLEFT = 1,
            BIND_TOP = 2,
            BIND_TOPRIGHT = 4,
            ...
            BIND_IGNORE_BOTTOMRIGHT = 1 << 24
        };

        enum TileMode { ... }
        struct AutotileData { ... };

    private:
        struct TileData { ... };
        Map<int, TileData> tile_map;
        ...
    }
..

.. _shape-data:

ShapeData
_________

.. code-block:: cpp

    struct ShapeData {
        Ref<Shape2D> shape;
        Transform2D shape_transform;
        Vector2 autotile_coord;
        bool one_way_collision;
        float one_way_collision_margin;

        ShapeData() {
            one_way_collision = false;
            one_way_collision_margin = 1.0;
        }
    };
..

scene/resources/shape_2d.h

.. code-block:: cpp

    class Shape2D : public Resource {
        GDCLASS(Shape2D, Resource);
        OBJ_SAVE_TYPE(Shape2D);

        RID shape;
        real_t custom_bias;
        ...
    }
..

core/rid.h

.. code-block:: cpp

    class RID_Data {
        uint32_t _id;

    public:
        _FORCE_INLINE_ uint32_t get_id() const { return _id; }
        virtual ~RID_Data();
    };

    class RID {
        mutable RID_Data *_data;

    public:
        _FORCE_INLINE_ RID_Data *get_data() const { return _data; }

        _FORCE_INLINE_ bool operator==(const RID &p_rid) const {
            return _data == p_rid._data;
        }
        ...
    }
..

Shape2D Subclasses
__________________

- CapsuleShape2D

scene/resources/capsule_shape_2d.h

.. code-block:: cpp

    class CapsuleShape2D : public Shape2D {
        GDCLASS(CapsuleShape2D, Shape2D);

        real_t height;
        real_t radius;
        ...
    }
..

- CircleShape2D

scene/resources/circle_shape_2d.h

.. code-block:: cpp

    class CircleShape2D : public Shape2D {
        GDCLASS(CircleShape2D, Shape2D);

        real_t radius;
        void _update_shape();
        ...
    }
..

- ConcavePolygonShape2D

scene/resources/concave_polygon_shape_2d.h

.. code-block:: cpp

    class ConcavePolygonShape2D : public Shape2D {
        GDCLASS(ConcavePolygonShape2D, Shape2D);
        ...
    }
..

- ConvexPolygonShape2D

.. code-block:: cpp

    class ConvexPolygonShape2D : public Shape2D {
        GDCLASS(ConvexPolygonShape2D, Shape2D);

        Vector<Vector2> points;
        ...
    }
..

- LineShape2D

.. code-block:: cpp

    class LineShape2D : public Shape2D {
        GDCLASS(LineShape2D, Shape2D);

        Vector2 normal;
        real_t d;
        ...
    }
..

- RectangleShape2D

.. code-block:: cpp

    class RectangleShape2D : public Shape2D {
        GDCLASS(RectangleShape2D, Shape2D);

        Vector2 extents;
        ...
    }
..

- SegmentShape2D & RayShape2D

.. code-block:: cpp

    class SegmentShape2D : public Shape2D {
        GDCLASS(SegmentShape2D, Shape2D);

        Vector2 a;
        Vector2 b;
        ...
    }


    class RayShape2D : public Shape2D {
        GDCLASS(RayShape2D, Shape2D);

        real_t length;
        bool slips_on_slope;
        ...
    }
..

.. _autotile-data:

AutotileData
____________

.. code-block:: cpp

    struct AutotileData {
        BitmaskMode bitmask_mode;
        Size2 size;
        int spacing;
        Vector2 icon_coord;
        Map<Vector2, uint32_t> flags;
        Map<Vector2, Ref<OccluderPolygon2D> > occluder_map;
        Map<Vector2, Ref<NavigationPolygon> > navpoly_map;
        Map<Vector2, int> priority_map;
        Map<Vector2, int> z_index_map;

        // Default size to prevent invalid value
        explicit AutotileData() :
                bitmask_mode(BITMASK_2X2),
                size(64, 64),
                spacing(0),
                icon_coord(0, 0) {}
    };
..

where BitmaskMode is also defined in tile_set.h

.. code-block:: cpp

    enum BitmaskMode {
        BITMASK_2X2,
        BITMASK_3X3_MINIMAL,
        BITMASK_3X3
    };
..

TileData
________

.. code-block:: cpp

    struct TileData {
        String name;
        Ref<Texture> texture;
        Ref<Texture> normal_map;
        Vector2 offset;
        Rect2i region;
        Vector<ShapeData> shapes_data;
        Vector2 occluder_offset;
        Ref<OccluderPolygon2D> occluder;
        Vector2 navigation_polygon_offset;
        Ref<NavigationPolygon> navigation_polygon;
        Ref<ShaderMaterial> material;
        TileMode tile_mode;
        Color modulate;
        AutotileData autotile_data;
        int z_index;

        // Default modulate for back-compat
        explicit TileData() :
                tile_mode(SINGLE_TILE),
                modulate(1, 1, 1),
                z_index(0) {}
    };
..

See also :ref:`shape-data`, :ref:`autotile-data`.

- OccluderPolygon2D

.. code-block:: cpp

    class OccluderPolygon2D : public Resource {
        GDCLASS(OccluderPolygon2D, Resource);

    public:
        enum CullMode {
            CULL_DISABLED,
            CULL_CLOCKWISE,
            CULL_COUNTER_CLOCKWISE
        };

    private:
        RID occ_polygon;
        PoolVector<Vector2> polygon;
        bool closed;
        CullMode cull;

        mutable Rect2 item_rect;
        mutable bool rect_cache_dirty;
        ...
    }
..

- NavigationPolygon

.. code-block:: cpp

    class NavigationPolygon : public Resource {
    	GDCLASS(NavigationPolygon, Resource);

    	PoolVector<Vector2> vertices;
    	struct Polygon {
    		Vector<int> indices;
    	};
    	Vector<Polygon> polygons;
    	Vector<PoolVector<Vector2> > outlines;

    	mutable Rect2 item_rect;
    	mutable bool rect_cache_dirty;
        ...
    }
..
