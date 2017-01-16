package com.pam.desertcraft;


import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ItemRegistry {
    public static final HashMap<String, Item> items = new HashMap<String, Item>();

    public static ItemArmor.ArmorMaterial armorCactus = EnumHelper.addArmorMaterial("CACTUS", "desertcraft:cactus", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
	
    public static Item cactusaxeitem;
	public static Item cactushoeitem;
	public static Item cactuspickaxeitem;
	public static Item cactusshovelitem;
	public static Item cactussworditem;

	public static Item cactusbootsitem;
	public static Item cactuschestitem;
	public static Item cactushelmitem;
	public static Item cactuslegsitem;
	
	public static Item sandstoneaxeitem;
	public static Item sandstonehoeitem;
	public static Item sandstonepickaxeitem;
	public static Item sandstoneshovelitem;
	public static Item sandstonesworditem;

	public static Item glasssteelingotitem;
	public static Item glasssteelaxeitem;
	public static Item glasssteelhoeitem;
	public static Item glasssteelpickaxeitem;
	public static Item glasssteelshovelitem;
	public static Item glasssteelsworditem;
	
    public static boolean initialized = false;

    public static void registerItems() {
        registerCactusItems();
        registerSandstoneItems();
        registerGlasssteelItems();

        initialized = true;
    }

    private static void registerCactusItems() {
    	cactusaxeitem = new ItemPamAxe(ToolMaterial.WOOD);
		cactushoeitem = new ItemPamHoe(ToolMaterial.WOOD);
		cactuspickaxeitem = new ItemPamPickaxe(ToolMaterial.WOOD);
		cactusshovelitem = new ItemPamShovel(ToolMaterial.WOOD);
		cactussworditem = new ItemPamSword(ToolMaterial.WOOD);
		
		cactushelmitem = new ItemPamCactusArmor(EntityEquipmentSlot.HEAD);
		cactuschestitem = new ItemPamCactusArmor(EntityEquipmentSlot.CHEST);
		cactuslegsitem = new ItemPamCactusArmor(EntityEquipmentSlot.LEGS);
		cactusbootsitem = new ItemPamCactusArmor(EntityEquipmentSlot.FEET);
	
		cactusaxeitem = registerItem(cactusaxeitem, "cactusaxeitem");
		cactushoeitem = registerItem(cactushoeitem, "cactushoeitem");
		cactuspickaxeitem = registerItem(cactuspickaxeitem, "cactuspickaxeitem");
		cactusshovelitem = registerItem(cactusshovelitem, "cactusshovelitem");
		cactussworditem = registerItem(cactussworditem, "cactussworditem");
		
		cactushelmitem = registerItem(cactushelmitem, "cactushelmitem");
		cactuschestitem = registerItem(cactuschestitem, "cactuschestitem");
		cactuslegsitem = registerItem(cactuslegsitem, "cactuslegsitem");
		cactusbootsitem = registerItem(cactusbootsitem, "cactusbootsitem");
    }

    private static void registerSandstoneItems() {
    	sandstoneaxeitem = new ItemPamAxe(ToolMaterial.WOOD);
		sandstonehoeitem = new ItemPamHoe(ToolMaterial.WOOD);
		sandstonepickaxeitem = new ItemPamPickaxe(ToolMaterial.WOOD);
		sandstoneshovelitem = new ItemPamShovel(ToolMaterial.WOOD);
		sandstonesworditem = new ItemPamSword(ToolMaterial.WOOD);
	
		sandstoneaxeitem = registerItem(sandstoneaxeitem, "sandstoneaxeitem");
		sandstonehoeitem = registerItem(sandstonehoeitem, "sandstonehoeitem");
		sandstonepickaxeitem = registerItem(sandstonepickaxeitem, "sandstonepickaxeitem");
		sandstoneshovelitem = registerItem(sandstoneshovelitem, "sandstoneshovelitem");
		sandstonesworditem = registerItem(sandstonesworditem, "sandstonesworditem");
    }
    
    private static void registerGlasssteelItems() {
    	glasssteelingotitem = new ItemPamDesert();
    	glasssteelaxeitem = new ItemPamAxe(ToolMaterial.WOOD);
		glasssteelhoeitem = new ItemPamHoe(ToolMaterial.WOOD);
		glasssteelpickaxeitem = new ItemPamPickaxe(ToolMaterial.WOOD);
		glasssteelshovelitem = new ItemPamShovel(ToolMaterial.WOOD);
		glasssteelsworditem = new ItemPamSword(ToolMaterial.WOOD);
	
		glasssteelingotitem = registerItem(glasssteelingotitem, "glasssteelingotitem");
		glasssteelaxeitem = registerItem(glasssteelaxeitem, "glasssteelaxeitem");
		glasssteelhoeitem = registerItem(glasssteelhoeitem, "glasssteelhoeitem");
		glasssteelpickaxeitem = registerItem(glasssteelpickaxeitem, "glasssteelpickaxeitem");
		glasssteelshovelitem = registerItem(glasssteelshovelitem, "glasssteelshovelitem");
		glasssteelsworditem = registerItem(glasssteelsworditem, "glasssteelsworditem");
    }



    private static Item registerGenericItem(String registryName) {
        final Item item = new Item();

        return registerItem(item, registryName);
    }


    public static Item registerItem(Item item, String registryName) {
        item.setCreativeTab(desertcraft.tabDesertcraft);
        item.setRegistryName(registryName);
        item.setUnlocalizedName(registryName);

        items.put(registryName, item);

        return GameRegistry.register(item);
    }
    
    private static ItemArmor.ArmorMaterial addArmorMaterial(String enumName, String textureName, int durability, int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness) {
		return EnumHelper.addEnum(ItemArmor.ArmorMaterial.class, enumName, new Class<?>[]{String.class, int.class, int[].class, int.class, SoundEvent.class, float.class}, textureName, durability, reductionAmounts, enchantability, soundOnEquip, toughness);
	}
}