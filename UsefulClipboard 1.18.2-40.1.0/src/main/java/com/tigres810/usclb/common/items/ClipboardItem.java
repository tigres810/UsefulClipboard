package com.tigres810.usclb.common.items;

import java.util.List;

import com.tigres810.usclb.core.network.MainNetwork;
import com.tigres810.usclb.core.network.message.ClipboardInfo;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public class ClipboardItem extends BlockItem {

	public ClipboardItem ( Block block, Properties pProperties ) {
		super( block, pProperties );
	}

	@Override
	public void appendHoverText ( ItemStack pStack, Level pLevel, List< Component > pTooltipComponents,
			TooltipFlag pIsAdvanced ) {

		if ( pStack.getTag( ) != null
				&& pStack.getTag( ).getCompound( "BlockEntityTag" ).getString( "author" ) != null ) {
			String name = pStack.getTag( ).getCompound( "BlockEntityTag" ).getString( "author" );
			MutableComponent component = new TextComponent( name ).withStyle( ChatFormatting.AQUA )
					.withStyle( ChatFormatting.BOLD );
			MutableComponent textComponent = new TranslatableComponent( "tooltip.usclb.clipboard.tooltip.notesby" )
					.withStyle( ChatFormatting.RED );
			pTooltipComponents.add( textComponent );
			pTooltipComponents.add( component );
		} else {
			MutableComponent textComponent = new TranslatableComponent( "tooltip.usclb.clipboard.tooltip.notwritten" )
					.withStyle( ChatFormatting.GOLD );
			pTooltipComponents.add( textComponent );
		}
	}

	@Override
	public InteractionResultHolder< ItemStack > use ( Level pLevel, Player pPlayer, InteractionHand pUsedHand ) {

		if ( !pLevel.isClientSide ) {

			if ( pPlayer.isCrouching( ) ) {
				ItemStack itemStack = pPlayer.getItemInHand( pUsedHand );
				CompoundTag tag = itemStack.getTag( );
				MainNetwork.sendToPlayer( new ClipboardInfo( tag ), ( ServerPlayer ) pPlayer );
			}
		}
		return super.use( pLevel, pPlayer, pUsedHand );
	}

	@Override
	public InteractionResult useOn ( UseOnContext pContext ) {

		if ( !pContext.getLevel( ).isClientSide( ) ) {
			Player player = pContext.getPlayer( );

			if ( player.isCrouching( ) ) {
				ItemStack itemStack = player.getItemInHand( InteractionHand.MAIN_HAND );
				CompoundTag tag = itemStack.getTag( );
				MainNetwork.sendToPlayer( new ClipboardInfo( tag ), ( ServerPlayer ) player );
				return InteractionResult.FAIL;
			} else {
				SoundType soundtype = this.getBlock( ).defaultBlockState( ).getSoundType( pContext.getLevel( ),
						pContext.getClickedPos( ), player );
				pContext.getLevel( ).playSound( ( Player ) null, pContext.getClickedPos( ),
						getPlaceSound( this.getBlock( ).defaultBlockState( ), pContext.getLevel( ),
								pContext.getClickedPos( ), player ),
						SoundSource.BLOCKS, ( soundtype.getVolume( ) + 1.0F ) / 2.0F, soundtype.getPitch( ) * 0.8F );
				return super.useOn( pContext );
			}
		}
		return InteractionResult.SUCCESS;
	}
}
