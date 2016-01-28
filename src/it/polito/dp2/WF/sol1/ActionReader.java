package it.polito.dp2.WF.sol1;

import java.util.HashMap;

import org.w3c.dom.*;

public class ActionReader implements it.polito.dp2.WF.ActionReader {
	
	private Node action;
	
	public ActionReader(Node act)
	{
		action = act;
	}

	@Override
	public WorkflowReader getEnclosingWorkflow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		HashMap<String,String> attr = WorkFlowModel.getAttibutes(action);
		return attr.get("name");
	}

	@Override
	public String getRole() {
		String role = WorkFlowModel.getNodeValue(WorkFlowModel.getRole(action));
		if(role != null)
			return role;
		else
			return "";
	}

	@Override
	public boolean isAutomaticallyInstantiated() {
		HashMap<String,String> attr = WorkFlowModel.getAttibutes(action);
		return Boolean.parseBoolean(attr.get("automInst"));
	}

}
