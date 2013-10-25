package vivadaylight3.myrmecology.common.entity.ai;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import vivadaylight3.myrmecology.api.EntityAIAntBehaviour;
import vivadaylight3.myrmecology.api.IEntityAnt;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntOdourous;
import vivadaylight3.myrmecology.common.lib.Environment;

public class AntBehaviourOdourous extends EntityAIAntBehaviour {

    public AntBehaviourOdourous(IEntityAnt parEntityAnt, World parWorld,
	    PathNavigate parPathFinder) {
	super(parEntityAnt, parWorld, parPathFinder);
    }

    @Override
    public void startExecuting() {

	ArrayList<EntityPlayer> list = Environment.getEntitiesInRadius(world,
		this.theAnt.getPosX(), this.theAnt.getPosY(),
		this.theAnt.getPosZ(), 5);

	for (int k = 0; k < list.size(); k++) {

	    list.get(k).addPotionEffect(
		    new PotionEffect(Potion.poison.id, 60, 1, true));

	}

    }

    @Override
    public void updateTask() {

	this.startExecuting();

    }

}
