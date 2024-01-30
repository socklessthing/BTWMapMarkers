package net.minecraft.src;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.minecraft.src.FCRecipes.AddShapelessRecipe;

public class SledgesMapMarkersAddon extends FCAddOn {
    public static SledgesMapMarkersAddon instance = new SledgesMapMarkersAddon();

    private static final int id_mapMarker = 2900;
    public static Block mapMarker;

    private static final int id_mapMarkerItem = 31900;
    public static Item mapMarkerItem;

    @SuppressWarnings("rawtypes")
    public static Map WorldMapMarkers = new LinkedHashMap<String, SMMMapMarkerData>();

    private SledgesMapMarkersAddon() {
        super("Sledge Map Markers", "1.1.0", "SMM");
    }

    @Override
    public void Initialize() {
        FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        AddDefinitions();
        AddRecipes();

        FCAddOnHandler.LogMessage(this.getName() + " Initialized");
    }

	@Override
    public FCAddOnUtilsWorldData createWorldData() {
        return new SMMUtilsWorldData();
    }

    private static void AddDefinitions()
    {
        mapMarker = new SMMBlockMapMarker(id_mapMarker);
        Item.itemsList[mapMarker.blockID] = new ItemBlock(mapMarker.blockID - 256);
        
        mapMarkerItem = (new SMMItemMapMarker(id_mapMarkerItem - 256, mapMarker, "smmItemMapMarker")).
        SetBuoyant().SetFilterableProperties(Item.m_iFilterable_SolidBlock).setCreativeTab(CreativeTabs.tabTools);

        TileEntity.addMapping(SMMTileEntityMapMarker.class, "SMMMapMarker");
    }

    private void AddRecipes() {
    	
    	int[] dyes = {
    			15, //white			
    			10, //lime green
    			1, //red
    			4, //blue    			
    			8, //gray
    			11, //yellow
    			14, //orange
    			5, //purple
    	};
    	
    	int[] markers = {
    			0,
    			5,
    			7,
    			8,
    			12,
    			13,
    			14,
    			15    			
    	};
    	
    	//Default, aka white marker
    	AddShapelessRecipe( new ItemStack( mapMarkerItem, 1 ), new Object[] {
                new ItemStack( FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, FCUtilsInventory.m_iIgnoreMetadata ),
                new ItemStack( Item.paper )
        } );

        AddShapelessRecipe( new ItemStack( mapMarkerItem, 1 ), new Object[] {
                new ItemStack( Item.stick ),
                new ItemStack( Item.paper )
        } );

    	//dying any marker with dyes
        for (int i = 0; i < markers.length; i++) {	    	
            AddShapelessRecipe( new ItemStack( mapMarkerItem, 1, markers[i] ), new Object[] {
            		new ItemStack( mapMarkerItem, 1, FCUtilsInventory.m_iIgnoreMetadata ),
                    new ItemStack( Item.dyePowder, 1, dyes[i] )
            } );
    	}
	}
}