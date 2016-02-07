package it.polito.dp2.WF.sol1;

import java.util.*;

import org.w3c.dom.*;

public class ProcessReader implements it.polito.dp2.WF.ProcessReader {
	
	private Node process;
	
	public ProcessReader(Node pr)
	{
		process = pr;
	}
	
	@Override
	public Calendar getStartTime() {
		HashMap<String,String> attr = WorkFlowModel.getAttibutes(process);
		
		Calendar cal  = Calendar.getInstance();
		cal.setTimeInMillis(Long.parseLong(attr.get("startAt")));
		return cal;	
	}

	@Override
	public List<it.polito.dp2.WF.ActionStatusReader> getStatus() {
		List<it.polito.dp2.WF.ActionStatusReader> ret = new ArrayList<it.polito.dp2.WF.ActionStatusReader>();
		
		for(Node currentNode:WorkFlowModel.allActionStatus(process))
			ret.add(new ActionStatusReader(currentNode));
		
		return ret;
	}

	@Override
	public WorkflowReader getWorkflow() {
		HashMap<String,String> att = WorkFlowModel.getAttibutes(process);
		return (new WorkflowReader(WorkFlowModel.findWorkflow(att.get("workflowName"))));
	}

}
