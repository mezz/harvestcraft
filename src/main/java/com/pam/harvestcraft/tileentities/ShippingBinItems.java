package com.pam.harvestcraft.tileentities;

import com.pam.harvestcraft.HarvestCraft;
import com.pam.harvestcraft.blocks.CropRegistry;
import com.pam.harvestcraft.config.ConfigHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ShippingBinItems {

    private enum CurrencyType {
        DEFAULT, SEEDS, SAPLING, ANIMAL
    }

    private static final ArrayList<ShippingBinData> items = new ArrayList<ShippingBinData>();

    private static void registerItems(ShippingBinData data) {
        items.add(data);
    }

    public static ShippingBinData getData(int i) {
        return items.get(i);
    }

    public static int getSize() {
        return items.size();
    }

    public static void registerItems() {
    	
        if (HarvestCraft.config.shippingbinbuyCrops) {
            registerCrops();
        }

    }

   
    private static void registerCrops() {
        final ItemStack currency = getCurrency(HarvestCraft.config.marketcurrencySeeds, CurrencyType.SEEDS);

        for (Item crop : CropRegistry.getFoods().values()) {
            registerItems(new ShippingBinData(currency, new ItemStack(crop), HarvestCraft.config.shippingbincropPrice));

        }
        
        if (ConfigHandler.shippingbinenablevanillaMCCrops)
        {
        	registerItems(new ShippingBinData(currency, new ItemStack(Items.WHEAT), HarvestCraft.config.shippingbincropPrice));
        	registerItems(new ShippingBinData(currency, new ItemStack(Blocks.PUMPKIN), HarvestCraft.config.shippingbincropPrice));
        	registerItems(new ShippingBinData(currency, new ItemStack(Items.MELON), HarvestCraft.config.shippingbincropPrice));
        	registerItems(new ShippingBinData(currency, new ItemStack(Items.BEETROOT), HarvestCraft.config.shippingbincropPrice));
        	registerItems(new ShippingBinData(currency, new ItemStack(Items.CARROT), HarvestCraft.config.shippingbincropPrice));
        	registerItems(new ShippingBinData(currency, new ItemStack(Items.POTATO), HarvestCraft.config.shippingbincropPrice));
        	registerItems(new ShippingBinData(currency, new ItemStack(Items.DYE, 1, 3), HarvestCraft.config.shippingbincropPrice));
        }

    }
    
    private static ItemStack getCurrency(int config, CurrencyType currencyType) {
        switch (config) {
            case 1:
                return new ItemStack(Items.DIAMOND);
            case 2:
                return new ItemStack(Items.GOLD_INGOT);
            case 3:
                return new ItemStack(Items.GOLD_NUGGET);
            case 4:
                return new ItemStack(Items.IRON_INGOT);
            case 5:
                if (currencyType.equals(CurrencyType.ANIMAL))
                    return new ItemStack(Items.EGG);
                if (currencyType.equals(CurrencyType.SEEDS))
                    return new ItemStack(Items.WHEAT_SEEDS);
                if (currencyType.equals(CurrencyType.SAPLING))
                    return new ItemStack(Blocks.SAPLING);
                else
                    return null;
            case 6:
                return new ItemStack(Items.APPLE);
            case 7:
                return new ItemStack(Items.DYE);
            case 0:
            default:
                return new ItemStack(Items.EMERALD);
        }
    }

}
