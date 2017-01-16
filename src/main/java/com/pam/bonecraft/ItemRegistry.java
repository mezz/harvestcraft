package com.pam.bonecraft;


import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ItemRegistry {
    public static final HashMap<String, Item> items = new HashMap<String, Item>();

    public static Item boneaxeitem;
	public static Item bonehoeitem;
	public static Item bonepickaxeitem;
	public static Item boneshovelitem;
	public static Item bonesworditem;
	
	public static Item bonebootsitem;
	public static Item bonechestitem;
	public static Item bonehelmitem;
	public static Item bonelegsitem;


    public static boolean initialized = false;

    public static void registerItems() {
        registerBoneItems();

        initialized = true;
    }

    private static void registerBoneItems() {
    	boneaxeitem = new ItemPamAxe(ToolMaterial.IRON);
		bonehoeitem = new ItemPamHoe(ToolMaterial.IRON);
		bonepickaxeitem = new ItemPamPickaxe(ToolMaterial.IRON);
		boneshovelitem = new ItemPamShovel(ToolMaterial.IRON);
		bonesworditem = new ItemPamSword(ToolMaterial.IRON);
		
		bonehelmitem = new ItemPamBoneArmor(EntityEquipmentSlot.HEAD);
		bonechestitem = new ItemPamBoneArmor(EntityEquipmentSlot.CHEST);
		bonelegsitem = new ItemPamBoneArmor(EntityEquipmentSlot.LEGS);
		bonebootsitem = new ItemPamBoneArmor(EntityEquipmentSlot.FEET);
	
		boneaxeitem = registerItem(boneaxeitem, "boneaxeitem");
		bonehoeitem = registerItem(bonehoeitem, "bonehoeitem");
		bonepickaxeitem = registerItem(bonepickaxeitem, "bonepickaxeitem");
		boneshovelitem = registerItem(boneshovelitem, "boneshovelitem");
		bonesworditem = registerItem(bonesworditem, "bonesworditem");
		
		bonehelmitem = registerItem(bonehelmitem, "bonehelmitem");
		bonechestitem = registerItem(bonechestitem, "bonechestitem");
		bonelegsitem = registerItem(bonelegsitem, "bonelegsitem");
		bonebootsitem = registerItem(bonebootsitem, "bonebootsitem");
    }

    



    private static Item registerGenericItem(String registryName) {
        final Item item = new Item();

        return registerItem(item, registryName);
    }


    public static Item registerItem(Item item, String registryName) {
        item.setCreativeTab(bonecraft.tabBonecraft);
        item.setRegistryName(registryName);
        item.setUnlocalizedName(registryName);

        items.put(registryName, item);

        return GameRegistry.register(item);
    }
}
