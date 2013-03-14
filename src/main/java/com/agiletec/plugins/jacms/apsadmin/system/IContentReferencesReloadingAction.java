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
package com.agiletec.plugins.jacms.apsadmin.system;

import com.agiletec.apsadmin.system.entity.IEntityReferencesReloadingAction;

/**
 * @author E.Santoboni
 */
public interface IContentReferencesReloadingAction extends IEntityReferencesReloadingAction {
	
	public String reloadContentsIndexes();
	
}
