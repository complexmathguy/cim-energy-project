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

public class LineValidator {
		
	/**
	 * default constructor
	 */
	protected LineValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public LineValidator getInstance() {
		return new LineValidator();
	}
		
	/**
	 * handles creation validation for a Line
	 */
	public void validate( CreateLineCommand line )throws Exception {
		Assert.notNull( line, "CreateLineCommand should not be null" );
//		Assert.isNull( line.getLineId(), "CreateLineCommand identifier should be null" );
	}

	/**
	 * handles update validation for a Line
	 */
	public void validate( UpdateLineCommand line ) throws Exception {
		Assert.notNull( line, "UpdateLineCommand should not be null" );
		Assert.notNull( line.getLineId(), "UpdateLineCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a Line
	 */
    public void validate( DeleteLineCommand line ) throws Exception {
		Assert.notNull( line, "{commandAlias} should not be null" );
		Assert.notNull( line.getLineId(), "DeleteLineCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Line
	 */
	public void validate( LineFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "LineFetchOneSummary should not be null" );
		Assert.notNull( summary.getLineId(), "LineFetchOneSummary identifier should not be null" );
	}


	/**
	 * handles add to Lines validation for a Line
	 * 
	 * @param	command AssignLinesToLineCommand
	 */	
	public void validate( AssignLinesToLineCommand command ) throws Exception {
		Assert.notNull( command, "AssignLinesToLineCommand should not be null" );
		Assert.notNull( command.getLineId(), "AssignLinesToLineCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignLinesToLineCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from Lines validation for a Line
	 * 
	 * @param	command RemoveLinesFromLineCommand
	 */	
	public void validate( RemoveLinesFromLineCommand command ) throws Exception {
		Assert.notNull( command, "RemoveLinesFromLineCommand should not be null" );
		Assert.notNull( command.getLineId(), "RemoveLinesFromLineCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveLinesFromLineCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getLineId(), "RemoveLinesFromLineCommand removeFrom attribubte identifier should not be null" );
	}
	

}
