pvz
===

SYSC 3110 Project

How To Play:
Select File->New from the drop down menu.
Click on a plant to chose a type.
Click anywhere on the grid after selecting a plant to place a plant. 
Click the End Turn button if you do not wish to place a plant. 
Zombies invade from the right.

Fixed Issues:
-tile-actor double-linking. is poor design, often leads to problems. an alternative will be found
-Undo/Redo feature buggy (?)


Changes from Previous Release:
-  Grid system of the game changed, Tile class removed as a result reduced coupling   between classes and more efficient stacking of zombies
-Tests for new plants and zombies (from previous milestone) added
-Undo/Redo has been implemented using the Clonnable interface
-Stacking of Zombies is supported
-Some classes re-factored, View split into smaller classes, other changes


