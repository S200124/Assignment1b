package it.polito.dp2.WF.sol1;

import org.w3c.dom.Node;

import it.polito.dp2.WF.WorkflowReader;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAutomaticallyInstantiated() {
		// TODO Auto-generated method stub
		return false;
	}

}
