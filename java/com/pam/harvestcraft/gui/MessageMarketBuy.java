package com.pam.harvestcraft.gui;

import com.pam.harvestcraft.tileentity.TileEntityMarket;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageMarketBuy implements IMessage, IMessageHandler<MessageMarketBuy, IMessage>
{
  private int itemNum;
  private int x;
  private int y;
  private int z;
  private boolean shouldClear;
  
  public MessageMarketBuy() {}
  
  public MessageMarketBuy(int itemNum, int x, int y, int z, boolean shouldClear)
  {
/* 23 */     this.itemNum = itemNum;
/* 24 */     this.x = x;
/* 25 */     this.y = y;
/* 26 */     this.z = z;
/* 27 */     this.shouldClear = shouldClear;
  }
  

  public void fromBytes(ByteBuf buf)
  {
/* 33 */     this.itemNum = buf.readInt();
/* 34 */     this.x = buf.readInt();
/* 35 */     this.y = buf.readInt();
/* 36 */     this.z = buf.readInt();
/* 37 */     this.shouldClear = buf.readBoolean();
  }
  

  public void toBytes(ByteBuf buf)
  {
/* 43 */     buf.writeInt(this.itemNum);
/* 44 */     buf.writeInt(this.x);
/* 45 */     buf.writeInt(this.y);
/* 46 */     buf.writeInt(this.z);
/* 47 */     buf.writeBoolean(this.shouldClear);
  }
  

  public IMessage onMessage(MessageMarketBuy message, MessageContext ctx)
  {
/* 53 */     EntityPlayerMP player = ctx.getServerHandler().playerEntity;
    
/* 55 */     net.minecraft.tileentity.TileEntity tile_entity = player.worldObj.getTileEntity(message.x, message.y, message.z);
/* 56 */     if ((tile_entity instanceof TileEntityMarket))
    {
/* 58 */       TileEntityMarket tileEntityMarket = (TileEntityMarket)tile_entity;
/* 59 */       MarketData data = MarketItems.getData(message.itemNum);
/* 60 */       int price = data.getPrice();
      
/* 62 */       if (message.shouldClear)
      {
/* 64 */         tileEntityMarket.clearInventory();
      }
      else
      {
/* 68 */         tileEntityMarket.takeEmeraldFromSlot(price);
      }
      
/* 71 */       EntityItem var14 = new EntityItem(player.worldObj, player.posX, player.posY + 1.0D, player.posZ, data.getItem().copy());
/* 72 */       player.worldObj.spawnEntityInWorld(var14);
    }
/* 74 */     return null;
  }
}


/* Location:              C:\Users\Modding\Desktop\Pam's HarvestCraft 1.7.10k.deobf.jar!\com\pam\harvestcraft\MessageMarketBuy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */