/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 *                       Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view LICENCE file for details. 
 *
 * @author The Dragonet Team
 */
package org.dragonet.proxy.network.translator.pe;

import com.github.steveice10.packetlib.packet.Packet;
import org.dragonet.proxy.network.UpstreamSession;
import org.dragonet.proxy.network.translator.IPEPacketTranslator;
import org.dragonet.protocol.packets.LevelSoundEventPacketV1;

public class PESoundEventPacketTranslator implements IPEPacketTranslator<LevelSoundEventPacketV1> {

    public Packet[] translate(UpstreamSession session, LevelSoundEventPacketV1 packet) {
//        System.out.println("LevelSoundEventPacketV1" + DebugTools.getAllFields(packet));
        return null;
    }
}
