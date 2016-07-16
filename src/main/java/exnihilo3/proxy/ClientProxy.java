package exnihilo3.proxy;

import exnihilo3.init.ModBlocks;
import exnihilo3.init.ModItems;

public class ClientProxy implements CommonProxy {

	@Override
	public void init() {
		
		ModItems.registerRenders();
		ModBlocks.registerRenders();
		
	}

}
