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
package com.agiletec.plugins.jacms.apsadmin.content;

import java.util.List;
import java.util.Map;

import org.entando.entando.aps.system.services.page.IPage;

import com.agiletec.plugins.jacms.apsadmin.content.util.AbstractBaseTestContentAction;
import com.agiletec.aps.system.RequestContext;
import com.agiletec.aps.system.SystemConstants;
import com.agiletec.aps.system.common.entity.model.attribute.ITextAttribute;
import com.agiletec.aps.system.services.group.Group;
import com.agiletec.apsadmin.system.ApsAdminSystemConstants;
import com.agiletec.plugins.jacms.aps.system.services.content.model.Content;
import com.opensymphony.xwork2.Action;

/**
 * @author E.Santoboni
 */
public class TestContentPreviewAction extends AbstractBaseTestContentAction {
	
	public void testPreviewNewContent() throws Throwable {
		String insertedDescr = "XXX Prova preview XXX";
		String contentTypeCode = "ART";
		Content prototype = this.getContentManager().createContentType(contentTypeCode);
		String contentOnSessionMarker = AbstractContentAction.buildContentOnSessionMarker(prototype, ApsAdminSystemConstants.ADD);
		
		String result = this.executeCreateNewVoid(contentTypeCode, insertedDescr, Content.STATUS_DRAFT,  Group.FREE_GROUP_NAME, "admin");
		assertEquals(Action.SUCCESS, result);
		
		Content content = this.getContentOnEdit(contentOnSessionMarker);
		ITextAttribute titleAttribute = (ITextAttribute) content.getAttribute("Titolo");
		assertNull(titleAttribute.getTextForLang("it"));
		assertEquals(content.getDescr(), insertedDescr);
		
		this.initContentAction("/do/jacms/Content", "preview", contentOnSessionMarker);
		this.addParameter("it_Titolo", "Nuovo titolo di prova");
		result = this.executeAction();
		assertEquals(Action.SUCCESS, result);
		
		content = this.getContentOnEdit(contentOnSessionMarker);
		titleAttribute = (ITextAttribute) content.getAttribute("Titolo");
		assertEquals("Nuovo titolo di prova", titleAttribute.getTextForLang("it"));
	}
	
	public void testPreviewContent() throws Throwable {
		String contentId = "EVN192";
		Content contentForTest = this.getContentManager().loadContent(contentId, true);
		String contentOnSessionMarker = AbstractContentAction.buildContentOnSessionMarker(contentForTest, ApsAdminSystemConstants.EDIT);
		
		String result = this.executeEdit(contentId, "admin");
		assertEquals(Action.SUCCESS, result);
		
		Content content = this.getContentOnEdit(contentOnSessionMarker);
		ITextAttribute titleAttribute = (ITextAttribute) content.getAttribute("Titolo");
		assertEquals("Titolo B - Evento 2", titleAttribute.getTextForLang("it"));
		
		this.initContentAction("/do/jacms/Content", "preview", contentOnSessionMarker);
		this.addParameter("mainGroup", Group.FREE_GROUP_NAME);
		this.addParameter("it_Titolo", "Nuovo titolo di prova");
		result = this.executeAction();
		assertEquals(Action.SUCCESS, result);
		
		content = this.getContentOnEdit(contentOnSessionMarker);
		titleAttribute = (ITextAttribute) content.getAttribute("Titolo");
		assertEquals("Nuovo titolo di prova", titleAttribute.getTextForLang("it"));
	}
	
	public void testExecutePreviewContent_1() throws Throwable {
		String contentId = "EVN192";
		Content contentForTest = this.getContentManager().loadContent(contentId, true);
		String contentOnSessionMarker = AbstractContentAction.buildContentOnSessionMarker(contentForTest, ApsAdminSystemConstants.EDIT);
		
		String result = this.executeEdit(contentId, "admin");
		assertEquals(Action.SUCCESS, result);
		
		result = this.executePreviewPage("", contentOnSessionMarker);
		assertEquals(Action.SUCCESS, result);
		
		RequestContext reqCtx = (RequestContext) this.getRequest().getAttribute(RequestContext.REQCTX);
		assertNotNull(reqCtx);
		IPage currentPage = (IPage) reqCtx.getExtraParam(SystemConstants.EXTRAPAR_CURRENT_PAGE);
		assertEquals("contentview", currentPage.getCode());
	}
	
	public void testExecutePreviewContent_2() throws Throwable {
		String contentId = "ART187";
		Content contentForTest = this.getContentManager().loadContent(contentId, true);
		String contentOnSessionMarker = AbstractContentAction.buildContentOnSessionMarker(contentForTest, ApsAdminSystemConstants.EDIT);
		
		String result = this.executeEdit(contentId, "admin");
		assertEquals(Action.SUCCESS, result);
		
		result = this.executePreviewPage(null, contentOnSessionMarker);
		assertEquals(Action.SUCCESS, result);
		
		RequestContext reqCtx = (RequestContext) this.getRequest().getAttribute(RequestContext.REQCTX);
		assertNotNull(reqCtx);
		IPage currentPage = (IPage) reqCtx.getExtraParam(SystemConstants.EXTRAPAR_CURRENT_PAGE);
		assertEquals("contentview", currentPage.getCode());
	}
	
	public void testExecutePreviewContent_3() throws Throwable {
		String contentId = "ART187";
		Content contentForTest = this.getContentManager().loadContent(contentId, true);
		String contentOnSessionMarker = AbstractContentAction.buildContentOnSessionMarker(contentForTest, ApsAdminSystemConstants.EDIT);
		
		String result = this.executeEdit(contentId, "admin");
		assertEquals(Action.SUCCESS, result);
		
		result = this.executePreviewPage("pagina_2", contentOnSessionMarker);
		assertEquals(Action.SUCCESS, result);
		
		RequestContext reqCtx = (RequestContext) this.getRequest().getAttribute(RequestContext.REQCTX);
		assertNotNull(reqCtx);
		IPage currentPage = (IPage) reqCtx.getExtraParam(SystemConstants.EXTRAPAR_CURRENT_PAGE);
		assertEquals("pagina_2", currentPage.getCode());
	}
	
	public void testFailureExecutePreviewContent() throws Throwable {
		String contentId = "ART187";
		Content contentForTest = this.getContentManager().loadContent(contentId, true);
		String contentOnSessionMarker = AbstractContentAction.buildContentOnSessionMarker(contentForTest, ApsAdminSystemConstants.EDIT);
		
		String result = this.executeEdit(contentId, "admin");
		assertEquals(Action.SUCCESS, result);
		
		result = this.executePreviewPage("wrongPageCode", contentOnSessionMarker);
		assertEquals(Action.INPUT, result);
		
		RequestContext reqCtx = (RequestContext) this.getRequest().getAttribute(RequestContext.REQCTX);
		assertNull(reqCtx);
		Map<String, List<String>> fieldErrors = this.getAction().getFieldErrors();
		assertEquals(1, fieldErrors.size());
		assertEquals(1, fieldErrors.get("previewPageCode").size());
	}
	
	private String executePreviewPage(String pageDest, String contentOnSessionMarker) throws Throwable {
		this.initContentAction("/do/jacms/Content", "executePreview", contentOnSessionMarker);
		this.addParameter("previewPageCode", pageDest);
		return this.executeAction();
	}
	
}
