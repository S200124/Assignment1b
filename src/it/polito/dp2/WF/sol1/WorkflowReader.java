package it.polito.dp2.WF.sol1;

import java.util.*;

import org.w3c.dom.*;


public class WorkflowReader implements it.polito.dp2.WF.WorkflowReader {

	private Node workflow;
	
	public WorkflowReader(Node wf)
	{
		workflow = wf;
	}
	@Override
	public ActionReader getAction(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<it.polito.dp2.WF.ActionReader> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<it.polito.dp2.WF.ProcessReader> getProcesses() {
		HashMap<String,String> attr = WorkFlowModel.getAttibutes(workflow);
		String name = attr.get("name");
		Set<it.polito.dp2.WF.ProcessReader> ret = new HashSet<it.polito.dp2.WF.ProcessReader>();
		
		for(Node currentNode:WorkFlowModel.whereProcesses(name))
			ret.add(new ProcessReader(currentNode));
		
		return ret;
	}

}
