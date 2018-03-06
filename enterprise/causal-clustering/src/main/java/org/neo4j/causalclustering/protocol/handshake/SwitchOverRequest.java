/*
 * Copyright (c) 2002-2018 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.causalclustering.protocol.handshake;

import java.util.List;
import java.util.Objects;

import org.neo4j.helpers.collection.Pair;

public class SwitchOverRequest implements ServerMessage
{
    private final String protocolName;
    private final int version;
    private final List<Pair<String,Integer>> modifierProtocols;

    public SwitchOverRequest( String applicationProtocolName, int applicationProtocolVersion, List<Pair<String,Integer>> modifierProtocols )
    {
        this.protocolName = applicationProtocolName;
        this.version = applicationProtocolVersion;
        this.modifierProtocols = modifierProtocols;
    }

    @Override
    public void dispatch( ServerMessageHandler handler )
    {
        handler.handle( this );
    }

    public String protocolName()
    {
        return protocolName;
    }

    public List<Pair<String,Integer>> modifierProtocols()
    {
        return modifierProtocols;
    }

    public int version()
    {
        return version;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        SwitchOverRequest that = (SwitchOverRequest) o;
        return version == that.version && Objects.equals( protocolName, that.protocolName ) && Objects.equals( modifierProtocols, that.modifierProtocols );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( protocolName, version, modifierProtocols );
    }

    @Override
    public String toString()
    {
        return "SwitchOverRequest{" + "protocolName='" + protocolName + '\'' + ", version=" + version + ", modifierProtocols=" + modifierProtocols + '}';
    }
}
