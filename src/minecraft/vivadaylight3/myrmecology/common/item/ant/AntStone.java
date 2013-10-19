package vivadaylight3.myrmecology.common.item.ant;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.common.Myrmecology;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.lib.Time;

public class AntStone extends ItemAnt {

    public AntStone(int par1) {
	super(par1);
	// TODO Auto-generated constructor stub
    }

    @Override
    public String getSpeciesName() {
	// TODO Auto-generated method stub
	return "Amber Ant";
    }

    @Override
    public String getSpeciesSubName() {
	return Reference.ANT_STONE_NAME;
    }

    @Override
    public boolean isHillAnt() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public int getFertility() {
	// TODO Auto-generated method stub
	return 2;
    }

    @Override
    public int getLifetime() {
	// TODO Auto-generated method stub
	return Time.getTicksFromMinutes(15);
    }

    @Override
    public boolean eatsSweet() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean eatsSavoury() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean eatsMeat() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean eatsLarvae() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void performBehaviour(World world, int x, int y, int z) {
	// TODO Auto-generated method stub

    }

    @Override
    public String getSpeciesBinomialName() {
	// TODO Auto-generated method stub
	return "Formicidae Lapidus";
    }

    @Override
    public BiomeGenBase[] getAntBiomes() {

	return null;
    }

    @Override
    public boolean getWinged() {

	return true;

    }

    @Override
    public boolean getNocturnal() {

	return true;

    }
    
    @Override
    protected int[] getColours(){
	
	return new int[] {0x2b2b2b, 0x636363};
	
    }

}
