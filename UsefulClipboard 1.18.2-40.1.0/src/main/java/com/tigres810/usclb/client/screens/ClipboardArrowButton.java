package com.tigres810.usclb.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tigres810.usclb.Main;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class ClipboardArrowButton extends Button {
	
	final ResourceLocation gui = new ResourceLocation( Main.MODID, "textures/gui/clipboardgui.png" );

	private static final int buttonwidth = 8;
	private static final int buttonheight = 8;

	private static final int u = 66; // right to left
	private static final int v = 210; // up to down
	private static final int v1 = 194; // up to down
	
	private final boolean isForward;

	public ClipboardArrowButton ( int pX, int pY, Boolean isForward, OnPress pOnPress ) {
		super( pX, pY, buttonwidth, buttonheight, new TextComponent( "" ), pOnPress );
		this.isForward = isForward;
	}
	
	@Override
	public void renderButton ( PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick ) {
		RenderSystem.setShaderTexture( 0, gui );

		if(this.isForward) {
			this.blit( pPoseStack, x, y, u, v, buttonwidth, buttonheight );
		} else {
			this.blit( pPoseStack, x, y, u, v1, buttonwidth, buttonheight );
		}
	}

	@Override
	public void playDownSound ( SoundManager pHandler ) {
		pHandler.play( SimpleSoundInstance.forUI( SoundEvents.BOOK_PAGE_TURN, 1.0F ) );
	}

}
