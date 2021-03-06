package com.bioxx.tfc.GUI;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.bioxx.tfc.Reference;
import com.bioxx.tfc.Containers.ContainerLiquidVessel;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.Player.PlayerInventory;

public class GuiVesselLiquid extends GuiContainer
{
	EntityPlayer player;
	private int bagsSlotNum;

	public GuiVesselLiquid(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
	{
		super(new ContainerLiquidVessel(inventoryplayer, world, i, j, k));
		player = inventoryplayer.player;
		bagsSlotNum = player.inventory.currentItem;
		xSize = 176;
		ySize = 85+PlayerInventory.invYSize;
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
	}

	protected void drawGuiContainerForegroundLayer()
	{
		fontRendererObj.drawString(StatCollector.translateToLocal("gui.Inventory"), 8, (ySize - 96) + 2, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		TFC_Core.bindTexture(new ResourceLocation(Reference.ModID, Reference.AssetPathGui + "gui_vessel_liquid.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);

		PlayerInventory.drawInventory(this, width, height, ySize-PlayerInventory.invYSize);
		
		NBTTagCompound tags = player.inventory.mainInventory[this.bagsSlotNum].getTagCompound();
		if((tags != null) && tags.hasKey("MetalType"))
		{
			drawCenteredString(this.fontRendererObj, tags.getString("MetalType"), l+87, i1+13, 0);
		}
		if((tags != null) && tags.hasKey("MetalAmount"))
		{
			drawCenteredString(this.fontRendererObj, tags.getInteger("MetalAmount")+" Units", l+87, i1+23, 0);
		}
	}

	@Override
	public void drawCenteredString(FontRenderer fontrenderer, String s, int i, int j, int k)
	{
		fontrenderer.drawString(s, i - fontrenderer.getStringWidth(s) / 2, j, k);
	}
}
