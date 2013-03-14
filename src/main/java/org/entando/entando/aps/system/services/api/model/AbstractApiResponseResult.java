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
package org.entando.entando.aps.system.services.api.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author E.Santoboni
 */
public abstract class AbstractApiResponseResult implements Serializable {
    
    public abstract Object getResult();
    
    protected Object getMainResult() {
        return this._mainResult;
    }
    public void setMainResult(Object mainResult) {
        this._mainResult = mainResult;
    }
    
    public void setHtml(String html) {
        if (null == html) {
            html = "";
        }
        this._html = html;
    }
    
    private Object _mainResult;
    
    @XmlJavaTypeAdapter(CDataXmlTypeAdapter.class)
    @XmlElement(name = "html", required = true)
    private String _html;
    
}