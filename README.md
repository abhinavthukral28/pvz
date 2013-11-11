pvz
===

SYSC 3110 Project

How To Play:
Select File->New from the drop down menu.
Click anywhere on the grid after selecting a plant to place a plant. Click the End Turn button if you do not wish to place a plant. Zombies invade from the right

Changes from Previous Release:
-Actor has been made Abstract
-Getter/Setter methods added to Model and other classes to aid testing/GUI
-GUI added with new View and Controller classes

Known Issues:
-stacking zombies. placing multiple zombies in the same square will be supported in the next release
-tile-actor double-linking. is poor design, often leads to problems. an alternative will be found
-size of classes. Model, View, and Controller have too many responsibilities and should be split into smaller classes
