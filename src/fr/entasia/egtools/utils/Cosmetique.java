package fr.entasia.egtools.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Cosmetique {
    DEAD_FIRE(101,new ItemStack(Material.FLINT_AND_STEEL), "§cFeu", CosmType.DEAD, 1000){
        @Override
        public void enable(Player p) {

        }

        @Override
        public void disable(Player p) {

        }


    },

    DEAD_WATER(102, new ItemStack(Material.WATER_BUCKET), "§3Eau", CosmType.DEAD, 1000){
        @Override
        public void enable(Player p) {

        }

        @Override
        public void disable(Player p) {

        }
    },

    PET_SHEEP(201, new ItemStack(Material.WOOL), "§7Mouton", CosmType.PET, 1500){
        @Override
        public void enable(Player p) {

        }

        @Override
        public void disable(Player p) {

        }
    },

    PET_HORSE(202, new ItemStack(Material.HAY_BLOCK), "§eCheval", CosmType.PET, 2600){
        @Override
        public void enable(Player p) {

        }

        @Override
        public void disable(Player p) {

        }
    },

    MOVE_MAGIC(301, new ItemStack(Material.ENCHANTMENT_TABLE), "§7Magie", CosmType.MOVE,3200){
        @Override
        public void enable(Player p) {

        }

        @Override
        public void disable(Player p) {

        }

    },

    SUIT_ANGEL(401, new ItemStack(Material.ELYTRA), "§7Ange", CosmType.SUIT,3650){
        @Override
        public void enable(Player p) {

        }

        @Override
        public void disable(Player p) {

        }
    };




    public abstract void enable(Player p);
    public abstract void disable(Player p);




    public static Cosmetique getByID(int id){
        for(Cosmetique c : Cosmetique.values()){
            if(c.id==id)return c;
        }
        return null;
    }
    public static ItemStack getItemStack(int id){
        for(Cosmetique c : Cosmetique.values()){
            if(c.id==id)return c.itemStack;
        }
        return null;
    }
    public CosmType type;
    public int id;
    public ItemStack itemStack;
    public int price;
    public String nom;

    Cosmetique(int id, ItemStack item, String nom, CosmType type, int price){
        this.price = price;
        this.type = type;
        this.id = id;
        this.itemStack = item;
        this.nom = nom;
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(nom);
        itemStack.setItemMeta(meta);

    }
}
