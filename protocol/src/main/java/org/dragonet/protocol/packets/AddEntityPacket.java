package org.dragonet.protocol.packets;

import com.google.common.collect.ImmutableMap;

import java.util.Collection;

import org.dragonet.common.data.entity.PEEntityAttribute;
import org.dragonet.common.data.entity.meta.EntityMetaData;
import org.dragonet.common.maths.Vector3F;
import org.dragonet.protocol.PEPacket;
import org.dragonet.protocol.ProtocolInfo;
import org.dragonet.common.data.entity.PEEntityLink;

/**
 * Created on 2017/10/21.
 */
public class AddEntityPacket extends PEPacket {

    public static ImmutableMap<Integer, String> LEGACY_IDS = ImmutableMap.<Integer, String>builder()
            .put(51, "minecraft:npc")
            .put(63, "minecraft:player")
            .put(48, "minecraft:wither_skeleton")
            .put(47, "minecraft:husk")
            .put(46, "minecraft:stray")
            .put(45, "minecraft:witch")
            .put(44, "minecraft:zombie_villager")
            .put(43, "minecraft:blaze")
            .put(42, "minecraft:magma_cube")
            .put(41, "minecraft:ghast")
            .put(40, "minecraft:cave_spider")
            .put(39, "minecraft:silverfish")
            .put(38, "minecraft:enderman")
            .put(37, "minecraft:slime")
            .put(36, "minecraft:zombie_pigman")
            .put(35, "minecraft:spider")
            .put(34, "minecraft:skeleton")
            .put(33, "minecraft:creeper")
            .put(32, "minecraft:zombie")
            .put(26, "minecraft:skeleton_horse")
            .put(25, "minecraft:mule")
            .put(24, "minecraft:donkey")
            .put(31, "minecraft:dolphin")
            .put(111, "minecraft:tropicalfish")
            .put(14, "minecraft:wolf")
            .put(17, "minecraft:squid")
            .put(110, "minecraft:drowned")
            .put(13, "minecraft:sheep")
            .put(16, "minecraft:mooshroom")
            .put(113, "minecraft:panda")
            .put(109, "minecraft:salmon")
            .put(12, "minecraft:pig")
            .put(15, "minecraft:villager")
            .put(112, "minecraft:cod")
            .put(108, "minecraft:pufferfish")
            .put(11, "minecraft:cow")
            .put(10, "minecraft:chicken")
            .put(107, "minecraft:balloon")
            .put(29, "minecraft:llama")
            .put(20, "minecraft:iron_golem")
            .put(18, "minecraft:rabbit")
            .put(21, "minecraft:snow_golem")
            .put(19, "minecraft:bat")
            .put(22, "minecraft:ocelot")
            .put(23, "minecraft:horse")
            .put(75, "minecraft:cat")
            .put(28, "minecraft:polar_bear")
            .put(27, "minecraft:zombie_horse")
            .put(74, "minecraft:turtle")
            .put(30, "minecraft:parrot")
            .put(49, "minecraft:guardian")
            .put(50, "minecraft:elder_guardian")
            .put(57, "minecraft:vindicator")
            .put(52, "minecraft:wither")
            .put(53, "minecraft:ender_dragon")
            .put(54, "minecraft:shulker")
            .put(55, "minecraft:endermite")
            .put(84, "minecraft:minecart")
            .put(96, "minecraft:hopper_minecart")
            .put(97, "minecraft:tnt_minecart")
            .put(98, "minecraft:chest_minecart")
            .put(100, "minecraft:command_block_minecart")
            .put(61, "minecraft:armor_stand")
            .put(64, "minecraft:item")
            .put(65, "minecraft:tnt")
            .put(66, "minecraft:falling_block")
            .put(68, "minecraft:xp_bottle")
            .put(69, "minecraft:xp_orb")
            .put(70, "minecraft:eye_of_ender_signal")
            .put(71, "minecraft:ender_crystal")
            .put(76, "minecraft:shulker_bullet")
            .put(77, "minecraft:fishing_hook")
            .put(79, "minecraft:dragon_fireball")
            .put(80, "minecraft:arrow")
            .put(81, "minecraft:snowball")
            .put(82, "minecraft:egg")
            .put(83, "minecraft:painting")
            .put(73, "minecraft:thrown_trident")
            .put(85, "minecraft:fireball")
            .put(86, "minecraft:splash_potion")
            .put(87, "minecraft:ender_pearl")
            .put(88, "minecraft:leash_knot")
            .put(89, "minecraft:wither_skull")
            .put(91, "minecraft:wither_skull_dangerous")
            .put(90, "minecraft:boat")
            .put(93, "minecraft:lightning_bolt")
            .put(94, "minecraft:small_fireball")
            .put(102, "minecraft:llama_spit")
            .put(95, "minecraft:area_effect_cloud")
            .put(101, "minecraft:lingering_potion")
            .put(72, "minecraft:fireworks_rocket")
            .put(103, "minecraft:evocation_fang")
            .put(104, "minecraft:evocation_illager")
            .put(105, "minecraft:vex")
            .put(56, "minecraft:agent")
            .put(106, "minecraft:ice_bomb")
            .put(58, "minecraft:phantom")
            .put(62, "minecraft:tripod_camera")
            .build();

