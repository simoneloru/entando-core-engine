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
package org.entando.entando.aps.system.services.oauth.model;

import java.util.Date;

/**
 * @author E.Santoboni
 */
public class ConsumerRecordVO {
    
    public String getKey() {
        return _key;
    }
    public void setKey(String key) {
        this._key = key;
    }
    
    public String getSecret() {
        return _secret;
    }
    public void setSecret(String secret) {
        this._secret = secret;
    }
    
    public String getDescription() {
        return _description;
    }
    public void setDescription(String description) {
        this._description = description;
    }
    
    public String getCallbackUrl() {
        return _callbackUrl;
    }
    public void setCallbackUrl(String callbackUrl) {
        this._callbackUrl = callbackUrl;
    }
    
    public Date getExpirationDate() {
        return _expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this._expirationDate = expirationDate;
    }
    
    private String _key;
    private String _secret;
    private String _description;
    private String _callbackUrl;
    private Date _expirationDate;
    
}