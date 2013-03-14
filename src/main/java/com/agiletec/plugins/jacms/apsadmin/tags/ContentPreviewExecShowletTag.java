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
package com.agiletec.plugins.jacms.apsadmin.tags;

import com.agiletec.aps.system.RequestContext;
import com.agiletec.aps.system.SystemConstants;
import com.agiletec.aps.system.services.page.IPage;
import com.agiletec.aps.system.services.page.Showlet;
import com.agiletec.aps.tags.ExecShowletTag;
import com.agiletec.aps.tags.util.IFrameDecoratorContainer;
import com.agiletec.plugins.jacms.aps.system.services.content.model.Content;
import com.agiletec.plugins.jacms.apsadmin.content.ContentActionConstants;
import java.util.List;

/**
 * This tag allows the preliminary execution of the showlet so to show the preview of the contents
 * within the content administration pages in the backend.
 * This tag class extends the {@link ExecShowletTag} class used in the front-end to build the pages of the portal.
 * @author E.Santoboni
 */
public class ContentPreviewExecShowletTag extends ExecShowletTag {
	
	@Override
	protected void includeShowlet(RequestContext reqCtx, Showlet showlet, List<IFrameDecoratorContainer> decorators) throws Throwable {
		Content contentOnSession = (Content) reqCtx.getRequest().getSession().getAttribute(ContentActionConstants.SESSION_PARAM_NAME_CURRENT_CONTENT);
		if (contentOnSession!=null && showlet != null 
				&& "viewerConfig".equals(showlet.getType().getAction())) {
			IPage currentPage = (IPage) reqCtx.getExtraParam(SystemConstants.EXTRAPAR_CURRENT_PAGE);
			if ((currentPage.getCode().equals(contentOnSession.getViewPage()) && (showlet.getConfig() == null || showlet.getConfig().size() == 0)) 
					|| (showlet.getConfig() != null && showlet.getConfig().get("contentId") != null && showlet.getConfig().get("contentId").equals(contentOnSession.getId()))) {
				String path = CONTENT_VIEWER_JSP;
				reqCtx.addExtraParam(SystemConstants.EXTRAPAR_CURRENT_SHOWLET, showlet);
				this.pageContext.include(path.toString());
				return;
			}
		}
		super.includeShowlet(reqCtx, showlet, decorators);
	}
	
	private final String CONTENT_VIEWER_JSP="/WEB-INF/plugins/jacms/apsadmin/jsp/content/preview/content_viewer.jsp";
	
}