package com.pam.harvestcraft.gui;

import com.pam.harvestcraft.tileentity.TileEntityMarket;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageMarketBrowse implements IMessage, IMessageHandler<MessageMarketBrowse, IMessage>
{
  private int itemNum;
  private int x;
  private int y;
  private int z;
  
  public MessageMarketBrowse() {}
  
  public MessageMarketBrowse(int itemNum, int x, int y, int z)
  {
/* 24 */     this.itemNum = itemNum;
/* 25 */     this.x = x;
/* 26 */     this.y = y;
/* 27 */     this.z = z;
  }
  

  public void fromBytes(ByteBuf buf)
  {
/* 33 */     this.itemNum = buf.readInt();
/* 34 */     this.x = buf.readInt();
/* 35 */     this.y = buf.readInt();
/* 36 */     this.z = buf.readInt();
  }
  

  public void toBytes(ByteBuf buf)
  {
/* 42 */     buf.writeInt(this.itemNum);
/* 43 */     buf.writeInt(this.x);
/* 44 */     buf.writeInt(this.y);
/* 45 */     buf.writeInt(this.z);
  }
  

  public IMessage onMessage(MessageMarketBrowse message, MessageContext ctx)
  {
/* 51 */     EntityPlayerMP player = ctx.getServerHandler().playerEntity;
    
/* 53 */     TileEntity tile_entity = player.worldObj.getTileEntity(message.x, message.y, message.z);
/* 54 */     if ((tile_entity instanceof TileEntityMarket))
    {
/* 56 */       TileEntityMarket tileEntityMarket = (TileEntityMarket)tile_entity;
/* 57 */       tileEntityMarket.setBrowsingInfo(message.itemNum);
    }
/* 59 */     player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
/* 60 */     return null;
  }

}
