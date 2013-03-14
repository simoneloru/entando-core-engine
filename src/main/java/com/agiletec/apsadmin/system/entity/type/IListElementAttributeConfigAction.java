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
package com.agiletec.apsadmin.system.entity.type;

/**
 * @author E.Santoboni
 */
public interface IListElementAttributeConfigAction {
	
	public String configureListElement();
	
	public String saveListElement();
	
	public static final String LIST_ATTRIBUTE_ON_EDIT_SESSION_PARAM = "listAttributeAttributeOnEdit_sessionParam";
	
	public static final String LIST_ELEMENT_ON_EDIT_SESSION_PARAM = "listElementAttributeOnEdit_sessionParam";
	
}
