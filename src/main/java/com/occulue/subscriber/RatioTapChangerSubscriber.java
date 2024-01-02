/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.subscriber;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.axonframework.messaging.responsetypes.ResponseTypes;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Component;


import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;

/**
 * Subscriber for RatioTapChanger related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("ratioTapChanger-subscriber")
public class RatioTapChangerSubscriber extends BaseSubscriber {

	public RatioTapChangerSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<RatioTapChanger>, RatioTapChanger> ratioTapChangerSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllRatioTapChangerQuery(), 
                		ResponseTypes.multipleInstancesOf(RatioTapChanger.class),
                		ResponseTypes.instanceOf(RatioTapChanger.class));
    }

    public SubscriptionQueryResult<RatioTapChanger, RatioTapChanger> ratioTapChangerSubscribe(@DestinationVariable UUID ratioTapChangerId) {
        return queryGateway
                .subscriptionQuery(new FindRatioTapChangerQuery(new LoadRatioTapChangerFilter(ratioTapChangerId)), 
                		ResponseTypes.instanceOf(RatioTapChanger.class),
                		ResponseTypes.instanceOf(RatioTapChanger.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