    public long eid;
    public long rtid;
    public int type;
    public String id;
    public Vector3F position;
    public Vector3F motion;
    public float pitch;
    public float yaw;
    public Collection<PEEntityAttribute> attributes;
    public EntityMetaData meta;
    public PEEntityLink[] links;

    public AddEntityPacket() {

    }

    @Override
    public int pid() {
        return ProtocolInfo.ADD_ENTITY_PACKET;
    }

    @Override
    public void encodePayload() {
        putVarLong(eid);
        putUnsignedVarLong(rtid);
        if (id == null) {
            id = LEGACY_IDS.get(type);
        }
        putString(this.id);
        putUnsignedVarInt(type);
        putVector3F(position);
        putVector3F(motion);
        putLFloat(pitch);
        putLFloat(yaw);

        if (attributes != null && attributes.size() > 0) {
            putUnsignedVarInt(attributes.size());
            for (PEEntityAttribute attr : attributes) {
                putString(attr.name);
                putLFloat(attr.min);
                putLFloat(attr.currentValue);
                putLFloat(attr.max);
            }
        } else {
            putUnsignedVarInt(0);
        }

        if (meta != null) {
            meta.encode();
            put(meta.getBuffer());
        } else {
            putUnsignedVarInt(0);
        }

        if (links != null && links.length > 0) {
            putUnsignedVarInt(links.length);
            for (PEEntityLink l : links) {
                putEntityLink(l);
            }
        } else {
            putUnsignedVarInt(0);
        }
    }

    /**
     * decode will NOT work since meta decoding is not implemented
     */
    @Override
    public void decodePayload() {
//        eid = getVarLong();
//        rtid = getUnsignedVarLong();
//        type = (int) getUnsignedVarInt();
//        position = getVector3F();
//        motion = getVector3F();
//        pitch = getLFloat();
//        yaw = getLFloat();
//
//        int lenAttr = (int) getUnsignedVarInt();

//        attributes = new PEEntityAttribute[lenAttr];
//        if (lenAttr > 0) {
//            for (int i = 0; i < lenAttr; i++) {
//                String name = getString();
//                float min = getLFloat();
//                float current = getLFloat();
//                float max = getLFloat();
//                attributes[i] = PEEntityAttribute.findAttribute(name);
//                if (attributes[i] != null) {
//                    attributes[i].min = min;
//                    attributes[i].max = max;
//                    attributes[i].currentValue = current;
//                }
//            }
//        }

        // TODO: read meta!!
//        int lenLinks = (int) getUnsignedVarInt();
//        links = new PEEntityLink[lenLinks];
//        if (lenLinks > 0) {
//            for (int i = 0; i < lenLinks; i++) {
//                links[i] = getEntityLink();
//            }
//        }
    }
}
