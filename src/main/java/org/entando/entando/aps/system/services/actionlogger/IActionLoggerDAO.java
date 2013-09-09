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
package org.entando.entando.aps.system.services.actionlogger;

import java.util.List;

import org.entando.entando.aps.system.services.actionlogger.model.ActionRecord;
import org.entando.entando.aps.system.services.actionlogger.model.IActionRecordSearchBean;

public interface IActionLoggerDAO {
	
	public List<Integer> getActionRecords(IActionRecordSearchBean searchBean);
	
	public void addActionRecord(ActionRecord actionRecord);
	
	public ActionRecord getActionRecord(int id);
	
	public void deleteActionRecord(int id);
	
}