package com.tigres810.usclb.common.data;

import com.google.common.base.Preconditions;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.TextComponent;

public final class Page
{
    public static final int LINE_COUNT = 12;
    public static final int BTN_STATE_COUNT = 3;

    private final TextComponent[] lines;
    private final byte[] buttons;

    private Page(TextComponent[] lines, byte[] buttons)
    {
        Preconditions.checkArgument(lines.length == LINE_COUNT);
        this.lines = lines;
        this.buttons = buttons;
    }

    public void setLine(int idx, String text)
    {
        Preconditions.checkArgument(idx >= 0 && idx < LINE_COUNT);
        Preconditions.checkNotNull(text);
        lines[idx] = new TextComponent(text);
    }

    public void setButton(int idx, byte state)
    {
        Preconditions.checkArgument(idx >= 0 && idx < LINE_COUNT);
        Preconditions.checkArgument(state >= 0 && state < BTN_STATE_COUNT, "Invalid button state");
        buttons[idx] = state;
    }

    public TextComponent line(int idx)
    {
        Preconditions.checkArgument(idx >= 0 && idx < LINE_COUNT);

        TextComponent line = lines[idx];
        return line != null ? line : (TextComponent) TextComponent.EMPTY;
    }

    public byte button(int idx)
    {
        Preconditions.checkArgument(idx >= 0 && idx < LINE_COUNT);
        return buttons[idx];
    }

    public Tag serialize()
    {
        ListTag listTag = new ListTag();

        for (int i = 0; i < LINE_COUNT; i++)
        {
            CompoundTag tag = new CompoundTag();
            tag.putString("text", line(i).getText());
            tag.putByte("button", button(i));
            listTag.add(tag);
        }

        return listTag;
    }

    public static Page deserialize(ListTag listTag)
    {
        TextComponent[] lines = new TextComponent[LINE_COUNT];
        byte[] buttons = new byte[LINE_COUNT];

        for (int i = 0; i < listTag.size(); i++)
        {
            CompoundTag tag = listTag.getCompound(i);
            lines[i] = new TextComponent(tag.getString("text"));
            buttons[i] = tag.getByte("button");
        }

        return new Page(lines, buttons);
    }

    public static Page emptyPage()
    {
        return new Page(new TextComponent[LINE_COUNT], new byte[LINE_COUNT]);
    }
}