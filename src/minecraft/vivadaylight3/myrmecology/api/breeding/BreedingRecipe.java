package vivadaylight3.myrmecology.api.breeding;

import vivadaylight3.myrmecology.api.item.ItemAnt;

public class BreedingRecipe {

    private ItemAnt ant1;
    private ItemAnt ant2;
    private ItemAnt antOutput;

    /**
     * Creates a new ant BreedingRecipe
     * 
     * @param ItemAnt
     *            ant input 1
     * @param ItemAnt
     *            ant input 2
     * @param ItemAnt
     *            ant output
     */
    public BreedingRecipe(ItemAnt par1, ItemAnt par2, ItemAnt par3) {

	this.ant1 = par1;
	this.ant2 = par2;
	this.antOutput = par3;

    }

    public ItemAnt getAnt1() {

	return this.ant1;

    }

    public ItemAnt getAnt2() {

	return this.ant2;

    }

    public ItemAnt getAntOutput() {

	return this.antOutput;

    }

}
