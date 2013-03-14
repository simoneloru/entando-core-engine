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
package com.agiletec.aps.system.common.entity.loader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;

import com.agiletec.aps.system.ApsSystemUtils;
import com.agiletec.aps.system.common.entity.IEntityManager;
import com.agiletec.aps.system.common.entity.model.attribute.AttributeRole;
import com.agiletec.aps.system.common.entity.parse.AttributeRoleDOM;
import com.agiletec.aps.util.FileTextReader;

/**
 * The Class loader of the extra attribute roles.
 * @author E.Santoboni
 */
public class AttributeRolesLoader {
	
	public Map<String, AttributeRole> extractAttributeRoles(String attributeRolesFileName, BeanFactory beanFactory, IEntityManager entityManager) {
		Map<String, AttributeRole> attributeRoles = new HashMap<String, AttributeRole>();
		try {
			this.setEntityManager(entityManager);
			this.setBeanFactory(beanFactory);
			this.loadDefaultRoles(attributeRolesFileName, attributeRoles);
			this.loadExtraRoles(attributeRoles);
		} catch (Throwable t) {
			ApsSystemUtils.logThrowable(t, this, "extractAttributeRoles", "Error loading attribute Roles");
		}
		return attributeRoles;
	}
	
	private void loadDefaultRoles(String attributeRolesFileName, Map<String, AttributeRole> attributeRoles) {
		try {
			String xml = this.extractConfigFile(attributeRolesFileName);
			if (null != xml) {
				AttributeRoleDOM dom = new AttributeRoleDOM();
				attributeRoles.putAll(dom.extractRoles(xml, attributeRolesFileName));
			}
		} catch (Throwable t) {
			ApsSystemUtils.logThrowable(t, this, "loadDefaultRoles", "Error loading attribute Roles : file " + attributeRolesFileName);
		}
	}
	
	private void loadExtraRoles(Map<String, AttributeRole> attributeRoles) {
		try {
			ListableBeanFactory factory = (ListableBeanFactory) this.getBeanFactory();
			String[] defNames = factory.getBeanNamesForType(ExtraAttributeRolesWrapper.class);
			for (int i=0; i<defNames.length; i++) {
				try {
					Object loader = this.getBeanFactory().getBean(defNames[i]);
					if (loader != null) {
						((ExtraAttributeRolesWrapper) loader).executeLoading(attributeRoles, this.getEntityManager());
					}
				} catch (Throwable t) {
					ApsSystemUtils.logThrowable(t, this, "loadExtraRoles", "Error extracting attribute support object : bean " + defNames[i]);
				}
			}
		} catch (Throwable t) {
			ApsSystemUtils.logThrowable(t, this, "loadExtraRoles", "Error loading attribute support object");
		}
	}
	
	private String extractConfigFile(String fileName) throws Throwable {
		InputStream is = this.getEntityManager().getClass().getResourceAsStream(fileName);
		if (null == is) {
			ApsSystemUtils.getLogger().config(this.getEntityManager().getClass().getName() + ": there isn't any object to load : file " + fileName);
			return null;
		}
		return FileTextReader.getText(is);
	}
	
	protected IEntityManager getEntityManager() {
		return _entityManager;
	}
	protected void setEntityManager(IEntityManager entityManager) {
		this._entityManager = entityManager;
	}
	
	protected BeanFactory getBeanFactory() {
		return _beanFactory;
	}
	protected void setBeanFactory(BeanFactory beanFactory) {
		this._beanFactory = beanFactory;
	}
	
	private IEntityManager _entityManager;
	private BeanFactory _beanFactory;
	
}