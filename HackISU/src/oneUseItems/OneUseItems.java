package oneUseItems;

import textGame.Item;

public abstract class OneUseItems extends Item{
	protected int usesLeft;
	
	/**
	 * 
	 * @return - the Item's remaining uses
	 */
	public int getUsesLeft() {
		return usesLeft;
	}
}
