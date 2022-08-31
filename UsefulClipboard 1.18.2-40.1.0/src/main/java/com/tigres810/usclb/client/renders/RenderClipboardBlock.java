package com.tigres810.usclb.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.tigres810.usclb.common.blockentitys.EntityClipboardBlock;
import com.tigres810.usclb.common.blocks.ClipboardBlock;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.block.state.BlockState;

public class RenderClipboardBlock implements BlockEntityRenderer< EntityClipboardBlock > {

	private final Font font;

	public RenderClipboardBlock ( BlockEntityRendererProvider.Context context ) {
		this.font = context.getFont( );
	}

	@Override
	public void render ( EntityClipboardBlock tileEntityIn, float partialTicks, PoseStack matrixStackIn,
			MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn ) {
		BlockState state = tileEntityIn.getBlockState( );
		Quaternion rotationX = Vector3f.YP.rotationDegrees( 180 );
		Quaternion rotationY = Vector3f.XP.rotationDegrees( -90 );

		matrixStackIn.pushPose( );
		matrixStackIn.mulPose( rotationX );
		matrixStackIn.mulPose( rotationY );
		matrixStackIn.translate( -0.665D, 0.7D, 0.01D );
		matrixStackIn.scale( 0.003F, -0.003F, 0.003F );

		if ( tileEntityIn.getPages( ).size( ) > 0 ) {

			switch ( state.getValue( ClipboardBlock.PROPERTY ) ) {
				case 0:
					// return SHAPE_DN;
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox1" ) ), 0, 0,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox2" ) ), 0, 11,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox3" ) ), 0, 22,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox4" ) ), 0, 33,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox5" ) ), 0, 44,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox6" ) ), 0, 55,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox7" ) ), 0, 66,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox8" ) ), 0, 77,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox9" ) ), 0, 88,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox10" ) ), 0, 99,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox11" ) ), 0, 111,
							0x000000 );
					this.font.draw( matrixStackIn,
							new TextComponent( tileEntityIn.getPages( ).get( 0 ).getString( "txtbox12" ) ), 0, 122,
							0x000000 );
				case 1:
					// return SHAPE_DE;
				case 2:
					// return SHAPE_DW;
				case 3:
					// return SHAPE_DS;
				case 4:
					// return SHAPE_E;
				case 5:
					// return SHAPE_W;
				case 6:
					// return SHAPE_N;
				case 7:
					// return SHAPE_S;
			}
		}
		matrixStackIn.popPose( );
	}

}
