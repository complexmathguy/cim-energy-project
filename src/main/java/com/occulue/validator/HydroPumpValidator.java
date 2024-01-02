/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.validator;

import org.springframework.util.Assert;

import com.occulue.api.*;

public class HydroPumpValidator {
		
	/**
	 * default constructor
	 */
	protected HydroPumpValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public HydroPumpValidator getInstance() {
		return new HydroPumpValidator();
	}
		
	/**
	 * handles creation validation for a HydroPump
	 */
	public void validate( CreateHydroPumpCommand hydroPump )throws Exception {
		Assert.notNull( hydroPump, "CreateHydroPumpCommand should not be null" );
//		Assert.isNull( hydroPump.getHydroPumpId(), "CreateHydroPumpCommand identifier should be null" );
	}

	/**
	 * handles update validation for a HydroPump
	 */
	public void validate( UpdateHydroPumpCommand hydroPump ) throws Exception {
		Assert.notNull( hydroPump, "UpdateHydroPumpCommand should not be null" );
		Assert.notNull( hydroPump.getHydroPumpId(), "UpdateHydroPumpCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a HydroPump
	 */
    public void validate( DeleteHydroPumpCommand hydroPump ) throws Exception {
		Assert.notNull( hydroPump, "{commandAlias} should not be null" );
		Assert.notNull( hydroPump.getHydroPumpId(), "DeleteHydroPumpCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a HydroPump
	 */
	public void validate( HydroPumpFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "HydroPumpFetchOneSummary should not be null" );
		Assert.notNull( summary.getHydroPumpId(), "HydroPumpFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign HydroPump validation for a HydroPump
	 * 
	 * @param	command AssignHydroPumpToHydroPumpCommand
	 */	
	public void validate( AssignHydroPumpToHydroPumpCommand command ) throws Exception {
		Assert.notNull( command, "AssignHydroPumpToHydroPumpCommand should not be null" );
		Assert.notNull( command.getHydroPumpId(), "AssignHydroPumpToHydroPumpCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignHydroPumpToHydroPumpCommand assignment should not be null" );
	}

	/**
	 * handles unassign HydroPump validation for a HydroPump
	 * 
	 * @param	command UnAssignHydroPumpFromHydroPumpCommand
	 */	
	public void validate( UnAssignHydroPumpFromHydroPumpCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignHydroPumpFromHydroPumpCommand should not be null" );
		Assert.notNull( command.getHydroPumpId(), "UnAssignHydroPumpFromHydroPumpCommand identifier should not be null" );
	}

	/**
	 * handles add to HydroPumps validation for a HydroPump
	 * 
	 * @param	command AssignHydroPumpsToHydroPumpCommand
	 */	
	public void validate( AssignHydroPumpsToHydroPumpCommand command ) throws Exception {
		Assert.notNull( command, "AssignHydroPumpsToHydroPumpCommand should not be null" );
		Assert.notNull( command.getHydroPumpId(), "AssignHydroPumpsToHydroPumpCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignHydroPumpsToHydroPumpCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from HydroPumps validation for a HydroPump
	 * 
	 * @param	command RemoveHydroPumpsFromHydroPumpCommand
	 */	
	public void validate( RemoveHydroPumpsFromHydroPumpCommand command ) throws Exception {
		Assert.notNull( command, "RemoveHydroPumpsFromHydroPumpCommand should not be null" );
		Assert.notNull( command.getHydroPumpId(), "RemoveHydroPumpsFromHydroPumpCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveHydroPumpsFromHydroPumpCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getHydroPumpId(), "RemoveHydroPumpsFromHydroPumpCommand removeFrom attribubte identifier should not be null" );
	}
	

}
