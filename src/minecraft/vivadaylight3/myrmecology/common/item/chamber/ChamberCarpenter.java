package vivadaylight3.myrmecology.common.item.chamber;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.api.item.ItemBreedingChamber;
import vivadaylight3.myrmecology.common.Register;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChamberCarpenter extends ItemBreedingChamber {

    public ChamberCarpenter(int par1) {
	super(par1);
	// TODO Auto-generated constructor stub
    }

    @Override
    public ItemAnt getAnt() {

	return Register.antCarpenter;

    }

    @Override
    public ItemStack getCraftingIngredient() {

	return new ItemStack(Item.axeStone);

    }

    @Override
    public String getChamberAntSpeciesName() {

	return "Carpenter Ant ";

    }

    public boolean usesColourRendering() {

	return true;

    }

    protected int[] getColours() {

	return this.getAnt().getColours();

    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
	return this.usesColourRendering();
    }

}
