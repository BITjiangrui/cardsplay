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
	public Set<PlayerId> playerIds;
	public final int size;
	
	public Table(TableId tableId, Dealer dealer, int volumn) {
		this.tableId = tableId;
		this.dealer = dealer;
		playerIds = Sets.newConcurrentHashSet();
		size =volumn;
	}
}
