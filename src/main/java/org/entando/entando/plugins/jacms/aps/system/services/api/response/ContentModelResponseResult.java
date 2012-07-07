/*
*
* Copyright 2012 Entando S.r.l. (http://www.entando.com) All rights reserved.
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
* Copyright 2012 Entando S.r.l. (http://www.entando.com) All rights reserved.
*
*/
package org.entando.entando.plugins.jacms.aps.system.services.api.response;

import com.agiletec.plugins.jacms.aps.system.services.contentmodel.ContentModel;
import javax.xml.bind.annotation.XmlElement;
import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;

/**
 * @author E.Santoboni
 */
public class ContentModelResponseResult extends AbstractApiResponseResult {
    
    @Override
    @XmlElement(name = "contentModel", required = false)
    public ContentModel getResult() {
        return (ContentModel) this.getMainResult();
    }
	
}