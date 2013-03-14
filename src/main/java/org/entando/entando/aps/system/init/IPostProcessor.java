/*
*
* Copyright 2013 Entando S.r.l. (http://www.entando.com) All rights reserved.
*
* This file is part of Entando Enterprise Edition software.
* You can redistribute it and/or modify it
* under the terms of the Entando's EULA
* 
* See the file License for the specific language governing permissions   
* and limitations under the License
* 
* 
* 
* Copyright 2013 Entando S.r.l. (http://www.entando.com) All rights reserved.
*
*/
package org.entando.entando.aps.system.init;

import com.agiletec.aps.system.exception.ApsSystemException;
import org.entando.entando.aps.system.init.model.IPostProcess;
import org.entando.entando.aps.system.init.model.InvalidPostProcessResultException;

/**
 * @author E.Santoboni
 */
public interface IPostProcessor {
	
	public int executePostProcess(IPostProcess postProcess) throws InvalidPostProcessResultException, ApsSystemException;
	
}
