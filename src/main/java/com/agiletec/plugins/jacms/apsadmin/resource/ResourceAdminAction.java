/*
*
* Copyright 2013 Entando S.r.l. (http://www.entando.com) All rights reserved.
*
* This file is part of Entando software.
* Entando is a free software; 
* you can redistribute it and/or modify it
* under the terms of the GNU General Public License (GPL) as published by the Free Software Foundation; version 2.
* 
* See the file License for the specific language governing permissions   
* and limitations under the License
* 
* 
* 
* Copyright 2013 Entando S.r.l. (http://www.entando.com) All rights reserved.
*
*/
package com.agiletec.plugins.jacms.apsadmin.resource;

import com.agiletec.aps.system.ApsSystemUtils;

/**
 * Action delegated to execute administrative tasks on resources
 * @author E.Santoboni
 */
public class ResourceAdminAction extends AbstractResourceAction implements IResourceAdminAction {
	
	@Override
	public String refreshResourcesInstances() {
		try {
			this.getResourceManager().refreshResourcesInstances(this.getResourceTypeCode());
			ApsSystemUtils.getLogger().info("Refreshing started");
		} catch (Throwable t) {
			ApsSystemUtils.logThrowable(t, this, "refreshResourcesInstances");
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public int getResourceManagerStatus() {
		return this.getResourceManager().getStatus();
	}
	
}