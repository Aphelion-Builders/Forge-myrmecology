package vivadaylight3.myrmecology.common.tileentity;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import vivadaylight3.myrmecology.api.AntProperties;
import vivadaylight3.myrmecology.api.ItemAnt;
import vivadaylight3.myrmecology.api.Metadata;
import vivadaylight3.myrmecology.common.inventory.ContainerIncubator;
import vivadaylight3.myrmecology.common.lib.Environment;

public class TileEntityIncubator extends TileEntity implements IInventory {

    private ItemStack[] contents = new ItemStack[this.getSizeInventory()];

    private String field_94045_s;

    private final int MAX_MATURATION_TIME = 300;
    private int maturationTime;
    
    // Will be changeable via a gui button, one for each ant type but larvae
    private int resultAntMeta = Metadata.getMetaQueen();

    Metadata ants = new Metadata();

    @Override
    public void updateEntity() {
	
	if(this.canIncubate()){
	    
	    if(this.getMaturingTimeComplete() < this.getMaturingTime()){
		
		this.increaseMaturingTime();
		
	    }else if(this.getMaturingTimeComplete() >= this.getMaturingTime()){
		
		this.finishIncubation();
		
	    }
	    	    
	}
	
	/*
	if(this.maturationTime > 0) {
	    --this.maturationTime;

	    if (this.maturationTime == 0) {
		//this.matureLarva();
		this.onInventoryChanged();
	    } else if (!this.canIncubate()) {
		this.maturationTime = 0;
		this.onInventoryChanged();
	    }
	} else if (this.canIncubate()) {
	    this.maturationTime = MAX_MATURATION_TIME;
	}
	*/
    }
    
    private ItemStack getMaturingResult(){
	
	ItemStack result = new ItemStack(this.getLarva().getItem(), ((ItemAnt) this.getLarva().getItem()).getFertility(), this.resultAntMeta);
	
	return result;
	
    }
    
    private int getMaturingTime(){
	
	if(this.getLarva() != null){
	    
	    if(this.getLarva().getItem() instanceof ItemAnt){
		
		return ((ItemAnt) this.getLarva().getItem()).getMaturingTime();
		
	    }
	    
	}
	
	return 1;
	
    }
    
    public int getMaturingTimeComplete(){
	
	return AntProperties.getMaturingTimeComplete(getLarva());
	
    }

    // Determines if there is a queen and drone to produce larvae
    private boolean canIncubate() {
	
	if(this.getLarva() != null){
	    
	    if(this.getLarva().getItem() instanceof ItemAnt){
	
		return (Environment.inventoryCanHold(this.getMaturingResult(), this.getContents(), this.getInventoryStackLimit()));
	    
	    }
    
	
	}
	
	return false;
	
    }
    
    private void increaseMaturingTime(){
	
	int complete = AntProperties.getMaturingTimeComplete(getLarva());
	AntProperties.setMaturingTime(getLarva(), complete+1);
	
    }

    // turns the larvae into a mature ant
    private void finishIncubation() {
	
	Environment.addItemStackToInventory(getMaturingResult(), getContents(), getMaxStackSize(), this);
	this.decrStackSize(ContainerIncubator.getLarvaSlot(), 1);
	
	this.onInventoryChanged();

    }
    
    private ItemStack getLarva(){
	
	return this.getContents()[ContainerIncubator.getLarvaSlot()];
	
    }
    
    public ItemStack[] getContents() {

   	return this.contents;

       }

    public int getMaxStackSize() {
	return this.getInventoryStackLimit();
    }

    public ItemStack getStackInSlot(int index) {
	return this.contents[index];
    }

    public ItemStack decrStackSize(int index, int amount) {
	if (this.contents[index] != null) {
	    ItemStack itemstack;

	    if (this.contents[index].stackSize <= amount) {
		itemstack = this.contents[index];
		this.contents[index] = null;
		this.onInventoryChanged();
		return itemstack;
	    } else {
		itemstack = this.contents[index].splitStack(amount);

		if (this.contents[index].stackSize == 0) {
		    this.contents[index] = null;
		}

		this.onInventoryChanged();
		return itemstack;
	    }
	} else {
	    return null;
	}
    }

    public ItemStack getStackInSlotOnClosing(int index) {
	if (this.contents[index] != null) {
	    ItemStack itemstack = this.contents[index];
	    this.contents[index] = null;
	    return itemstack;
	} else {
	    return null;
	}
    }

    public void setInventorySlotContents(int index, ItemStack itemStack) {
	this.contents[index] = itemStack;

	if (itemStack != null
		&& itemStack.stackSize > this.getInventoryStackLimit()) {
	    itemStack.stackSize = this.getInventoryStackLimit();
	}

	this.onInventoryChanged();
    }

    public String getInvName() {
	return "Ant Incubator";
    }

    public boolean isInvNameLocalized() {
	return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
	super.readFromNBT(par1NBTTagCompound);
	NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
	this.contents = new ItemStack[this.getSizeInventory()];

	if (par1NBTTagCompound.hasKey("CustomName")) {
	    this.field_94045_s = par1NBTTagCompound.getString("CustomName");
	}

	for (int i = 0; i < nbttaglist.tagCount(); ++i) {
	    NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
		    .tagAt(i);
	    int j = nbttagcompound1.getByte("Slot") & 255;

	    if (j >= 0 && j < this.contents.length) {
		this.contents[j] = ItemStack
			.loadItemStackFromNBT(nbttagcompound1);
	    }
	}
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
	super.writeToNBT(par1NBTTagCompound);
	NBTTagList nbttaglist = new NBTTagList();

	for (int i = 0; i < this.contents.length; ++i) {
	    if (this.contents[i] != null) {
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setByte("Slot", (byte) i);
		this.contents[i].writeToNBT(nbttagcompound1);
		nbttaglist.appendTag(nbttagcompound1);
	    }
	}

	par1NBTTagCompound.setTag("Items", nbttaglist);

	if (this.isInvNameLocalized()) {
	    par1NBTTagCompound.setString("CustomName", this.field_94045_s);
	}
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
	return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
		this.zCoord) != this ? false
		: par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D,
			this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack itemstack) {
	return false;
    }

    @Override
    public int getSizeInventory() {
	return ContainerIncubator.getInventorySize();
    }

    @Override
    public void openChest() {
	// do nothing
    }

    @Override
    public void closeChest() {
	// do nothing
    }

    @Override
    public int getInventoryStackLimit() {
	return ContainerIncubator.stackLimit;
    }
}