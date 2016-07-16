package exnihilo3;

public class Reference {

	public static final String MOD_ID = "en3";
	public static final String NAME = "Ex Nihilo 3";
	public static final String VERSION = "0.0.1";
	public static final String ACCEPTED_VERSIONS = "[1.9.4]";
	
	public static final String CLIENT_PROXY_CLASS = "exnihilo3.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "exnihilo3.proxy.ServerProxy";
	
	public static enum EN3Items {
		CROOKWOOD("crookWood", "crookWood"),
		CROOKBONE("crookBone", "crookBone");
		
		private String unlocalizedName;
		private String registryName;
		
		private EN3Items(String unlocalizedName, String registryName) {
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getRegistryName() {
			return registryName;
		}
		
		public String getUnlocalizedName() {
			return unlocalizedName;
		}
		
	}
	
	public static enum EN3Blocks {
		
		CRUCIBLE("crucible", "crucible");
		
		private String unlocalizedName;
		private String registryName;
		
		private EN3Blocks(String unlocalizedName, String registryName) {
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getRegistryName() {
			return registryName;
		}
		
		public String getUnlocalizedName() {
			return unlocalizedName;
		}
		
	}
	
}
