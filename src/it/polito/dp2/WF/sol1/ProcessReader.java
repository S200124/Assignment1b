package it.polito.dp2.WF.sol1;

import it.polito.dp2.WF.ActionStatusReader;
import it.polito.dp2.WF.WorkflowReader;

import java.util.Calendar;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProcessReader implements it.polito.dp2.WF.ProcessReader {

	private Node process;
	
	public ProcessReader(Node proc)
	{
		process = proc;
	}
	
	@Override
	public Calendar getStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActionStatusReader> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkflowReader getWorkflow() {
		// TODO Auto-generated method stub
		return null;
	}

}
