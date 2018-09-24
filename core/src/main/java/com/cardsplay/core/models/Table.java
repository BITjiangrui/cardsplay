package com.cardsplay.core.models;

import java.util.Set;
import com.google.common.collect.Sets;

/**
 * <pre>
 * ÅÆ×À
 * 
 * </pre>
 */
public class Table {
	public final TableId tableId;
	public final Dealer dealer;
	public Set<Player> players;
	public final int size;
	
	public Table(TableId tableId, Dealer dealer, DealType dealType, int volumn) {
		this.tableId = tableId;
		this.dealer = dealer;
		players = Sets.newConcurrentHashSet();
		size =volumn;
	}
}
