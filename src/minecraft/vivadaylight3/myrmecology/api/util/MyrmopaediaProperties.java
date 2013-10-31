package vivadaylight3.myrmecology.api.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import vivadaylight3.myrmecology.api.item.ItemAnt;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.handler.MyrmecologyPacketHandler;
import vivadaylight3.myrmecology.common.lib.Nbt;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

/**
 * Handles Myrmopaedia properties
 * 
 * @author samueltebbs
 * 
 */
public class MyrmopaediaProperties {

    public static ItemStack[] myrmopaediaIDs = new ItemStack[1000];

    /**
     * Initiates a new antopedia with NBT data
     * 
     * @param itemStack
     */
    public static void initiateMyrmopaedia(ItemStack itemStack) {

	Nbt.setTag(itemStack);

	for (int k = 0; k < Register.getAntList().size(); k++) {

	    Nbt.set(itemStack, ((ItemAnt) Register.getAntList().get(k))
		    .getSpeciesSubName(), false);

	}

    }

    /**
     * Gets a new vacant myrmopaedia ID
     * 
     * @param itemStack
     * @return
     */
    private static int newMyrmopaediaID(ItemStack itemStack) {

	for (int k = 0; k < myrmopaediaIDs.length; k++) {

	    if (myrmopaediaIDs[k] == null) {

		myrmopaediaIDs[k] = itemStack;

		return k;

	    }

	}

	return -1;

    }

    public static ItemStack getMyrmopaediaFromID(int id) {

	if (myrmopaediaIDs[id] != null) {

	    return myrmopaediaIDs[id];

	} else {

	    return null;

	}

    }

    public static void clearMyrmopaediaID(int id) {

	myrmopaediaIDs[id] = null;

    }

    public static void addAntToMyrmopaedia(ItemStack itemstack, ItemAnt ant,
	    Player parPlayer) {

	boolean bool = true;

	int bytes = ant.getSpeciesSubName().getBytes().length + 4;

	ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes);
	DataOutputStream dos = new DataOutputStream(bos);

	try {

	    dos.writeUTF(ant.getSpeciesSubName());
	    dos.writeInt(newMyrmopaediaID(itemstack));

	} catch (Exception ex) {

	    ex.printStackTrace();

	}

	Packet250CustomPayload packet = new Packet250CustomPayload();

	packet.channel = Reference.MOD_CHANNEL;
	packet.data = bos.toByteArray();
	packet.length = bos.size();

	if (MyrmecologyPacketHandler.getSide() == Side.CLIENT) {

	    EntityClientPlayerMP player = (EntityClientPlayerMP) MyrmecologyPacketHandler
		    .getSidedPlayer(parPlayer);
	    player.sendQueue.addToSendQueue(packet);

	}

    }

    public static ArrayList<ItemAnt> getMyrmopaediaAnts(ItemStack itemStack) {

	ArrayList<ItemAnt> result = new ArrayList<ItemAnt>();

	for (int k = 0; k < Register.getAntList().size(); k++) {

	    if (Nbt.getBoolean(itemStack,
		    ((ItemAnt) Register.getAntList().get(k))
			    .getSpeciesSubName())) {

		result.add((ItemAnt) Register.getAntList().get(k));

	    }

	}

	return result;

    }

}
