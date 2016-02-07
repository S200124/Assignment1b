package it.polito.dp2.WF.sol1;

import java.util.*;

import org.w3c.dom.*;


public class WorkflowMonitor implements it.polito.dp2.WF.WorkflowMonitor {

	@Override
	public Set<it.polito.dp2.WF.ProcessReader> getProcesses() {
		Set<it.polito.dp2.WF.ProcessReader> ret = new HashSet<it.polito.dp2.WF.ProcessReader>();
		
		for(Node processNode:WorkFlowModel.allProcesses())
			ret.add(new ProcessReader(processNode));
		
		return ret;
	}

	@Override
	public WorkflowReader getWorkflow(String arg0) {
		return (new WorkflowReader(WorkFlowModel.findWorkflow(arg0)));
	}

	@Override
	public Set<it.polito.dp2.WF.WorkflowReader> getWorkflows() {
		Set<it.polito.dp2.WF.WorkflowReader> ret = new HashSet<it.polito.dp2.WF.WorkflowReader>();
		
		for(Node currentNode:WorkFlowModel.allWorkflow())
			ret.add(new WorkflowReader(currentNode));
		
		return ret;
	}
	
}
