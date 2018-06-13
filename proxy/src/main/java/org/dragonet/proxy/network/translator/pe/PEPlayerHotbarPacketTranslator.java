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
import org.dragonet.api.sessions.IUpstreamSession;
import org.dragonet.api.translators.IPEPacketTranslator;
import org.dragonet.protocol.packets.PlayerHotbarPacket;

public class PEPlayerHotbarPacketTranslator implements IPEPacketTranslator<PlayerHotbarPacket> {

    public Packet[] translate(IUpstreamSession session, PlayerHotbarPacket packet) {
//        System.out.println("PlayerHotbarPacket" + DebugTools.getAllFields(packet));
        return null;
    }
}
