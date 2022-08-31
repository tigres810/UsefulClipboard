package com.tigres810.usclb.client.screens;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class CustomEditBox extends EditBox {
	
	private String oldstring = "";

	public CustomEditBox ( Font pFont, int pX, int pY, int pWidth, int pHeight, Component pMessage ) {
		super( pFont, pX, pY, pWidth, pHeight, pMessage );
	}

	@Override
	public void renderButton ( PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick ) {

		if ( this.isVisible( ) ) {

			if ( this.isBordered( ) ) {
				int i = this.isFocused( ) ? -1 : -6250336;
				fill( pPoseStack, this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, i );
				fill( pPoseStack, this.x, this.y, this.x + this.width, this.y + this.height, -16777216 );
			}

			int i2 = this.isEditable ? this.textColor : this.textColorUneditable;
			int j = this.cursorPos - this.displayPos;
			int k = this.highlightPos - this.displayPos;
			String s = this.font.plainSubstrByWidth( this.getValue( ).substring( this.displayPos ), this.getInnerWidth( ) );
			boolean flag = j >= 0 && j <= s.length( );
			boolean flag1 = this.isFocused( ) && this.frame / 6 % 2 == 0 && flag;
			int l = this.isBordered( ) ? this.x + 4 : this.x;
			int i1 = this.isBordered( ) ? this.y + ( this.height - 8 ) / 2 : this.y;
			int j1 = l;

			if ( k > s.length( ) ) {
				k = s.length( );
			}

			if ( !s.isEmpty( ) ) {
				String s1 = flag ? s.substring( 0, j ) : s;
				j1 = this.font.draw( pPoseStack, this.formatter.apply( s1, this.displayPos ), ( float ) l,
						( float ) i1, i2 );
			}

			boolean flag2 = this.cursorPos < this.getValue( ).length( ) || this.getValue( ).length( ) >= this.getMaxLength( );
			int k1 = j1;

			if ( !flag ) {
				k1 = j > 0 ? l + this.width : l;
			} else if ( flag2 ) {
				k1 = j1 - 1;
				--j1;
			}

			if ( !s.isEmpty( ) && flag && j < s.length( ) ) {
				this.font.draw( pPoseStack, this.formatter.apply( s.substring( j ), this.cursorPos ),
						( float ) j1, ( float ) i1, i2 );
			}

			if ( !flag2 && this.suggestion != null ) {
				this.font.draw( pPoseStack, this.suggestion, ( float ) ( k1 - 1 ), ( float ) i1, -8355712 );
			}

			if ( flag1 ) {

				if ( flag2 ) {
					GuiComponent.fill( pPoseStack, k1, i1 - 1, k1 + 1, i1 + 1 + 9, -3092272 );
				} else {
					this.font.draw( pPoseStack, "_", ( float ) k1, ( float ) i1, i2 );
				}
			}

			if ( k != j ) {
				int l1 = l + this.font.width( s.substring( 0, k ) );
				this.renderHighlight( k1, i1 - 1, l1 - 1, i1 + 1 + 9 );
			}

		}
	}
	
	public String getOldString() {
		return oldstring;
	}
	
	public void setOldString(String newstring) {
		this.oldstring = newstring;
	}

}
