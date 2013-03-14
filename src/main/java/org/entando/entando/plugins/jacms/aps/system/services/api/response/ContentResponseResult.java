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
package org.entando.entando.plugins.jacms.aps.system.services.api.response;

import javax.xml.bind.annotation.XmlElement;

import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;

import org.entando.entando.plugins.jacms.aps.system.services.api.model.JAXBContent;

/**
 * @author E.Santoboni
 */
public class ContentResponseResult extends AbstractApiResponseResult {
    
    @Override
    @XmlElement(name = "item", required = false)
    public JAXBContent getResult() {
        return (JAXBContent) this.getMainResult();
    }
    
}