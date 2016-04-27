package com.pam.harvestcraft.proxy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMarket
  extends AbstractPacket
{
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {}
  
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {}
  
  public void handleClientSide(EntityPlayer player) {}
  
  public void handleServerSide(EntityPlayer player) {}
}


/* Location:              C:\Users\Modding\Desktop\Pam's HarvestCraft 1.7.10k.deobf.jar!\com\pam\harvestcraft\PacketMarket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */