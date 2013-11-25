package model;

/**
 * 
 * This class is resposible for Actors sprites
 * @author Mamoon
 *
 */
public class Sprite {
	
	private String spriteIcon;
	
	/**
	 * 
	 * @param icon Is the  the initial sprite icon 
	 */
	public Sprite(String icon){
		
		this.spriteIcon = icon;
	}
	/**
	 * 
	 * @param newIcon is the new sprite to be updated
	 */
	public void setIcon(String newIcon){
		
		this.spriteIcon = newIcon;
	}
	/**
	 * 
	 * @return sprite
	 */
	public String getSprite(){
		
		return this.spriteIcon;
	}
	

}
