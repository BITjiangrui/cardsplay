package com.cardsplay.core.api;

import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.TableId;

public interface DealerService extends LifeCycleService{

     Dealer getDealerForTable(TableId tableId);
     
}
