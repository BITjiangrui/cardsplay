package org.playcards.core.models;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * <pre>
 * ÅÆ×À
 * 
 * </pre>
 */
public class Table {
	public final TableId tableId;
	public final Dealer dealer;
	public Map<Player, PlayerState> players;
	public final int size;
	
	public Table(TableId tableId, Dealer dealer, DealType dealType, int volumn) {
		this.tableId = tableId;
		this.dealer = dealer;
		players = Maps.newConcurrentMap();
		size =volumn;
	}
}
