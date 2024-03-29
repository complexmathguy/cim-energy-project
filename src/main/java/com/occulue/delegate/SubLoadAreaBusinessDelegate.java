/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.delegate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.util.concurrent.*;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.validator.*;

/**
 * SubLoadArea business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of SubLoadArea related services in the case of a SubLoadArea business related service failing.</li>
 * <li>Exposes a simpler, uniform SubLoadArea interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill SubLoadArea business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class SubLoadAreaBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public SubLoadAreaBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* SubLoadArea Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	SubLoadAreaBusinessDelegate
	*/
	public static SubLoadAreaBusinessDelegate getSubLoadAreaInstance() {
		return( new SubLoadAreaBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createSubLoadArea( CreateSubLoadAreaCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getSubLoadAreaId() == null )
				command.setSubLoadAreaId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	SubLoadAreaValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateSubLoadAreaCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateSubLoadAreaCommand of SubLoadArea is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create SubLoadArea - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateSubLoadAreaCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateSubLoadArea( UpdateSubLoadAreaCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	SubLoadAreaValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateSubLoadAreaCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save SubLoadArea - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteSubLoadAreaCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteSubLoadAreaCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	SubLoadAreaValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteSubLoadAreaCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete SubLoadArea using Id = "  + command.getSubLoadAreaId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the SubLoadArea via SubLoadAreaFetchOneSummary
     * @param 	summary SubLoadAreaFetchOneSummary 
     * @return 	SubLoadAreaFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public SubLoadArea getSubLoadArea( SubLoadAreaFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "SubLoadAreaFetchOneSummary arg cannot be null" );
    	
    	SubLoadArea entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	SubLoadAreaValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a SubLoadArea
        	// --------------------------------------
        	CompletableFuture<SubLoadArea> futureEntity = queryGateway.query(new FindSubLoadAreaQuery( new LoadSubLoadAreaFilter( summary.getSubLoadAreaId() ) ), ResponseTypes.instanceOf(SubLoadArea.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate SubLoadArea with id " + summary.getSubLoadAreaId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all SubLoadAreas
     *
     * @return 	List<SubLoadArea> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<SubLoadArea> getAllSubLoadArea() 
    throws ProcessingException {
        List<SubLoadArea> list = null;

        try {
        	CompletableFuture<List<SubLoadArea>> futureList = queryGateway.query(new FindAllSubLoadAreaQuery(), ResponseTypes.multipleInstancesOf(SubLoadArea.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all SubLoadArea";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }


    /**
     * add SubLoadArea to SubLoadAreas 
     * @param		command AssignSubLoadAreasToSubLoadAreaCommand
     * @exception	ProcessingException
     */     
	public void addToSubLoadAreas( AssignSubLoadAreasToSubLoadAreaCommand command ) throws ProcessingException {
		
		
		// -------------------------------------------
		// load the parent
		// -------------------------------------------
		load( command.getSubLoadAreaId() );

		SubLoadAreaBusinessDelegate childDelegate 	= SubLoadAreaBusinessDelegate.getSubLoadAreaInstance();
		SubLoadAreaBusinessDelegate parentDelegate = SubLoadAreaBusinessDelegate.getSubLoadAreaInstance();		
		UUID childId = command.getAddTo().getSubLoadAreaId();
		
		try {		
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	SubLoadAreaValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );			
		}
		catch( Exception exc ) {
			final String msg = "Failed to add a SubLoadArea as SubLoadAreas to SubLoadArea" ; 
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}

	}

    /**
     * remove SubLoadArea from SubLoadAreas
     * @param		command RemoveSubLoadAreasFromSubLoadAreaCommand
     * @exception	ProcessingException
     */     	
	public void removeFromSubLoadAreas( RemoveSubLoadAreasFromSubLoadAreaCommand command ) throws ProcessingException {		
		
		SubLoadAreaBusinessDelegate childDelegate 	= SubLoadAreaBusinessDelegate.getSubLoadAreaInstance();
		UUID childId = command.getRemoveFrom().getSubLoadAreaId();

		try {
			
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	SubLoadAreaValidator.getInstance().validate( command );    

	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );

		}
		catch( Exception exc ) {
			final String msg = "Failed to remove child using Id " + childId; 
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		SubLoadArea
	 */
	protected SubLoadArea load( UUID id ) throws ProcessingException {
		subLoadArea = SubLoadAreaBusinessDelegate.getSubLoadAreaInstance().getSubLoadArea( new SubLoadAreaFetchOneSummary(id) );	
		return subLoadArea;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private SubLoadArea subLoadArea 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(SubLoadAreaBusinessDelegate.class.getName());
    
}
