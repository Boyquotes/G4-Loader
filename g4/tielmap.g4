/**
 * TileMap Grammar
 */

parser grammar TileMap;
options { tokenVocab=tilemap; }

TileMap
    : resource_section
	: node_section
    ;
