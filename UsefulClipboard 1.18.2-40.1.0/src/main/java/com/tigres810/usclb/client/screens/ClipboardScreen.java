package com.tigres810.usclb.client.screens;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tigres810.usclb.Main;
import com.tigres810.usclb.core.network.MainNetwork;
import com.tigres810.usclb.core.network.message.UpdateClipboardItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class ClipboardScreen extends Screen {

	private static final int WIDTH = 192;
	private static final int HEIGHT = 192;

	private static final int OFFSETX = 64;
	private static final int OFFSETY = 15;

	private static final int OFFSETBUTTON = 12;
	private static final int OFFSETBUTTON1 = 13;

	private static final int OFFSETTEXTBOXX = 1;
	private static final int OFFSETTEXTBOXY = 3;

	private static final int OFFSETTEXTAUTHORX = 35;
	private static final int OFFSETTEXTAUTHORY = 164;

	private static final int OFFSETTEXTPAGES = 50;

	private ResourceLocation GUI = new ResourceLocation( Main.MODID, "textures/gui/clipboardgui.png" );

	public CompoundTag nbt;

	private String author = "";

	private ClipboardButton button1;
	private ClipboardButton button2;
	private ClipboardButton button3;
	private ClipboardButton button4;
	private ClipboardButton button5;
	private ClipboardButton button6;
	private ClipboardButton button7;
	private ClipboardButton button8;
	private ClipboardButton button9;
	private ClipboardButton button10;
	private ClipboardButton button11;
	private ClipboardButton button12;

	private CustomEditBox txtbox1;
	private CustomEditBox txtbox2;
	private CustomEditBox txtbox3;
	private CustomEditBox txtbox4;
	private CustomEditBox txtbox5;
	private CustomEditBox txtbox6;
	private CustomEditBox txtbox7;
	private CustomEditBox txtbox8;
	private CustomEditBox txtbox9;
	private CustomEditBox txtbox10;
	private CustomEditBox txtbox11;
	private CustomEditBox txtbox12;

	private ClipboardArrowButton pagebuttonleft;
	private ClipboardArrowButton pagebuttonright;

	private List< CompoundTag > pages = new ArrayList< CompoundTag >( );
	private int currentpage = 0;

	private static final int maxpages = 5;

	public ClipboardScreen ( CompoundTag newnbt ) {
		super( new TranslatableComponent( "" ) );

		if ( newnbt != null ) {
			this.nbt = newnbt.getCompound( "BlockEntityTag" );
			this.author = this.nbt.getString( "author" );
		}
	}

	@Override
	public void tick ( ) {
		this.txtbox1.tick( );
		this.txtbox2.tick( );
		this.txtbox3.tick( );
		this.txtbox4.tick( );
		this.txtbox5.tick( );
		this.txtbox6.tick( );
		this.txtbox7.tick( );
		this.txtbox8.tick( );
		this.txtbox9.tick( );
		this.txtbox10.tick( );
		this.txtbox11.tick( );
		this.txtbox12.tick( );
		checkValueChanged( );
		super.tick( );
	}

	@Override
	protected void init ( ) {
		this.minecraft.keyboardHandler.setSendRepeatsToGui( true );
		this.clearWidgets( );
		this.children( ).clear( );
		// int centerX = ( this.width - WIDTH ) / 2;
		int centerY = ( this.height - HEIGHT ) / 2;
		button1 = new ClipboardButton( ( this.width / 2 - OFFSETX ), centerY + OFFSETY, button -> nothing( ) );
		button2 = new ClipboardButton( ( this.width / 2 - OFFSETX ), centerY + OFFSETY + OFFSETBUTTON,
				button -> nothing( ) );
		button3 = new ClipboardButton( ( this.width / 2 - OFFSETX ), centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON,
				button3 -> nothing( ) );
		button4 = new ClipboardButton( ( this.width / 2 - OFFSETX ),
				centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON, button -> nothing( ) );
		button5 = new ClipboardButton( ( this.width / 2 - OFFSETX ),
				centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON, button -> nothing( ) );
		button6 = new ClipboardButton( ( this.width / 2 - OFFSETX ),
				centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON,
				button -> nothing( ) );
		button7 = new ClipboardButton( ( this.width / 2 - OFFSETX ), centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON
				+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON, button -> nothing( ) );
		button8 = new ClipboardButton( ( this.width / 2 - OFFSETX ), centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON
				+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON, button -> nothing( ) );
		button9 = new ClipboardButton(
				( this.width / 2 - OFFSETX ), centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON1,
				button -> nothing( ) );
		button10 = new ClipboardButton(
				( this.width / 2 - OFFSETX ), centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON1,
				button -> nothing( ) );
		button11 = new ClipboardButton( ( this.width / 2 - OFFSETX ),
				centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON1,
				button -> nothing( ) );
		button12 = new ClipboardButton( ( this.width / 2 - OFFSETX ),
				centerY + OFFSETY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON1,
				button -> nothing( ) );
		this.txtbox1 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY, 109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox1 );
		this.txtbox2 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON, 109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox2 );
		this.txtbox3 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON, 109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox3 );
		this.txtbox4 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON, 109, 9,
				new TextComponent( "" ) );
		configureTxtBox( this.txtbox4 );
		this.txtbox5 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON, 109, 9,
				new TextComponent( "" ) );
		configureTxtBox( this.txtbox5 );
		this.txtbox6 = new CustomEditBox(
				this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ), centerY + OFFSETY
						+ OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON,
				109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox6 );
		this.txtbox7 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON,
				109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox7 );
		this.txtbox8 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON,
				109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox8 );
		this.txtbox9 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON1,
				109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox9 );
		this.txtbox10 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON1 + OFFSETBUTTON,
				109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox10 );
		this.txtbox11 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON1 + OFFSETBUTTON1 + OFFSETBUTTON
						- OFFSETTEXTBOXX,
				109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox11 );
		this.txtbox12 = new CustomEditBox( this.font, ( this.width / 2 - OFFSETX + OFFSETBUTTON + OFFSETTEXTBOXX ),
				centerY + OFFSETY + OFFSETTEXTBOXY + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON
						+ OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON + OFFSETBUTTON1 + OFFSETBUTTON1 + OFFSETBUTTON1
						+ OFFSETBUTTON + OFFSETTEXTBOXX - OFFSETTEXTBOXY,
				109, 9, new TextComponent( "" ) );
		configureTxtBox( this.txtbox12 );
		this.pagebuttonleft = new ClipboardArrowButton( ( ( width / 2 ) - this.font.width( "test" ) / 2 ) + 41,
				centerY + 9, false, button -> changePageBack( ) );
		this.pagebuttonright = new ClipboardArrowButton( ( ( width / 2 ) - this.font.width( "test" ) / 2 ) + 56,
				centerY + 9, true, button -> changePageForward( ) );
		this.addRenderableWidget( button1 );
		this.addRenderableWidget( button2 );
		this.addRenderableWidget( button3 );
		this.addRenderableWidget( button4 );
		this.addRenderableWidget( button5 );
		this.addRenderableWidget( button6 );
		this.addRenderableWidget( button7 );
		this.addRenderableWidget( button8 );
		this.addRenderableWidget( button9 );
		this.addRenderableWidget( button10 );
		this.addRenderableWidget( button11 );
		this.addRenderableWidget( button12 );
		this.addRenderableWidget( txtbox1 );
		this.addRenderableWidget( txtbox2 );
		this.addRenderableWidget( txtbox3 );
		this.addRenderableWidget( txtbox4 );
		this.addRenderableWidget( txtbox5 );
		this.addRenderableWidget( txtbox6 );
		this.addRenderableWidget( txtbox7 );
		this.addRenderableWidget( txtbox8 );
		this.addRenderableWidget( txtbox9 );
		this.addRenderableWidget( txtbox10 );
		this.addRenderableWidget( txtbox11 );
		this.addRenderableWidget( txtbox12 );
		this.addRenderableWidget( pagebuttonleft );
		this.addRenderableWidget( pagebuttonright );

		loadValuesNbt( );
	}

	@Override
	public boolean isPauseScreen ( ) { return false; }

	private void nothing ( ) {}

	private void checkValueChanged ( ) {

		if ( this.button1.getOldState( ) != this.button1.getState( )
				|| this.button2.getOldState( ) != this.button2.getState( )
				|| this.button3.getOldState( ) != this.button3.getState( )
				|| this.button4.getOldState( ) != this.button4.getState( )
				|| this.button5.getOldState( ) != this.button5.getState( )
				|| this.button6.getOldState( ) != this.button6.getState( )
				|| this.button7.getOldState( ) != this.button7.getState( )
				|| this.button8.getOldState( ) != this.button8.getState( )
				|| this.button9.getOldState( ) != this.button9.getState( )
				|| this.button10.getOldState( ) != this.button10.getState( )
				|| this.button11.getOldState( ) != this.button11.getState( )
				|| this.button12.getOldState( ) != this.button12.getState( )
				|| !this.txtbox1.getOldString( ).equals( this.txtbox1.getValue( ) )
				|| !this.txtbox2.getOldString( ).equals( this.txtbox2.getValue( ) )
				|| !this.txtbox3.getOldString( ).equals( this.txtbox3.getValue( ) )
				|| !this.txtbox4.getOldString( ).equals( this.txtbox4.getValue( ) )
				|| !this.txtbox5.getOldString( ).equals( this.txtbox5.getValue( ) )
				|| !this.txtbox6.getOldString( ).equals( this.txtbox6.getValue( ) )
				|| !this.txtbox7.getOldString( ).equals( this.txtbox7.getValue( ) )
				|| !this.txtbox8.getOldString( ).equals( this.txtbox8.getValue( ) )
				|| !this.txtbox9.getOldString( ).equals( this.txtbox9.getValue( ) )
				|| !this.txtbox10.getOldString( ).equals( this.txtbox10.getValue( ) )
				|| !this.txtbox11.getOldString( ).equals( this.txtbox11.getValue( ) )
				|| !this.txtbox12.getOldString( ).equals( this.txtbox12.getValue( ) ) ) {
			this.button1.setOldState( this.button1.getState( ) );
			this.button2.setOldState( this.button2.getState( ) );
			this.button3.setOldState( this.button3.getState( ) );
			this.button4.setOldState( this.button4.getState( ) );
			this.button5.setOldState( this.button5.getState( ) );
			this.button6.setOldState( this.button6.getState( ) );
			this.button7.setOldState( this.button7.getState( ) );
			this.button8.setOldState( this.button8.getState( ) );
			this.button9.setOldState( this.button9.getState( ) );
			this.button10.setOldState( this.button10.getState( ) );
			this.button11.setOldState( this.button11.getState( ) );
			this.button12.setOldState( this.button12.getState( ) );
			this.txtbox1.setOldString( this.txtbox1.getValue( ) );
			this.txtbox2.setOldString( this.txtbox2.getValue( ) );
			this.txtbox3.setOldString( this.txtbox3.getValue( ) );
			this.txtbox4.setOldString( this.txtbox4.getValue( ) );
			this.txtbox5.setOldString( this.txtbox5.getValue( ) );
			this.txtbox6.setOldString( this.txtbox6.getValue( ) );
			this.txtbox7.setOldString( this.txtbox7.getValue( ) );
			this.txtbox8.setOldString( this.txtbox8.getValue( ) );
			this.txtbox9.setOldString( this.txtbox9.getValue( ) );
			this.txtbox10.setOldString( this.txtbox10.getValue( ) );
			this.txtbox11.setOldString( this.txtbox11.getValue( ) );
			this.txtbox12.setOldString( this.txtbox12.getValue( ) );

			if ( pages.isEmpty( ) ) {
				makePages( );
			} else {
				pages.get( currentpage ).putInt( "button1", this.button1.getState( ) );
				pages.get( currentpage ).putInt( "button2", this.button2.getState( ) );
				pages.get( currentpage ).putInt( "button3", this.button3.getState( ) );
				pages.get( currentpage ).putInt( "button4", this.button4.getState( ) );
				pages.get( currentpage ).putInt( "button5", this.button5.getState( ) );
				pages.get( currentpage ).putInt( "button6", this.button6.getState( ) );
				pages.get( currentpage ).putInt( "button7", this.button7.getState( ) );
				pages.get( currentpage ).putInt( "button8", this.button8.getState( ) );
				pages.get( currentpage ).putInt( "button9", this.button9.getState( ) );
				pages.get( currentpage ).putInt( "button10", this.button10.getState( ) );
				pages.get( currentpage ).putInt( "button11", this.button11.getState( ) );
				pages.get( currentpage ).putInt( "button12", this.button12.getState( ) );
				pages.get( currentpage ).putString( "txtbox1", this.txtbox1.getValue( ) );
				pages.get( currentpage ).putString( "txtbox2", this.txtbox2.getValue( ) );
				pages.get( currentpage ).putString( "txtbox3", this.txtbox3.getValue( ) );
				pages.get( currentpage ).putString( "txtbox4", this.txtbox4.getValue( ) );
				pages.get( currentpage ).putString( "txtbox5", this.txtbox5.getValue( ) );
				pages.get( currentpage ).putString( "txtbox6", this.txtbox6.getValue( ) );
				pages.get( currentpage ).putString( "txtbox7", this.txtbox7.getValue( ) );
				pages.get( currentpage ).putString( "txtbox8", this.txtbox8.getValue( ) );
				pages.get( currentpage ).putString( "txtbox9", this.txtbox9.getValue( ) );
				pages.get( currentpage ).putString( "txtbox10", this.txtbox10.getValue( ) );
				pages.get( currentpage ).putString( "txtbox11", this.txtbox11.getValue( ) );
				pages.get( currentpage ).putString( "txtbox12", this.txtbox12.getValue( ) );
			}
		}
	}

	private void makePages ( ) {

		if ( this.nbt == null ) {

			for ( int r = 0; r < maxpages + 1; r++ ) {
				CompoundTag nbt = new CompoundTag( );
				nbt.putInt( "button1", this.button1.getState( ) );
				nbt.putInt( "button2", this.button2.getState( ) );
				nbt.putInt( "button3", this.button3.getState( ) );
				nbt.putInt( "button4", this.button4.getState( ) );
				nbt.putInt( "button5", this.button5.getState( ) );
				nbt.putInt( "button6", this.button6.getState( ) );
				nbt.putInt( "button7", this.button7.getState( ) );
				nbt.putInt( "button8", this.button8.getState( ) );
				nbt.putInt( "button9", this.button9.getState( ) );
				nbt.putInt( "button10", this.button10.getState( ) );
				nbt.putInt( "button11", this.button11.getState( ) );
				nbt.putInt( "button12", this.button12.getState( ) );
				nbt.putString( "txtbox1", this.txtbox1.getValue( ) );
				nbt.putString( "txtbox2", this.txtbox2.getValue( ) );
				nbt.putString( "txtbox3", this.txtbox3.getValue( ) );
				nbt.putString( "txtbox4", this.txtbox4.getValue( ) );
				nbt.putString( "txtbox5", this.txtbox5.getValue( ) );
				nbt.putString( "txtbox6", this.txtbox6.getValue( ) );
				nbt.putString( "txtbox7", this.txtbox7.getValue( ) );
				nbt.putString( "txtbox8", this.txtbox8.getValue( ) );
				nbt.putString( "txtbox9", this.txtbox9.getValue( ) );
				nbt.putString( "txtbox10", this.txtbox10.getValue( ) );
				nbt.putString( "txtbox11", this.txtbox11.getValue( ) );
				nbt.putString( "txtbox12", this.txtbox12.getValue( ) );
				pages.add( nbt );
				this.pages.get( r ).putString( "txtbox1", "" );
			}
		}
	}

	private void changePageBack ( ) {

		if ( currentpage > 0 ) {
			currentpage--;
		}

		if ( currentpage < 0 ) {
			currentpage = 0;
		}
		loadPage( );
	}

	private void changePageForward ( ) {

		if ( currentpage < maxpages ) {
			currentpage++;
		}

		if ( currentpage > maxpages ) {
			currentpage = maxpages;
		}
		loadPage( );
	}

	private void configureTxtBox ( EditBox txtEditBox ) {
		txtEditBox.setMaxLength( 21 );
		txtEditBox.setBordered( false );
		txtEditBox.setVisible( true );
		txtEditBox.setTextColor( 0x000000 );
		txtEditBox.setValue( "" );
	}

	private void loadPage ( ) {
		saveValuesNbt( );

		if ( this.pages.size( ) > 0 ) {
			CompoundTag thisnbt = this.pages.get( currentpage );
			this.button1.setState( thisnbt.getInt( "button1" ) );
			this.button2.setState( thisnbt.getInt( "button2" ) );
			this.button3.setState( thisnbt.getInt( "button3" ) );
			this.button4.setState( thisnbt.getInt( "button4" ) );
			this.button5.setState( thisnbt.getInt( "button5" ) );
			this.button6.setState( thisnbt.getInt( "button6" ) );
			this.button7.setState( thisnbt.getInt( "button7" ) );
			this.button8.setState( thisnbt.getInt( "button8" ) );
			this.button9.setState( thisnbt.getInt( "button9" ) );
			this.button10.setState( thisnbt.getInt( "button10" ) );
			this.button11.setState( thisnbt.getInt( "button11" ) );
			this.button12.setState( thisnbt.getInt( "button12" ) );
			this.txtbox1.setValue( thisnbt.getString( "txtbox1" ) );
			this.txtbox2.setValue( thisnbt.getString( "txtbox2" ) );
			this.txtbox3.setValue( thisnbt.getString( "txtbox3" ) );
			this.txtbox4.setValue( thisnbt.getString( "txtbox4" ) );
			this.txtbox5.setValue( thisnbt.getString( "txtbox5" ) );
			this.txtbox6.setValue( thisnbt.getString( "txtbox6" ) );
			this.txtbox7.setValue( thisnbt.getString( "txtbox7" ) );
			this.txtbox8.setValue( thisnbt.getString( "txtbox8" ) );
			this.txtbox9.setValue( thisnbt.getString( "txtbox9" ) );
			this.txtbox10.setValue( thisnbt.getString( "txtbox10" ) );
			this.txtbox11.setValue( thisnbt.getString( "txtbox11" ) );
			this.txtbox12.setValue( thisnbt.getString( "txtbox12" ) );
		}
	}

	private void loadValuesNbt ( ) {

		if ( this.nbt != null ) {

			for ( Tag page : this.nbt.getList( "pages", Tag.TAG_COMPOUND ) ) {
				this.pages.add( ( CompoundTag ) page );
			}

			loadPage( );
		}
	}

	private void saveValuesNbt ( ) {

		CompoundTag copynbt = new CompoundTag( );
		ListTag pagesNBT = new ListTag( );

		if ( this.nbt == null ) {
			this.nbt = new CompoundTag( );
			copynbt.putString( "author", this.minecraft.player.getName( ).getString( ) );
			this.author = copynbt.getString( "author" );
		} else {
			this.nbt = new CompoundTag( );
			copynbt.putString( "author", this.author );
		}

		for ( CompoundTag page : pages ) {
			pagesNBT.add( page );
		}

		copynbt.put( "pages", pagesNBT );
		this.nbt.put( "BlockEntityTag", copynbt );

		MainNetwork.sendToServer( new UpdateClipboardItem( this.nbt ) );
	}

	@Override
	public void render ( PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick ) {
		RenderSystem.colorMask( true, true, true, true );
		RenderSystem.setShaderTexture( 0, GUI );
		int relX = ( this.width - WIDTH ) / 2;
		int relY = ( this.height - HEIGHT ) / 2;
		this.blit( pPoseStack, relX, relY, 0, 0, WIDTH, HEIGHT );
		this.font.draw( pPoseStack, I18n.get( "gui.title" ), ( ( width / 2 ) - this.font.width( "test" ) / 2 ),
				relY + 8, 0x000000 );

		if ( this.nbt != null ) {
			this.font.draw( pPoseStack, I18n.get( "tooltip.usclb.clipboard.tooltip.notesby" ) + this.author,
					relX + OFFSETTEXTAUTHORX, relY + OFFSETTEXTAUTHORY, 0x000000 );
		}
		this.font.draw( pPoseStack, new TextComponent( Integer.toString( currentpage ) ),
				( ( width / 2 ) - this.font.width( "test" ) / 2 ) + OFFSETTEXTPAGES, relY + 9, 0x000000 );
		this.txtbox1.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox2.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox3.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox4.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox5.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox6.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox7.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox8.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox9.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox10.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox11.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		this.txtbox12.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
		super.render( pPoseStack, pMouseX, pMouseY, pPartialTick );
	}

	@Override
	public void resize ( Minecraft pMinecraft, int pWidth, int pHeight ) {
		List< CompoundTag > oldlist = pages;
		super.resize( pMinecraft, pWidth, pHeight );
		pages = oldlist;
		loadPage( );
	}

	@Override
	public void onClose ( ) {
		saveValuesNbt( );
		this.minecraft.keyboardHandler.setSendRepeatsToGui( false );
		super.onClose( );
	}

}
