package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import model.Actor;
import model.Model;
import model.LevelBuilder;
import controller.Controller;

public class BuildGamePanel extends GamePanel implements Observer {
	
	public void addAction(Controller c){
		//the buttons do nothing
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		LevelBuilder builder = ((LevelBuilder)arg0);
		int x=0;
		int y=0;

		//draw each actor added to the level
		for(Actor a: builder.getLevel().getActorList()){
			if(builder.getLevel().inBounds(x, y)){
				super.b[y][x].setIcon(new ImageIcon(a.getSprite()));
			}
			x++;
			if(x > builder.getLevel().getMaxX()){
				x = 0;
				y++;
				if(y > builder.getLevel().getMaxY()){
					y = 0;
				}
			}
		}
		
	}
}
