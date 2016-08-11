package com.pam.desertcraft;


import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ItemRegistry {
    public static final HashMap<String, Item> items = new HashMap<>();

    public static ItemArmor.ArmorMaterial armorCactus = EnumHelper.addArmorMaterial("CACTUS", "desertcraft:cactus", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
	
    public static Item cactusaxeItem;
	public static Item cactushoeItem;
	public static Item cactuspickaxeItem;
	public static Item cactusshovelItem;
	public static Item cactusswordItem;

	public static Item cactusbootsItem;
	public static Item cactuschestItem;
	public static Item cactushelmItem;
	public static Item cactuslegsItem;
	
	public static Item sandstoneaxeItem;
	public static Item sandstonehoeItem;
	public static Item sandstonepickaxeItem;
	public static Item sandstoneshovelItem;
	public static Item sandstoneswordItem;

	public static Item glasssteelingotItem;
	public static Item glasssteelaxeItem;
	public static Item glasssteelhoeItem;
	public static Item glasssteelpickaxeItem;
	public static Item glasssteelshovelItem;
	public static Item glasssteelswordItem;
	
    public static boolean initialized = false;

    public static void registerItems() {
        registerCactusItems();
        registerSandstoneItems();
        registerGlasssteelItems();

        initialized = true;
    }

    private static void registerCactusItems() {
    	cactusaxeItem = new ItemPamAxe(ToolMaterial.WOOD);
		cactushoeItem = new ItemPamHoe(ToolMaterial.WOOD);
		cactuspickaxeItem = new ItemPamPickaxe(ToolMaterial.WOOD);
		cactusshovelItem = new ItemPamShovel(ToolMaterial.WOOD);
		cactusswordItem = new ItemPamSword(ToolMaterial.WOOD);
		
		cactushelmItem = new ItemPamCactusArmor(EntityEquipmentSlot.HEAD);
		cactuschestItem = new ItemPamCactusArmor(EntityEquipmentSlot.CHEST);
		cactuslegsItem = new ItemPamCactusArmor(EntityEquipmentSlot.LEGS);
		cactusbootsItem = new ItemPamCactusArmor(EntityEquipmentSlot.FEET);
	
		cactusaxeItem = registerItem(cactusaxeItem, "cactusaxeItem");
		cactushoeItem = registerItem(cactushoeItem, "cactushoeItem");
		cactuspickaxeItem = registerItem(cactuspickaxeItem, "cactuspickaxeItem");
		cactusshovelItem = registerItem(cactusshovelItem, "cactusshovelItem");
		cactusswordItem = registerItem(cactusswordItem, "cactusswordItem");
		
		cactushelmItem = registerItem(cactushelmItem, "cactushelmItem");
		cactuschestItem = registerItem(cactuschestItem, "cactuschestItem");
		cactuslegsItem = registerItem(cactuslegsItem, "cactuslegsItem");
		cactusbootsItem = registerItem(cactusbootsItem, "cactusbootsItem");
    }

    private static void registerSandstoneItems() {
    	sandstoneaxeItem = new ItemPamAxe(ToolMaterial.WOOD);
		sandstonehoeItem = new ItemPamHoe(ToolMaterial.WOOD);
		sandstonepickaxeItem = new ItemPamPickaxe(ToolMaterial.WOOD);
		sandstoneshovelItem = new ItemPamShovel(ToolMaterial.WOOD);
		sandstoneswordItem = new ItemPamSword(ToolMaterial.WOOD);
	
		sandstoneaxeItem = registerItem(sandstoneaxeItem, "sandstoneaxeItem");
		sandstonehoeItem = registerItem(sandstonehoeItem, "sandstonehoeItem");
		sandstonepickaxeItem = registerItem(sandstonepickaxeItem, "sandstonepickaxeItem");
		sandstoneshovelItem = registerItem(sandstoneshovelItem, "sandstoneshovelItem");
		sandstoneswordItem = registerItem(sandstoneswordItem, "sandstoneswordItem");
    }
    
    private static void registerGlasssteelItems() {
    	glasssteelingotItem = new ItemPamDesert();
    	glasssteelaxeItem = new ItemPamAxe(ToolMaterial.WOOD);
		glasssteelhoeItem = new ItemPamHoe(ToolMaterial.WOOD);
		glasssteelpickaxeItem = new ItemPamPickaxe(ToolMaterial.WOOD);
		glasssteelshovelItem = new ItemPamShovel(ToolMaterial.WOOD);
		glasssteelswordItem = new ItemPamSword(ToolMaterial.WOOD);
	
		glasssteelingotItem = registerItem(glasssteelingotItem, "glasssteelingotItem");
		glasssteelaxeItem = registerItem(glasssteelaxeItem, "glasssteelaxeItem");
		glasssteelhoeItem = registerItem(glasssteelhoeItem, "glasssteelhoeItem");
		glasssteelpickaxeItem = registerItem(glasssteelpickaxeItem, "glasssteelpickaxeItem");
		glasssteelshovelItem = registerItem(glasssteelshovelItem, "glasssteelshovelItem");
		glasssteelswordItem = registerItem(glasssteelswordItem, "glasssteelswordItem");
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