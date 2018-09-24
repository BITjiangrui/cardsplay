package com.cardsplay.core.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.onosproject.ovsdb.controller.EventSubject;
import org.onosproject.ovsdb.controller.OvsdbEvent;
import org.onosproject.ovsdb.controller.OvsdbEventListener;
import org.onosproject.ovsdb.controller.OvsdbEventSubject;
import org.onosproject.ovsdb.provider.host.DefaultHostDescription;
import org.onosproject.ovsdb.provider.host.DeviceId;
import org.onosproject.ovsdb.provider.host.HostDescription;
import org.onosproject.ovsdb.provider.host.HostId;
import org.onosproject.ovsdb.provider.host.HostLocation;
import org.onosproject.ovsdb.provider.host.PortNumber;
import org.onosproject.ovsdb.provider.host.SparseAnnotations;

import com.cardsplay.core.api.Event;
import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.EventRegistryService;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;

public class PlayerManager implements PlayerService, EventRegistryService {
    Map<PlayerId, Player> PlayerStore;
    protected Set<EventListener> eventListener = new CopyOnWriteArraySet<>();



    @Override
    public void activate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub

    }


    @Override
    public void addEventListener(EventListener listener) {
        if (!eventListener.contains(listener)) {
            this.eventListener.add(listener);
        }
    }

    @Override
    public void removeEventListener(EventListener listener) {
        this.eventListener.remove(listener);
    }

    @Override
    public void playerOnline(PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerOffline(PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerIsReady(PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerUndoReady(PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getPlayerInRoom(RoomId room) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getPlayerInTable(TableId table) {
        // TODO Auto-generated method stub
        
    }

}
