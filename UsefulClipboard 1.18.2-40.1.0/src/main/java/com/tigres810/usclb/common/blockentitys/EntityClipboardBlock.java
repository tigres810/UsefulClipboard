package com.tigres810.usclb.common.blockentitys;

import java.util.ArrayList;
import java.util.List;

import com.tigres810.usclb.core.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EntityClipboardBlock extends BlockEntity {

	String author = "";
	private List< CompoundTag > pages = new ArrayList< CompoundTag >( );

	public EntityClipboardBlock ( BlockPos pos, BlockState state ) {
		super( BlockEntityInit.CLIPBOARD_BLOCK_TILE.get( ), pos, state );
	}

	public List< CompoundTag > getPages ( ) {
		System.out.print( this.pages );
		return this.pages;
	}

	@Override
	public Packet< ClientGamePacketListener > getUpdatePacket ( ) {
		return ClientboundBlockEntityDataPacket.create( this );
	}

	@Override
	public void onDataPacket ( Connection net, ClientboundBlockEntityDataPacket pkt ) {
		handleUpdateTag( pkt.getTag( ) );
	}

	@Override
	public CompoundTag getUpdateTag ( ) {
		CompoundTag nbtTagCompound = new CompoundTag( );
		ListTag pagesNBT = new ListTag( );

		for ( CompoundTag page : pages ) {
			pagesNBT.add( page );
		}

		nbtTagCompound.putString( "author", author );
		nbtTagCompound.put( "pages", pagesNBT );
		return nbtTagCompound;
	}

	@Override
	public void handleUpdateTag ( CompoundTag parentNBTTagCompound ) {
		this.author = parentNBTTagCompound.getString( "author" );

		for ( Tag page : parentNBTTagCompound.getList( "pages", Tag.TAG_COMPOUND ) ) {
			this.pages.add( ( CompoundTag ) page );
		}
	}

	@Override
	protected void saveAdditional ( CompoundTag pTag ) {
		super.saveAdditional( pTag );
		ListTag pagesNBT = new ListTag( );

		for ( CompoundTag page : pages ) {
			pagesNBT.add( page );
		}

		pTag.putString( "author", author );
		pTag.put( "pages", pagesNBT );
	}

	@Override
	public void load ( CompoundTag pTag ) {
		super.load( pTag );
		this.author = pTag.getString( "author" );

		for ( Tag page : pTag.getList( "pages", Tag.TAG_COMPOUND ) ) {
			this.pages.add( ( CompoundTag ) page );
		}
	}

}
