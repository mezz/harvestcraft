/*    */ package com.pam.harvestcraft.proxy;
import com.pam.harvestcraft.gui.MessageMarketBrowse;
import com.pam.harvestcraft.gui.MessageMarketBuy;
import com.pam.harvestcraft.gui.MessageMarketClosed;


import net.minecraftforge.fml.common.Mod.Instance;

 
public class PacketHandler
{
public static final net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper INSTANCE = net.minecraftforge.fml.common.network.NetworkRegistry.INSTANCE.newSimpleChannel("harvestcraft");

public static void init()
{
Instance.registerMessage(MessageMarketBrowse.class, MessageMarketBrowse.class, 0, Side.SERVER);
Instance.registerMessage(MessageMarketBuy.class, MessageMarketBuy.class, 1, Side.SERVER);
Instance.registerMessage(MessageMarketClosed.class, MessageMarketClosed.class, 2, Side.SERVER);
}
}
