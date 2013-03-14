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
package org.entando.entando.plugins.jacms.apsadmin.content;

import com.agiletec.aps.system.common.entity.model.AttributeTracer;
import com.agiletec.aps.system.common.entity.model.attribute.AttributeInterface;
import com.agiletec.aps.system.common.entity.model.attribute.CompositeAttribute;
import com.agiletec.aps.system.common.entity.model.attribute.ListAttribute;
import com.agiletec.aps.system.common.entity.model.attribute.MonoListAttribute;
import com.agiletec.plugins.jacms.aps.system.services.content.model.Content;
import com.opensymphony.xwork2.Action;

/**
 * @author E.Santoboni
 */
public class TestValidateBooleanAttributes extends AbstractTestContentAttribute {
	
	public void testValidateSingleBoolean() throws Throwable {
		this.validateSingle("Boolean");
	}
	
	public void testValidateSingleCheckBox() throws Throwable {
		this.validateSingle("CheckBox");
	}
	
	public void testValidateSingleThreeState() throws Throwable {
		this.validateSingle("ThreeState");
	}
	
	protected void validateSingle(String attributeName) throws Throwable {
		try {
			Content content = this.executeCreateNewContent();
			AttributeTracer tracer = this.getTracer();
			AttributeInterface booleanAttribute = (AttributeInterface) content.getAttribute(attributeName);
			String formFieldName = tracer.getFormFieldName(booleanAttribute);
			
			this.initSaveContentAction();
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldName, "true");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldName, "false");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
		} catch (Throwable t) {
			this.deleteTestContent();
			throw t;
		}
	}
	
	public void testValidateBooleanMonolist() throws Throwable {
		this.validateMonolistElement("MonoLBool");
	}
	
	public void testValidateCheckBoxMonolist() throws Throwable {
		this.validateMonolistElement("MonoLChec");
	}
	
	public void testValidateThreeStateMonolist() throws Throwable {
		this.validateMonolistElement("MonoL3stat");
	}
	
	protected void validateMonolistElement(String monolistAttributeName) throws Throwable {
		try {
			Content content = this.executeCreateNewContent();
			AttributeTracer tracer = this.getTracer();
			MonoListAttribute monolist = (MonoListAttribute) content.getAttribute(monolistAttributeName);
			AttributeInterface attribute = monolist.addAttribute();
			
			tracer.setListIndex(monolist.getAttributes().size() - 1);
			tracer.setListLang(this.getLangManager().getDefaultLang());
			tracer.setMonoListElement(true);
			
			String formFieldName = tracer.getFormFieldName(attribute);
			assertEquals(monolistAttributeName + "_0", formFieldName);
			
			this.initSaveContentAction();
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldName, "true");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldName, "false");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
			AttributeInterface attribute2 = monolist.addAttribute();
			tracer.setListIndex(monolist.getAttributes().size() - 1);
			String formFieldName2 = tracer.getFormFieldName(attribute2);
			assertEquals(monolistAttributeName + "_1", formFieldName2);
			
			this.initSaveContentAction();
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName2);
			
		} catch (Throwable t) {
			this.deleteTestContent();
			throw t;
		}
	}
	
	public void testValidateBooleanList() throws Throwable {
		this.validateListElement("ListBoolea");
	}
	
	public void testValidateCheckBoxList() throws Throwable {
		this.validateListElement("ListCheck");
	}
	
	public void testValidateThreeStateList() throws Throwable {
		this.validateListElement("List3Stat");
	}
	
	protected void validateListElement(String listAttributeName) throws Throwable {
		try {
			Content content = this.executeCreateNewContent();
			AttributeTracer tracerIT = this.getTracer();
			ListAttribute list = (ListAttribute) content.getAttribute(listAttributeName);
			AttributeInterface attributeIT = list.addAttribute("it");
			assertEquals(0, list.getAttributeList("en").size());
			assertEquals(1, list.getAttributeList("it").size());
			
			tracerIT.setListIndex(list.getAttributeList("it").size() - 1);
			tracerIT.setListLang(this.getLangManager().getLang("it"));
			tracerIT.setListElement(true);
			
			String formFieldItName = tracerIT.getFormFieldName(attributeIT);
			assertEquals("it_"+ listAttributeName + "_0", formFieldItName);
			
			AttributeTracer tracerEN = tracerIT.clone();
			tracerEN.setLang(this.getLangManager().getLang("en"));
			tracerEN.setListLang(this.getLangManager().getLang("en"));
			
			this.initSaveContentAction();
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldItName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldItName, "true");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldItName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldItName, "false");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldItName);
			
			AttributeInterface attribute2 = list.addAttribute("it");
			tracerIT.setListIndex(list.getAttributes().size() - 1);
			formFieldItName = tracerIT.getFormFieldName(attribute2);
			assertEquals("it_"+ listAttributeName + "_1", formFieldItName);
			
			this.initSaveContentAction();
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldItName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldItName, "true");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldItName);
			
			AttributeInterface attributeEN = list.addAttribute("en");
			String formFieldEnName = tracerEN.getFormFieldName(attributeEN);
			assertEquals("en_"+ listAttributeName + "_0", formFieldEnName);
			
			this.initSaveContentAction();
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldEnName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldEnName, "true");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldEnName);
			
		} catch (Throwable t) {
			this.deleteTestContent();
			throw t;
		}
	}
	
	public void testValidateBooleanCompositeElement() throws Throwable {
		this.validateCompositeElement("Composite", "Boolean");
	}
	
	public void testValidateCheckBoxCompositeElement() throws Throwable {
		this.validateCompositeElement("Composite", "CheckBox");
	}
	
	public void testValidateThreeStateCompositeElement() throws Throwable {
		this.validateCompositeElement("Composite", "ThreeState");
	}
	
	protected void validateCompositeElement(String compositeAttributeName, String attributeName) throws Throwable {
		try {
			Content content = this.executeCreateNewContent();
			AttributeTracer tracer = this.getTracer();
			CompositeAttribute compositeAttribute = (CompositeAttribute) content.getAttribute(compositeAttributeName);
			AttributeInterface attribute = compositeAttribute.getAttribute(attributeName);
			
			tracer.setCompositeElement(true);
			tracer.setParentAttribute(compositeAttribute);
			
			String formFieldName = tracer.getFormFieldName(attribute);
			assertEquals(compositeAttributeName + "_" + attributeName, formFieldName);
			
			this.initSaveContentAction();
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldName, "true");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldName, "false");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
		} catch (Throwable t) {
			this.deleteTestContent();
			throw t;
		}
	}
	
	public void testValidateBooleanMonolistCompositeElement() throws Throwable {
		this.validateMonolistCompositeElement("MonoLCom2", "Boolean");
	}
	
	public void testValidateCheckBoxMonolistCompositeElement() throws Throwable {
		this.validateMonolistCompositeElement("MonoLCom2", "CheckBox");
	}
	
	public void testValidateThreeStateMonolistCompositeElement() throws Throwable {
		this.validateMonolistCompositeElement("MonoLCom2", "ThreeState");
	}
	
	protected void validateMonolistCompositeElement(String monolistAttributeName, String attributeName) throws Throwable {
		try {
			Content content = this.executeCreateNewContent();
			AttributeTracer tracer = this.getTracer();
			MonoListAttribute monolist = (MonoListAttribute) content.getAttribute(monolistAttributeName);
			CompositeAttribute compositeElement = (CompositeAttribute) monolist.addAttribute();
			AttributeInterface attribute = compositeElement.getAttribute(attributeName);
			
			tracer.setListIndex(monolist.getAttributes().size() - 1);
			tracer.setListLang(this.getLangManager().getDefaultLang());
			tracer.setMonoListElement(true);
			tracer.setCompositeElement(true);
			tracer.setParentAttribute(compositeElement);
			
			String formFieldName = tracer.getFormFieldName(attribute);
			assertEquals(monolistAttributeName + "_" + attributeName + "_0", formFieldName);
			
			String monolistElementName = tracer.getMonolistElementFieldName(compositeElement);
			assertEquals(monolistAttributeName + "_0", monolistElementName);
			
			this.initSaveContentAction();
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, monolistElementName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldName, "true");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
			
			this.initSaveContentAction();
			this.addParameter(formFieldName, "false");
			this.executeAction(Action.INPUT);
			this.checkFieldErrors(0, formFieldName);
		} catch (Throwable t) {
			this.deleteTestContent();
			throw t;
		}
	}
	
}