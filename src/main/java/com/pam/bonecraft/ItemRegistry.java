package com.pam.bonecraft;


import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ItemRegistry {
    public static final HashMap<String, Item> items = new HashMap<>();

    public static Item boneaxeItem;
	public static Item bonehoeItem;
	public static Item bonepickaxeItem;
	public static Item boneshovelItem;
	public static Item boneswordItem;
	
	public static Item bonebootsItem;
	public static Item bonechestItem;
	public static Item bonehelmItem;
	public static Item bonelegsItem;


    public static boolean initialized = false;

    public static void registerItems() {
        registerBoneItems();

        initialized = true;
    }

    private static void registerBoneItems() {
    	boneaxeItem = new ItemPamAxe(ToolMaterial.IRON);
		bonehoeItem = new ItemPamHoe(ToolMaterial.IRON);
		bonepickaxeItem = new ItemPamPickaxe(ToolMaterial.IRON);
		boneshovelItem = new ItemPamShovel(ToolMaterial.IRON);
		boneswordItem = new ItemPamSword(ToolMaterial.IRON);
		
		bonehelmItem = new ItemPamBoneArmor(EntityEquipmentSlot.HEAD);
		bonechestItem = new ItemPamBoneArmor(EntityEquipmentSlot.CHEST);
		bonelegsItem = new ItemPamBoneArmor(EntityEquipmentSlot.LEGS);
		bonebootsItem = new ItemPamBoneArmor(EntityEquipmentSlot.FEET);
	
		boneaxeItem = registerItem(boneaxeItem, "boneaxeItem");
		bonehoeItem = registerItem(bonehoeItem, "bonehoeItem");
		bonepickaxeItem = registerItem(bonepickaxeItem, "bonepickaxeItem");
		boneshovelItem = registerItem(boneshovelItem, "boneshovelItem");
		boneswordItem = registerItem(boneswordItem, "boneswordItem");
		
		bonehelmItem = registerItem(bonehelmItem, "bonehelmItem");
		bonechestItem = registerItem(bonechestItem, "bonechestItem");
		bonelegsItem = registerItem(bonelegsItem, "bonelegsItem");
		bonebootsItem = registerItem(bonebootsItem, "bonebootsItem");
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
