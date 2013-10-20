package vivadaylight3.myrmecology.api;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.lib.Resources;

/**
 * Extend this class to create a new breeding chamber used in ant breeding
 * 
 * @author samueltebbs
 * 
 */
public class ItemBreedingChamber extends Item {

    private Icon iconOverlay;
    private Icon iconBase;

    public ItemBreedingChamber(int par1) {
	super(par1);
	prepareItem();
    }
    
    /**
     * Returns true if you want your chamber to use Myrmecology's texture overlay.
     * The colour codes returned by getColours() will be applied to the base
     * ant textures to create your chamber's coloured texture. Also used with
     * ItemBreedingChamber
     * 
     * @return
     */
    public boolean usesColourRendering() {

	return true;

    }
    
    public Icon getIconFromDamageForRenderPass(int par1, int par2) {

	if (par2 == 0) {

	    return iconBase;

	} else {

	    return iconOverlay;

	}
    }
    
    protected int[] getColours() {

	return new int[] { 0xE6AD4B, 0x3CE9F5 };

    }
    
    @Override
    public int getColorFromItemStack(ItemStack par1ItemStack, int pass) {

	return this.getColours()[pass];

    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
	return this.usesColourRendering();
    }

    private void prepareItem() {

	setUnlocalizedName(this.getChamberAntSpeciesName()
		+ Reference.ITEM_CHAMBER_NAME);
	setCreativeTab(Register.tabMyrmecology);
	setMaxStackSize(64);
	// func_111206_d(Resources.TEXTURE_PREFIX + "ant_"
	// + this.getSpeciesSubName());

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {

	this.iconOverlay = register.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.ITEM_CHAMBER_NAME+"_overlay");
	
	this.iconBase = register.registerIcon(Resources.TEXTURE_PREFIX
		+ Reference.ITEM_CHAMBER_NAME);

    }

    /**
     * Gets the ant that this chamber is used to breed
     * 
     * @return ItemAnt (null if no ants use it...)
     */
    public ItemAnt getAnt() {

	return null;

    }

    /**
     * Used in prepareItem() to get the name of the ant species which this
     * chamber is used for, should end with a space
     * 
     * @return String
     */
    public String getChamberAntSpeciesName() {

	return "";

    }

}
