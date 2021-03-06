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
package com.agiletec.apsadmin;

import org.entando.entando.apsadmin.api.TestApiServiceFinderAction;

import junit.framework.Test;
import junit.framework.TestSuite;
import com.agiletec.apsadmin.admin.TestBaseAdminAction;
import com.agiletec.apsadmin.admin.TestSystemParamsUtils;
import com.agiletec.apsadmin.admin.lang.TestLangAction;
import com.agiletec.apsadmin.admin.lang.TestLangFinderAction;
import com.agiletec.apsadmin.admin.localestring.TestLocaleStringAction;
import com.agiletec.apsadmin.admin.localestring.TestLocaleStringFinderAction;
import com.agiletec.apsadmin.category.TestCategoryAction;
import com.agiletec.apsadmin.common.TestBaseCommonAction;
import com.agiletec.apsadmin.common.TestDispatchForward;
import com.agiletec.apsadmin.common.TestLoginAction;
import com.agiletec.apsadmin.common.TestShortcutConfigAction;
import com.agiletec.apsadmin.portal.TestPageAction;
import com.agiletec.apsadmin.portal.TestPageConfigAction;
import com.agiletec.apsadmin.portal.TestPageTreeAction;
import com.agiletec.apsadmin.portal.TestShowletTypeAction;
import com.agiletec.apsadmin.portal.TestShowletsViewerAction;
import com.agiletec.apsadmin.portal.specialshowlet.TestSimpleShowletConfigAction;
import com.agiletec.apsadmin.portal.specialshowlet.navigator.TestNavigatorShowletConfigAction;
import com.agiletec.apsadmin.system.entity.TestEntityManagersAction;
import com.agiletec.apsadmin.system.services.TestShortcutManager;
import com.agiletec.apsadmin.user.TestAuthorityToUsersAction;
import com.agiletec.apsadmin.user.TestUserAction;
import com.agiletec.apsadmin.user.TestUserFinderAction;
import com.agiletec.apsadmin.user.TestUserToAuthoritiesAction;
import com.agiletec.apsadmin.user.group.TestGroupAction;
import com.agiletec.apsadmin.user.group.TestGroupFinderAction;
import com.agiletec.apsadmin.user.role.TestRoleAction;
import com.agiletec.apsadmin.user.role.TestRoleFinderAction;

public class AllTests {
	
    public static Test suite() {
		TestSuite suite = new TestSuite("Test for apsadmin");
		System.out.println("Test for apsadmin");
		
		// Lang
		suite.addTestSuite(TestLangAction.class);
		suite.addTestSuite(TestLangFinderAction.class);
		
		// LocalString
		suite.addTestSuite(TestLocaleStringAction.class);
		suite.addTestSuite(TestLocaleStringFinderAction.class);
		
		suite.addTestSuite(TestBaseAdminAction.class);
		suite.addTestSuite(TestSystemParamsUtils.class);
		
		// Common
		suite.addTestSuite(TestDispatchForward.class);
		suite.addTestSuite(TestLoginAction.class);
		suite.addTestSuite(TestBaseCommonAction.class);
		suite.addTestSuite(TestShortcutConfigAction.class);
		
		//API
		//suite.addTestSuite(TestApiMethodFinderAction.class);
		suite.addTestSuite(TestApiServiceFinderAction.class);
		
		//Category
		suite.addTestSuite(TestCategoryAction.class);
		
		// Page
		suite.addTestSuite(TestPageAction.class);
		suite.addTestSuite(TestPageConfigAction.class);
		suite.addTestSuite(TestPageTreeAction.class);
		suite.addTestSuite(TestShowletsViewerAction.class);
		suite.addTestSuite(TestShowletTypeAction.class);
		suite.addTestSuite(TestSimpleShowletConfigAction.class);
		suite.addTestSuite(TestNavigatorShowletConfigAction.class);
		
		//Entity
		suite.addTestSuite(TestEntityManagersAction.class);
		
		//Admin Area Manager
		suite.addTestSuite(TestShortcutManager.class);
		
		//User
		suite.addTestSuite(TestUserAction.class);
		suite.addTestSuite(TestUserFinderAction.class);
		suite.addTestSuite(TestUserToAuthoritiesAction.class);
		suite.addTestSuite(TestAuthorityToUsersAction.class);
		
		//Group
		suite.addTestSuite(TestGroupAction.class);
		suite.addTestSuite(TestGroupFinderAction.class);
		
		//Role
		suite.addTestSuite(TestRoleAction.class);
		suite.addTestSuite(TestRoleFinderAction.class);
		
		return suite;
	}
    
}
