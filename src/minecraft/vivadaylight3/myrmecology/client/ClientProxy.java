package vivadaylight3.myrmecology.client;

import java.util.EnumSet;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.input.Keyboard;

import vivadaylight3.myrmecology.client.model.ModelAnt;
import vivadaylight3.myrmecology.client.renderer.ItemRendererAntChest;
import vivadaylight3.myrmecology.client.renderer.RenderAnt;
import vivadaylight3.myrmecology.client.renderer.RendererAntChest;
import vivadaylight3.myrmecology.client.renderer.RendererAntFarm;
import vivadaylight3.myrmecology.client.renderer.RendererIncubator;
import vivadaylight3.myrmecology.common.CommonProxy;
import vivadaylight3.myrmecology.common.Reference;
import vivadaylight3.myrmecology.common.Register;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntCarpenter;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntDredger;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntFungal;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntMound;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntOdourous;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntScavenger;
import vivadaylight3.myrmecology.common.entity.ant.EntityAntSprouter;
import vivadaylight3.myrmecology.common.handler.KeyBindingHandler;
import vivadaylight3.myrmecology.common.handler.PlayerTickHandler;
import vivadaylight3.myrmecology.common.lib.Resources;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntChest;
import vivadaylight3.myrmecology.common.tileentity.TileEntityAntFarm;
import vivadaylight3.myrmecology.common.tileentity.TileEntityIncubator;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    public static int incubatorRenderID;
    public static int incubatorRenderPass;

    @Override
    public World getClientWorld() {
	return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    /**
     * Registers an entity renderer
     */
    public void addAntRenderer(Class<? extends EntityLiving> parClass,
	    RenderLiving render) {

	RenderingRegistry.registerEntityRenderingHandler(
		(Class<? extends Entity>) parClass, render);

    }

    @Override
    public void registerKeyBindings() {

	KeyBinding[] key = { new KeyBinding("Receive "
		+ Reference.ANTBOOK_TITLE, Keyboard.KEY_L) };
	boolean[] repeat = { false };
	KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler("Receive "
		+ Reference.ANTBOOK_TITLE, key, repeat));

	TickRegistry
		.registerTickHandler(
			new PlayerTickHandler(EnumSet.of(TickType.PLAYER)),
			Side.SERVER);

    }

    @Override
    public void registerRenderers() {

	addAntRenderer(EntityAntScavenger.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntCarpenter.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntOdourous.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntDredger.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntFungal.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntMound.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	addAntRenderer(EntityAntSprouter.class, new RenderAnt(
		Resources.ENTITY_ANT_SCAVENGER, new ModelAnt(), 0.5f));

	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAntChest.class,
		new RendererAntChest());
	MinecraftForgeClient.registerItemRenderer(
		Register.blockAntChest.blockID, new ItemRendererAntChest());

	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIncubator.class,
		new RendererIncubator());
	
	/*MinecraftForgeClient.registerItemRenderer(
		Register.blockAntChest.blockID, new ItemRendererAntChest());*/
	
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAntFarm.class,
		new RendererAntFarm());
	/*MinecraftForgeClient.registerItemRenderer(
		Register.blockAntFarm.blockID, new ItemRendererAntChest());*/

    }

    public String getCurrentLanguage() {

	return FMLClientHandler.instance().getCurrentLanguage();

    }

}